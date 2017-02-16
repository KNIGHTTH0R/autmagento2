package com.tests.us9.us9008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.persistance.MongoReader;
import com.workflows.commission.CommissionPartyPerformanceValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US9008ValidateHostRewordAfterCancelAnOrderWithTpTest extends BaseTest {
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	CommissionPartyPerformanceValidationWorkflows commissionPartyValidationWorkflows;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public CustomVerification customVerifications;

	private static UrlModel urlModel = new UrlModel();
	private String username, password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us9" + File.separator + "us9001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("aici");
		urlModel = MongoReader.grabUrlModels("US10008CreatePartyWithExistingContactHostTest").get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us9008ValidateHostRewordAfterCancelAnOrderWithTpTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		customerRegistrationSteps.navigate(urlModel.getUrl());
		ClosedPartyPerformanceModel grabbedClosedPartyPerformanceModel = partyDetailsSteps.grabClosedPartyPerformanceNoOrders();
		commissionPartyValidationWorkflows
				.validatePartyPerformanceAfterCancelAnOrder(grabbedClosedPartyPerformanceModel, "0.00", "0");
		customVerifications.printErrors();
	}

	@After
	public void tearDown() {
		// MongoWriter.saveClosedPartyPerformanceModel(expectedClosedPartyPerformanceModel,
		// getClass().getSimpleName());
	}
}
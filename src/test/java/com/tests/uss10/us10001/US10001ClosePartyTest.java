package com.tests.uss10.us10001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.OrdersInfoMagentoCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.commision.PartyHostBonusCalculator;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.commission.CommissionPartyPerformanceValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US9.2b Place Host Order With 40% Discount, JB and Buy 3 get 1 for 50% Test", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(SerenityRunner.class)
public class US10001ClosePartyTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	CommissionPartyPerformanceValidationWorkflows commissionPartyValidationWorkflows;

	public static UrlModel urlModel = new UrlModel();
	public static DateModel dateModel = new DateModel();
	private String username, password;
	boolean runTest = true;

	private ClosedPartyPerformanceModel expectedClosedPartyPerformanceModel = new ClosedPartyPerformanceModel();
	private List<PartyBonusCalculationModel> partyBonusCalculationModelList = new ArrayList<PartyBonusCalculationModel>();

	public static void main(String[] args) throws Exception {
		US10001ClosePartyTest x = new US10001ClosePartyTest();
		x.setUp();
	}

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		urlModel = MongoReader.grabUrlModels("US10001CreatePartyWithCustomerHostTestVDV" + SoapKeys.GRAB).get(0);
		System.out.println(urlModel.getUrl());

		partyBonusCalculationModelList = PartyHostBonusCalculator.partyBonusCalculationModelList(
				OrdersInfoMagentoCalls.getPartyOrdersList(urlModel.getUrl().replaceAll("\\D+", "")));

		expectedClosedPartyPerformanceModel = PartyHostBonusCalculator
				.partyTotalBonusCalculation(partyBonusCalculationModelList, "10", false);

		expectedClosedPartyPerformanceModel.setFourthyDiscounts("0");

		System.out.println(expectedClosedPartyPerformanceModel);

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
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

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us10001ClosePartyTest() {

		customerRegistrationSteps.performLogin(username, password);

		customerRegistrationSteps.navigate(urlModel.getUrl());
		ClosedPartyPerformanceModel grabbedDataFromClosePartyModal = partyDetailsSteps.closeThePartyAndGrabModalData();
		partyDetailsSteps.returnToParty();
		ClosedPartyPerformanceModel grabbedClosedPartyPerformanceModel = partyDetailsSteps.grabClosedPartyPerformance();

		commissionPartyValidationWorkflows.validateClosedPartyFromModalPerformance(grabbedDataFromClosePartyModal,
				expectedClosedPartyPerformanceModel);

		commissionPartyValidationWorkflows.validateClosedPartyPerformance(grabbedClosedPartyPerformanceModel,
				expectedClosedPartyPerformanceModel);

		partyDetailsSteps.verifyClosedPartyAvailableActions();

	}

}

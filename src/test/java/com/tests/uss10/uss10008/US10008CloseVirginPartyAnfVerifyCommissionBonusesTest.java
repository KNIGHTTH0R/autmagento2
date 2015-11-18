package com.tests.uss10.uss10008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

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
import com.tools.calculation.PartyBonusCalculation;
import com.tools.data.UrlModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.commission.CommissionPartyPerformanceValidationWorkflows;

@WithTag(name = "US10.8 Check virgin party performance and bonuses", type = "Scenarios")
@Story(Application.PartyPerformance.US10_8.class)
@RunWith(ThucydidesRunner.class)
public class US10008CloseVirginPartyAnfVerifyCommissionBonusesTest extends BaseTest {

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
	private ClosedPartyPerformanceModel expectedClosedPartyPerformanceModel = new ClosedPartyPerformanceModel();
	private List<PartyBonusCalculationModel> partyBonusCalculationModelList = new ArrayList<PartyBonusCalculationModel>();
	private String username, password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

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

		partyBonusCalculationModelList.add(MongoReader.grabPartyBonusCalculationModel("US10008OrderForCustomerAsPartyHostTest").get(0));

		urlModel = MongoReader.grabUrlModels("US10008CreatePartyWithNewContactHostTest").get(0);

		expectedClosedPartyPerformanceModel.setJewelryBonus(String.valueOf(PartyBonusCalculation.calculatePartyJewelryBonus(partyBonusCalculationModelList, "100")));
		expectedClosedPartyPerformanceModel.setNoOfOrders(String.valueOf(partyBonusCalculationModelList.size()));
		expectedClosedPartyPerformanceModel.setRetail(String.valueOf(PartyBonusCalculation.calculatePartyTotal(partyBonusCalculationModelList)));
		expectedClosedPartyPerformanceModel.setFourthyDiscounts("1");
		expectedClosedPartyPerformanceModel.setIp(String.valueOf(PartyBonusCalculation.calculatePartyIp(partyBonusCalculationModelList)));
		expectedClosedPartyPerformanceModel.setIpInPayment(String.valueOf(PartyBonusCalculation.calculatePartyIp(partyBonusCalculationModelList)));
		PrintUtils.printClosedPartyModel(expectedClosedPartyPerformanceModel);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us10008CloseVirginPartyAnfVerifyCommissionBonusesTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.closeTheParty();
		partyDetailsSteps.returnToParty();
		ClosedPartyPerformanceModel grabbedClosedPartyPerformanceModel = partyDetailsSteps.grabClosedPartyPerformance();
		PrintUtils.printClosedPartyModel(grabbedClosedPartyPerformanceModel);
		commissionPartyValidationWorkflows.validateClosedPartyPerformance(grabbedClosedPartyPerformanceModel, expectedClosedPartyPerformanceModel);

		customVerifications.printErrors();
	}

	@After
	public void tearDown() {
		MongoWriter.saveClosedPartyPerformanceModel(expectedClosedPartyPerformanceModel, getClass().getSimpleName());
	}

}

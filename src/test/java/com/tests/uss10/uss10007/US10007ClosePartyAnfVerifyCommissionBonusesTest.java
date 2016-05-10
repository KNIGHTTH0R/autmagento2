package com.tests.uss10.uss10007;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.UrlModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.generalCalculation.PartyBonusCalculation;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.commission.CommissionPartyPerformanceValidationWorkflows;

@WithTag(name = "US10.7 Check party and follow up party performance and bonuses", type = "Scenarios")
@Story(Application.PartyPerformance.US10_7.class)
@RunWith(SerenityRunner.class)
public class US10007ClosePartyAnfVerifyCommissionBonusesTest extends BaseTest {

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

		partyBonusCalculationModelList.add(MongoReader.grabPartyBonusCalculationModel("US10007OrderForCustomerAsPartyHostTest").get(0));
		partyBonusCalculationModelList.add(MongoReader.grabPartyBonusCalculationModel("US10007PlaceSecondOrderAsPartyHostTest").get(0));
		partyBonusCalculationModelList.add(MongoReader.grabPartyBonusCalculationModel("US10007PlaceThirdOrderAsPartyHostTest").get(0));

		urlModel = MongoReader.grabUrlModels("US10007CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);

		expectedClosedPartyPerformanceModel.setJewelryBonus(String.valueOf(PartyBonusCalculation.calculatePartyJewelryBonus(partyBonusCalculationModelList, "0")));
		expectedClosedPartyPerformanceModel.setNoOfOrders(String.valueOf(partyBonusCalculationModelList.size()));
		expectedClosedPartyPerformanceModel.setRetail(String.valueOf(PartyBonusCalculation.calculatePartyTotal(partyBonusCalculationModelList)));
		expectedClosedPartyPerformanceModel.setFourthyDiscounts("1");
		expectedClosedPartyPerformanceModel.setIp(String.valueOf(PartyBonusCalculation.calculatePartyIp(partyBonusCalculationModelList)));
		expectedClosedPartyPerformanceModel.setIpInPayment(String.valueOf(PartyBonusCalculation.calculatePartyIp(partyBonusCalculationModelList)));

		PrintUtils.printClosedPartyModel(expectedClosedPartyPerformanceModel);

	}

	@Test
	public void us10007ClosePartyAnfVerifyCommissionBonusesTest() {

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

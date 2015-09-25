package com.tests.uss10.uss10007;

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

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartiesListSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
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

@WithTag(name = "US10", type = "frontend")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10007CloseFollowUpPartyAnfVerifyCommissionBonusesTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	CommissionPartyPerformanceValidationWorkflows commissionPartyValidationWorkflows;
	@Steps
	public PartiesListSteps partiesListSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public CustomVerification customVerifications;
	public static UrlModel urlModel = new UrlModel();
	ClosedPartyPerformanceModel expectedClosedPartyPerformanceModel = new ClosedPartyPerformanceModel();
	List<PartyBonusCalculationModel> partyBonusCalculationModelList = new ArrayList<PartyBonusCalculationModel>();
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

		partyBonusCalculationModelList.add(MongoReader.grabPartyBonusCalculationModel("US10007OrderForCustomerAsFollowUpPartyHostTest").get(0));

		expectedClosedPartyPerformanceModel.setJewelryBonus(String.valueOf(PartyBonusCalculation.calculatePartyJewelryBonus(partyBonusCalculationModelList)));
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
		headerSteps.goToPartieList();
		urlModel.setUrl(partiesListSteps.goToFirstParty());
		partyDetailsSteps.closeTheParty();
		partyDetailsSteps.returnToParty();
		ClosedPartyPerformanceModel grabbedClosedPartyPerformanceModel = partyDetailsSteps.grabClosedPartyPerformance();
		PrintUtils.printClosedPartyModel(grabbedClosedPartyPerformanceModel);

		commissionPartyValidationWorkflows.validateClosedPartyPerformance(grabbedClosedPartyPerformanceModel, expectedClosedPartyPerformanceModel);

		customVerifications.printErrors();

	}

	@After
	public void saveData() {

		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

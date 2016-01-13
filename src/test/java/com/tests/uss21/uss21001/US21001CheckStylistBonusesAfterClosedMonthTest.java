package com.tests.uss21.uss21001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.calculation.ClosedMonthBonusCalculation;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.commission.CommissionClosedMonthRewardsValidationWorkflows;

@WithTag(name = "US21.1 Verify Closed Month Frontend and Backend Performance", type = "Scenarios")
@Story(Application.CloseMonthRewardPoints.US21_1.class)
@RunWith(ThucydidesRunner.class)
public class US21001CheckStylistBonusesAfterClosedMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CommissionClosedMonthRewardsValidationWorkflows commissionClosedMonthRewardsValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	private String email;
	RewardPointsOfStylistModel initialRewardPointsOfStylistModel = new RewardPointsOfStylistModel();
	RewardPointsOfStylistModel calculatedRewardPointsOfStylistModel = new RewardPointsOfStylistModel();
	RewardPointsOfStylistModel finalRewardPointsOfStylistModel = new RewardPointsOfStylistModel();

	@Before
	public void setUp() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_21_FOLDER + File.separator + "us21001.properties");
			prop.load(input);
			email = prop.getProperty("email");

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

		initialRewardPointsOfStylistModel = MongoReader.grabReviewPoints("US21001CheckStylistBonusesBeforeClosedMonthTest").get(0);
		calculatedRewardPointsOfStylistModel = MongoReader.grabReviewPoints("US21001CloseMonthTest").get(0);
		finalRewardPointsOfStylistModel = ClosedMonthBonusCalculation.calculateRewardPoints(initialRewardPointsOfStylistModel, calculatedRewardPointsOfStylistModel);

	}

	@Test
	public void us21001CheckStylistBonusesAfterClosedMonthTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(email);
		backEndSteps.openCustomerDetails(email);
		backEndSteps.clickOnRewardsPointsTab();
		RewardPointsOfStylistModel grabbedRewardPointsOfStylistModel = backEndSteps.getRewardPointsOfStylistModel();
		commissionClosedMonthRewardsValidationWorkflows.validateClosedMonthRewardPoints(finalRewardPointsOfStylistModel, grabbedRewardPointsOfStylistModel);

		customVerifications.printErrors();

	}

	@After
	public void save() {
		MongoWriter.saveRewardPointsModel(finalRewardPointsOfStylistModel, getClass().getSimpleName());
	}
}

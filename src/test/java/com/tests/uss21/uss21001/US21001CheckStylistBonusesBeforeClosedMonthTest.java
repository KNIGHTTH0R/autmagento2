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
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US21.1 Verify Closed Month Frontend and Backend Performance", type = "Scenarios")
@Story(Application.CloseMonthRewardPoints.US21_1.class)
@RunWith(ThucydidesRunner.class)
public class US21001CheckStylistBonusesBeforeClosedMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	RewardPointsOfStylistModel rewardPointsOfStylistModel = new RewardPointsOfStylistModel();
	String email;

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
	}

	@Test
	public void us21001CheckStylistBonusesBeforeClosedMonthTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(email);
		backEndSteps.openCustomerDetails(email);
		backEndSteps.clickOnRewardsPointsTab();
		rewardPointsOfStylistModel = backEndSteps.getRewardPointsOfStylistModel();
	}

	@After
	public void save() {
		MongoWriter.saveRewardPointsModel(rewardPointsOfStylistModel, getClass().getSimpleName());
	}

}

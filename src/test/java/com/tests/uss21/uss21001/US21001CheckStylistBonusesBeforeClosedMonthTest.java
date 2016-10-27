package com.tests.uss21.uss21001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.StylecoachDetailsBackendSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.constants.DateConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US21.1 Verify Closed Month Frontend and Backend Performance", type = "Scenarios")
@Story(Application.CloseMonthRewardPoints.US21_1.class)
@RunWith(SerenityRunner.class)
public class US21001CheckStylistBonusesBeforeClosedMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public StylecoachDetailsBackendSteps stylecoachDetailsBackendSteps;

	DateModel dateModel = new DateModel();
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
	public void us21001CheckStylistBonusesBeforeClosedMonthTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(email);
		backEndSteps.openCustomerDetails(email);
		backEndSteps.clickOnRewardsPointsTab();
		rewardPointsOfStylistModel = backEndSteps.getRewardPointsOfStylistModel();
		backEndSteps.clickOnStylecoachList();
		stylecoachListBackendSteps.searchForStylist(email);
		stylecoachListBackendSteps.openStylistDetails();
		stylecoachDetailsBackendSteps.addNewActivatedAtDate(DateUtils.getThreeMonthsBackMiddle(DateConstants.FORMAT));
		dateModel.setDate(DateUtils.addHoursToAGivenDate(DateUtils.getThreeMonthsBackMiddle(DateConstants.FORMAT), DateConstants.FORMAT_12_HOURS, 1));
	}

	@After
	public void save() {
		MongoWriter.saveRewardPointsModel(rewardPointsOfStylistModel, getClass().getSimpleName());
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
	}

}

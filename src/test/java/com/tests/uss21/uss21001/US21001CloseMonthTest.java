package com.tests.uss21.uss21001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.steps.external.commission.CommissionReportSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US21.1 Verify Closed Month Frontend and Backend Performance", type = "Scenarios")
@Story(Application.CloseMonthRewardPoints.US21_1.class)
@RunWith(SerenityRunner.class)
public class US21001CloseMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CommissionReportSteps commissionReportSteps;

	RewardPointsOfStylistModel calculatedRewordPointsOfStylistModel = new RewardPointsOfStylistModel();
	DateModel registrationModel = new DateModel();
	private String stylistId;
	private String lastCommissionRun;

	@Before
	public void setUp() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_21_FOLDER + File.separator + "us21001.properties");
			prop.load(input);

			lastCommissionRun = prop.getProperty("lastCommissionRun");
			stylistId = prop.getProperty("stylistId");

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
		registrationModel = MongoReader.grabDateModels("US21001CheckStylistBonusesBeforeClosedMonthTest").get(0);
	}

	@Test
	public void us21001CloseMonthTest() throws Exception {

		backEndSteps.navigate(UrlConstants.COMMISSION_REPORTS_URL);
		calculatedRewordPointsOfStylistModel = commissionReportSteps.closeMonthAndCalculateRewardPoints(stylistId, registrationModel.getDate(), lastCommissionRun);

	}

	@After
	public void save() {
		MongoWriter.saveRewardPointsModel(calculatedRewordPointsOfStylistModel, getClass().getSimpleName());
	}
}

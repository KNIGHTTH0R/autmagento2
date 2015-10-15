package com.tests.uss21.uss21001;

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
import com.steps.external.commission.CommissionReportSteps;
import com.tests.BaseTest;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US21", type = "frontend")
@Story(Application.Commission.CloseMonthRewardPoints.class)
@RunWith(ThucydidesRunner.class)
public class US21001CloseMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CommissionReportSteps commissionReportSteps;

	RewardPointsOfStylistModel calculatedRewordPointsOfStylistModel = new RewardPointsOfStylistModel();

	@Before
	public void setUp() {
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us21001CloseMonthAndVerifyReceivedJbAndMmbTest() throws Exception {

		backEndSteps.navigate(UrlConstants.COMMISSION_REPORTS_URL);
		calculatedRewordPointsOfStylistModel = commissionReportSteps.closeMonthAndCalculateRewardPoints("1835");

	}

	@After
	public void save() {
		MongoWriter.saveRewardPointsModel(calculatedRewordPointsOfStylistModel, getClass().getSimpleName());
	}
}

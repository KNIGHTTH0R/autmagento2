package com.tests.uss21.uss21001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.calculation.ClosedMonthBonusCalculation;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.commission.CommissionClosedMonthRewardsValidationWorkflows;

@WithTag(name = "US21", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US20001CheckStylistBonusesAfterClosedMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CommissionClosedMonthRewardsValidationWorkflows commissionClosedMonthRewardsValidationWorkflows;

	RewardPointsOfStylistModel initialRewardPointsOfStylistModel = new RewardPointsOfStylistModel();
	RewardPointsOfStylistModel calculatedRewardPointsOfStylistModel = new RewardPointsOfStylistModel();
	RewardPointsOfStylistModel finalRewardPointsOfStylistModel = new RewardPointsOfStylistModel();

	@Before
	public void setUp() {
		initialRewardPointsOfStylistModel = MongoReader.grabReviewPoints("US20001CheckStylistBonusesBeforeClosedMonthTest").get(0);
		calculatedRewardPointsOfStylistModel = MongoReader.grabReviewPoints("US20001CloseMonthAndVerifyReceivedJbAndMmbTest").get(0);
		finalRewardPointsOfStylistModel = ClosedMonthBonusCalculation.calculateRewardPoints(initialRewardPointsOfStylistModel, calculatedRewardPointsOfStylistModel);

	}

	@Test
	public void us20001CheckStylistBonusesAfterClosedMonthTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("mihaialexandrubarta@gmail.com");
		backEndSteps.openCustomerDetails("mihaialexandrubarta@gmail.com");
		backEndSteps.clickOnRewardsPointsTab();
		RewardPointsOfStylistModel grabbedRewardPointsOfStylistModel = backEndSteps.getRewardPointsOfStylistModel();
		commissionClosedMonthRewardsValidationWorkflows.validateClosedMonthRewardPoints(finalRewardPointsOfStylistModel, grabbedRewardPointsOfStylistModel);

	}
}

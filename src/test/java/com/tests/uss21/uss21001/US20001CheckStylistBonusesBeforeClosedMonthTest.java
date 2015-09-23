package com.tests.uss21.uss21001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US21", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US20001CheckStylistBonusesBeforeClosedMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	RewardPointsOfStylistModel rewardPointsOfStylistModel = new RewardPointsOfStylistModel();

	@Test
	public void us20001CheckStylistBonusesBeforeClosedMonthTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("mihaialexandrubarta@gmail.com");
		backEndSteps.openCustomerDetails("mihaialexandrubarta@gmail.com");
		backEndSteps.clickOnRewardsPointsTab();
		rewardPointsOfStylistModel = backEndSteps.getRewardPointsOfStylistModel();
	}

	@After
	public void save() {
		MongoWriter.saveRewardPointsModel(rewardPointsOfStylistModel, getClass().getSimpleName());
	}

}

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

@WithTag(name = "US21", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US20001CheckStylistBonusesAfterClosedMonthTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	RewardPointsOfStylistModel rewardPointsOfStylistModel = new RewardPointsOfStylistModel();
	
	@Before
	public void setUp() {
		rewardPointsOfStylistModel=MongoReader.grabReviewPoints("US20001CheckStylistBonusesBeforeClosedMonthTest").get(0);
//		rewardPointsOfStylistModel2=MongoReader.grabReviewPoints("US20001CheckStylistBonusesBeforeClosedMonthTest").get(0);
//		
//		totalREwords =  ClosedMonthBonusCalculation.calculateRewards(rewardPointsOfStylistModel, rewardPointsOfStylistModel2)
//	    	
	
	}
	
	
	


	@Test
	public void us10008ValidatePartyBackendPerformanceTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("mihaialexandrubarta@gmail.com");
		backEndSteps.openCustomerDetails("mihaialexandrubarta@gmail.com");
		backEndSteps.clickOnRewardsPointsTab();
		rewardPointsOfStylistModel = backEndSteps.getRewardPointsOfStylistModel();
		//validations

	}

}

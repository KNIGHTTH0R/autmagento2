package com.workflows.commission;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.CustomVerification;
import com.tools.data.backend.RewardPointsOfStylistModel;

public class CommissionClosedMonthRewardsValidationWorkflows {
	@Title("Validate that closed month reward points are correct")
	@Step
	public void validateClosedMonthRewardPoints(RewardPointsOfStylistModel expectedModel, RewardPointsOfStylistModel grabbedModel) {
		verifyJewelryBonus(expectedModel.getJewelryBonus(), grabbedModel.getJewelryBonus());
		verifyMarketingMaterial(expectedModel.getMarketingMaterialBonus(), grabbedModel.getMarketingMaterialBonus());
	}

	@Step
	public void verifyJewelryBonus(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: JB doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyMarketingMaterial(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: MMB doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

}

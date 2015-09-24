package com.workflows.commission;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.backend.RewardPointsOfStylistModel;

public class CommissionClosedMonthRewardsValidationWorkflows {

	@Step
	public void validateClosedMonthRewardPoints(RewardPointsOfStylistModel expectedModel, RewardPointsOfStylistModel grabbedModel) {
		verifyJewelryBonus(grabbedModel.getJewelryBonus(), expectedModel.getJewelryBonus());
		verifyMarketingMaterial(grabbedModel.getMarketingMaterialBonus(), expectedModel.getMarketingMaterialBonus());
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

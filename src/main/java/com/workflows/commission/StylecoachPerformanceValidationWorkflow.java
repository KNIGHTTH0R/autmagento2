package com.workflows.commission;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.CustomVerification;
import com.tools.data.backend.IpModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.frontend.LoungeIpPerformanceModel;

public class StylecoachPerformanceValidationWorkflow {

	public LoungeIpPerformanceModel populateLoungeIpPerformanceModel(IpModel ipModel, CommissionStylistModel commissionStylistModel) {

		LoungeIpPerformanceModel result = new LoungeIpPerformanceModel();

		result.setCareerLevel(commissionStylistModel.getCareer());
		result.setPayLevel(commissionStylistModel.getPayLevelName());
		result.setIndividualPoints(ipModel.getIp());
		result.setUnsafeIndividualPoints(ipModel.getUnsafeIp());
		result.setTeamPoints(String.valueOf(commissionStylistModel.getTeamPoints()));
		result.setGoldStyleCoaches(String.valueOf(commissionStylistModel.getGoldFrontliners()));
		result.setStyleCoachFirstLevel(commissionStylistModel.getFrontliners());

		return result;
	}
	@Title("Validate that SC performance values are correct in backend")
	@Step
	public void validatePerformanceValues(LoungeIpPerformanceModel expectedModel, LoungeIpPerformanceModel grabbedModel) {
		verifyCareerLevel(expectedModel.getCareerLevel(), grabbedModel.getCareerLevel());
		verifyPayLevel(expectedModel.getPayLevel(), grabbedModel.getPayLevel());
		verifyIndividulPoints(expectedModel.getIndividualPoints(), grabbedModel.getIndividualPoints());
		verifyTeamPoints(expectedModel.getTeamPoints(), grabbedModel.getTeamPoints());
		verifyStylecoachFirstLevel(expectedModel.getStyleCoachFirstLevel(), grabbedModel.getStyleCoachFirstLevel());
		verifyGoldStyleCoaches(expectedModel.getGoldStyleCoaches(), grabbedModel.getGoldStyleCoaches());
	}
	@Title("Validate that SC performance values are correct in frontend")
	@Step
	public void validatePerformanceValuesInFrontend(LoungeIpPerformanceModel expectedModel, LoungeIpPerformanceModel grabbedModel) {
		verifyCareerLevel(expectedModel.getCareerLevel(), grabbedModel.getCareerLevel());
		verifyPayLevel(expectedModel.getPayLevel(), grabbedModel.getPayLevel());
		verifyIndividulPoints(expectedModel.getIndividualPoints(), grabbedModel.getIndividualPoints());
		verifyUnsafeIpPoints(expectedModel.getUnsafeIndividualPoints(), grabbedModel.getUnsafeIndividualPoints());
		verifyTeamPoints(expectedModel.getTeamPoints(), grabbedModel.getTeamPoints());
	}
	@Step
	private void verifyGoldStyleCoaches(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Gold Style coach value doesn't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}
	@Step
	private void verifyStylecoachFirstLevel(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Style coach first value doesn't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}
	@Step
	private void verifyTeamPoints(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Team points value doesn't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}
	@Step
	private void verifyIndividulPoints(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Individual points value doesn't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}
	@Step
	private void verifyPayLevel(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Pay Level doesn't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}
	@Step
	private void verifyCareerLevel(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Career level doesn't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}
	@Step
	private void verifyUnsafeIpPoints(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Unsafe ip points don't match: Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

}

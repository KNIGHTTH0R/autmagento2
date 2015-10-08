package com.workflows.commission;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.LoungeIpPerformanceModel;

public class StylecoachPerformanceValidationWorkflow {

	@Step
	public void validatePerformanceValues(LoungeIpPerformanceModel expectedModel, LoungeIpPerformanceModel grabbedModel) {
		verifyCareerLevel(expectedModel.getCareerLevel(), grabbedModel.getCareerLevel());
		verifyPayLevel(expectedModel.getPayLevel(), grabbedModel.getPayLevel());
		verifyIndividulPoints(expectedModel.getIndividualPoints(), grabbedModel.getIndividualPoints());
		verifyTeamPoints(expectedModel.getTeamPoints(), grabbedModel.getTeamPoints());
		verifyStylecoachFirstLevel(expectedModel.getStyleCoachFirstLevel(), grabbedModel.getStyleCoachFirstLevel());
		verifyGoldStyleCoaches(expectedModel.getGoldStyleCoaches(), grabbedModel.getGoldStyleCoaches());
		verifyMonthYear(expectedModel.getMonthYear(), grabbedModel.getMonthYear());
	}

	private void verifyMonthYear(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Month and year doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}

	private void verifyGoldStyleCoaches(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Gold Style coach value doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}

	private void verifyStylecoachFirstLevel(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Style coach first value doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}

	private void verifyTeamPoints(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: team points value doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}

	private void verifyIndividulPoints(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: individual points value doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}

	private void verifyPayLevel(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Pay Level doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));

	}

	private void verifyCareerLevel(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Career level doesn't match: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

}

package com.workflows.frontend.reports;

import com.tools.CustomVerification;
import com.tools.data.frontend.TermPurchaseIpModel;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class IpReportValidationWorkflow {

	@Title("Validate current month and next month ips")
	@Step
	public void validateIps(TermPurchaseIpModel expectedModel, TermPurchaseIpModel grabbedModel) {

		verifyCurrentMonthIps(expectedModel.getCurrentMonthIp(), grabbedModel.getCurrentMonthIp());
		verifyNextMonthIps(expectedModel.getNextMonthIp(), grabbedModel.getNextMonthIp());
	}

	@Step
	public void verifyNextMonthIps(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue(
				"Failure: Next month ips don't match  - Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyCurrentMonthIps(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue(
				"Failure: Current month ips don't match  - Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}

}

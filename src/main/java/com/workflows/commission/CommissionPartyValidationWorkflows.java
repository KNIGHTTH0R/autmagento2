package com.workflows.commission;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;

public class CommissionPartyValidationWorkflows {

	@Step
	public void verifyClosedPartyJewelryBonus(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: JB doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyJFourthyDiscount(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: 40 discount doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

}

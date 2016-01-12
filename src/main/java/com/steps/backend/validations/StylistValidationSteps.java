package com.steps.backend.validations;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;

public class StylistValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	@Step
	public void validateStatus(String confirmationstatus, String expectedconfirmationstatus) {
		CustomVerification.verifyTrue("Failure: The confirmation status is not as expected!", confirmationstatus.contentEquals(expectedconfirmationstatus));
	}

	@Step
	public void validateJewwelryBonus(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: The jewelry bonus amount is not as expected!", grabbed.contentEquals(expected));
	}

	@Step
	public void validateCustomerType(String grabbedCustomerType, String expectedCustomerType) {
		CustomVerification.verifyTrue("Failure: The customer type is not as expected!", grabbedCustomerType.contentEquals(expectedCustomerType));
	}

	@Step
	public void validateDates(String grabbedDate, String expectedDate) {
		CustomVerification.verifyTrue("Failure: The date not as expected! Actual: " + grabbedDate + "Expected : " + expectedDate, grabbedDate.contains(expectedDate));
	}

}

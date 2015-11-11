package com.workflows.stockSynk;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;

public class StockProductsValidations extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, compare.contains(productNow));
	}

	@Step
	public void validateIsDiscontinued(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Is discontinued status doesn't match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	@Step
	public void validateEarliestAvailability(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values doesn't match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

}

package com.workflows.stockSynk;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;

public class StockProductsValidations extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void validateSku(String productNow, String compare) {
		System.out.println("Validate sku "+productNow+" - "+compare);
		CustomVerification.verifyTrue("Failure: SKU doesn't match: " + productNow + " - " + compare,
				productNow.contains(compare));
	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		System.out.println("validateMatchQuantity "+productNow+" - "+compare);
		CustomVerification.verifyTrue("Failure: Quantity values dont match: " + productNow + " - " + compare,
				compare.contentEquals(productNow));
	}

	@Step
	public void validateIsDiscontinued(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Is discontinued status doesn't match: " + productNow + " - " + compare,
				productNow.contentEquals(compare));
	}

	@Step
	public void validateEarliestAvailability(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Earliest Availability  doesn't match: " + productNow + " - " + compare,
				productNow.contentEquals(compare));
	}

	@Step
	public void validateMinimumQuantity(String productNow, String compare) {

		System.out.println("validateMinimumQuantity " + productNow + " - " + compare);
		
		CustomVerification.verifyTrue("Failure: Min quantity values don't match: " + productNow + " - " + compare,
				productNow.contentEquals(compare));
	}

	@Step
	public void validateIsInStock(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Is in Stock status doesn't match: " + productNow + " - " + compare,
				productNow.contentEquals(compare));
	}

	@Title("Verify that order is syncronyzed in NAV")
	@Step
	public void validateSyncronizedStatus(boolean status) {
		CustomVerification.verifyTrue("Failure: Products are not syncronized !!", status == true);
	}

	@Step
	public void validateSimpleQty(String simpleQty, String compare) {
		// TODO Auto-generated method stub
		System.out.println("validate Simple Qty(qty from product) " + simpleQty + " - " + compare);
		CustomVerification.verifyTrue("Failure: Simple Qty(qty from product) doesn't match: calculated shop->"
				+ simpleQty + " - calculated nav-> " + compare, simpleQty.contentEquals(compare));
	}

}

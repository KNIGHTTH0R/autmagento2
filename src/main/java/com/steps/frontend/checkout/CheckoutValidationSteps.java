package com.steps.frontend.checkout;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;


public class CheckoutValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	@Step
	public void verifySuccessMessage() {
		successPage().verifySuccessMessage();
	}

	/**
	 * Method is set to cantains. to validate compares like 15.53 == 15.5
	 * @param productNow
	 * @param compare
	 */
	
	@Step
	public void validateSize(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Size values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validateColour(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Colour values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validateReason(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Reason values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validateProductSku(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Product sku  values dont match: expected " + productNow + " - grabbed " + compare, compare.contains(productNow));
	}
	
	@Step
	public void validateSpecifiedAmount(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Specified Amount values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validateReturnedQty(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Returned Qty values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validateStatus(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Returned Qty values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validatePaidPrice(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Paid Price values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void validateMatchPrice(String productNow, String compare) {
		System.out.println(productNow+"-"+compare);
		CustomVerification.verifyTrue("Failure: Price values dont match: expected " + productNow + " - grabbed " + compare, compare.replaceAll("\\s+","").contains(productNow.replaceAll("\\s+","")));
	}

	@Step
	public void matchName(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Product name  dont match: expected " + productNow + " - grabbed " + compare, compare.toLowerCase().replaceAll("\\s+","").contains(productNow.toLowerCase().replaceAll("\\s+","")));

	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values dont match: expected " + productNow + " - grabbed " + compare, productNow.contentEquals(compare));
	}
	@Step
	public void validateMatchFinalPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Final price values dont match: expected " + productNow + " - grabbed" + compare, productNow.contentEquals(compare));
	}
	@Step
	public void validateMatchIpPoints(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Ip points values dont match: expected " + productNow + " - grabbed" + compare, productNow.contentEquals(compare));
	}

	@Step
	public void checkTotalAmountFromUrl(String url, String totalAmount) {
		CustomVerification.verifyTrue("Failure: The total amount from URL is incorrect. Expected: " + totalAmount + " Actual: " + url, url.contains(totalAmount));

	}
	@Step
	public void verifyBonusType(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: THe applied bonus type doesn't match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}
	@Step
	public void validateIpPoints(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: The ip points don't match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
		
	}

}

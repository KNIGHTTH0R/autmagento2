package com.workflows.frontend.stylecoachRegistration;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;
import com.tools.CustomVerification;

public class StylecoachRegistrationCartWorkflows {

	private StylistRegistrationCartTotalModel calculatedTotals = new StylistRegistrationCartTotalModel();
	private StylistRegistrationCartTotalModel grabbedTotals = new StylistRegistrationCartTotalModel();

	public void setVerifyTotalsDiscount(StylistRegistrationCartTotalModel calculatedTotals, StylistRegistrationCartTotalModel grabbedTotals) {
		this.grabbedTotals = grabbedTotals;
		this.calculatedTotals = calculatedTotals;

	}

	@StepGroup
	public void verifyTotalsDiscount() {
		verifyTotalPrice(calculatedTotals.getTotalPrice(), grabbedTotals.getTotalPrice());
		verifyShippingPrice(calculatedTotals.getDelivery(), grabbedTotals.getDelivery());

	}

	@Step
	public void verifyTotalPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyShippingPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Shipping Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

}
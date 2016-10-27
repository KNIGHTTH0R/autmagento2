package com.workflows.frontend.stylecoachRegistration;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;
import com.tools.CustomVerification;
import com.tools.data.StylistRegistrationCartCalcDetailsModel;

public class StylecoachRegistrationCartWorkflows {

	private StylistRegistrationCartCalcDetailsModel calculatedTotals = new StylistRegistrationCartCalcDetailsModel();
	private StylistRegistrationCartTotalModel grabbedTotals = new StylistRegistrationCartTotalModel();

	public void setVerifyTotalsDiscount(StylistRegistrationCartCalcDetailsModel calculatedTotals, StylistRegistrationCartTotalModel grabbedTotals) {
		this.grabbedTotals = grabbedTotals;
		this.calculatedTotals = calculatedTotals;

	}

	@StepGroup
	public void verifyTotalsDiscount(String name) {
		verifyTotalPrice(calculatedTotals.getTotalAmount(), grabbedTotals.getTotalPrice());
		verifyVoucherPrice(calculatedTotals.getVoucherDiscount(), grabbedTotals.getVoucher());
		verifyShippingPrice(calculatedTotals.getShipping(), grabbedTotals.getDelivery());

	}

	@Step
	public void verifyTotalPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyShippingPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Shipping Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyVoucherPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Voucher Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

}
package com.workflows.frontend.stylecoachRegistration;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.frontend.ShippingModel;

public class StarterSetConfirmationWorkflows {

	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	@Steps
	public CustomVerification customVerification;



	private ShippingModel shippingGrabbedModel = new ShippingModel();
	private ShippingModel shippingCalculatedModel = new ShippingModel();

	public void setVerifyConfirmationTotals(ShippingModel shippingTotals, ShippingModel shippingCalculatedModel) {
		this.shippingCalculatedModel = shippingCalculatedModel;
		this.shippingGrabbedModel = shippingTotals;
	}

	@Step
	public void verifyConfirmationTotals(String string) {
		verifyTotalAmount(shippingGrabbedModel.getTotalAmount(), shippingCalculatedModel.getTotalAmount());
		verifyShippingPrice(shippingGrabbedModel.getShippingPrice(), shippingCalculatedModel.getShippingPrice());
		verifySubTotals(shippingGrabbedModel.getSubTotal(), shippingCalculatedModel.getSubTotal());
		verifyDiscountsPrice(shippingGrabbedModel.getDiscountPrice(), shippingCalculatedModel.getDiscountPrice());
	}

	@Step
	public void verifyTotalAmount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifySubTotals(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Sub Totals dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyShippingPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Shipping Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyDiscountsPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Discounts Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}
}

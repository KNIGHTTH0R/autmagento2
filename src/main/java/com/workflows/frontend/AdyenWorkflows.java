package com.workflows.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.ShippingModel;

public class AdyenWorkflows {

	private OrderModel orderModel = new OrderModel();;
	private ShippingModel shippingCalculatedModel = new ShippingModel();

	public void setVerifyAdyenTotals(OrderModel orderModel, ShippingModel shippingCalculatedModel) {
		this.shippingCalculatedModel = shippingCalculatedModel;
		this.orderModel = orderModel;
	}

	@Step
	public void veryfyAdyenTotals(String string) {

		verifyTotal(orderModel.getTotalPrice(), shippingCalculatedModel.getSubTotal());
	}

	@Step
	public void verifyTotal(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Adyen totals doesn't match   Expected: " + compare + " Actual: " + productNow, productNow.contentEquals(compare.replace(".", "")));
	}

}

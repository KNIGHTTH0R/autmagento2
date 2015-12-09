package com.workflows.frontend;

import java.math.BigDecimal;

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

		verifyTotal(orderModel.getTotalPrice(), shippingCalculatedModel.getTotalAmount());
	}

	@Step
	public void verifyTotal(String adyenTotal, String compare) {
		BigDecimal total = BigDecimal.valueOf(Double.parseDouble(adyenTotal));
		total = total.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		CustomVerification.verifyTrue("Failure: Adyen totals doesn't match   Expected: " + compare + " Actual: " + String.valueOf(total), String.valueOf(total).contains(compare));
	}

}

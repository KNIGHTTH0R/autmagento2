package com.workflows.frontend;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.ComissionConfigConstants;

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
	public void verifyTotal(String adyenTotal, String compare) {
		BigDecimal total = BigDecimal.valueOf(Double.parseDouble(adyenTotal));
		System.out.println(total);
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
//		total = total.divide(BigDecimal.valueOf(100), mc);
		total = total.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
		System.out.println(total);
		CustomVerification.verifyTrue("Failure: Adyen totals doesn't match   Expected: " + compare + " Actual: " + String.valueOf(total),
				adyenTotal.contains(String.valueOf(total)));
	}

}

package com.tools.cartcalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.tools.data.frontend.ShippingModel;

public class GeneralCartCalculations {

	/**
	 * Calculates new shipping based on remaining sum if voucher is bigger than
	 * total amount
	 * 
	 * @param totalAmount
	 * @param ruleDiscount
	 * @param shipping
	 * @return
	 */
	public static String calculateNewShipping(BigDecimal totalAmount, BigDecimal ruleDiscount, BigDecimal shipping) {

		if (ruleDiscount.compareTo(totalAmount) > 0) {

			BigDecimal remainingSum = ruleDiscount.subtract(totalAmount);
			shipping = shipping.subtract(remainingSum);
			shipping = shipping.compareTo(BigDecimal.ZERO) > 0 ? shipping : BigDecimal.ZERO;
		}

		return String.valueOf(shipping.setScale(2, RoundingMode.HALF_UP));
	}
	
	public static String calculateAdyenTotal(ShippingModel... shippingModelsList) {

		BigDecimal total = BigDecimal.ZERO;

		for (ShippingModel list : shippingModelsList) {
			total = total.add(BigDecimal.valueOf(Double.parseDouble(list.getTotalAmount())));
		}
		return String.valueOf(total);
	}

}

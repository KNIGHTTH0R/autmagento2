package com.tools.cartcalculations;

import java.math.BigDecimal;

public class GeneralCartCalculations {

	public static String calculateNewShippingBasedOnRemaingSumFromRuleDiscount(BigDecimal totalAmount, BigDecimal ruleDiscount, BigDecimal shipping) {

		if (ruleDiscount.compareTo(totalAmount) > 0) {
			
			BigDecimal remainingSum = ruleDiscount.subtract(totalAmount);
			shipping = shipping.subtract(remainingSum);
			shipping = shipping.compareTo(BigDecimal.ZERO) > 0 ? shipping : BigDecimal.ZERO;
		}

		return String.valueOf(shipping);
	}

}

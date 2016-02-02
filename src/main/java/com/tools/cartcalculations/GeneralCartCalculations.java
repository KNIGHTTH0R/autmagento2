package com.tools.cartcalculations;

import java.math.BigDecimal;

public class GeneralCartCalculations {

	public static String calculateNewShippingBasedOnRemaingSumFromRuleDiscount(BigDecimal totalAmount, BigDecimal ruleDiscount, BigDecimal shipping) {

		BigDecimal newShipping = BigDecimal.ZERO;

		if (ruleDiscount.compareTo(totalAmount) > 0) {
			BigDecimal remainingSum = ruleDiscount.subtract(totalAmount);
			newShipping = shipping.subtract(remainingSum);
			newShipping = newShipping.compareTo(BigDecimal.ZERO) > 0 ? newShipping : BigDecimal.ZERO;
		} else {
			newShipping = shipping;
		}

		return String.valueOf(newShipping);
	}

}

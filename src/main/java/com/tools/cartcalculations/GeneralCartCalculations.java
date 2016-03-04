package com.tools.cartcalculations;

import java.math.BigDecimal;

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
			System.out.println("BOOM " + shipping);
			shipping = shipping.compareTo(BigDecimal.ZERO) > 0 ? shipping : BigDecimal.ZERO;
			System.out.println("BOOM " + shipping);
		}

		return String.valueOf(shipping);
	}

}

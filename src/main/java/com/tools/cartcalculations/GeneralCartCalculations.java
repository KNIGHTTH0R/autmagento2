package com.tools.cartcalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.tools.data.frontend.ShippingModel;

public class GeneralCartCalculations {
	
	public static void main (String[] args){
		System.out.println(GeneralCartCalculations.calculateIpBasedOnSpecialPrice("63", "75.00", "39.00"));
	}
	

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

	public static String calculateIpBasedOnSpecialPrice(String initialIp, String price, String specialPrice) {

		BigDecimal specialIps = BigDecimal.ZERO;

		BigDecimal initialProductIps = BigDecimal.valueOf(Double.parseDouble(initialIp));
		BigDecimal specialProductPrice = BigDecimal.valueOf(Double.parseDouble(specialPrice));
		BigDecimal productPrice = BigDecimal.valueOf(Double.parseDouble(price));

		specialIps = specialIps.add(specialProductPrice);
		specialIps = specialIps.divide(productPrice, 4, RoundingMode.HALF_UP);
		specialIps = specialIps.multiply(initialProductIps);

		return String.valueOf(specialIps.setScale(0, RoundingMode.HALF_UP));

	}
	
}

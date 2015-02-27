package com.tools.calculation;

import java.math.BigDecimal;

public class NewCalculation {

	public static String calculateAskingPrice(String unitPrice, String quantity) {

		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(unitPrice));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(quantity));

		return String.valueOf(price.multiply(qty));

	}

	public static String calculateFinalPrice(String askingPrice, String discount) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		BigDecimal disc = BigDecimal.valueOf(Double.parseDouble(discount));

		discountValue = askPrice.multiply(disc);
		discountValue = discountValue.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		result = askPrice.subtract(discountValue);

		return String.valueOf(result);
	}

}

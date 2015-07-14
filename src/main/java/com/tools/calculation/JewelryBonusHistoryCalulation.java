package com.tools.calculation;

import java.math.BigDecimal;

public class JewelryBonusHistoryCalulation {

	public static String addNewJewelryBonusToTotal(String total, String amount) {

		BigDecimal decimalTotal = BigDecimal.valueOf(Double.parseDouble(total.replace(".", "").replace(",", ".")));
		BigDecimal decimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));

		return String.valueOf(decimalTotal.add(decimalAmount));

	}

	public static String substractNewJewelryBonusFromTotal(String total, String amount) {

		BigDecimal decimalTotal = BigDecimal.valueOf(Double.parseDouble(total.replace(".", "").replace(",", ".")));
		BigDecimal decimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));

		return String.valueOf(decimalTotal.subtract(decimalAmount));

	}

}

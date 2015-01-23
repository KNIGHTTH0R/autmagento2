package com.tools;

import java.math.BigDecimal;

public class MathUtils {
	public double roundToTwoDecimals(String number) {
		BigDecimal a = new BigDecimal(number);
		BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(roundOff);
		return Double.parseDouble(String.valueOf(roundOff));
	}

}

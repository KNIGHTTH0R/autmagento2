package com.tools.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterUtils {
	
	public static String getAndFormatCurrentDate() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date(System.currentTimeMillis() - 3600 * 1000);
		return dateFormat.format(date);

	}

	public static BigDecimal cleanNumberToBigDecimal(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");

		return BigDecimal.valueOf(Double.parseDouble(result));
	}
	
	public static String extractPriceFromURL(String url){
		
		String result = "";
		String[] splitter = url.split("&");
		for (String string : splitter) {
			if(string.contains("paymentAmount=")){
				result = string.replace("paymentAmount=", "");
			}
		}
		return result;
	}
	public static String extractOrderIDFromURL(String url){
		String result = "";
		String[] splitter = url.split("&");
		for (String string : splitter) {
			if(string.contains("merchantReference=")){
				result = string.replace("merchantReference=TEST-PAYMENT-", "");
			}
		}
		return result;
	}

	public static String cleanNumberToString(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");
		result = result.replace("%", "");

		return result;
	}

	public static int cleanNumberToInt(String unitPrice) {
		String result = unitPrice;
		int finalResult = 0;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace(",", ".");

		try {
			finalResult = Integer.valueOf(result);
		} catch (Exception e) {
			System.err.println("WARNING: Could not convert to integer - " + unitPrice);
			e.printStackTrace();
		}

		return finalResult;
	}
}

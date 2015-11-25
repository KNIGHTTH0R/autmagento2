package com.tools.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import com.tools.env.constants.Separators;

public class FormatterUtils {

	/**
	 * This is a helper method to aproximate the (local time RO) of a form
	 * creation. 1 Hour is substracted from the time (GMT offset)
	 * 
	 * @return
	 */
	public static String getAndFormatCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date(System.currentTimeMillis() - 3600 * 1000);
		return dateFormat.format(date);
	}

	/**
	 * This method can generate a date in a certain format Ex dd.MM.yyyy; and
	 * set a date offset from today -> cuttentTime + offset = result offset =
	 * 3600 (1 hour offset); offset = 86400 (24 hour offset)
	 * 
	 * @param dateFormatPattern
	 * @param dayOffset
	 * @return
	 */
	public static String getCustomDate(String dateFormatPattern, long dayOffset) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		Date date = new Date(System.currentTimeMillis() + dayOffset * 1000);
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

	public static String extractPriceFromURL(String url) {

		String result = "";
		String[] splitter = url.split("&");
		for (String string : splitter) {
			if (string.contains("paymentAmount=")) {
				result = string.replace("paymentAmount=", "");
			}
		}
		return result;
	}

	public static String extractOrderIDFromURL(String url) {
		String result = "";
		String[] splitter = url.split("&");
		for (String string : splitter) {
			if (string.contains("merchantReference=")) {
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
		result = result.replace("IP", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");
		result = result.replace("%", "");

		return result;
	}
	public static String cleanString(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
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
		result = result.replace("IP", "");
		result = result.replace(",", ".");

		try {
			finalResult = Integer.valueOf(result);
		} catch (Exception e) {
			System.err.println("WARNING: Could not convert to integer - " + unitPrice);
			e.printStackTrace();
		}

		return finalResult;
	}

	public static String[] splitDate(String dateOfBirth) {
		String elems[] = dateOfBirth.split(Separators.DATE_SEPARATOR);
		if (elems.length != 3) {
			Assert.assertTrue("Error: birth date provided is not a valid format. Valid format - 'Feb,1970,12'", elems.length != 3);
		}
		return elems;
	}
	
	public static void main(String[] args){
		System.out.println(FormatterUtils.getCustomDate("yyyy.MM.dd HH:mm:ss", 3600));
	}
}

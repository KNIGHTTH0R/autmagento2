package com.tools.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.tools.constants.Separators;

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
		result = result.replace("€", "");
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

	/**
	 * return new order id after order is splitted for term purchase. add 1 to
	 * adyen order id for regular order, add 2 for TP because of pippajean, add
	 * 3 for TP because of customer
	 * 
	 * @param incrementNumber
	 * @return new order id
	 */

	public static String getOrderId(String url) {

		return extractOrderIDFromURL(url);
	}

	public static String incrementOrderId(String orderId, int incrementNumber) {

		String charPart = FormatterUtils.getNotDigitsFromString(orderId);
		String digitPart = FormatterUtils.getIntegerNumberFromString(orderId);

		int number = Integer.parseInt(digitPart) + incrementNumber;

		return charPart + StringUtils.leftPad(String.valueOf(number), 10, "0");
	}

	public static void main(String[] args) {
		// System.out.println(FormatterUtils.incrementSingleTpOrderId("10026526800",
		// 2));
	//	System.out.println(FormatterUtils.getIntegerNumberFromString("311.0"));
	/*	BigDecimal ipPoints = BigDecimal.ZERO;
		ipPoints=BigDecimal.valueOf(311.0);*/
		
		String x= "86,00 €";
	//	String y=FormatterUtils.cleanString(x);
		
		System.out.println(FormatterUtils
					.parseValueToBigDecimalWithZeroDecimals(x));
		//System.out.println(getTestIntegerFromString("-1.108"));
	//	System.out.println(parseValueToZeroDecimals("-1.108"));
	}

	public static String incrementSingleTpOrderId(String orderId, int incrementNumber) {

		String digitPart = FormatterUtils.getIntegerNumberFromString(orderId);
		String orderWithIncrement = digitPart.substring(0, digitPart.length() - 1) + incrementNumber;

		return orderWithIncrement;
	}

	public static String cleanNumberToString(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace("€", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace("IP", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");
		result = result.replace("%", "");
		result = result.replace("$", "");

		return result;
	}

	public static String getIntegerNumberFromString(String s) {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)|| ch=='-') {
				t.append(ch);
			}
		}
		return String.valueOf(t);
	}
	
	public static int getTestIntegerFromString(String s){
		
		int x = Integer.parseInt(s);

		System.out.println(x);
		return x;
	}

	public static String getNotDigitsFromString(String s) {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isDigit(ch)) {
				t.append(ch);
			}
		}
		return String.valueOf(t);
	}

	/**
	 * adds decimal point to a string.The decimals parameter tells how many
	 * digits remain after the decimal point
	 * 
	 * @param value
	 * @param decimals
	 * @return
	 */
	public static String parseValue(String value, int decimals) {
		String cleanValue = getIntegerNumberFromString(value);
		StringBuilder builder = new StringBuilder(cleanValue);
		if (decimals != 0)
			builder.insert(builder.length() - decimals, ".");

		return String.valueOf(builder);
	}

	public static String parseValueToTwoDecimals(String value) {
		return parseValue(value, 2);
	}

	public static String parseValueToZeroDecimals(String value) {
		return parseValue(value, 0);
	}

	public static int parseValueToInt(String value) {
		return Integer.parseInt(parseValue(value, 0));
	}

	public static BigDecimal parseValueToBigDecimal(String value) {
		return BigDecimal.valueOf(Double.parseDouble(parseValue(value, 0)));
	}
	
	public static BigDecimal parseValueToBigDecimalWithZeroDecimals(String value) {
		return BigDecimal.valueOf(Double.parseDouble(parseValue(value, 0))).setScale(0);
	}

	public static double parseValueToDouble(String value) {
		return Double.parseDouble(value);
	}

	public static String cleanString(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace("€", "");
		result = result.replace(".", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");
		result = result.replace("%", "");

		return result;
	}

	public static String cleanToInteger(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace("€", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace("IP", "");
		result = result.replace(",", ".");
		result = result.replace("%", "");

		return result;
	}

	public static int cleanNumberToInt(String unitPrice) {
		String result = unitPrice;
		int finalResult = 0;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace("€", "");
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

	// coleague
	public static String[] splitDate(String dateOfBirth) {
		String elems[] = dateOfBirth.split(Separators.DATE_SEPARATOR);
		if (elems.length != 3) {
			Assert.assertTrue("Error: birth date provided is not a valid format. Valid format - 'Feb,1970,12'",
					elems.length != 3);
		}
		return elems;
	}

	public static double roundDouble(double value) {
		double roudValue = Math.round(value * 100.0) / 100.0;
		return roudValue;
	}


}

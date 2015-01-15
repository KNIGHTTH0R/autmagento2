//package com.tools;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import junit.framework.Assert;
//
//public class MathUtils {
//
//	/**
//	 * Returns the integer percentage value calculated using round() by default
//	 * alternatively, the 3rd optional parameter can specify if ceil() of
//	 * floor() should be used
//	 */
//	public static int getPercentage(String batch, String total,
//			String... functionName) {
//		int p = (int) Math.round(100 * Double.valueOf(batch)
//				/ Double.valueOf(total));
//		if (functionName.length > 0) {
//			if (functionName.length == 1) {
//				if (functionName[0] == "floor") {
//					return (int) Math.floor(100 * Double.valueOf(batch)
//							/ Double.valueOf(total));
//				}
//				if (functionName[0] == "ceil") {
//					return (int) Math.ceil(100 * Double.valueOf(batch)
//							/ Double.valueOf(total));
//				}
//				if (functionName[0] == "round") {
//				}
//			} else { // user provided too many arguments
//				Assert.fail("Too Many Arguments in getPercentage() function");
//			}
//		}
//		return p;
//	}
//
//	/**
//	 * Returns the first integer value contained in a given string
//	 * 
//	 * @param text
//	 * @return
//	 */
//	public static String getIntegerValue(String text) {
//		String intValue = null;
//		Pattern p = Pattern.compile("(^|\\s)([0-9]+)($|\\s)");
//		Matcher m = p.matcher(text);
//		if (m.find()) {
//			intValue = m.group(2);
//		}
//		return intValue;
//	}
//
//	public static String getPriceWithPercentageIncluded(String price,
//			String percentage) {
//		Double dblPrice = Double.valueOf(price);
//		Double dblPercentage = Double.valueOf(percentage);
//		Double finalPrice = dblPrice * (100 + dblPercentage) / 100;
//		return getDoubleValueWithTwoDigits(finalPrice);
//	}
//
//	public static String getPercentageValue(String price, String percentage) {
//		Double dblPrice = Double.valueOf(price);
//		Double dblPercentage = Double.valueOf(percentage);
//		Double finalPrice = dblPrice * dblPercentage / 100;
//		return getDoubleValueWithTwoDigits(finalPrice);
//	}
//
//	public static String getPercentageValueFromPriceThatIncludesPercentageValue(
//			String price, String percentage) {
//		Double dblPrice = Double.valueOf(price);
//		Double dblPercentage = Double.valueOf(percentage);
//		Double percentageValue = dblPrice * (1 - 100 / (dblPercentage + 100));
//		return getDoubleValueWithTwoDigits(percentageValue);
//	}
//
//	public static double extractDoubleFromString(String str) {
//		double finalDouble = 0;
//		Pattern p = Pattern
//				.compile("[^\\d]*([0-9]+[\\s]*[.,]{0,2}[\\s]*[0-9]*).?$");
//		Matcher m = p.matcher(str);
//		if (m.find()) {
//			finalDouble = Double.parseDouble(m.group(1));
//
//		}
//		return finalDouble;
//	}
//
//	public static String getIntegerFormatPercentageValue(String price,
//			String percentage) {
//		String doubleFormat = getPercentageValue(price, percentage);
//		Double doubleValue = Double.parseDouble(doubleFormat);
//		Integer intValue = (int) Math.round(doubleValue);
//		return Integer.toString(intValue);
//	}
//
//	public static Double roundDoubleToTwoDigits(Double number) {
//		BigDecimal bdNumber = new BigDecimal(number);
//		return bdNumber.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
//
//	public static Double getAverageValueAsDouble(String... terms) {
//		Double sum = 0.0;
//		for (String term : terms)
//			sum += Double.valueOf(term);
//		return sum / terms.length;
//	}
//
//	public static boolean checkIfThereIsAtLeastOneFalseValue(
//			boolean... booleanList) {
//		for (boolean b : booleanList) {
//			if (!b)
//				return true;
//		}
//		return false;
//	}
//	
//	public static List<Integer> getListWithIndexesOfTrueValues(
//			boolean... booleanList) {
//		List<Integer> trueValuesIndexes = new ArrayList<Integer>();
//		for (int i = 0; i < booleanList.length; i++) {
//			boolean b = booleanList[i];
//			if (b)
//				trueValuesIndexes.add(i);
//		}
//		return trueValuesIndexes;
//	}
//
//	public static List<Integer> getListWithIndexesOfFalseValues(
//			boolean... booleanList) {
//		List<Integer> falseValuesIndexes = new ArrayList<Integer>();
//		for (int i = 0; i < booleanList.length; i++) {
//			boolean b = booleanList[i];
//			if (!b)
//				falseValuesIndexes.add(i);
//		}
//		return falseValuesIndexes;
//	}
//
//	public static boolean checkIfAllValuesAreEqual(int... values) {
//		for (int i = 1; i < values.length; i++) {
//			if (values[i] != values[0])
//				return false;
//		}
//		return true;
//	}
//
//	public static String getSumOfDoubleValuesAsString(String... doubleValues) {
//		Double sum = 0.0;
//		for (String doubleValue : doubleValues) {
//			doubleValue = doubleValue.replaceAll(",", "");
//			sum += Double.valueOf(doubleValue);
//		}
//		return getDoubleValueWithTwoDigits(sum);
//	}
//
//	public static String getProductOfDoubleValuesAsString(
//			String... doubleValues) {
//		Double product = 1.0;
//		for (String doubleValue : doubleValues) {
//			doubleValue = doubleValue.replaceAll(",", "");
//			product *= Double.valueOf(doubleValue);
//		}
//		return getDoubleValueWithTwoDigits(product);
//	}
//
//	/**
//	 * Returns the digits of the provided double number, without the digital "0"
//	 * padding
//	 * 
//	 * @param number
//	 * @return
//	 */
//	public static String getDigitsWithoutZeroPadding(Double number) {
//		String stringValue = number.toString();
//		if (stringValue.contains(".")) {
//			while (stringValue.charAt(stringValue.length() - 1) == '0')
//				stringValue = stringValue
//						.substring(0, stringValue.length() - 1);
//			stringValue = stringValue.replace(".", "");
//		}
//		return stringValue;
//	}
//
//	public static Double getDoubleWithTwoDigits(Double number) {
//		DecimalFormat twoFiguresFormat = new DecimalFormat("####0.00");
//		return Double.valueOf(twoFiguresFormat.format(number));
//	}
//
//	public static String getDoubleValueWithTwoDigits(String number) {
//		DecimalFormat twoFiguresFormat = new DecimalFormat("##0.00");
//		return twoFiguresFormat.format(Double.valueOf(number));
//	}
//
//	public static String getDoubleValueWithTwoDigits(Double number) {
//		DecimalFormat twoFiguresFormat = new DecimalFormat("##0.00");
//		return twoFiguresFormat.format(number);
//	}
//
//	public static String subtractIntegerValueIfGraterThan(String number,
//			String greaterThan, String subtractWith) {
//		int intNumber = Integer.parseInt(number);
//		int intGreaterThan = Integer.parseInt(greaterThan);
//		int intSubtractWith = Integer.parseInt(subtractWith);
//		if (intNumber > intGreaterThan) {
//			intNumber -= intSubtractWith;
//		}
//		return String.valueOf(intNumber);
//	}
//
//	public static String incrementIntegerValueIfLessThan(String number,
//			String lessThan, String incrementWith) {
//		int intNumber = Integer.parseInt(number);
//		int intLessThan = Integer.parseInt(lessThan);
//		int intIncrementWith = Integer.parseInt(incrementWith);
//		if (intNumber < intLessThan) {
//			intNumber += intIncrementWith;
//		}
//		return String.valueOf(intNumber);
//	}
//}

package com.tools.utils;

import java.math.BigDecimal;

public class FormatterUtils {

	
	public static void main(String args[]){
		String url = "https://test.adyen.com/hpp/pay.shtml?allowedMethods=&blockedMethods=&currencyCode=EUR&merchantReference=TEST-PAYMENT-staging100051243&paymentAmount=8399&shopperEmail=testautomation%40evozon.com&shopperReference=26349&countryCode=AT&shipBeforeDate=2015-02-05&skinCode=miho5oFn&merchantAccount=PippaJeanDE&sessionValidity=2015-02-02T15%3A18%3A10%2B00%3A00&shopperLocale=de_de&recurringContract=RECURRING&shopperStatement=PIPPAJEAN+Bestellung+staging100051243&merchantReturnData=a%3A2%3A%7Bs%3A6%3A%22amount%22%3Bs%3A4%3A%228399%22%3Bs%3A17%3A%22shopper_reference%22%3Bs%3A5%3A%2226349%22%3B%7D&offset=&billingAddressSig=mVuy80p%2FJ%2F6kOTc7IilfV0SRJ9Q%3D&deliveryAddressSig=mVuy80p%2FJ%2F6kOTc7IilfV0SRJ9Q%3D&shopperSig=yiSuIREVgJb1Bmp6matJZUUDqP0%3D&merchantSig=XUJI9qUyMOSCMFwBt5yfsxWX%2Bs4%3D&billingAddress.street=tttt&billingAddress.houseNumberOrName=3&billingAddress.city=Wien&billingAddress.postalCode=2345&billingAddress.country=AT&billingAddressType=&deliveryAddress.street=tttt&deliveryAddress.houseNumberOrName=3&deliveryAddress.city=Wien&deliveryAddress.postalCode=2345&deliveryAddress.country=AT&deliveryAddressType=&shopper.firstName=sss&shopper.infix=&shopper.lastName=sss&shopper.email=testautomation%40evozon.com&shopperType=";
		String subStringURL = FormatterUtils.extractPriceFromURL(url);
		String orderId = FormatterUtils.extractOrderIDFromURL(url);
		
		System.out.println("URL ----> " + url);
		System.out.println("Price URL ----> " + subStringURL);
		
		System.out.println("URL ----> " + url);
		System.out.println("OrderID ----> " + orderId);
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
		//merchantReference=TEST-PAYMENT-
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

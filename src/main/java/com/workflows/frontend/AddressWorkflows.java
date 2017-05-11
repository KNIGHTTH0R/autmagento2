package com.workflows.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;

public class AddressWorkflows {

	private static AddressModel billingAddress = new AddressModel();
	private static String billingCountryName;
	private static String billingPlz;
	private static AddressModel shippingAddress = new AddressModel();
	private static String shippingCountryName;
	private static String shippingPlz;

	public static void setBillingAddressModels(String billingCountryNameValue, AddressModel billingAddressValue) {
		billingAddress = billingAddressValue;
		billingCountryName = billingCountryNameValue;
	}

	public static void setBillingPlz(String billingCountryNameValue, AddressModel billingAddressValue) {
		billingAddress = billingAddressValue;
		billingPlz = billingCountryNameValue;
	}

	@Step
	public static void validateBillingAddress(String message) {
		
		System.out.println(" ");
		System.out.println(message);
		verifyCountry(billingCountryName, billingAddress.getCountryName());
	}

	public static void setShippingAddressModels(String shippingCountryNameValue, AddressModel shippingAddressValue) {
		shippingAddress = shippingAddressValue;
		shippingCountryName = shippingCountryNameValue;
	}

	public static void setShippingPlz(String shippingCountryNameValue, AddressModel shippingAddressValue) {
		shippingAddress = shippingAddressValue;
		shippingPlz = shippingCountryNameValue;
	}

	@Step
	public static void validateShippingAddress(String message) {
		System.out.println(" ");
		System.out.println(message);
		verifyCountry(shippingCountryName, shippingAddress.getCountryName());
	}

	@Step
	public static void validateBillingPostcode() {
		verifyCountry(billingPlz, billingAddress.getPostCode());
	}

	@Step
	public static void validateShippingPostcode() {
		verifyCountry(shippingPlz, shippingAddress.getPostCode());
	}

	// @Step
	public static void verifyCountry(String address, String countryName) {
		CustomVerification.verifyTrue("Failure: Values don't match !", address.contains(countryName));
	}
	
}

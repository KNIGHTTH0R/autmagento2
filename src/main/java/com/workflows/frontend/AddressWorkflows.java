package com.workflows.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;

public class AddressWorkflows {

	private static AddressModel billingAddress = new AddressModel();
	private static String billingCountryName;
	private static AddressModel shippingAddress = new AddressModel();
	private static String shippingCountryName;

	public static void setBillingAddressModels(String billingCountryNameValue, AddressModel billingAddressValue) {
		System.out.println("HERE __AddressWorkflows__ setBillingAddressModels");
		billingAddress = billingAddressValue;
		billingCountryName = billingCountryNameValue;
	}
	@Step
	public static void validateBillingAddress(String message) {
		verifyCountry(billingCountryName, billingAddress.getCountryName());
	}

	public static void setShippingAddressModels(String shippingCountryNameValue, AddressModel shippingAddressValue) {
		shippingAddress = shippingAddressValue;
		shippingCountryName = shippingCountryNameValue;
	}
	@Step
	public static void validateShippingAddress(String message) {
		verifyCountry(shippingCountryName, shippingAddress.getCountryName());
	}

//	@Step
	public static void verifyCountry(String address, String countryName) {
		CustomVerification.verifyTrue("Failure: Countries dont match !", address.contains(countryName));
	}

}

package com.workflows.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;

public class AddressWorkflows {
	
	private static AddressModel billingAddress = new AddressModel();
	private static String billingCountryName;
	private static AddressModel shippingAddress = new AddressModel();
	private static String shippingCountryName;

	public void setBillingAddressModels(String billingCountryName,AddressModel billingAddress) {
		AddressWorkflows.billingAddress = billingAddress;
		AddressWorkflows.billingCountryName = billingCountryName;
	}
	
	public void validateBillingAddress(String message){
		verifyCountry(billingCountryName,billingAddress.getCountryName());
		
	}
	
	public void setShippingAddressModels(String shippingCountryName,AddressModel shippingAddress) {
		AddressWorkflows.shippingAddress = shippingAddress;
		AddressWorkflows.shippingCountryName = shippingCountryName;
	}
	
	public void validateShippingAddress(String message){
		verifyCountry(shippingCountryName,shippingAddress.getCountryName());
		
	}
	
	@Step
	public void verifyCountry(String address, String countryName) {
		CustomVerification.verifyTrue("Failure: Countries dont match !", address.contains(countryName));
	}

}

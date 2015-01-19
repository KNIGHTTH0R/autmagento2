package com.pages.frontend.checkout;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.Constants;
import com.tools.data.AddressModel;

public class ConfirmationPage extends AbstractPage{

	
	@FindBy(css = "li.shipping-address-preview")
	private WebElement shippingContainer;
	
	@FindBy(css = "li.billing-address-preview")
	private WebElement billingContainer;
	
	@FindBy(css = "div.checkout-totals-section")
	private WebElement totalsContainer;
	
	
	public AddressModel grabShippingData(){
		AddressModel result = new AddressModel();
		element(shippingContainer).waitUntilVisible();
		
		String addressSplitter = shippingContainer.findElement(By.cssSelector("p:nth-child(2) font")).getText();
		System.out.println(shippingContainer.getText());
		
		
		if(addressSplitter.split(Constants.ADDRESS_SEPARATOR).length == 2){
			System.out.println("GRAB success");
			result.setStreetAddress(addressSplitter.split(Constants.ADDRESS_SEPARATOR)[0]);
			result.setStreetNumber(addressSplitter.split(Constants.ADDRESS_SEPARATOR)[1]);
			
		}else{
			System.out.println("GRAB ERROR in grabShippingData");
			result.setStreetAddress("");
			result.setStreetNumber("");
		}
		
		String locationSplitter = shippingContainer.findElement(By.cssSelector("p:nth-child(4) font")).getText();
		
		if(locationSplitter.split(Constants.ADDRESS_SEPARATOR).length == 2){
			System.out.println("GRAB success");
			result.setHomeTown(locationSplitter.split(Constants.ADDRESS_SEPARATOR)[0]);
			result.setPostCode(locationSplitter.split(Constants.ADDRESS_SEPARATOR)[1]);
			
		}else{
			System.out.println("GRAB ERROR in grabShippingData");
			result.setHomeTown("");
			result.setPostCode("");
		}
		
		result.setCountryName(shippingContainer.findElement(By.cssSelector("p:nth-child(5) font")).getText());
		result.setPhoneNumber("");
		
		return result;
		
	}
	
	public AddressModel grabBillingData(){
		AddressModel result = new AddressModel();
		element(billingContainer).waitUntilVisible();
		
		String addressSplitter = billingContainer.findElement(By.cssSelector("p:nth-child(2) font")).getText();
		System.out.println(shippingContainer.getText());
		
		if(addressSplitter.split(Constants.ADDRESS_SEPARATOR).length == 2){
			System.out.println("GRAB success");
			result.setStreetAddress(addressSplitter.split(Constants.ADDRESS_SEPARATOR)[0]);
			result.setStreetNumber(addressSplitter.split(Constants.ADDRESS_SEPARATOR)[1]);
			
		}else{
			System.out.println("GRAB ERROR in grabShippingData");
			result.setStreetAddress("");
			result.setStreetNumber("");
		}
		
		String locationSplitter = billingContainer.findElement(By.cssSelector("p:nth-child(4) font")).getText();
		
		if(locationSplitter.split(Constants.ADDRESS_SEPARATOR).length == 2){
			System.out.println("GRAB success");
			result.setHomeTown(locationSplitter.split(Constants.ADDRESS_SEPARATOR)[0]);
			result.setPostCode(locationSplitter.split(Constants.ADDRESS_SEPARATOR)[1]);
			
		}else{
			System.out.println("GRAB ERROR in grabShippingData");
			result.setHomeTown("");
			result.setPostCode("");
		}
		
		result.setCountryName(billingContainer.findElement(By.cssSelector("p:nth-child(5) font")).getText());
		result.setPhoneNumber("");
		
		return result;
	}
	
	
//	public CartTotalsModel grabTotalsData(){
//		
//	}
}

package com.pages.frontend.checkout;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.Constants;
import com.tools.data.AddressModel;

public class ConfirmationPage extends AbstractPage {

	@FindBy(css = "li.shipping-address-preview")
	private WebElement shippingContainer;

	@FindBy(css = "li.billing-address-preview")
	private WebElement billingContainer;

	@FindBy(css = "div.checkout-totals-section")
	private WebElement totalsContainer;

	public AddressModel grabAddressData(WebElement addressPreview) {
		AddressModel result = new AddressModel();
		element(addressPreview).waitUntilVisible();
		String textparse = addressPreview.getText();
		
		if(textparse.split(Constants.LINE_SEPARATOR).length == 4){
			//TODO FIX this
			result.setStreetAddress("");
			result.setStreetNumber("");
			
			result.setHomeTown("");
			result.setPostCode("");
			
			result.setCountryName("");
			result.setPhoneNumber("");
			
		}
		if(textparse.split(Constants.LINE_SEPARATOR).length == 5){
			System.out.println("FAILURE: error on shipping parsing - Confirmation Page - 5");
		}
		else{
			System.out.println("FAILURE: error on shipping parsing - Confirmation Page");
		}

		return result;

	}

	public AddressModel grabBillingData() {
		AddressModel result = new AddressModel();
		element(billingContainer).waitUntilVisible();

		System.out.println(billingContainer.getText());



		return result;
	}

	public static void main(String args[]) {
		String a = "simona gmail" + "\n" + "test, 12"  + "\n" + "berlin, 12456"  + "\n" + "Deutschland";
		
		System.out.println("print: " + a);
		
		String[] splitter = a.split("\n");
		System.out.println(splitter.length);
	}

}

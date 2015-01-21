package com.pages.frontend.checkout;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.Constants;
import com.tools.data.AddressModel;
import com.tools.data.CartProductModel;

public class ConfirmationPage extends AbstractPage {

	@FindBy(css = "li.shipping-address-preview")
	private WebElement shippingContainer;

	@FindBy(css = "li.billing-address-preview")
	private WebElement billingContainer;

	@FindBy(css = "div.checkout-totals-section")
	private WebElement totalsContainer;
	
	@FindBy(id = "terms")
	private WebElement acceptTerms;
	
	@FindBy(id = "submit-confirmation-step")
	private WebElement submitButton;
	
	@FindBy(css = "div#cart-section-1 div.items-section")
	private WebElement productListContainer;

	public AddressModel grabAddressData(WebElement addressPreview) {
		AddressModel result = new AddressModel();
		element(addressPreview).waitUntilVisible();
		String textparse = addressPreview.getText();
		String[] splittedText = textparse.split(Constants.LINE_SEPARATOR);	
		
		if(splittedText.length == 4){
			
			String[] streetData = splittedText[1].split(Constants.COMMA_SEPARATOR);
			String streetName = streetData[0];
			String streetNumber = streetData[1];
			
			String[] townData  = splittedText[2].split(Constants.COMMA_SEPARATOR);
			String homeTown = townData[0];
			String postCode = townData[1];
			
			result.setStreetAddress(streetName);
			result.setStreetNumber(streetNumber);
			
			result.setHomeTown(homeTown);
			result.setPostCode(postCode);
			
			result.setCountryName(splittedText[3]);
			
			System.out.println("grabAdressData method results are : ");
			System.out.println("StreetAddress: " + result.getStreetAddress());
			System.out.println("StreetNumber: " + result.getStreetNumber());
			System.out.println("HomeTown: " + result.getHomeTown());
			System.out.println("PostCode: " + result.getPostCode());
			System.out.println("CountryName: " + result.getCountryName());
			
			
		}
		if(textparse.split(Constants.LINE_SEPARATOR).length == 5){
			System.out.println("FAILURE: error on shipping parsing - Confirmation Page - 5");
			
			
			String[] streetData = splittedText[1].split(Constants.COMMA_SEPARATOR);
			String streetName = streetData[0];
			String streetNumber = streetData[1];
			
			String[] townData  = splittedText[2].split(Constants.COMMA_SEPARATOR);
			String homeTown = townData[0];
			String postCode = townData[1];
			
			result.setStreetAddress(streetName);
			result.setStreetNumber(streetNumber);
			
			result.setHomeTown(homeTown);
			result.setPostCode(postCode);
			
			result.setCountryName(splittedText[3]);
			
			System.out.println("grabAdressData method results are : ");
			System.out.println("StreetAddress: " + result.getStreetAddress());
			System.out.println("StreetNumber: " + result.getStreetNumber());
			System.out.println("HomeTown: " + result.getHomeTown());
			System.out.println("PostCode: " + result.getPostCode());
			System.out.println("CountryName: " + result.getCountryName());
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
		result = grabAddressData(billingContainer);

		return result;
	}
	
	public AddressModel grabShippingData() {
		AddressModel result = new AddressModel();
		element(shippingContainer).waitUntilVisible();
		
		System.out.println(shippingContainer.getText());
		result = grabAddressData(shippingContainer);		
		
		return result;
	}
	

	public void clickIAgree() {
		element(acceptTerms).waitUntilVisible();
		acceptTerms.click();
	}

	public void clickOnSubmit() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}
	
	public List<CartProductModel> grabProductsList() {
		element(productListContainer).waitUntilVisible();
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			productNow.setUnitPrice(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
		}

		return resultList;
	}

}

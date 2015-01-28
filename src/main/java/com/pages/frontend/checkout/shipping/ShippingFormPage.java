package com.pages.frontend.checkout.shipping;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;

public class ShippingFormPage extends AbstractPage{

	
	@FindBy(css = "select#shipping-address-select")
	private WebElement addressDropDown;
	
	@FindBy(css = "input#shipping:firstname")
	private WebElement firstNameInput;
	
	@FindBy(css = "input#shipping:lastname")
	private WebElement lastNameInput;
	
	@FindBy(css = "input#shipping:street1")
	private WebElement street1Input;
	
	@FindBy(css = "input#shipping:house_number")
	private WebElement houseNumberInput;
	
	@FindBy(css = "input#shipping:street2")
	private WebElement street2Input;
	
	@FindBy(css = "input#shipping:postcode")
	private WebElement postcodeInput;
	
	@FindBy(css = "input#shipping:city")
	private WebElement cityInput;
	
	@FindBy(css = "select#shipping:country_id")
	private WebElement countryDropDown;
	
	@FindBy(css = "select#shipping:telephone")
	private WebElement telephoneInput;
	
	@FindBy(css = "input#same_as_billing")
	private WebElement sameAsBilling;
	
	/**
	 * Select from dropdown an existing address or the "NEUE ADRESSE' value
	 */
	public void selectAdressDropdown(String value){
		element(addressDropDown).waitUntilVisible();
		selectFromDropdown(addressDropDown, value);
	}
	
	public void inputFirstName(String firstName) {
		element(firstNameInput).waitUntilVisible();
		firstNameInput.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		element(lastNameInput).waitUntilVisible();
		lastNameInput.sendKeys(lastName);
	}
	
	public void inputStreet1Address(String streetAddress) {
		element(street1Input).waitUntilVisible();
		street1Input.sendKeys(streetAddress);
	}

	public void inputStreetNumber(String streetNumber) {
		houseNumberInput.sendKeys(streetNumber);
	}
	
	public void inputStreet2Address(String streetAddress) {
		element(street2Input).waitUntilVisible();
		street2Input.sendKeys(streetAddress);
	}
	
	public void inputPostCode(String postCode) {
		postcodeInput.sendKeys(postCode);
	}

	public void inputHomeTown(String homeTown) {
		cityInput.sendKeys(homeTown);
	}

	public void selectCountryName(String countryName) {
		element(countryDropDown).waitUntilVisible();
		element(countryDropDown).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		telephoneInput.sendKeys(phoneNumber);
	}
	
	public void setSameAsBilling(boolean checked) {
		
		boolean isSelected = sameAsBilling.isSelected();
		
		if((checked && !isSelected) || (!checked && isSelected))
			sameAsBilling.click();
			
	}
	
	
	
	
}

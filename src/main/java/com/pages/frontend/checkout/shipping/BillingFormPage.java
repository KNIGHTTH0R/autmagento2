package com.pages.frontend.checkout.shipping;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class BillingFormPage extends AbstractPage {

	@FindBy(css = "select#billing-address-select")
	private WebElement addressDropDown;

	@FindBy(css = "input#billing:firstname")
	private WebElement firstNameInput;

	@FindBy(css = "input#billing:lastname")
	private WebElement lastNameInput;

	@FindBy(css = "input#billing:street1")
	private WebElement street1Input;

	@FindBy(css = "input#billing:house_number")
	private WebElement houseNumberInput;

	@FindBy(css = "input#billing:street2")
	private WebElement street2Input;

	@FindBy(css = "input#billing:postcode")
	private WebElement postcodeInput;

	@FindBy(css = "input#billing:city")
	private WebElement cityInput;

	@FindBy(css = "select#billing:country_id")
	private WebElement countryDropDown;

	@FindBy(css = "select#billing:telephone")
	private WebElement telephoneInput;

	/**
	 * Select from dropdown an existing address or the "NEUE ADRESSE' value
	 */
	public void selectAdressDropdown(String value) {
		element(addressDropDown).waitUntilVisible();
		element(addressDropDown).selectByVisibleText(value);
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

}

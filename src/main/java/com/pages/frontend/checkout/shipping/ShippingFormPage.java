package com.pages.frontend.checkout.shipping;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class ShippingFormPage extends AbstractPage {

	@FindBy(css = "select#shipping-address-select")
	private WebElement addressDropDown;


	@FindBy(css = "input[name='shipping[firstname]']")
	private WebElement firstNameInput;

	@FindBy(css = "input[name='shipping[lastname]']")
	private WebElement lastNameInput;

	@FindBy(css = "input[id*='shipping:street1']")
	private WebElement street1Input;

	@FindBy(css = "input[name='shipping[house_number]']")
	private WebElement houseNumberInput;

	@FindBy(css = "input[name='shipping[postcode]']")
	private WebElement postcodeInput;

	@FindBy(css = "input[name='shipping[city]']")
	private WebElement cityInput;

	@FindBy(css = "select[name='shipping[country_id]']")
	private WebElement countryDropDown;

	@FindBy(css = "input[name='shipping[telephone]']")
	private WebElement telephoneInput;

	@FindBy(css = "input#same_as_billing")
	private WebElement sameAsBilling;

	@FindBy(id = "know_stylist_no")
	private WebElement knowStylistNo;

	@FindBy(id = "terms")
	private WebElement termsCheckbox;

	@FindBy(id = "new-shipping-address")
	private WebElement newShippingAddress;

	public void selectKnowStylistNoOption() {
		element(knowStylistNo).waitUntilVisible();
		knowStylistNo.click();
	}

	public void checkTermsCheckbox() {
		element(termsCheckbox).waitUntilVisible();
		termsCheckbox.click();
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
		element(houseNumberInput).waitUntilVisible();
		houseNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		element(postcodeInput).waitUntilVisible();
		postcodeInput.sendKeys(postCode);
	}

	public void inputHomeTown(String homeTown) {
		element(cityInput).waitUntilVisible();
		cityInput.sendKeys(homeTown);
	}

	public void selectCountryName(String countryName) {
		element(countryDropDown).waitUntilVisible();
		element(countryDropDown).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		element(telephoneInput).waitUntilVisible();
		telephoneInput.sendKeys(phoneNumber);
	}

	public void setSameAsBilling(boolean checked) {

		boolean isSelected = sameAsBilling.isSelected();

		if ((checked && !isSelected) || (!checked && isSelected))
			sameAsBilling.click();
		
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void selectShippingAddress(String value) {
		element(addressDropDown).waitUntilVisible();
		element(addressDropDown).selectByVisibleText(value);
	}

	public void verifyThatYouCannotShipOnRestrictedCountries() {
		Assert.assertTrue("The ddl contains the country name and it should not !!!", !addressDropDown.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE)
				|| !addressDropDown.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
		System.out.println(addressDropDown.getText());
	}
	
	public void clickAddNewAddress() {
		element(newShippingAddress).waitUntilVisible();
		newShippingAddress.click();
	}

}

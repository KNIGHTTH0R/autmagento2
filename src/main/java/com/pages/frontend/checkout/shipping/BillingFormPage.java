package com.pages.frontend.checkout.shipping;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class BillingFormPage extends AbstractPage {

	@FindBy(css = "select#billing-address-select")
	private WebElement addressDropDown;

	@FindBy(css = "input[name='billing[firstname]']")
	private WebElement firstNameInput;

	@FindBy(css = "input[name='billing[lastname]']")
	private WebElement lastNameInput;

	@FindBy(css = "input[id*='billing:street1']")
	private WebElement street1Input;

	@FindBy(css = "input[name='billing[house_number]']")
	private WebElement houseNumberInput;

	@FindBy(css = "input[name='billing[postcode]']")
	private WebElement postcodeInput;

	@FindBy(css = "input[name='billing[city]']")
	private WebElement cityInput;

	@FindBy(css = "select[name='billing[country_id]']")
	private WebElement countryDropDown;

	@FindBy(css = "input[name='billing[telephone]']")
	private WebElement telephoneInput;

	@FindBy(id = "new-billing-address")
	private WebElement newBillingAddress;

	public void clickAddNewAddress() {
		element(newBillingAddress).waitUntilVisible();
		newBillingAddress.click();
	}

	public void verifyThatYouCannotBillOnRestrictedCountries() {
		Assert.assertTrue("The ddl contains the country name and it should not !!!", !addressDropDown.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE)
				|| !addressDropDown.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
		System.out.println(addressDropDown.getText());
		waitABit(2000);
	}

	/**
	 * Select from dropdown an existing address or the "NEUE ADRESSE' value
	 */
	public void selectAdressDropdown(String value) {
		element(addressDropDown).waitUntilVisible();
		element(addressDropDown).selectByVisibleText(value);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
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
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
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
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		element(countryDropDown).waitUntilVisible();
		element(countryDropDown).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		element(telephoneInput).waitUntilVisible();
		telephoneInput.sendKeys(phoneNumber);
	}

}

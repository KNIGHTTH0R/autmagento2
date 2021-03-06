package com.pages.frontend.checkout.shipping;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

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

	@FindBy(css = "div[id*='advice-validate-zip']")
	private WebElement zipValidationMessage;
	
	

	public void clickAddNewAddress() {
		element(newBillingAddress).waitUntilVisible();
		newBillingAddress.click();
	}

	public void verifyThatYouCannotBillOnRestrictedCountries() {
		Assert.assertTrue("The ddl contains the country name and it should not !!!",
				!addressDropDown.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE)
						|| !addressDropDown.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
		System.out.println(addressDropDown.getText());
		waitABit(2000);
	}

	/**
	 * Select from dropdown an existing address or the "NEUE ADRESSE' value
	 */
	public void selectAdressDropdown(String value) {
//		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
//				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitForLoadingImageToDissapear();
		element(addressDropDown).waitUntilVisible();
		element(addressDropDown).selectByVisibleText(value);
		waitForLoadingImageToDissapear();
//		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
//				ContextConstants.LOADING_MESSAGE));
	}
	
	
	public void selectAdress(String address){
		waitForLoadingImageToDissapear();
		element(addressDropDown).waitUntilVisible();
		Select oSelect = new Select(addressDropDown);
		oSelect.deselectByVisibleText(address);
		waitForLoadingImageToDissapear();

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
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		element(street1Input).waitUntilVisible();
		street1Input.clear();
		street1Input.sendKeys(streetAddress);
	}

	public void inputStreetNumber(String streetNumber) {
		element(houseNumberInput).waitUntilVisible();
		houseNumberInput.clear();
		houseNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		element(postcodeInput).waitUntilVisible();
		postcodeInput.clear();
		postcodeInput.sendKeys(postCode);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
	}

	public void inputPostCodeAndValdiateErrorMessage(String postCode) {
		element(postcodeInput).waitUntilVisible();
		postcodeInput.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(postcodeInput).typeAndTab(postCode);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		validateZipValidationErrorMessage();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void inputHomeTown(String homeTown) {
		element(cityInput).waitUntilVisible();
		cityInput.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		cityInput.clear();
		cityInput.sendKeys(homeTown);
	}

	public void clearAndInputNewPostCode(String postCode) {
		element(postcodeInput).waitUntilVisible();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		postcodeInput.clear();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		postcodeInput.sendKeys(postCode);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void clearAndInputNewHomeTown(String homeTown) {
		element(cityInput).waitUntilVisible();
		cityInput.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		cityInput.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		cityInput.sendKeys(homeTown);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void selectCountryName(String countryName) {
		element(countryDropDown).waitUntilVisible();
		element(countryDropDown).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		element(telephoneInput).waitUntilVisible();
		telephoneInput.clear();
		telephoneInput.sendKeys(phoneNumber);
	}

	public void validateZipValidationErrorMessage() {
		element(zipValidationMessage).waitUntilVisible();
		Assert.assertTrue("The message from validation message is not the expected one!!",
				zipValidationMessage.getText().contains(ContextConstants.PLZ_ERROR_MESSAGE));
	}

	
}

package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class KlarnaPage extends AbstractPage{

	@FindBy(id = "klarna.shopper.firstName")
	private WebElement klarnaFirstNameInput;
	
	@FindBy(id = "klarna.shopper.lastName")
	private WebElement klarnaLastNameInput;
	
	@FindBy(id = "klarna.shopper.gender")
	private WebElement klarnaGender;
	
	@FindBy(id = "klarna.shopper.dateOfBirthDayOfMonth")
	private WebElement dayOfBirthday;
	
	@FindBy(id = "klarna.shopper.dateOfBirthMonth")
	private WebElement monthOfBirthday;
	
	@FindBy(id = "klarna.shopper.dateOfBirthYear")
	private WebElement yearOfBirthday;
	
	@FindBy(id = "klarna.shopper.telephoneNumber")
	private WebElement phoneNumberInput;
	
	@FindBy(id = "klarna.billingAddress.street")
	private WebElement streetBillingInput;

	@FindBy(id = "klarna.billingAddress.houseNumberOrName")
	private WebElement houseNumberBillingInput;
	
	@FindBy(id = "klarna.billingAddress.postalCode")
	private WebElement postalCodeBillingInput;
	
	@FindBy(id = "klarna.billingAddress.city")
	private WebElement cityBillingInput;	
	
	@FindBy(id = "klarna.billingAddress.country")
	private WebElement countryBillingInput;	
	
	@FindBy(id = "klarna.deliveryAddress.street")
	private WebElement streetShippingInput;

	@FindBy(id = "klarna.deliveryAddress.houseNumberOrName")
	private WebElement houseNumberShippingInput;
	
	@FindBy(id = "klarna.deliveryAddress.postalCode")
	private WebElement postalCodeShippingInput;
	
	@FindBy(id = "klarna.deliveryAddress.city")
	private WebElement cityShippingInput;	
	
	@FindBy(id = "klarna.deliveryAddress.country")
	private WebElement countryShippingInput;	
	
	@FindBy(id = "klarna.specifyDelivery")
	private WebElement deliveryAddressCheckbox;
	
	@FindBy(id = "klarna.acceptPrivacyPolicy")
	private WebElement termsAndConditionsCheckbox;
	
	@FindBy(css = "input.paySubmit.paySubmitklarna")
	private WebElement submitButton;
	
	public void FirstNameInput(String firstName) {
		element(klarnaFirstNameInput).waitUntilVisible();
		klarnaFirstNameInput.sendKeys(firstName);
	}

	public void LastNameInput(String lastName) {
		element(klarnaLastNameInput).waitUntilVisible();
		klarnaLastNameInput.sendKeys(lastName);
	}

	public void selectGender(String gender) {
		selectFromDropdown(klarnaGender, gender);
	}
	
	public void DateOfBirthDayInput(String day) {
		element(dayOfBirthday).waitUntilVisible();
		dayOfBirthday.sendKeys(day);
	}
	
	public void DateOfBirthMonthInput(String month) {
		element(monthOfBirthday).waitUntilVisible();
		monthOfBirthday.sendKeys(month);
	}
	
	public void DateOfBirthYearInput(String year) {
		element(yearOfBirthday).waitUntilVisible();
		yearOfBirthday.sendKeys(year);
	}
	
	public void mobilePhoneInput(String phoneNumber) {
		element(phoneNumberInput).waitUntilVisible();
		phoneNumberInput.sendKeys(phoneNumber);
	}
	
	public void streetBillingInput(String streetBilling) {
		element(streetBillingInput).waitUntilVisible();
		streetBillingInput.sendKeys(streetBilling);
	}
	
	public void houseNumberBillingInput(String houseNumberBilling) {
		element(houseNumberBillingInput).waitUntilVisible();
		houseNumberBillingInput.sendKeys(houseNumberBilling);
	}
	
	public void postalCodeBillingInput(String postalCodeBilling) {
		element(postalCodeBillingInput).waitUntilVisible();
		postalCodeBillingInput.sendKeys(postalCodeBilling);
	}
	
	public void cityBillingInput(String cityBilling) {
		element(cityBillingInput).waitUntilVisible();
		cityBillingInput.sendKeys(cityBilling);
	}
	
	public void countryBillingInput(String countryBilling) {
		element(countryBillingInput).waitUntilVisible();
		countryBillingInput.sendKeys(countryBilling);
	}
	
	public void separateAddressCheckbox() {
		element(countryBillingInput).waitUntilVisible();
		countryBillingInput.click();
	}
	
	public void streetShippingInput(String streetShipping) {
		element(streetShippingInput).waitUntilVisible();
		streetShippingInput.sendKeys(streetShipping);
	}
	
	public void houseNumberShippingInput(String houseNumberShipping) {
		element(houseNumberShippingInput).waitUntilVisible();
		houseNumberShippingInput.sendKeys(houseNumberShipping);
	}
	
	public void postalCodeShippingInput(String postalCodeShipping) {
		element(postalCodeShippingInput).waitUntilVisible();
		postalCodeShippingInput.sendKeys(postalCodeShipping);
	}
	
	public void cityShippingInput(String cityShipping) {
		element(cityShippingInput).waitUntilVisible();
		cityShippingInput.sendKeys(cityShipping);
	}
	
	public void countryShippingInput(String countryShipping) {
		element(countryShippingInput).waitUntilVisible();
		countryShippingInput.sendKeys(countryShipping);
	}
	
	public void clickOnTermAndConditions() {
		element(termsAndConditionsCheckbox).waitUntilVisible();
		termsAndConditionsCheckbox.click();
	}
	
	public void clickOnConfirm() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}
	
	
	
	
	
	
	
	
}

package com.pages.frontend.registration.contactBooster;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

public class ContactBoosterRegistrationPage extends AbstractPage {

	@FindBy(id = "firstname")
	private WebElement firstNameInput;

	@FindBy(id = "nachname")
	private WebElement lastNameInput;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "confirmation")
	private WebElement passwordConfirmInput;
	// no method
	@FindBy(id = "is_subscribed")
	private WebElement isSubscribedCheckbox;
	// no method
	@FindBy(id = "flag_stylist_parties")
	private WebElement stylePartiesCheckbox;
	// no method
	@FindBy(id = "flag_stylist_member")
	private WebElement styleMemberCheckbox;

	@FindBy(id = "terms")
	private WebElement termsCheckbox;

	@FindBy(id = "kostenlos-anmelden")
	private WebElement submitButton;

	// ---------------------------------------------------
	@FindBy(id = "street_1")
	private WebElement streetInput;

	@FindBy(id = "house_number")
	private WebElement streetNumberInput;

	@FindBy(id = "zip")
	private WebElement postCodeInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "registration-distribution-country")
	private WebElement countrySelect;

	@FindBy(id = "telephone")
	private WebElement telephoneInput;

	// ---------------------------------------------------

	public void checkIsSubscribedCheckbox() {
		element(isSubscribedCheckbox).waitUntilVisible();
		isSubscribedCheckbox.click();
	}

	public void checkStylePartiesCheckbox() {
		element(stylePartiesCheckbox).waitUntilVisible();
		stylePartiesCheckbox.click();
	}

	public void checkStyleMemberCheckbox() {
		element(styleMemberCheckbox).waitUntilVisible();
		styleMemberCheckbox.click();
	}

	public void firstNameInput(String firstName) {
		element(firstNameInput).waitUntilVisible();
		firstNameInput.sendKeys(firstName);
	}

	public void lastNameInput(String lastName) {
		element(lastNameInput).waitUntilVisible();
		lastNameInput.sendKeys(lastName);
	}

	public void emailInput(String email) {
		element(emailInput).waitUntilVisible();
		emailInput.sendKeys(email);
	}

	public void passwordInput(String password) {
		element(passwordInput).waitUntilVisible();
		passwordInput.sendKeys(password);
	}

	public void passwordConfirmInput(String password) {
		element(passwordConfirmInput).waitUntilVisible();
		passwordConfirmInput.sendKeys(password);
	}

	public void checkIAgree() {
		element(termsCheckbox).waitUntilVisible();
		termsCheckbox.click();
	}

	public void submitAndContinue() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}

	public void inputStreetAddress(String streetAddress) {
		element(streetInput).waitUntilVisible();
		streetInput.sendKeys(streetAddress);
	}

	public void inputStreetNumber(String streetNumber) {
		streetNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		postCodeInput.sendKeys(postCode);
	}

	public void inputHomeTown(String homeTown) {
		cityInput.sendKeys(homeTown);
	}

	public void selectCountryName(String countryName) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		telephoneInput.sendKeys(phoneNumber);
	}
}

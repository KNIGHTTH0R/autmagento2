package com.pages.frontend.registration.connectWithMe;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ConnectWithMeRegistrationPage extends AbstractPage {

	@FindBy(id = "frau")
	private WebElement frauRadioButton;

	@FindBy(id = "herr")
	private WebElement herrRadioButton;

	@FindBy(id = "firstname")
	private WebElement firstNameInput;

	@FindBy(id = "lastname")
	private WebElement lastNameInput;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "postcode")
	private WebElement plzInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "land")
	private WebElement countrySelect;

	@FindBy(id = "area_code")
	private WebElement telefoneCodeInput;

	@FindBy(id = "telephone")
	private WebElement telefoneInput;

	@FindBy(id = "flag_newsletter")
	private WebElement flagNewsletter;

	@FindBy(id = "flag_parties")
	private WebElement flagParties;

	@FindBy(id = "flagMember")
	private WebElement flagMember;

	@FindBy(id = "terms")
	private WebElement iAgreeCheckbox;

	@FindBy(css = "form#form-stylist-connect button[type='submit']")
	private WebElement okButton;

	public void selectGender(boolean isMale) {
		element(frauRadioButton).waitUntilVisible();
		if (isMale) {
			frauRadioButton.click();
		} else {
			herrRadioButton.click();
		}
	}

	public void inputFirstName(String firstName) {
		element(firstNameInput).waitUntilVisible();
		firstNameInput.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		element(lastNameInput).waitUntilVisible();
		lastNameInput.sendKeys(lastName);
	}

	public void inputEmail(String email) {
		element(emailInput).waitUntilVisible();
		emailInput.sendKeys(email);
	}

	public void inputCity(String city) {
		element(cityInput).waitUntilVisible();
		cityInput.sendKeys(city);
	}

	public void inputPostCode(String postCode) {
		element(plzInput).waitUntilVisible();
		plzInput.clear();
		plzInput.sendKeys(postCode);
	}

	public void selectCountry(String country) {
		element(countrySelect).waitUntilVisible();
		selectFromDropdown(countrySelect, country);
	}

	public void inputTelephoneAreaCode(String telCode) {
		element(telefoneCodeInput).waitUntilVisible();
		telefoneCodeInput.sendKeys(telCode);
	}

	public void inputTelephone(String telNr) {
		element(telefoneInput).waitUntilVisible();
		telefoneInput.sendKeys(telNr);
	}

	public void iAgreeCheckbox() {
		element(iAgreeCheckbox).waitUntilVisible();
		iAgreeCheckbox.click();
	}

	public void checkNewslletterCheckbox(boolean checked) {

		boolean isSelected = flagNewsletter.isSelected();

		if ((checked && !isSelected) || (!checked && isSelected))
			flagNewsletter.click();

	}

	public void checkPartiesCheckbox(boolean checked) {

		boolean isSelected = flagParties.isSelected();

		if ((checked && !isSelected) || (!checked && isSelected))
			flagParties.click();
	}

	public void checkMemberCheckbox(boolean checked) {

		boolean isSelected = flagMember.isSelected();

		if ((checked && !isSelected) || (!checked && isSelected))
			flagMember.click();
	}

	public void clickOk() {
		element(okButton).waitUntilVisible();
		okButton.click();
	}

}

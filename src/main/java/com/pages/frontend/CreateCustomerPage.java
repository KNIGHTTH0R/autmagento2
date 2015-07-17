package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class CreateCustomerPage extends AbstractPage {

	@FindBy(id = "firstname")
	private WebElement firstnameInput;

	@FindBy(id = "lastname")
	private WebElement lastnameInput;

	@FindBy(id = "email_address")
	private WebElement emailaddressInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "confirmation")
	private WebElement confirmationeInput;

	@FindBy(id = "invitation_email")
	private WebElement invitationEmailInput;

	@FindBy(id = "is_subscribed")
	private WebElement newsletterCheckbox;

	@FindBy(id = "flag_stylist_parties")
	private WebElement partiesCheckbox;

	@FindBy(id = "flag_stylist_member")
	private WebElement memberCheckbox;

	@FindBy(id = "accept-checkbox")
	private WebElement iAgreeCheckbox;

	@FindBy(css = "div.buttons-set.form-buttons.to-the-left button")
	private WebElement completeButton;

	// ---------------------------------------------------
	@FindBy(id = "street_1")
	private WebElement streetInput;

	@FindBy(id = "house_number")
	private WebElement streetNumberInput;

	@FindBy(id = "zip")
	private WebElement postCodeInput;

	@FindBy(id = "distribution_zip")
	private WebElement distributionZip;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "country")
	private WebElement countrySelect;

	@FindBy(id = "registration-distribution-country")
	private WebElement distributionCountry;

	@FindBy(id = "telephone")
	private WebElement telephoneInput;

	// ----------------------search SC

	@FindBy(id = "by_geoip")
	private WebElement searchStylistByGeoip;

	@FindBy(id = "search_postcode")
	private WebElement searchPostcode;

	@FindBy(id = "search_countryId")
	private WebElement searchCountry;

	@FindBy(css = "ul#stylist-list li:nth-child(1) div button")
	private WebElement firstStylistContainer;

	@FindBy(css = "button[name='search_by_geoip_submit']")
	private WebElement searchByGeoipSubmitButton;

	// ---------------------------------------------------

	public void inputFirstName(String firstName) {
		element(firstnameInput).waitUntilVisible();
		firstnameInput.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		element(lastnameInput).waitUntilVisible();
		lastnameInput.sendKeys(lastName);
	}

	public void inputEmail(String emailAddress) {
		element(emailaddressInput).waitUntilVisible();
		emailaddressInput.sendKeys(emailAddress);
	}

	public void inputPassword(String passText) {
		element(passwordInput).waitUntilVisible();
		passwordInput.sendKeys(passText);

		waitABit(TimeConstants.TIME_CONSTANT);
		passwordInput.clear();
		passwordInput.sendKeys(passText);

	}

	public void inputConfirmation(String passText) {
		element(confirmationeInput).waitUntilVisible();
		confirmationeInput.sendKeys(passText);
	}

	public void inputStylistEmail(String stylistEmail) {
		invitationEmailInput.sendKeys(stylistEmail);
	}

	public void checkParties() {
		partiesCheckbox.click();
		waitABit(1000);
	}

	public void checkMember() {
		memberCheckbox.click();
	}

	public void checkIAgree() {
		iAgreeCheckbox.click();
	}

	public void clickCompleteButton() {
		completeButton.click();
	}

	public void inputStreetAddress(String streetAddress) {
		element(streetInput).waitUntilVisible();
		streetInput.sendKeys(streetAddress);
	}

	public void inputStreetNumber(String streetNumber) {
		streetNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		postCodeInput.clear();
		postCodeInput.sendKeys(postCode);
		waitABit(1000);
	}

	public void inputPostCodeFromPersonalInfo(String postCode) {
		distributionZip.sendKeys(postCode);
	}

	public void inputHomeTown(String homeTown) {
		cityInput.sendKeys(homeTown);
	}

	public void selectCountryName(String countryName) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(countryName);
	}

	public void selectCountryNameFromPersonalInfo(String countryName) {
		element(distributionCountry).waitUntilVisible();
		element(distributionCountry).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		telephoneInput.sendKeys(phoneNumber);
	}

	// ---------------Sc search

	public void searchStylistByGeoip() {
		searchStylistByGeoip.click();
	}

	public void inputPostcodeFilter(String postcode) {
		searchPostcode.clear();
		searchPostcode.sendKeys(postcode);
	}

	public void selectCountryFilter(String countryName) {
		element(searchCountry).waitUntilVisible();
		element(searchCountry).selectByVisibleText(countryName);
	}

	public void selectFirstStylistFromList() {
		element(firstStylistContainer).waitUntilVisible();
		firstStylistContainer.click();
	}

	public void searchByGeoipSubmit() {
		element(searchByGeoipSubmitButton).waitUntilVisible();
		searchByGeoipSubmitButton.click();
		waitABit(2000);
	}

}

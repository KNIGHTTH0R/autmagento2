package com.pages.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

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

//	@FindBy(css = "div.buttons-set.form-buttons.to-the-left button[type='submit']")
	@FindBy(css = ".buttons-set.form-buttons.page-bottom button[type='Submit']")
	private WebElement completeButton;

	// ---------------------------------------------------
	@FindBy(id = "street_1")
	private WebElement streetInput;

	@FindBy(id = "house_number")
	private WebElement streetNumberInput;

	@FindBy(id = "zip")
	private WebElement postCodeInput;

	@FindBy(id = "distribution_postcode")
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

	@FindBy(id = "by_sc_name")
	private WebElement searchStylistByName;

	@FindBy(id = "search_firstname")
	private WebElement searchFirstNameInput;

	@FindBy(id = "search_lastname")
	private WebElement searchLastNameInput;

	@FindBy(id = "search_postcode")
	private WebElement searchPostcode;

	@FindBy(id = "search_countryId")
	private WebElement searchCountry;

	@FindBy(id = "sc_name_result")
	private WebElement styleCoachNameResult;

	@FindBy(css = "ul#stylist-list li:nth-child(1) div button")
	private WebElement firstStylistContainer;

	@FindBy(css = "button[name='search_by_geoip_submit']")
	private WebElement searchByGeoipSubmitButton;

	@FindBy(css = "button[name='search_by_name_submit']")
	private WebElement searchByNameSubmitButton;

	@FindBy(id = "advice-validate-length-zip")
	private WebElement zipValidationMessage;

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
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void checkMember() {
		memberCheckbox.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void checkIAgree() {
		iAgreeCheckbox.click();
	}

	public void clickCompleteButton() {
		completeButton.click();
	}

	public void inputStreetAddress(String streetAddress) {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(streetInput).waitUntilVisible();
		streetInput.sendKeys(streetAddress);
	}

	public void inputStreetNumber(String streetNumber) {
		streetNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		postCodeInput.clear();
		element(postCodeInput).typeAndTab(postCode);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void inputPostCodeAndValdiateErrorMessage(String postCode) {
		element(postCodeInput).waitUntilVisible();
		postCodeInput.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(postCodeInput).typeAndTab(postCode);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		validateZipValidationErrorMessage();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void inputPostCodeFromPersonalInfo(String postCode) {
		distributionZip.clear();
		element(distributionZip).typeAndTab(postCode);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
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
		telephoneInput.clear();
		telephoneInput.sendKeys(phoneNumber);
	}

	// ---------------Sc search

	public void searchStylistByGeoip() {
		searchStylistByGeoip.click();
	}

	public void inputPostcodeFilter(String postcode) {
		searchPostcode.clear();
		element(searchPostcode).typeAndTab(postcode);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void selectCountryFilter(String countryName) {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(searchCountry).waitUntilVisible();
		element(searchCountry).selectByVisibleText(countryName);
	}

	public void searchStylistByName() {
		searchStylistByName.click();
	}

	public void inputSearchFirstName(String postcode) {
		searchFirstNameInput.sendKeys(postcode);
	}

	public void inputSearchLastName(String postcode) {
		searchLastNameInput.sendKeys(postcode);
	}

	public void selectFirstStylistFromList() {
		element(firstStylistContainer).waitUntilVisible();
		firstStylistContainer.click();
	}

	public List<DykscSeachModel> getFoundStylecoachesData() {

		List<DykscSeachModel> resultList = new ArrayList<DykscSeachModel>();

		List<WebElement> foundStylecoaches = getDriver().findElements(By.cssSelector("ul#stylist-list li"));

		for (WebElement stylecoach : foundStylecoaches) {

			DykscSeachModel model = new DykscSeachModel();

			model.setId(stylecoach.getAttribute("rel"));
			model.setName(stylecoach.findElement(By.cssSelector("h4.sc-id span")).getText());
			resultList.add(model);

		}

		return resultList;

	}

	public void searchByGeoipSubmit() {
		element(searchByGeoipSubmitButton).waitUntilVisible();
		searchByGeoipSubmitButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		waitABit(1000);
	}

	public void searchByNameSubmit() {
		element(searchByNameSubmitButton).waitUntilVisible();
		searchByNameSubmitButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		waitABit(1000);
	}

	public boolean isStylecoachFound() {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(styleCoachNameResult).waitUntilVisible();
		return !styleCoachNameResult.getText().contains(ContextConstants.NO_SC_FOUND_BY_GEOIP);

	}

	public void validateZipValidationErrorMessage() {
		element(zipValidationMessage).waitUntilVisible();
		Assert.assertTrue("The message from validation message is not the expected one!!",
				zipValidationMessage.getText().contains(ContextConstants.PLZ_ERROR_MESSAGE));
	}

}

package com.pages.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.tools.CustomVerification;
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

	@FindBy(css = ".psc-mail")
	private WebElement stylistEmail;

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

	// @FindBy(css = "div.buttons-set.form-buttons.to-the-left
	// button[type='submit']")
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

	@FindBy(css = "div[id*='advice-validate-zip']")
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
		waitABit(2000);
		element(partiesCheckbox).waitUntilVisible();
		//partiesCheckbox.click();
		clickElement(partiesCheckbox);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void checkMember() {
		memberCheckbox.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void checkIAgree() {
		clickElement(iAgreeCheckbox);
		//iAgreeCheckbox.click();
	}

	public void clickCompleteButton() {
		clickElement(completeButton);
	//	completeButton.click();
	}

	public void inputStreetAddress(String streetAddress) {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(streetInput).waitUntilVisible();
		////
		clickElement(streetInput);
		streetInput.sendKeys(streetAddress);
	}

	public void inputStreetNumber(String streetNumber) {
		clickElement(streetNumberInput);
		streetNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		clickElement(postCodeInput);
		postCodeInput.clear();
		element(postCodeInput).typeAndTab(postCode);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
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
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void inputHomeTown(String homeTown) {
		clickElement(cityInput);
		cityInput.sendKeys(homeTown);
	}

	public void selectCountryName(String countryName) {
		element(countrySelect).waitUntilVisible();
	//	focusElement("#country");
//	/	element(countrySelect).selectByVisibleText(countryName);
		
		
		
		clickElement(countrySelect);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(countrySelect).perform();
		
		Select oSelect = new Select(getDriver().findElement(By.id("country")));

		oSelect.selectByVisibleText(countryName);
	}

	public void selectCountryNameFromPersonalInfo(String countryName) {
		element(distributionCountry).waitUntilVisible();
		element(distributionCountry).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		clickElement(telephoneInput);
		telephoneInput.clear();
		telephoneInput.sendKeys(phoneNumber);
	}

	// ---------------Sc search

	public void searchStylistByGeoip() {
		clickElement(searchStylistByGeoip);
	//	searchStylistByGeoip.click();
	}

	public void inputPostcodeFilter(String postcode) {
		clickElement(searchPostcode);
		searchPostcode.clear();
		element(searchPostcode).typeAndTab(postcode);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
	}

	public void selectCountryFilter(String countryName) {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(searchCountry).waitUntilVisible();
		clickElement(searchCountry);
		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(countrySelect).perform();
		
		Select oSelect = new Select(getDriver().findElement(By.id("country")));

		oSelect.selectByVisibleText(countryName);
		
		//element(searchCountry).selectByVisibleText(countryName);
	}
	
	public void selectCountryFilterLead(String countryName) {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		waitABit(4000);
		Select oSelect = new Select(getDriver().findElement(By.id("search_countryId")));
		oSelect.selectByVisibleText(countryName);
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
		clickElement(firstStylistContainer);
	//	firstStylistContainer.click();
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
	//	searchByGeoipSubmitButton.click();
		clickElement(searchByGeoipSubmitButton);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		waitABit(1000);
	}

	public void searchByNameSubmit() {
		element(searchByNameSubmitButton).waitUntilVisible();
		searchByNameSubmitButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		waitABit(1000);
	}

	public boolean isStylecoachFound() {
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(styleCoachNameResult).waitUntilVisible();
		return !styleCoachNameResult.getText().contains(ContextConstants.NO_SC_FOUND_BY_GEOIP);

	}

	public void validateZipValidationErrorMessage() {
		element(zipValidationMessage).waitUntilVisible();
		Assert.assertTrue("The message from validation message is not the expected one!!",
				zipValidationMessage.getText().contains(ContextConstants.PLZ_ERROR_MESSAGE));
	}

	public void verifyFBUserEmailPrefield(String fbEmail) {
		element(emailaddressInput).waitUntilVisible();
		String grabbedEmail = emailaddressInput.getAttribute("value");
		CustomVerification.verifyTrue("Failure: The fb email input is not prefield correctly" + "grabbed: "
				+ grabbedEmail + "expected: " + fbEmail, grabbedEmail.contentEquals(fbEmail));

	}

	public void verifyStylistEmail(String stylistEmail2) {
		element(stylistEmail).waitUntilVisible();
		String grabbedStylistEmail = stylistEmail.getText();
		System.out.println("grabbedStylistEmail: "+grabbedStylistEmail );
		CustomVerification.verifyTrue("Failure: The expected stylist is not preselected" + "expected: " + stylistEmail2
				+ "grabbed: " + grabbedStylistEmail, grabbedStylistEmail.contains(stylistEmail2));

	}

}

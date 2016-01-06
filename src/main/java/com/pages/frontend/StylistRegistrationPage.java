package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;
import com.tools.datahandlers.stylistRegistration.StylistRegDataGrabber;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class StylistRegistrationPage extends AbstractPage {

	@FindBy(id = "toggle_cctab")
	private WebElement expandCredtCardButton;

	@FindBy(id = "firstname")
	private WebElement firstnameInput;

	@FindBy(css = ".bordered-box.pitch.clearfix.starterkit-wrapper-inactive input")
	private WebElement starterKit;

	@FindBy(id = "lastname")
	private WebElement lastnameInput;

	@FindBy(id = "dateofbirth-dp")
	private WebElement dob;

	@FindBy(css = ".ui-datepicker-month")
	private WebElement monthPicker;

	@FindBy(css = ".ui-datepicker-year")
	private WebElement yearPicker;

	@FindBy(css = "tbody tr td")
	private WebElement daypicker;

	@FindBy(id = "email_address")
	private WebElement emailaddressInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "confirmation")
	private WebElement confirmationeInput;

	@FindBy(id = "invitation_email")
	private WebElement invitationEmailInput;

	@FindBy(id = "flag_stylist_parties")
	private WebElement partiesCheckbox;

	@FindBy(id = "flag_stylist_member")
	private WebElement memberCheckbox;

	@FindBy(id = "accept-checkbox")
	private WebElement iAgreeCheckbox;

	@FindBy(id = "leave_master")
	private WebElement noCoachCheckbox;

	@FindBy(id = "by_default")
	private WebElement noInviteCheckbox;

	// @FindBy(css = "button[title*='Senden']")
	@FindBy(css = "div.buttons-set.form-buttons.to-the-left button")
	// int
	private WebElement completeButton;

	@FindBy(id = "submit-step")
	private WebElement submitStepButton;

	@FindBy(id = "submit_prepaid")
	private WebElement weiter;

	@FindBy(css = "input#submit_cc")
	private WebElement submitCC;

	@FindBy(id = "toggle_cctab")
	private WebElement hinzufugen;

	@FindBy(id = "placeYourOrder_bottom")
	private WebElement submitPaymentMethod;

	@FindBy(id = "continueShopping_top")
	private WebElement finishPayment;

	@FindBy(css = "ul.messages li ul li span")
	private WebElement existingAccountMessageContainer;

	@FindBy(css = "li.error-msg ul li span")
	private WebElement contextErrorMessageContainer;

	@FindBy(css = "div.page-title h1")
	private WebElement stylistRegisterPageTitleContainer;

	// ---------------------------------------------------
	@FindBy(id = "street_1")
	private WebElement streetInput;

	@FindBy(id = "house_number")
	private WebElement streetNumberInput;

	@FindBy(id = "zip")
	private WebElement postCodeInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "country")
	private WebElement countrySelect;

	@FindBy(id = "telephone")
	private WebElement telephoneInput;

	@FindBy(id = "stylistref")
	private WebElement stylistref;

	@FindBy(id = "fancybox-outer")
	private WebElement infoBox;

	@FindBy(id = "fancybox-close")
	private WebElement closeInfoBox;

	@FindBy(id = "advice-validate-length-zip")
	private WebElement zipValidationMessage;

	// ---------------------------------------------------

	@FindBy(css = "select#addCreditCardIssuer")
	private WebElement cartTypeSelect;

	@FindBy(id = "addCreditCardNumber")
	private WebElement creditCardNumberInput;

	@FindBy(css = "select#addCreditCardMonth")
	private WebElement creditCardMonthInput;

	@FindBy(css = "select#addCreditCardYear")
	private WebElement creditCardYearInput;

	@FindBy(css = "input#addCreditCardCVV")
	private WebElement creditCardCVVInput;

	@FindBy(css = "input[name='pin']")
	private WebElement pinInput;

	@FindBy(css = "a[href='javascript:submiteCip()']")
	private WebElement submitFinalVisaStep;

	// ----------------------------------------------------

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
		passwordInput.clear();
		passwordInput.sendKeys(passText);
	}

	public void inputConfirmation(String passText) {
		element(confirmationeInput).waitUntilVisible();
		confirmationeInput.sendKeys(passText);
	}

	public void inputStylistRef(String ref) {
		element(stylistref).waitUntilVisible();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		stylistref.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(stylistref).sendKeys(ref);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void inputStylistEmail(String stylistEmail) {
		invitationEmailInput.sendKeys(stylistEmail);
	}

	public void selectCardType(String cardType) {
		waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("payco-iframe-transaction")));
		element(cartTypeSelect).waitUntilVisible();
		element(cartTypeSelect).selectByVisibleText(cardType);
	}

	public void selectCardTypeEs(String cardType) {
		element(cartTypeSelect).waitUntilVisible();
		element(cartTypeSelect).selectByVisibleText(cardType);
	}

	public void inputCardCvv(String cvv) {
		element(creditCardCVVInput).waitUntilVisible();
		element(creditCardCVVInput).sendKeys(cvv);
	}

	public void inputCardPin(String pin) {
		element(pinInput).waitUntilVisible();
		element(pinInput).sendKeys(pin);
	}

	public void inputCardNumber(String cardNumber) {
		element(creditCardNumberInput).waitUntilVisible();
		element(creditCardNumberInput).sendKeys(cardNumber);
	}

	public void inputCardExpiryMonth(String month) {
		element(creditCardMonthInput).waitUntilVisible();
		element(creditCardMonthInput).selectByValue(month);
	}

	public void inputCardExpiryYear(String year) {
		element(creditCardYearInput).waitUntilVisible();
		element(creditCardYearInput).selectByValue(year);
		waitABit(2000);
	}

	public void submitCreditCard() {
		element(submitCC).waitUntilVisible();
		submitCC.click();
	}

	public void submitVisaFinalStep() {
		element(submitFinalVisaStep).waitUntilVisible();
		submitFinalVisaStep.click();
	}

	public void checkParties() {
		partiesCheckbox.click();
	}

	public void checkMember() {
		memberCheckbox.click();
	}

	public void checkNoInvite() {
		noInviteCheckbox.click();
	}

	public void checkIAgree() {
		iAgreeCheckbox.click();
	}

	public void checkNoCoachCheckbox() {
		noCoachCheckbox.click();
	}

	public void clickCompleteButton() {
		completeButton.click();
	}

	public void clickDob() {
		dob.click();
	}

	public void closeInfoBox() {
		closeInfoBox.click();
	}

	public void expandCreditCardForm() {
		waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("payco-iframe-transaction")));
		element(expandCredtCardButton).waitUntilVisible();
		expandCredtCardButton.click();
	}

	public void clickOnNachahmePaymentMethod() {
		waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("payco-iframe-transaction")));
		element(weiter).waitUntilVisible();
		weiter.click();
	}

	public void clickOnKreditkartePaymentMethod() {
		waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("payco-iframe-transaction")));
		element(hinzufugen).waitUntilVisible();
		hinzufugen.click();
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

	public void inputPostCodeAndValdiateErrorMessage(String postCode) {
		element(postCodeInput).waitUntilVisible();
		postCodeInput.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(postCodeInput).typeAndTab(postCode);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		validateZipValidationErrorMessage();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void inputContextCodeAndValdiateErrorMessage(String postCode) {

		element(stylistref).waitUntilVisible();
		stylistref.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(stylistref).typeAndEnter(postCode);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.WAIT_TIME_SMALL);
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

	public void submitStep() {
		element(submitStepButton).waitUntilVisible();
		submitStepButton.click();
	}

	public void submitPaymentMethod() {
		submitPaymentMethod.click();
	}

	public void finishPayment() {
		finishPayment.click();
	}

	public void selectStarterKit() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2941");
	}

	public StylistRegistrationCartTotalModel grabCartTotal() {

		StylistRegistrationCartTotalModel result = new StylistRegistrationCartTotalModel();

		result.setDelivery(FormatterUtils.cleanString(getDriver().findElement(By.cssSelector("#shipping-value")).getText()));
		result.setTotalPrice(FormatterUtils.cleanString(getDriver().findElement(By.cssSelector("#total-price-value")).getText()));

		StylistRegDataGrabber.cartTotals = result;

		return result;

	}

	public static void verifyCartTotals() {

	}

	public void selectMonth(String month) {
		element(monthPicker).waitUntilVisible();
		element(monthPicker).selectByVisibleText(month);
	}

	public void selectYear(String year) {
		element(yearPicker).waitUntilVisible();
		element(yearPicker).selectByVisibleText(year);
	}

	public void selectDay(String day) {
		element(daypicker).waitUntilVisible();
		List<WebElement> daysList = getDriver().findElements(By.cssSelector("tbody tr td"));
		boolean found = false;
		for (WebElement currentDay : daysList) {
			if (currentDay.getText().contentEquals(day)) {
				found = true;
				currentDay.click();
			}
		}
		Assert.assertTrue("Day was not found", found);
	}

	public void clickLoginLinkFromMessage() {
		if (existingAccountMessageContainer.getText().contentEquals(ContextConstants.EXISTING_ACCOUNT_MESSAGE)) {
			existingAccountMessageContainer.findElement(By.cssSelector("a")).click();
		} else {
			Assert.assertFalse("The message and the link were not found", true);
		}
	}

	public String getStylistRegisterPageTitle() {
		return stylistRegisterPageTitleContainer.getText();
	}

	public void validateInfoBoxMessage() {
		element(infoBox).waitUntilVisible();
		Assert.assertTrue("The message from infobox is not the expected one!!", infoBox.getText().contains(ContextConstants.CHANGE_WEBSITE_POPUP_MESSAGE));
	}

	public void validateZipValidationErrorMessage() {
		element(zipValidationMessage).waitUntilVisible();
		Assert.assertTrue("The message from validation message is not the expected one!!", zipValidationMessage.getText().contains(ContextConstants.PLZ_ERROR_MESSAGE));
	}

	public void validateContextValidationErrorMessage() {
		element(contextErrorMessageContainer).waitUntilVisible();
		Assert.assertTrue("The message from context validation message is not the expected one!!", element(contextErrorMessageContainer).isDisplayed());
	}
}

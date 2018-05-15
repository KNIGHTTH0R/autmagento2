package com.pages.frontend.registration.party;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class CreateNewContactPage extends AbstractPage {

	@FindBy(css = "#addContactForm input[name*='firstname']")
	private WebElement firstname;

	@FindBy(css = "#addContactForm input[name*='lastname']")
	private WebElement lastname;

	@FindBy(css = "div.input-box input[name*='email']")
	private WebElement emailInput;

	@FindBy(css = "#addContactForm input[name*='postcode']")
	private WebElement postcode;

	@FindBy(css = "#addContactForm input[name*='city']")
	private WebElement cityInput;

	@FindBy(id = "land")
	private WebElement countrySelect;

	@FindBy(id = "telephone")
	private WebElement telephoneInput;

	@FindBy(id = "flag_newsletter")
	private WebElement flagNewsletter;

	@FindBy(id = "flag_parties")
	private WebElement flagParties;

	@FindBy(id = "flag_member")
	private WebElement flagMember;

	
	@FindBy(id = "street_1")
	private WebElement streetInput;

	@FindBy(css = "div.input-box.address-street input[name*='house_number']")
	private WebElement houseNumberInput;

	@FindBy(css = "#addContactForm button[type*='submit']")
	private WebElement sumbitContact;
	
	@FindBy(css = "div[id*='advice-validate-zip']")
	private WebElement zipValidationMessage;
	
	@FindBy(css = ".button-container a:nth-child(1)")
	private WebElement oneHundrederButton;
	
	
	@FindBy(css = "#mass-contact-form input[name*='firstname']")
	private WebElement firstname100er;

	@FindBy(css = "#mass-contact-form input[name*='lastname']")
	private WebElement lastname100er;
	
	@FindBy(css = "#mass-contact-form input[name*='email']")
	private WebElement email100er;
	
	@FindBy(css = "#mass-contact-form input[name*='telephone']")
	private WebElement telehone100er;
	
	@FindBy(css = ".tagit-new input[type*='text']")
	private WebElement tagName100er;
	
	@FindBy(css = ".ui-menu-item a")
	private WebElement selectTag;
	
	@FindBy(id = "sc-wannabe")
	private WebElement flagMember100er;
	
	@FindBy(id = "host-wannabe")
	private WebElement flagParties100er;
	
	@FindBy(css = ".form-footer button[type*='submit']")
	private WebElement sumbitContact100er;
	
	@FindBy(css = ".buttons-set.page-bottom a")
	private WebElement clickBackToContactList;
	
	@FindBy(css = "#btn-leave-yes")
	private WebElement leavePage;
	
	
	
	public void firstnameInput(String name) {
		element(firstname).waitUntilVisible();
		firstname.sendKeys(name);
	}

	public void skipFirstnameInput(String name) {
		evaluateJavascript("document.getElementsByName('firstname').className = 'input-text';");
	}

	public void lastnameInput(String name) {
		element(lastname).waitUntilVisible();
		lastname.sendKeys(name);
	}

	public void emailInput(String email) {
		element(emailInput).waitUntilVisible();
		emailInput.sendKeys(email);
	}

	public void streetInput(String street) {
		element(streetInput).waitUntilVisible();
		streetInput.sendKeys(street);
	}

	public void houseNumberInput(String number) {
		element(houseNumberInput).waitUntilVisible();
		houseNumberInput.sendKeys(number);
	}

	public void postcodeInput(String code) {
		element(postcode).waitUntilVisible();
		postcode.sendKeys(code);
	}

	public void inputPostCodeAndValdiateErrorMessage(String postCode) {
		element(postcode).waitUntilVisible();
		postcode.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(postcode).typeAndTab(postCode);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		validateZipValidationErrorMessage();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void cityInput(String city) {
		element(cityInput).waitUntilVisible();
		cityInput.sendKeys(city);
	}

	public void selectCountryName(String countryName) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(countryName);
	}

	public void inputPhoneNumber(String phoneNumber) {
		telephoneInput.sendKeys(phoneNumber);
	}

	public void checkNewsletter() {
		element(flagNewsletter).waitUntilVisible();
		flagNewsletter.click();
	}

	public void checkParties() {
		element(flagParties).waitUntilVisible();
		flagParties.click();
	}

	public void checkMember() {
		element(flagMember).waitUntilVisible();
		flagMember.click();
	}

	public void submitContact() {
		element(sumbitContact).waitUntilVisible();
		sumbitContact.click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 120);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	
	//	waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		
		waitABit(TimeConstants.TIME_CONSTANT);
		
		
		
	}

	public void validateZipValidationErrorMessage() {
		element(zipValidationMessage).waitUntilVisible();
		Assert.assertTrue("The message from validation message is not the expected one!!", zipValidationMessage.getText().contains(ContextConstants.PLZ_ERROR_MESSAGE));
	}

	public void clickOn100erButton() {
		element(oneHundrederButton).waitUntilVisible();
		oneHundrederButton.click();
	}

	public void firstnameInput100er(String firstName) {
		element(firstname100er).waitUntilVisible();
		firstname100er.sendKeys(firstName);
	}

	public void lastnameInput100er(String lastName) {
		element(lastname100er).waitUntilVisible();
		lastname100er.sendKeys(lastName);		
	}

	public void emailInput100er(String emailName) {
		element(email100er).waitUntilVisible();
		email100er.sendKeys(emailName);		
	}

	public void checkParties100er() {
		element(flagParties100er).waitUntilVisible();
		flagParties100er.click();	
	}

	public void checkMember100er() {
		element(flagMember100er).waitUntilVisible();
		flagMember100er.click();		
	}

	public void tagName100er(String tag) {
		element(tagName100er).waitUntilVisible();
		tagName100er.sendKeys(tag);
		List<WebElement> listtab=getDriver().findElements(By.cssSelector(".ui-menu-item a"));
		if(!listtab.isEmpty()){
			listtab.get(0).click();
		}
	}

	public void submitContact100er() {
		element(sumbitContact100er).waitUntilVisible();
		sumbitContact100er.click();
		
		
	//	wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	
	//	waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		
		waitABit(TimeConstants.TIME_CONSTANT);		
	}

	
	public void clickBackToContactList() {
		element(clickBackToContactList).waitUntilVisible();
		clickBackToContactList.click();
		
		waitABit(TimeConstants.TIME_CONSTANT);		
	}

	public void clickLeaveYhePage() {
		element(leavePage).waitUntilVisible();
		leavePage.click();
		waitABit(TimeConstants.TIME_CONSTANT);		
	}

	
	
	public void inputPhoneNumber100er(String phoneNumber) {
		element(telehone100er).waitUntilVisible();
		telehone100er.sendKeys(phoneNumber);		
	}

}

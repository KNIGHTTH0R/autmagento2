package com.pages.frontend.registration.party;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

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
		WebDriverWait wait = new WebDriverWait(getDriver(),60);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.TIME_CONSTANT);
	}

}

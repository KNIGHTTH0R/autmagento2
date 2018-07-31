package com.pages.frontend;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class CreateUserPage extends AbstractPage {

	@FindBy(id = "firstname")
	private WebElement firstnameInput;

	@FindBy(id = "lastname")
	private WebElement lastnameInput;

	@FindBy(id = "is_subscribed")
	private WebElement newsletterCheckbox;

	@FindBy(id = "email_address")
	private WebElement emailaddressInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "password-confirmation")
	private WebElement passwordConfirmationeInput;

	@FindBy(css = ".action.submit.primary")
	private WebElement createAccount;

	// ---------------------------------------------------
	public void inputFirstName(String firstName) {
		element(firstnameInput).waitUntilVisible();
		firstnameInput.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		element(lastnameInput).waitUntilVisible();
		lastnameInput.sendKeys(lastName);
	}

	public void checkNewsletter() {
		element(newsletterCheckbox).waitUntilVisible();
		newsletterCheckbox.click();
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

	public void inputPasswordConfirmation(String passText) {
		element(passwordConfirmationeInput).waitUntilVisible();
		passwordConfirmationeInput.sendKeys(passText);
	}

	public void clickOnCreateAnAccount() {
		element(createAccount).waitUntilVisible();
		createAccount.click();
	}

}

package com.pages.frontend.registration.widget;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

public class RegisterLandingPage extends AbstractPage {

	@FindBy(id = "member-code")
	private WebElement memberCodeInput;

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

	public void memberCodeInput(String code) {
		element(memberCodeInput).waitUntilVisible();
		memberCodeInput.sendKeys(code);
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
}

package com.pages.external.mailchimp;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MailchimpLoginPage extends AbstractPage {

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(css = "button[type='submit']")
	private WebElement loginButton;

	public void enterUsername(String username) {
		element(usernameInput).waitUntilVisible();
		element(usernameInput).sendKeys(username);
	}

	public void enterPassword(String username) {
		element(passwordInput).waitUntilVisible();
		element(passwordInput).sendKeys(username);
	}

	public void submitLogin() {
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

}

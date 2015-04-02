package com.pages.frontend.registration.landing;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

public class ThankYouPage extends AbstractPage {

	@FindBy(id = "thankyou-register")
	private WebElement thankYouForm;

	@FindBy(css = "input[name='email']")
	private WebElement emailField;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(name = "confirmation")
	private WebElement passwordConfirmInput;

	@FindBy(id = "accept-checkbox")
	private WebElement iAgreeCheckbox;

	@FindBy(id = "thankyou-submit")
	private WebElement submitButton;

	public String pageSource() {
		return getDriver().getPageSource();
	}

	public String pageTitle() {
		return getDriver().getTitle();
	}

	public void passwordInput(String password) {
		element(thankYouForm).waitUntilVisible();
		passwordInput.sendKeys(password);
		passwordConfirmInput.sendKeys(password);
	}

	public void checkIAgree() {
		element(iAgreeCheckbox).waitUntilVisible();
		iAgreeCheckbox.click();
	}

	public void submitButton() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}

	public String getEmailText() {
		element(emailField).waitUntilVisible();
		return emailField.getAttribute("value");
	}

}

package com.pages.frontend;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

public class LoginPage extends AbstractPage {

	@FindBy(id = "email")
	private WebElement userInput;

	@FindBy(id = "pass")
	private WebElement passInput;

	@FindBy(id = "send2")
	private WebElement loginButton;

	@FindBy(css = "button[rel='facebook-connect']")
	private WebElement facebookLoginButton;

	@FindBy(css = ".col-2.new-users p a")
	private WebElement stylistRegistrationlink;

	@FindBy(css = "button.button.left")
	private WebElement customerRegistrationButton;

	public void inputUserName(String userName) {
		element(userInput).waitUntilVisible();
		userInput.sendKeys(userName);
	}

	public void inputUserPass(String userPass) {
		element(passInput).waitUntilVisible();
		passInput.sendKeys(userPass);
	}

	public void clickOnLoginButton() {
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

	public void clickGoToCustomerRegistration() {
		element(customerRegistrationButton).waitUntilVisible();
		customerRegistrationButton.click();
	}

	public void clickOnFacebookSignIn() {
		element(facebookLoginButton).waitUntilVisible();
		facebookLoginButton.click();
	}

	public void clickOnStylistRegistrationLink() {
		element(stylistRegistrationlink).waitUntilVisible();
		stylistRegistrationlink.click();
	}

}

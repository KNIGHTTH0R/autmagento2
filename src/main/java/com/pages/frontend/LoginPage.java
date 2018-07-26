package com.pages.frontend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class LoginPage extends AbstractPage {

	@FindBy(id = "email")
	private WebElement userInput;

	@FindBy(id = "pass")
	private WebElement passInput;

	@FindBy(id = "send2")
	private WebElement loginButton;
	
	@FindBy(id = "rememberme")
	private WebElement rememberMeButton;

	@FindBy(css = "button[rel='facebook-connect']")
	private WebElement facebookLoginButton;
	
	@FindBy(css = ".fieldset button[rel='facebook-connect']:nth-child(1)")
	private WebElement facebookRegistrationButton;

	@FindBy(css = ".col-2.new-users p a")
	private WebElement stylistRegistrationlink;

	@FindBy(css = "button.button.left")
	private WebElement customerRegistrationButton;
	
	@FindBy(css = "button[onclick*='allowSaveCookie()']")
	private WebElement cookieConsent;

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

	public void clickOnFacebookRegistrationButton() {
		element(facebookRegistrationButton).waitUntilVisible();
		facebookRegistrationButton.click();
		
	}

	public void acceptCookieConsent() {
		List<WebElement> cookie= getDriver().findElements(By.cssSelector("button[onclick*='allowSaveCookie()']"));
		if(!cookie.isEmpty()) {
			element(cookieConsent).waitUntilVisible();
			cookieConsent.click();
		}
		
	}

}

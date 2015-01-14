package com.pages.frontend;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

public class LoginPage extends AbstractPage {

	@FindBy(id = "email")
	private WebElement userInput;

	@FindBy(id = "pass")
	private WebElement passInput;

	@FindBy(id = "send2")
	private WebElement loginButton;

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

}

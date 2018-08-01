
package com.pages.backend;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class MagentoLoginPage extends AbstractPage {

	@FindBy(id = "username")
	private WebElement userNameInput;

	@FindBy(id = "login")
	private WebElement userPassInput;

	@FindBy(css = ".action-login.action-primary")
	private WebElement loginButton;

	public void inputUserName(String userName) {
		element(userNameInput).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		userNameInput.sendKeys(userName);
	}

	public void inputUserPassword(String userPass) {
		element(userPassInput).waitUntilVisible();
		userPassInput.sendKeys(userPass);
	}

	public void clickOnLogin(){
		evaluateJavascript("jQuery.noConflict();");
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

}

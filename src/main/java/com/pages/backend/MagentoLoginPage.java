
package com.pages.backend;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;

public class MagentoLoginPage extends AbstractPage {

	@FindBy(id = "username")
	private WebElement userNameInput;

	@FindBy(id = "login")
	private WebElement userPassInput;

	@FindBy(css = "input[value*='Anmelden']")
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

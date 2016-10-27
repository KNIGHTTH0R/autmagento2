package com.pages.backend;


import java.util.ArrayList;


import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tools.requirements.AbstractPage;

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

	public void clickOnLogin() {
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

}

package com.pages.external.academy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class LoginAcademyPage extends AbstractPage{
	@FindBy(id = "button-magento-sql")
	private WebElement pjAccountBtn;
	
	@FindBy(id = "username")
	private WebElement inputUserName;

	@FindBy(id = "password")
	private WebElement inputPassword;
	
	@FindBy(css = ".btn.btn-submit")
	private WebElement submitBtn;
	
	public void clickLoginWithPippajeanAccount() {
		element(pjAccountBtn).waitUntilVisible();
		pjAccountBtn.click();
		
	}

	public void inputEmail(String name) {
		element(inputUserName).waitUntilVisible();
		inputUserName.sendKeys(name);
	}

	public void inputPassword(String password) {
		element(inputUserName).waitUntilVisible();
		inputPassword.sendKeys(password);
		
	}
	
	public void clickLoginButton() {
		element(submitBtn).waitUntilVisible();
		submitBtn.click();
		
	}
	
}

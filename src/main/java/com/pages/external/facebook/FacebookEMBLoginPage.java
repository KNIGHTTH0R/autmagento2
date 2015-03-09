package com.pages.external.facebook;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

/**
 * This page will contain mapping from the pop-up facebook login window.
 * @author voicu.vac
 *
 */
public class FacebookEMBLoginPage extends AbstractPage {

	@FindBy(id = "email")
	private WebElement userNameInput;
	
	@FindBy(id = "pass")
	private WebElement userPassInput;
	
	@FindBy(id = "loginbutton")
	private WebElement loginButton;

	public void inputUser(String user){
		element(userNameInput).waitUntilVisible();
		userNameInput.sendKeys(user);
	}
	
	public void inputPass(String pass){
		element(userPassInput).waitUntilVisible();
		userPassInput.sendKeys(pass);
	}
	
	public void clickLogin(){
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

}

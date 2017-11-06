package com.pages.frontend;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class SingleSignOnPage extends AbstractPage {

//	@FindBy(css = "div.col-main")
//	private WebElement textContainer;
	
	@FindBy(css = "section.col-main")
	private WebElement textContainer;
	
	@FindBy(css=".header .quick-wrap .switcher-header li span")
	private WebElement storeViewMessage;
	
	@FindBy(css="#primary .course-container span")
	private WebElement academyHomePage;
	
	@FindBy(css=".login-form .head h1")
	private WebElement academyLoginPage;
	
	
	
	
	public void verifyLink() {
		Assert.assertTrue("Failure: URL not redirected to success page. ",getDriver().getCurrentUrl().contains("registersuccess"));
	}

	public void verifyText() {
		waitForPageToLoad();
		element(textContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", textContainer.getText().contains(ContextConstants.CREATE_ACCOUNT_SUCCESS_MESSAGE));
	}

	public void validateShopLoginWebsite(String website) {
		System.out.println("url: "+getDriver().getCurrentUrl());
		Assert.assertTrue("Failure: The Website is not correct ",getDriver().getCurrentUrl().contains(website));
		
	}
	

	public void validateLoggedInAcademy() {
		waitForPageToLoad();
		element(academyHomePage).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", academyHomePage.getText().contains("Online Style Party Training"));
	}

	public void vaslidateShopLoginStoreView(String storeView) {
		element(storeViewMessage).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewMessage.getText().toLowerCase().contains(storeView) );
	}

	public void validateContext(String context) {
		Assert.assertTrue("Failure: The Context is not displayed correctly ",getDriver().getCurrentUrl().contains(context));
	}

	public void validateLoggedOutFromAcademy() {
		// TODO Auto-generated method stub
		waitForPageToLoad();
		element(academyLoginPage).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", academyLoginPage.getText().contains("Login"));
	}
}

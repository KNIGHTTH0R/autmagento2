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
	
	@FindBy(css=".columns.thicker li span")
	private WebElement websiteFlag;
	
	
	
	@FindBy(css=".header .quick-wrap .message")
	private WebElement headerMessage;
	
	@FindBy(css="#primary .course-container span")
	private WebElement academyHomePage;
	
	@FindBy(css=".login-form .head h1")
	private WebElement academyLoginPage;
	
	@FindBy(css=".categories li:nth-child(1)")
	private WebElement shopLogo;
	
	@FindBy(css="#PreferredPopup #keep-shop")
	private WebElement keepShop;
	
	@FindBy(css="#commentform .logged-in-as a:nth-child(1)")
	private WebElement loggedAs;
	
	
	
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
		//Assert.assertTrue("Failure: The Website is not correct ",getDriver().getCurrentUrl().contains(website));
		Assert.assertTrue("Failure: The user is not keept in the right store view",websiteFlag.getText().toLowerCase().contains(website) );
		
		
	}
	

	public void validateLoggedInAcademy() {
		waitForPageToLoad();
		element(academyHomePage).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", academyHomePage.getText().contains("Online Style Party Training"));
	}

	public void vaslidateShopLoginStoreView(String storeView) {
		List<WebElement> storeViewElements=getDriver().findElements(By.cssSelector(".header .quick-wrap .message"));
		element(storeViewMessage).waitUntilVisible();
		if(storeView.contentEquals("es")){
			CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewMessage.getText().toLowerCase().contains(storeView) );
			CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewElements.get(0).getText().toUpperCase().contains("¡BIENVENIDO"));
		}
		if(storeView.contentEquals("de")){
			CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewMessage.getText().toLowerCase().contains(storeView) );
			CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewElements.get(0).getText().toUpperCase().contains("HI,"));
		}
		if(storeView.contentEquals("en")){
			CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewMessage.getText().toLowerCase().contains(storeView) );
			CustomVerification.verifyTrue("Failure: User is not logged in in the right store View",storeViewElements.get(0).getText().toUpperCase().contains("WELCOME,"));
		}
	
		
	}

	public void validateContext(String context) {
		Assert.assertTrue("Failure: The Context is not displayed correctly ",getDriver().getCurrentUrl().contains(context));
	}

	public void validateLoggedOutFromAcademy() {
		waitForPageToLoad();
		element(academyLoginPage).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", academyLoginPage.getText().contains("Login"));
	}

	public void validateShopLogoutStoreView(String storeView) {
		
		List<WebElement> storeViewElements=getDriver().findElements(By.cssSelector(".header .quick-wrap .message"));
		element(storeViewMessage).waitUntilVisible();
		if(storeView.contentEquals("es")){
			CustomVerification.verifyTrue("Failure: The user is not keept in the right store view",storeViewMessage.getText().toLowerCase().contains(storeView) );
			CustomVerification.verifyTrue("Failure: User is not logged out from SHOP",!storeViewElements.isEmpty());
		}
		if(storeView.contentEquals("de")){
			CustomVerification.verifyTrue("Failure: The user is not keept in the right store view",storeViewMessage.getText().toLowerCase().contains(storeView) );
			CustomVerification.verifyTrue("Failure: User is not logged out from SHOP",!storeViewElements.isEmpty());
		}
		if(storeView.contentEquals("en")){
			CustomVerification.verifyTrue("Failure: The user is not keept in the right store view",storeViewMessage.getText().toLowerCase().contains(storeView) );
			CustomVerification.verifyTrue("Failure: User is not logged out from SHOP",!storeViewElements.isEmpty());
		}
	}

	public void validateCustomerName(String customerName) {
		List<WebElement> storeViewElements=getDriver().findElements(By.cssSelector(".header .quick-wrap .message"));
		CustomVerification.verifyTrue("Failure: The name is not displayed in shop footer ",storeViewElements.get(0).getText().toUpperCase().contains("JULIA"));
	}

	public void clickOnShopLogo() {
		element(shopLogo).waitUntilVisible();
		shopLogo.click();
	}
	public void keepStylistContext() {
		List<WebElement> changeModal=getDriver().findElements(By.cssSelector("#PreferredPopup #keep-shop"));
		if(!changeModal.isEmpty()){
			changeModal.get(0).click();
		}else{
			Assert.assertTrue("Failure: The <Change Stylist> modal is not displayed ",false);
		}
		
	}

	public void validateLoggedWithRightUser(String userEmail) {
		element(loggedAs).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: Not Expected user is logged in Academy ",loggedAs.getText().contains(userEmail));
	}
}

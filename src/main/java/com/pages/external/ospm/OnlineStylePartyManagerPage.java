package com.pages.external.ospm;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

/**
 * This page will contain mapping from the pop-up facebook login window.
 * 
 * @author voicu.vac
 *
 */
public class OnlineStylePartyManagerPage extends AbstractPage {

	@FindBy(id = "email")
	private WebElement userNameInput;

	@FindBy(id = "pass")
	private WebElement userPassInput;

	@FindBy(id = "loginbutton")
	private WebElement loginButton;

	@FindBy(css = ".fb-btn")
	private WebElement loginWithFacebookbutton;

	@FindBy(css = ".social-circle-wrapper .row:nth-child(1) img")
	private WebElement clickOnFirstGroup;

	@FindBy(css = "button[name*='CONFIRM']")
	private WebElement continueAsUserButton;

	@FindBy(css = ".thumbnail-wrapper .row:nth-child(3) a")
	private WebElement clickOnFirstLiveStreamButton;

	@FindBy(id = "new-comment")
	private WebElement commentTextArea;

	@FindBy(css = "#comments li:nth-child(1) ")
	private WebElement firstComment;

	@FindBy(id = "search")
	private WebElement searchImputField;

	@FindBy(id = "products")
	private List<WebElement> productList;

	@FindBy(id = "btn-link-to-shop")
	private WebElement shopButton;

	@FindBy(id = "btn-link-to-contact-booster")
	private WebElement contactBoosterButton;

	@FindBy(id = "btn-link-to-overview")
	private WebElement overviewButton;

	@FindBy(id = "btn-link-to-ring-calibrator")
	private WebElement ringCalibrator;
	
	@FindBy(css = "a[action*='cancel']")
	private WebElement closePopUp;
	
	@FindBy(css = "a[action*='cancel']")
	private List<WebElement> closePopUpFB;

	@FindBy(css = "._1a_q  a[role*='button']")
	private WebElement reviewInfoLink;

	@FindBy(css = ".uiScrollableAreaContent label")
	private WebElement accessGroupsRadioButton;
//.uiScrollableAreaContent  label
	@FindBy(css = ".alert.alert-danger")
	private List<WebElement> alertMessage;

	// input[type*='checkbox']

	public void inputUser(String user) {
		element(userNameInput).waitUntilVisible();
		userNameInput.sendKeys(user);
	}

	public void inputPass(String pass) {
		element(userPassInput).waitUntilVisible();
		userPassInput.sendKeys(pass);
	}

	public void clickLogin() {
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

	public void clickOnLoginWithFacebookButton() {
		element(loginWithFacebookbutton).waitUntilVisible();
		loginWithFacebookbutton.click();
	}

	public void clickContinueAsUser() {
		waitABit(2000);
		element(continueAsUserButton).waitUntilVisible();
		continueAsUserButton.click();

	}

	public void clickOnFirstGroup() {
		waitABit(7000);
		element(clickOnFirstGroup).waitUntilVisible();
		clickOnFirstGroup.click();
	}

	public void clickOnFirstLiveStreamVideo() {
		waitABit(8000);
		element(clickOnFirstLiveStreamButton).waitUntilVisible();
		clickOnFirstLiveStreamButton.click();
	}

	public void addSomeTextAsComment(String text) {
		element(commentTextArea).waitUntilVisible();
		commentTextArea.sendKeys(text);

		commentTextArea.sendKeys(Keys.RETURN);
		waitABit(10000);
	}

	public void verifyIfCommentIsPosted(String text) {
		CustomVerification.verifyTrue("Failure:The comment is not posted", firstComment.getText().contains(text));
	}

	public void searchForAProduct(String text) {
		element(searchImputField).waitUntilPresent();
		searchImputField.sendKeys(text);
	}

	// Tory Necklace
	public void selectAndLinkSearchedProduct(String productName) {
		waitABit(2000);
		boolean found = false;
		if(productList.size()!=0){
			for (WebElement product : productList) {
				if (product.getText().contains(productName)) {
					found = true;
					
					product.findElement(By.cssSelector("button")).click();
					break;

				}
				CustomVerification.verifyTrue("Failure: The products is not displayed in the list", found);
			}
		}else{
			CustomVerification.verifyTrue("Failure: product not found", found);
		}
		
		
	}

	// url-key tory-necklace
	public void verifyIfProductIsPosted(String productName) {
		waitABit(25000);
		CustomVerification.verifyTrue("Failure:The product is not posted", firstComment.getText().contains(productName));
	}

	public void verifyShopButtonRedirect() {
		element(shopButton).waitUntilVisible();
		shopButton.click();
		waitABit(25000);
		System.out.println("shop link " + firstComment.getText());
		CustomVerification.verifyTrue("The shop Link is not posted",
				firstComment.getText().contains("emx?p="));

	}

	public void verifyContactBoosterButtonRedirect() {
		element(contactBoosterButton).waitUntilVisible();
		contactBoosterButton.click();
		waitABit(25000);
		System.out.println("contact Booster link " + firstComment.getText());
		CustomVerification.verifyTrue("Failure: The Contact Booster Link is not correctly posted ",
				firstComment.getText().contains("/k/"));

	}

	public void verifyOverviewButtonRedirect() {
		element(overviewButton).waitUntilVisible();
		overviewButton.click();
		waitABit(25000);
		System.out.println("overview link " + firstComment.getText());

		CustomVerification.verifyTrue("Failure:The Overview Link is not posted",
				firstComment.getText().contains("schmuckstucke.html?p"));

	}
	
	
	public void verifyRingCalibratorButtonRedirect() {
		element(ringCalibrator).waitUntilVisible();
		ringCalibrator.click();
		waitABit(25000);
		System.out.println("ringCalibrator link " + firstComment.getText());

		CustomVerification.verifyTrue("Failure: The Ring Calibrator Link is not posted",
				firstComment.getText().contains("ring.pippajean.com"));

	}

//	public void closePopUp() {
//		element(closePopUp).waitUntilVisible();
//		closePopUp.click();
//
//	}
	
	
	public void closePopUp() {
		if(closePopUpFB.size()!=0){
			element(closePopUp).waitUntilVisible();
			closePopUp.click();
		}
		

	}
	
	

	public void clickReviewInfoYouProvideLink() {
		waitABit(2000);
		element(reviewInfoLink).waitUntilVisible();
		reviewInfoLink.click();
	}

	public void uncheckAccessTheGroupYouManageOption() {
		waitABit(5000);
		accessGroupsRadioButton.click();
	

	}

	public void verifyMessage(String message) {
		waitABit(5000);
		if(alertMessage.size()>0){
			element(alertMessage.get(0)).waitUntilVisible();
			CustomVerification.verifyTrue("Failure: The Allert Message Link is not displayed correctly",
					alertMessage.get(0).getText().contains(message));
		}else{
			CustomVerification.verifyTrue("The expected text is not displayed in app", false);
		}
		
	}
}
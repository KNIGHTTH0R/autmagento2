package com.pages.frontend.invitation;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class FacebookInvitationPage extends AbstractPage {

	// mandatory fields
	@FindBy(id = "distribution_zip")
	private WebElement fbUserEmail;
	
	@FindBy(css = "iframe[src*='facebook.com/dialog']")
	private WebElement fbiFrame;
	
//	@FindBy(id = "facebook")
//	private WebElement fbiFrame;
	
	@FindBy(css = "button[onClick*='sendFriends']")
	private WebElement sendFriendsInvitationBtn;
	
	@FindBy(css = ".pam table tbody tr:nth-child(1) td:nth-child(2)")
	private WebElement inputFriendName;
	
	@FindBy(css = ".pam table tbody tr:nth-child(2) td:nth-child(2)")
	private WebElement inputMessage;
	
	@FindBy(css = ".pam table tbody tr:nth-child(2) td:nth-child(2)")
	private WebElement selectFriend;
	
	@FindBy(id = "publish")
	private WebElement submitInvitation;
	@FindBy(css = "button[name*='CONFIRM']")
	private WebElement shareButton;
	
	@FindBy(id = ".mentionsTextarea.textInput")
	private WebElement shareMessage;
	
	
	
	public void verifyFbUserEmail(String fbEmail) {
		// TODO Auto-generated method stub
		element(fbUserEmail).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: The User Email is not displayed correctly in registration form", fbUserEmail.getText().contains(fbEmail));
	}


	public void clickOnInviteFacebookButton() {
		element(sendFriendsInvitationBtn).waitUntilVisible();		
		sendFriendsInvitationBtn.click();
	}


	public void switchToFBiFrame() {
		waitABit(3000);
		element(fbiFrame).waitUntilVisible();
		getDriver().switchTo().frame(fbiFrame);
		
	}


	public void selectFriendName(String name) {
		waitABit(5000);
		List<WebElement> iframe=getDriver().findElements(By.cssSelector(".FB_UI_Dialog"));
		getDriver().switchTo().frame(iframe.get(0));
		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(inputFriendName);
		actions.click();
		actions.sendKeys(name);
		actions.build().perform();
		
		System.out.println("am inputat cv");
		waitABit(2000);
		selectFriend(name);
		
	}
	
	public void selectFriend(String name){
		
		List<WebElement> friends = getDriver().findElements(By.cssSelector("ul.compact li"));
		boolean found=false;
		for (WebElement friend : friends) {
			if(friend.getText().contains(name)){
				friend.click();
				found=true;
				break;
			}
			
		}
		Assert.assertTrue("Failure: The FB Friends was not found ", found);
	}
	
	public void insertMessage(String message) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(inputMessage);
		actions.click();
		actions.sendKeys(message);
		actions.build().perform();
	
		waitABit(2000);
	
	}
	
	public void insertShareMessage(String message) {
//		List<WebElement> iframe=getDriver().findElements(By.cssSelector(".FB_UI_Dialog"));
//		getDriver().switchTo().frame(iframe.get(0));
		
//		/element(shareMessage).waitUntilVisible();
		
		
		 getDriver().findElement(By.cssSelector(".mentionsTextarea.textInput")).clear();
		 getDriver().findElement(By.cssSelector(".mentionsTextarea.textInput")).sendKeys(message);
	//	shareMessage.sendKeys(message);
//		Actions actions = new Actions(getDriver());
//		actions.moveToElement(shareMessage);
//		actions.click();
//		actions.sendKeys(message);
//		actions.build().perform();
	
		waitABit(2000);
	}
	


	public void sendFbInvitation() {
		element(submitInvitation).waitUntilPresent();
		submitInvitation.click();
		
		waitABit(5000);
//		Actions actions = new Actions(getDriver());
//		actions.moveToElement(submitInvitation);
//		actions.click();
	}


	public void verifyInvitationSending(String email, String status) {
		getDriver().switchTo().defaultContent();
		List<WebElement> invitationList= getDriver().findElements(By.cssSelector("#invitations-list-table tbody tr"));
		boolean found=false;
		for (WebElement invitation : invitationList) {
			WebElement emailInv=invitation.findElement(By.cssSelector("td:nth-child(1)"));
			WebElement statusInv=invitation.findElement(By.cssSelector("td:nth-child(2)"));
			
			if(emailInv.getText().contains(email)){
				CustomVerification.verifyTrue("Failure the status is not as expected", statusInv.getText().contains(status));
				found=true;
				break;
			}
		}
		
		Assert.assertTrue("The email was not found in <Sent invitation> list", found);
	}


	public void sendSharePost() {
		element(shareButton).waitUntilVisible();
		shareButton.click();
		waitABit(4000);
	}


	
	
	
	
}

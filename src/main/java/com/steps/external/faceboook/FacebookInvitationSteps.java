package com.steps.external.faceboook;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class FacebookInvitationSteps extends AbstractSteps {

	private static final long serialVersionUID = -2649339632021723245L;
	public static String currentWindowHandle;
	@Step
	public void clickOnInviteFacebookButton() {
		//currentWindowHandle = getDriver().getWindowHandle();
		facebookInvitationPage().clickOnInviteFacebookButton();
		findFrame("Log in With Facebook");
	}

	@Step
	public void clickOnInviteFacebookButtonUserLoggedIn() {
		//currentWindowHandle = getDriver().getWindowHandle();
		facebookInvitationPage().clickOnInviteFacebookButton();
		//findFrame("Log in With Facebook");
	}
	
	@Step
	public void switchToFBiFrame() {
		// TODO Auto-generated method stub
		facebookInvitationPage().switchToFBiFrame();
		
	}

	@Step
	public void selectFriendName(String name) {
	//	waitABit(6000);
//	/	findFrame("Meine Einladungen");
		facebookInvitationPage().selectFriendName(name);
		
	}
	
	@Step
	public void insertMessage(String message) {
		facebookInvitationPage().insertMessage(message);
		
	}

	@Step
	public void sendFbInvitation() {
		facebookInvitationPage().sendFbInvitation();
		
	}

	@Step
	public void verifyInvitationSending(String email, String status) {
		facebookInvitationPage().verifyInvitationSending(email,status);
		
	}

	@Step
	public void selectLatestFBMessage() {
		waitABit(3000);
		facebookMessagePage().selectLatestFBMessage();
	}

	@Step
	public void verifyInvitationIsReceived(String message) {
		waitABit(3000);
		facebookMessagePage().verifyInvitationIsReceived(message);;		
	}
	
	@Step
	public void clickOnInvitationLink() {
		facebookMessagePage().clickOnInvitationLink();;		
	}
	
	@Step
	public void clickOnInvitationLinkAppNotInstalled() {
		facebookMessagePage().clickOnInvitationLink();
		findFrame("Log in With Facebook");
	}
	
}

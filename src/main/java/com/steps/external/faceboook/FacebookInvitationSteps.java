package com.steps.external.faceboook;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class FacebookInvitationSteps extends AbstractSteps {

	private static final long serialVersionUID = -2649339632021723245L;

	@Step
	public void clickOnInviteFacebookButton() {
		facebookInvitationPage().clickOnInviteFacebookButton();
		findFrame("Log in With Facebook");
	}

	@Step
	public void switchToFBiFrame() {
		// TODO Auto-generated method stub
		facebookInvitationPage().switchToFBiFrame();
		
	}

	@Step
	public void selectFriendName(String name) {
		waitABit(2000);
		findFrame("Meine Einladungen");
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

	public void verifyInvitationSending(String email, String status) {
		facebookInvitationPage().verifyInvitationSending(email,status);
		
	}

	public void selectLatestFBMessage() {
		waitABit(3000);
		facebookMessagePage().selectLatestFBMessage();
	}

	public void verifyInvitationIsReceived(String message) {
		waitABit(3000);
		facebookMessagePage().verifyInvitationIsReceived(message);;		
	}
	
	
}

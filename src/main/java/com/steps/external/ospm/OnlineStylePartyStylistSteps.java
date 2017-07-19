package com.steps.external.ospm;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

public class OnlineStylePartyStylistSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;

	@Step
	public void navigateToHostPage(String urlModel) {
		navigate(urlModel);
		
	}
	
	public void loginIntoHostAccount(String hostPass) {
		onlineStylePartyStylistPage().insertHostPassword(hostPass);
		onlineStylePartyStylistPage().clickOnLoginBtn();
	}
	
	@StepGroup
	public void verifyIfInvitedGuestAppersInList(String guestName){
		onlineStylePartyStylistPage().verifyIfInvitedGuestAppersInList(guestName);
		onlineStylePartyStylistPage().verifyInvitationStatus();
	}
	
	
}

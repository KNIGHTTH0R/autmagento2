package com.steps.external.ospm;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

public class OnlineStylePartyManagerSteps extends AbstractSteps {
	
	

	private static final long serialVersionUID = 1L;
	
	@Step
	public void clickOnLoginWithFacebookButton() {
		onlineStylePartyManagerPage().clickOnLoginWithFacebookButton();
	}

	@Step
	public void acceptAllThePermissions() {
		waitABit(3000);
		onlineStylePartyManagerPage().closePopUp();
		onlineStylePartyManagerPage().clickContinueAsUser();
		onlineStylePartyManagerPage().clickContinueAsUser();
		onlineStylePartyManagerPage().clickContinueAsUser();
	}

	@Step
	public void selectFirstGroup() {
		onlineStylePartyManagerPage().clickOnFirstGroup();
	}
	
	@Step
	public void selectFirstLiveStreamVideo() {
		onlineStylePartyManagerPage().clickOnFirstLiveStreamVideo();
	}
	
	@Step
	public void addSomeTextAsComment(String text) {
		onlineStylePartyManagerPage().addSomeTextAsComment(text);
	}
	@Step
	public void verifyIfCommentIsPosted(String text) {
		onlineStylePartyManagerPage().verifyIfCommentIsPosted(text);
	}
	
	@Step
	public void searchForAProduct(String text) {
		onlineStylePartyManagerPage().searchForAProduct(text);
	}
	
	@Step
	public void selectAndLinkSearchedProduct(String text) {
		onlineStylePartyManagerPage().selectAndLinkSearchedProduct(text);
	}
	
	@Step
	public void verifyIfProductIsPosted(String text) {
		onlineStylePartyManagerPage().verifyIfProductIsPosted(text);
	}

	@Step
	public void verifyShopButtonRedirect() {
		onlineStylePartyManagerPage().verifyShopButtonRedirect();
		
	}
	
	@Step
	public void verifyContactBoosterButtonRedirect() {
		onlineStylePartyManagerPage().verifyContactBoosterButtonRedirect();		
	}

	public void verifyOverviewButtonRedirect() {
		onlineStylePartyManagerPage().verifyOverviewButtonRedirect();				
	}
	
	@Step
	public void closePopUp(){
		onlineStylePartyManagerPage().closePopUp();	
	}
	
	@Step
	public void uncheckTheRemoveManageGroupsPermissions() {
		waitABit(3000);
		onlineStylePartyManagerPage().closePopUp();
		onlineStylePartyManagerPage().clickContinueAsUser();
		onlineStylePartyManagerPage().clickContinueAsUser();
		onlineStylePartyManagerPage().clickReviewInfoYouProvideLink();
		onlineStylePartyManagerPage().uncheckAccessTheGroupYouManageOption();
		onlineStylePartyManagerPage().clickContinueAsUser();
		
	}

	@Step
	public void verifyMessage(String message) {
		onlineStylePartyManagerPage().verifyMessage(message);
	}
}

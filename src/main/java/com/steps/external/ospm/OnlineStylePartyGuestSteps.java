package com.steps.external.ospm;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

public class OnlineStylePartyGuestSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;

	@Step
	public void navigateToGuestPage(String urlModel) {
		String partyId=extractPartyID(urlModel);
		navigate("http://staging-aut.pippajean.com/p/g/"+partyId);
		
	}

	private String extractPartyID(String urlModel) {
		return urlModel.substring(urlModel.indexOf("id/")+3).replaceAll("\\D+","");
		
		
	}

	@Step
	public void clickOnAcceptInvitationButton() {
		onlineStylePartyGuestPage().clickOnAcceptInvitationButton();
		
	}
	
	@Step
	public void clickOnFbRegistrationBtn() {
		onlineStylePartyGuestPage().clickOnFbRegistrationBtn();
		findFrame("Log in With Facebook");
		
	}
	
	@Step
	public void clickOnFbRegistrationBtnNoSwitch() {
		onlineStylePartyGuestPage().clickOnFbRegistrationBtn();
		
	}
	
	
	@StepGroup
	public void validateGuestRegistrationForm(String firstName,String lastName,String email,String country) {
		findFrame("Your Guest page");
		onlineStylePartyGuestPage().checkFbFirstName(firstName);
		onlineStylePartyGuestPage().checkFbLastName(lastName);
		onlineStylePartyGuestPage().checkFbEmail(email);
		onlineStylePartyGuestPage().selectCountry(country);
		
	}
	
	@Step
	public void clickOnSaveButton() {
		onlineStylePartyGuestPage().clickOnSaveButton();
		waitABit(4000);
		
	}
}

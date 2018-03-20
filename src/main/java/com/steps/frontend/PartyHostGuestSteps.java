package com.steps.frontend;

import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

public class PartyHostGuestSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;


	@StepGroup
	public void loginInHostPage(String password) {
		partyHostGuestPage().inserPassword(password);
		partyHostGuestPage().clickLoginBtn();
	}
	
	@Step
	public void expendInviteGuestForm() {
		
		partyHostGuestPage().expendInviteGuestForm();
		
	}
	

	@StepGroup
	public void insertGuest(String firstname, String lastname) {
		
	//	partyHostGuestPage().expendInviteGuestForm();
		partyHostGuestPage().insertGuestFirstName(firstname);
		partyHostGuestPage().insertGuestLastName(lastname);
		partyHostGuestPage().clickSaveBtn();
	}


	@Step
	public void verifyGuestIsDisplayedOnHostPage(String firstname, String lastname, boolean isApproved) {
		
		partyHostGuestPage().verifyGuestIsDisplayedOnHostPage(firstname, lastname,isApproved);
		
	}

	public void hostDeclineGuestInvitation(String firstname, String lastname) {
		// TODO Auto-generated method stub
		partyHostGuestPage().hostDeclineGuestInvitation(firstname,lastname);
	}

	public void sendGuestInvitationByEmail() {
		partyHostGuestPage().sendGuestInvitationByEmail();
	}

	public void openGuestPartyPage() {
		waitABit(3000);
		partyHostGuestPage().openGuestPartyPage();
	}

	public void acceptInvitation() {
		partyHostGuestPage().acceptInvitation();
	}

	public void fillAcceptInvitationForm(CustomerFormModel customer) {
		partyHostGuestPage().insertGuestFirstName(customer.getFirstName());
		partyHostGuestPage().insertGuestLastName(customer.getLastName());
		partyHostGuestPage().insertGuestEmail(customer.getEmailName());
		partyHostGuestPage().selectGuestCountry("Deutschland");
		partyHostGuestPage().clickOnSaveBtn();
	}

	public void clickOnCustomerRegistrationLink() {
		partyHostGuestPage().clickOnCustomerRegistrationLink();
	}

	public void fillAcceptInvitationFormUsingFB(CustomerFormModel customerData) {
		// TODO Auto-generated method stub
		partyHostGuestPage().clickOnSaveBtn();

	}

	public void clickRegisterWithFB() {
		partyHostGuestPage().clickRegisterWithFB();
		findFrame("Facebook");

	}

	public void verifyPrepopulatedFieldsOnForm(CustomerFormModel customerData) {
		partyHostGuestPage().verifyFirstName(customerData.getFirstName());
		partyHostGuestPage().verifyLastName(customerData.getLastName());
		partyHostGuestPage().verifyEmailName(customerData.getEmailName());

		partyHostGuestPage().clickOnSaveBtn();
	}

	public void declineInvitation() {
		partyHostGuestPage().declineInvitation();

	}


}

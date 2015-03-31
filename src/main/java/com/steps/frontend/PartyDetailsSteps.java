package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class PartyDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@StepGroup
	public void closeTheParty(String number) {
		partyDetailsPage().closeParty();
		partyDetailsPage().typePartyAttendersNumber(number);
		partyDetailsPage().popupCloseParty();

	}
	
	@StepGroup
	public void sendInvitationToGest(CustomerFormModel customerData){
		partyDetailsPage().inviteGuests();
		partyDetailsPage().typeGuestName(customerData.getFirstName());
		partyDetailsPage().typeGuestEmail(customerData.getEmailName());
		partyDetailsPage().sendInvitation();
	}
	

	@Step
	public void verifyThatPartyIsClosed() {
		partyDetailsPage().verifyThatPartyIsClosed();
	}

	@Step
	public void typePartyAttendersNumber(String number) {

		partyDetailsPage().typePartyAttendersNumber(number);
	}

	@Step
	public void popupCloseParty() {
		partyDetailsPage().popupCloseParty();
	}

	@Step
	public void inviteGuests() {
		partyDetailsPage().inviteGuests();
	}
	@Step
	public void verifyThatGuestIsInvited(CustomerFormModel customerData) {
		partyDetailsPage().verifyThatGuestIsInvited(customerData);
	}

}

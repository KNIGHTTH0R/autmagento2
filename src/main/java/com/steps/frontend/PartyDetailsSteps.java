package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class PartyDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void closeParty() {
		partyDetailsPage().closeParty();
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

}

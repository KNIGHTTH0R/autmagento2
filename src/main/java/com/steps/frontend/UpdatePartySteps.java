package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.requirements.AbstractSteps;

public class UpdatePartySteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectFirstAvailableDate() {
		updatePartyPage().selectFirstAvailableDate();
	}

	@Step
	public void selectSecondAvailableHour() {
		updatePartyPage().selectSecondAvailableHour();
	}

	@Step
	public void submitParty() {
		updatePartyPage().submitParty();
	}

	@Step
	public void verifyThatCountryDropdownDoesNotContainNotAllowedCountries() {
		updatePartyPage().verifyThatCountryDropdownDoesNotContainNotAllowedCountries();

	}
	@Step
	public void selectNotAllowedCountryName(String countryName) {
		partyDetailsPage().editParty();
		updatePartyPage().selectCountryName(countryName);
		updatePartyPage().submitParty();
		
		
		
	}

	@StepGroup
	public void updatePartyDateAndHour() {
		partyDetailsPage().editParty();
		updatePartyPage().selectFirstAvailableDate();
		updatePartyPage().selectSecondAvailableHour();
		updatePartyPage().submitParty();
	}

	@StepGroup
	public void updatePartyDateWithMinimum180DaysLater() {
		updatePartyPage().selectADateGreaterWithMinimum180Days();
		updatePartyPage().confirmMoveParty();
		updatePartyPage().selectSecondAvailableHour();
		updatePartyPage().submitParty();
	}
	@Step
	public void verifyCountryRestrictionMessage() {
		updatePartyPage().verifyCountryRestrictionMessage();
		
	}

}

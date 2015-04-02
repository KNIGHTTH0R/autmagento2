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

	@StepGroup
	public void updatePartyDateAndHour() {
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

}

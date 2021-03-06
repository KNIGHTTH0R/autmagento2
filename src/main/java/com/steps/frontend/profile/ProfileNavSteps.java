package com.steps.frontend.profile;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class ProfileNavSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectMenu(String menu) {
		profileNavPage().selectMenu(menu);
	}

	@Step
	public void clickStylePartyDetailsButton() {
		profileNavPage().clickStylePartyDetailsButton();
	}

	@Step
	public void inviteGuestsToPartyButton() {
		profileNavPage().inviteGuestsToPartyButton();
	}
	
	@Step
	public void checkSosMenuIsNotPresent(String menu) {
		profileNavPage().checkSosMenuIsNotPresent(menu);
	}

}

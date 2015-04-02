package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class StarterSetSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickOnJetztStyleCoachWerdenButton() {
		starterSetPage().clickOnJetztStyleCoachWerdenButton();
	}

	@Step
	public void clickOnJetztStartenFromStarterSet() {
		starterSetPage().clickOnJetztStartenFromStarterSet();
	}

}

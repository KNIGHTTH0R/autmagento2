package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class KoboSuccesFormSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void verifyKoboFormIsSuccsesfullyFilledIn() {
		koboSuccesFormPage().verifyKoboFormIsSuccsesfullyFilledIn();
	}

	@Step
	public void verifyThatTheWebsiteHasChanged() {
		koboSuccesFormPage().verifyThatTheWebsiteHasChanged();
	}

}

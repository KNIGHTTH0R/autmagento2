package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.frontend.StylistRegistrationPage;

public class StylistRegistrationStepsWithCsv extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String plz;

	StylistRegistrationPage stylistRegistrationPage;

	public StylistRegistrationStepsWithCsv(Pages pages) {
		super(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		stylistRegistrationPage.inputPostCodeAndValdiateErrorMessage(plz);
		stylistRegistrationPage.submitStep();
	}

}

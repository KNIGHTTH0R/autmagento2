package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.annotations.Qualifier;

import com.pages.frontend.StylistRegistrationPage;

public class StylistRegistrationStepsCsvContext extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	StylistRegistrationPage stylistRegistrationPage;

	public StylistRegistrationStepsCsvContext(Pages pages) {
		super(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		stylistRegistrationPage.inputPostCodeAndValdiateErrorMessage(plz);
		stylistRegistrationPage.submitStep();
	}

}

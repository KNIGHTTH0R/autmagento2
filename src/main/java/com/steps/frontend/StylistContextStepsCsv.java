package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.annotations.Qualifier;

import com.pages.frontend.StylistContextPage;
import com.pages.frontend.StylistRegistrationPage;

public class StylistContextStepsCsv extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String context;

	@Qualifier
	public String getQualifier() {
		return context;
	}

	StylistRegistrationPage stylistRegistrationPage;
	StylistContextPage stylistContextPage;

	public StylistContextStepsCsv(Pages pages) {
		super(pages);
	}

	@Step
	public void inputContextCsv() {
		stylistContextPage.inputContextCodeAndValdiateErrorMessage(context);
		stylistRegistrationPage.validateContextValidationErrorMessage();
		
	}

}

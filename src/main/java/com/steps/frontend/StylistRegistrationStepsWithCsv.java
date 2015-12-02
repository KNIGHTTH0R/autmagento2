package com.steps.frontend;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class StylistRegistrationStepsWithCsv extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	private String plz;

	public StylistRegistrationStepsWithCsv(Pages pages) {
		new ScenarioSteps(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		stylistRegistrationPage().inputPostCode(plz);
		stylistRegistrationPage().submitStep();
	}

}

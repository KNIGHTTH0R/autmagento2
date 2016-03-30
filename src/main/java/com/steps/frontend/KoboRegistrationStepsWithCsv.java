package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.frontend.registration.contactBooster.KoboCampaignPage;

public class KoboRegistrationStepsWithCsv extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String plz;


	KoboCampaignPage koboCampaignPage;

	public KoboRegistrationStepsWithCsv(Pages pages) {
		super(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		koboCampaignPage.inputPostCodeAndValdiateErrorMessage(plz);
	}

}

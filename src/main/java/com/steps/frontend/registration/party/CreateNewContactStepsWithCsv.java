package com.steps.frontend.registration.party;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.annotations.Qualifier;

import com.pages.frontend.registration.party.CreateNewContactPage;

public class CreateNewContactStepsWithCsv extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	CreateNewContactPage createNewContactPage;

	public CreateNewContactStepsWithCsv(Pages pages) {
		super(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		createNewContactPage.inputPostCodeAndValdiateErrorMessage(plz);
	}

}

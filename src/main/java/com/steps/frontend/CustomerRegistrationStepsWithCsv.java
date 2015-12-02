package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.annotations.Qualifier;

import com.pages.frontend.CreateCustomerPage;

public class CustomerRegistrationStepsWithCsv extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	CreateCustomerPage createCustomerPage;

	public CustomerRegistrationStepsWithCsv(Pages pages) {
		super(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		createCustomerPage.inputPostCodeAndValdiateErrorMessage(plz);
		createCustomerPage.clickCompleteButton();
	}

}

package com.steps.frontend.checkout;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.annotations.Qualifier;

import com.pages.frontend.checkout.shipping.BillingFormPage;

public class ShippingStepsWithCsvStepsWithCsv extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	BillingFormPage billingFormPage;

	public ShippingStepsWithCsvStepsWithCsv(Pages pages) {
		super(pages);
	}

	@Step
	public void inputPostCodeCsv() {
		billingFormPage.inputPostCodeAndValdiateErrorMessage(plz);
	}

}

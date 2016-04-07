package com.steps.external.unbounce;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.UrlConstants;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

public class UnbounceSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void navigateToUnbouncePage() {
		getDriver().get(UrlConstants.URL_UNBOUNCE);
	}

	@StepGroup
	public void fillUnbounceDetails(CustomerFormModel customerModel, AddressModel addresModel) {
		unbouncePage().enterFirstname(customerModel.getFirstName());
		unbouncePage().enterLastname(customerModel.getLastName());
		unbouncePage().enterPhone(addresModel.getPhoneNumber());
		unbouncePage().enterPlz(addresModel.getPostCode());
		unbouncePage().enterEmail(customerModel.getEmailName());
		unbouncePage().acceptTerms();
	}

	@Step
	public void submitUnbounceForm() {
		unbouncePage().submitForm();
	}

}

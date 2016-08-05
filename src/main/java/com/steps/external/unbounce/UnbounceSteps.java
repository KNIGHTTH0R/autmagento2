package com.steps.external.unbounce;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.UrlConstants;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class UnbounceSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void navigateToUnbouncePage() {
		navigate(UrlConstants.URL_UNBOUNCE);
	}

	@Title("Fill in unbounce details")
	@StepGroup
	public String fillUnbounceDetails(CustomerFormModel customerModel, AddressModel addresModel) {
		unbouncePage().enterFirstname(customerModel.getFirstName());
		unbouncePage().enterLastname(customerModel.getLastName());
		unbouncePage().enterPhone(addresModel.getPhoneNumber());
		unbouncePage().enterPlz(addresModel.getPostCode());
		unbouncePage().enterEmail(customerModel.getEmailName());
		unbouncePage().submitForm();
		
		String date = FormatterUtils.getAndFormatCurrentDate();

		return date;
	}

}

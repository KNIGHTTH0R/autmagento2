package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.ContactModel;
import com.tools.requirements.AbstractSteps;

public class ContactDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public ContactModel grabContactDetails() {
		return contactDetailsPage().grabContactDetails();
	}

}

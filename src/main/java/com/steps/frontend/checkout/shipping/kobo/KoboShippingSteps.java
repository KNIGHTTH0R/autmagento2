package com.steps.frontend.checkout.shipping.kobo;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class KoboShippingSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void acceptTerms() {
		koboShippingPage().acceptTerms();
	}

}

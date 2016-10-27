package com.steps.frontend.checkout.shipping.regularUser;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class ShippingPomSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickPartyYesOption() {
		regularUserShippingPage().clickPartyYesOption();
	}

	@Step
	public void clickPartyNoOption() {
		regularUserShippingPage().clickPartyNoOption();
	}


}

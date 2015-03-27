package com.steps.frontend.checkout.shipping.contactHost;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class ContactHostShippingHostSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void checkItemNotReceivedYet() {
		contactHostShippingPage().checkItemNotReceivedYet();
	}

	@Step
	public void verifyStyleCoachAndOrderForDetails(String details) {
		contactHostShippingPage().verifyStyleCoachAndOrderForDetails(details);
	}

}

package com.steps.external.mailchimp;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class MailchimpSubscriberProfileSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void grabSubribersData() {
		mailchimpSubscriberProfilePage().grabSubribersData();
	}

}

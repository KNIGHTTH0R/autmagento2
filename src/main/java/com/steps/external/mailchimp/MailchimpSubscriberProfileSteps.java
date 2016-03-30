package com.steps.external.mailchimp;

import net.thucydides.core.annotations.Step;

import com.tools.data.newsletter.SubscriberModel;
import com.tools.requirements.AbstractSteps;

public class MailchimpSubscriberProfileSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public SubscriberModel grabSubribersData() {
		 return mailchimpSubscriberProfilePage().grabSubribersData();
	}

}

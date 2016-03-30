package com.steps.external.mailchimp;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class MailchimpListsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void goToDesiredList(String listName) {
		mailchimpHeaderPage().goToListsTab();
		mailchimpListsPage().selectDesiredLink(listName);
	}


}

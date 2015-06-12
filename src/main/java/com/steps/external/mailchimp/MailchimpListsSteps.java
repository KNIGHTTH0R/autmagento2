package com.steps.external.mailchimp;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class MailchimpListsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void goToDesiredList(String listName) {
		mailchimpHeaderPage().goToListsTab();
		mailchimpListsPage().selectDesiredLink(listName);
	}


}

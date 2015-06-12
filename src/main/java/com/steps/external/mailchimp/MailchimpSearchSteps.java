package com.steps.external.mailchimp;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class MailchimpSearchSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void searchForSubscriber(String email) {
		mailchimpListDetailsPage().startSearch();
		mailchimpSearchPage().inputSearchTerm(email);
		mailchimpSearchPage().submitSearch();
		mailchimpSearchPage().selectSearchResultAndViewProfile(email);

	}

}

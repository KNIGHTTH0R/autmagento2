package com.steps.external.mailchimp;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class MailchimpSearchSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void searchForSubscriber(String email) {
		mailchimpListDetailsPage().startSearch();
//		mailchimpSearchPage().inputSearchTerm(email);
//		mailchimpSearchPage().subMitSearch();
		mailchimpSearchPage().selectSearchResultAndViewProfile(email);

	}

}

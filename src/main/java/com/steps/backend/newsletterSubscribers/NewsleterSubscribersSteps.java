package com.steps.backend.newsletterSubscribers;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class NewsleterSubscribersSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void inputEmailFilter(String emailText) {
		newsletterSubscribersListPage().inputEmailFilter(emailText);
	}

	@Step
	public void clickOnSearch() {
		newsletterSubscribersListPage().clickOnSearch();
	}

	@Step
	public void clickOnResetFilter() {
		newsletterSubscribersListPage().clickOnResetFilter();
	}

	@Step
	public void openNewsletterSubscriberDetails(String searchTerm) {
		newsletterSubscribersListPage().openNewsletterSubscriberDetails(searchTerm);
	}

}

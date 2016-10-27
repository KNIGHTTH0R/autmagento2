package com.steps.external.mailchimp;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class MailchimpListDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void enterUsername(String username) {
		mailchimpLoginPage().enterUsername(username);
	}

	@Step
	public void enterPassword(String password) {
		mailchimpLoginPage().enterPassword(password);
	}

	@Step
	public void submitLogin() {
		mailchimpLoginPage().submitLogin();
	}

}

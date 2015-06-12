package com.steps.external.mailchimp;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class MailchimpHeaderSteps extends AbstractSteps {

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

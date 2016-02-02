package com.steps.external.mailchimp;

import net.thucydides.core.annotations.StepGroup;

import com.tools.env.constants.UrlConstants;
import com.tools.requirements.AbstractSteps;

public class MailchimpLoginSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;
	
	

	@StepGroup
	public void loginOnMailchimp(String username, String password) {
		getDriver().get(UrlConstants.URL_CHIMPMAIL);
		mailchimpLoginPage().enterUsername(username);
		mailchimpLoginPage().enterPassword(password);
		mailchimpLoginPage().submitLogin();
	}

}

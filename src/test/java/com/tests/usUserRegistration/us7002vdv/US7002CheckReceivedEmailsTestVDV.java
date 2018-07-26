package com.tests.usUserRegistration.us7002vdv;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.2 Regular Customer Registration on Context Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_2.class)
@RunWith(SerenityRunner.class)
public class US7002CheckReceivedEmailsTestVDV extends BaseTest {
	
	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTestVDV").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTestVDV").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");
	}
	
	@Test
	public void us7002CheckReceivedEmailsTestVDV() {
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),ContextConstants.NEWSLETTER_MAIL_SUBJECT);
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),ContextConstants.WELCOME_MAIL_SUBJECT);
	
	}

}


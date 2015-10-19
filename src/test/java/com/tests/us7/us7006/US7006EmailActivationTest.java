package com.tests.us7.us7006;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.EmailSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.6 Landing Page Registration Selected SC Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_6.class)
@RunWith(ThucydidesRunner.class)
public class US7006EmailActivationTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;

	private String clientName;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7006LandingPageRegistrationSelectedScTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7006LandingPageRegistrationSelectedScTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us7006EmailActivationTest() {

		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(clientName.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
		customVerifications.printErrors();
	}

}

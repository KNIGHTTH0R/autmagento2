package com.tests.us7.us7007;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.EmailSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.7 Widget Registration Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_7.class)
@RunWith(SerenityRunner.class)
public class US7007EmailActivationTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;

	private String clientName;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7007WidgetUserRegistrationTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7007WidgetUserRegistrationTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us7007EmailActivationTest() {

		emailClientSteps.confirmAccount(clientName.replace("@" + ConfigConstants.WEB_MAIL, ""),
				ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
	}

}

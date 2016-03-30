package com.tests.uss13.us13006;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US13.6 DYKSC Assignation to party host qualified lead SC", type = "Scenarios")
@Story(Application.Distribution.US13_6.class)
@RunWith(SerenityRunner.class)
public class US13006ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US13006HostLeadDykscPlzAndCountryTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US13006HostLeadDykscPlzAndCountryTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us13006ConfirmCustomerTest() {

		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);

	}

}

package com.tests.uss13.us13005;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US13.5 DYKSC Assignation to SC qualified lead SC", type = "Scenarios")
@Story(Application.Distribution.US13_5.class)
@RunWith(ThucydidesRunner.class)
public class US13005ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US13005StyleCoachLeadDykscPlzAndCountryTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US13005StyleCoachLeadDykscPlzAndCountryTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us13005ConfirmCustomerTest() {

		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);

	}

}

package com.tests.uss13.us13007;

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

@WithTag(name = "US13.7 DYKSC By Name Assignation", type = "Scenarios")
@Story(Application.Distribution.US13_7.class)
@RunWith(SerenityRunner.class)
public class US13007ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US13007DykscSearchByNameTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US13007DykscSearchByNameTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us13007ConfirmCustomerTest() {

		emailClientSteps.confirmAccount(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);

	}

}

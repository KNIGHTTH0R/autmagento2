package com.tests.uss10.us10003;

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

@WithTag(name = "US10", type = "external")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10003CheckInviteEmailAndRegistratiionLinkTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;
	
	public String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US10003CreatePartyWithNewContactHostTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US10003CreatePartyWithNewContactHostTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us10003CheckInviteEmailTest() {
		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceivedAndClickRegister(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.INVITE_EMAIL_SUBJECT);

	}

}

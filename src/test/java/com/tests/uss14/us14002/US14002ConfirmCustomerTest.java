package com.tests.uss14.us14002;

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

@WithTag(name = "US14.2 Distribution during checkout to party host lead qualified SC", type = "Scenarios")
@Story(Application.DistributionDuringCheckout.US14_2.class)
@RunWith(SerenityRunner.class)
public class US14002ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	public String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US14002HostLeadDistributionTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US14002HostLeadDistributionTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us14002ConfirmCustomerTest() {

		emailClientSteps.confirmAccount(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);

	}

}

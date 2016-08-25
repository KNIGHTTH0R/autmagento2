package com.tests.uss12.uss12001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(SerenityRunner.class)
public class US12001ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;

	@Before
	public void setUp() throws Exception {

		stylistEmail = MongoReader.grabCustomerFormModels("US12001RegularCustomerRegistrationTest").get(0).getEmailName();

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us12001ConfirmCustomerTest() {

		emailClientSteps.confirmAccount(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);

	}

}

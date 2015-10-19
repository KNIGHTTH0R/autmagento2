package com.tests.uss15.us15002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US15.2 Check registered user with kobo all states in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_2.class)
@RunWith(ThucydidesRunner.class)
public class US15002ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	public String stylistEmail;

	private DateModel dateModel;

	@Before
	public void setUp() throws Exception {

		dateModel = new DateModel();
		stylistEmail = MongoReader.grabCustomerFormModels("US15002KoboRegistrationNewsletterSubscribeTest").get(0).getEmailName();

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15002ConfirmCustomerTest() {

		emailClientSteps.openMailinator();
		dateModel.setDate(emailClientSteps.grabEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.KOBO_CONFIRM_ACCOUNT_MAIL_SUBJECT));

	}

	@After
	public void saveData() {

		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
	}

}

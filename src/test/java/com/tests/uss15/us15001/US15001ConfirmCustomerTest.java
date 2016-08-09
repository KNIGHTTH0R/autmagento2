package com.tests.uss15.us15001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.connectors.mongo.MongoConnector;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.EnvironmentConstants;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US15.1 Check simple subscriber in mailchimp", type = "Scenarios")
@Story(Application.Newsletter.US15_1.class)
@RunWith(SerenityRunner.class)
public class US15001ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;
	private DateModel dateModel;

	@Before
	public void setUp() throws Exception {

		dateModel = new DateModel();
		stylistEmail = MongoReader.grabCustomerFormModels("US15001SubscribeToNewsletterTest").get(0).getEmailName();

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15001ConfirmCustomerTest() throws Exception {

		dateModel.setDate(emailClientSteps.grabEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.NEWSLETTER_MAIL_SUBJECT));
		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_EMAIL_JOB_TRIGGER_URL + stylistEmail + EnvironmentConstants.JOB_TOKEN);

	}

	@After
	public void saveData() {
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
	}

}

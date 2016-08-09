package com.tests.uss15.us15004;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

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

@WithTags({ @WithTag(name = "US15.4 Validate Zzz Product JB for all order states", type = "Scenarios"),
	@WithTag(name = "US15.4 Check place a customer order details in mailchimp", type = "Scenarios") })
@Story(Application.Newsletter.US15_4.class)
@RunWith(SerenityRunner.class)
public class US15004ConfirmCustomerTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;
	private DateModel dateModel;

	@Before
	public void setUp() throws Exception {

		dateModel = new DateModel();
		stylistEmail = MongoReader.grabCustomerFormModels("US15004OrderZzzProductsForCustomerTest").get(0).getEmailName();

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15004ConfirmCustomerTest() throws Exception {

		dateModel.setDate(emailClientSteps.grabEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.NEWSLETTER_MAIL_SUBJECT));
		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_EMAIL_JOB_TRIGGER_URL + stylistEmail + EnvironmentConstants.JOB_TOKEN);

	}

	@After
	public void saveData() {

		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
	}

}

package com.tests.uss15.us15001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.external.mailchimp.MailchimpListsSteps;
import com.steps.external.mailchimp.MailchimpLoginSteps;
import com.steps.external.mailchimp.MailchimpSearchSteps;
import com.steps.external.mailchimp.MailchimpSubscriberProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.mailchimp.MailchimpValidationWorkflows;

@WithTag(name = "US15.1 Check simple subscriber in mailchimp", type = "Scenarios")
@Story(Application.Newsletter.US15_1.class)
@RunWith(SerenityRunner.class)
public class US15001CheckMailchimpConfigTest extends BaseTest {

	@Steps
	public MailchimpLoginSteps mailchimpLoginSteps;
	@Steps
	public MailchimpListsSteps mailchimpListsSteps;
	@Steps
	public MailchimpSearchSteps mailchimpSearchSteps;
	@Steps
	public MailchimpValidationWorkflows mailchimpValidationWorkflows;
	@Steps
	public MailchimpSubscriberProfileSteps mailchimpSubscriberProfileSteps;
	@Steps
	public CustomVerification customVerifications;

	private SubscriberModel grabbedSubscriberModel = new SubscriberModel();
	private SubscriberModel expectedSubscriberModel = new SubscriberModel();
	private CustomerFormModel dataModel;
	private DateModel dateModel;
	private String listName = "staging_AUT_newsletter_all_subscribers";

	@Before
	public void setUp() {

		dataModel = MongoReader.grabCustomerFormModels("US15001SubscribeToNewsletterTest").get(0);
		dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.WEB_MAIL, ConfigConstants.EVOZON));
		dateModel = MongoReader.grabDateModels("US15001ConfirmCustomerTest").get(0);
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15001CheckMailchimpConfigTest() {

		mailchimpLoginSteps.loginOnMailchimp(Credentials.MAILCHIMP_USERNAME, Credentials.MAILCHIMP_PASSWORD);
		mailchimpListsSteps.goToDesiredList(listName);
		mailchimpSearchSteps.searchForSubscriber(dataModel.getEmailName());
		grabbedSubscriberModel = mailchimpSubscriberProfileSteps.grabSubribersData();
		expectedSubscriberModel = mailchimpValidationWorkflows.populateSubsriberModelFromExistingData(dataModel, dateModel);
		mailchimpValidationWorkflows.validateNewContactSubscriberMailchimpProperties(grabbedSubscriberModel, expectedSubscriberModel);

		customVerifications.printErrors();
	}
}

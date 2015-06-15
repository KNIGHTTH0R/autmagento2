package com.tests.uss15.us15001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.external.mailchimp.MailchimpListsSteps;
import com.steps.external.mailchimp.MailchimpLoginSteps;
import com.steps.external.mailchimp.MailchimpSearchSteps;
import com.steps.external.mailchimp.MailchimpSubscriberProfileSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.mailchimp.MailchimpValidationWorkflows;

@WithTag(name = "US13", type = "external")
@Story(Application.Distribution.CustomerLead.class)
@RunWith(ThucydidesRunner.class)
public class US15001CheckMailchimpConfigTest extends BaseTest {

	@Steps
	public MailchimpLoginSteps mailchimpLoginSteps;
	@Steps
	public MailchimpListsSteps mailchimpListsSteps;
	@Steps
	public MailchimpSearchSteps mailchimpSearchSteps;

	public MailchimpValidationWorkflows mailchimpValidationWorkflows;
	@Steps
	public MailchimpSubscriberProfileSteps mailchimpSubscriberProfileSteps;

	SubscriberModel grabbedSubscriberModel = new SubscriberModel();
	SubscriberModel expectedSubscriberModel = new SubscriberModel();
	CustomerFormModel dataModel;
	DateModel dateModel;

	private String listName = "staging_newsletter_all_subscribers";

	@Before
	public void setUp() {
	
		dataModel = MongoReader.grabCustomerFormModels("US15001SubscribeToNewsletterTest").get(0);
		dateModel = MongoReader.grabStylistDateModels("US15001ConfirmCustomerTest").get(0);
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us13001ConfirmCustomerTest() {

		mailchimpLoginSteps.loginOnMailchimp(Credentials.MAILCHIMP_USERNAME, Credentials.MAILCHIMP_PASSWORD);
		mailchimpListsSteps.goToDesiredList(listName);
		mailchimpSearchSteps.searchForSubscriber(dataModel.getEmailName());
		grabbedSubscriberModel = mailchimpSubscriberProfileSteps.grabSubribersData();
		expectedSubscriberModel = mailchimpValidationWorkflows.populateSubsriberModelFromExistingData(dataModel, dateModel);
		mailchimpValidationWorkflows.validateNewContactSubscriberMailchimpProperties(grabbedSubscriberModel, expectedSubscriberModel);

	}
}

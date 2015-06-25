package com.tests.uss15.us15003;

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
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.mailchimp.MailchimpValidationWorkflows;

@WithTag(name = "US15", type = "external")
@Story(Application.Distribution.CustomerLead.class)
@RunWith(ThucydidesRunner.class)
public class US15003CheckMailchimpConfigTest extends BaseTest {

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

	SubscriberModel grabbedSubscriberModel = new SubscriberModel();
	SubscriberModel expectedSubscriberModel = new SubscriberModel();
	private ShippingModel shippingModel = new ShippingModel();
	public BasicProductModel product = new BasicProductModel();
	CustomerFormModel dataModel;
	DateModel dateModel;
	String koboCode;

	private String listName = "staging_AUT_newsletter_all_subscribers";

	@Before
	public void setUp() {

		product = MongoReader.grabBasicProductModel("US15003SubscribedStyleCoachCheckoutProcessTest").get(0);
		shippingModel = MongoReader.grabShippingModel("US15003SubscribedStyleCoachCheckoutProcessTest").get(0);
		dataModel = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").get(0);
		dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.MAILINATOR, ConfigConstants.EVOZON));
		dateModel = MongoReader.grabStylistDateModels("US15003ConfirmCustomerTest").get(0);
		koboCode = MongoReader.grabKoboModel("US15003SubscribedStyleCoachCheckoutProcessTest");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15003CheckMailchimpConfigTest() {

		mailchimpLoginSteps.loginOnMailchimp(Credentials.MAILCHIMP_USERNAME, Credentials.MAILCHIMP_PASSWORD);
		mailchimpListsSteps.goToDesiredList(listName);
		mailchimpSearchSteps.searchForSubscriber(dataModel.getEmailName());
		grabbedSubscriberModel = mailchimpSubscriberProfileSteps.grabSubribersData();
		expectedSubscriberModel = mailchimpValidationWorkflows.populateNewStyleCoachFromExistingData(dataModel, dateModel, product, shippingModel, koboCode);
		mailchimpValidationWorkflows.validateNewScKoboSubscriptionAndShopForMyselfMailchimpProperties(grabbedSubscriberModel, expectedSubscriberModel);
	}
}

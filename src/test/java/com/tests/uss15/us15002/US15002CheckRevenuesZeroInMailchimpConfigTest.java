package com.tests.uss15.us15002;

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
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.mailchimp.MailchimpValidationWorkflows;

@WithTag(name = "US15", type = "external")
@Story(Application.Distribution.CustomerLead.class)
@RunWith(ThucydidesRunner.class)
public class US15002CheckRevenuesZeroInMailchimpConfigTest extends BaseTest {

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
	public RegularBasicProductModel product = new RegularBasicProductModel();
	CustomerFormModel dataModel;
	DateModel dateModel;
	String koboCode;

	private String listName = "staging_AUT_newsletter_all_subscribers";

	@Before
	public void setUp() {

		product = MongoReader.grabRegularBasicProductModel("US15002SubscribedCustomerBuyWithContactBoosterTest").get(2);
		shippingModel = MongoReader.grabShippingModel("US15002SubscribedCustomerBuyWithContactBoosterTest").get(0);
		dataModel = MongoReader.grabCustomerFormModels("US15002KoboRegistrationNewsletterSubscribeTest").get(0);
		dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.MAILINATOR, ConfigConstants.EVOZON));
		dateModel = MongoReader.grabStylistDateModels("US15002ConfirmCustomerTest").get(0);
		koboCode = MongoReader.grabKoboModel("US15002KoboRegistrationNewsletterSubscribeTest");
		MongoConnector.cleanCollection(getClass().getSimpleName());
		System.out.println(product.getProdCode());
		System.out.println(shippingModel.getTotalAmount());
		System.out.println(dataModel.getFirstName());
		System.out.println(dataModel.getLastName());
		System.out.println(dataModel.getEmailName());
		System.out.println(dateModel.getDate());
		System.out.println(koboCode);
		System.out.println(MongoReader.getContext());
	}

	@Test
	public void us15002CheckMailchimpConfigTest() {

		mailchimpLoginSteps.loginOnMailchimp(Credentials.MAILCHIMP_USERNAME, Credentials.MAILCHIMP_PASSWORD);
		mailchimpListsSteps.goToDesiredList(listName);
		mailchimpSearchSteps.searchForSubscriber(dataModel.getEmailName());
		grabbedSubscriberModel = mailchimpSubscriberProfileSteps.grabSubribersData();

		expectedSubscriberModel = mailchimpValidationWorkflows.populateNewCustomerWithKoboFromExistingDataWithZeroRevenue(dataModel, dateModel, product, shippingModel, koboCode);

		 System.out.println("---------------");
		 PrintUtils.printCustomerSubscriberData(expectedSubscriberModel);
		// mailchimpValidationWorkflows.validateNewCustomerOrderWithKoboMailchimpProperties(grabbedSubscriberModel,
		// expectedSubscriberModel);
	}
}

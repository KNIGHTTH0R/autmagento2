package com.tests.uss15.us15004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.mailchimp.MailchimpValidationWorkflows;

@WithTag(name = "US15", type = "external")
@Story(Application.Newsletter.class)
@RunWith(ThucydidesRunner.class)
public class US15004CheckMailchimpConfigWithZeroRevenuesTest extends BaseTest {

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
	public HostBasicProductModel product = new HostBasicProductModel();
	CustomerFormModel dataModel;
	DateModel dateModel;
	String koboCode = "";

	private String listName = "staging_AUT_newsletter_all_subscribers";

	@Before
	public void setUp() {

		dataModel = MongoReader.grabCustomerFormModels("US15004OrderForCustomerTest").get(0);
		dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.MAILINATOR, ConfigConstants.EVOZON));
		product = MongoReader.grabHostBasicProductModel("US15004OrderForCustomerTest").get(2);
		shippingModel = MongoReader.grabShippingModel("US15004OrderForCustomerTest").get(0);
		dateModel = MongoReader.grabStylistDateModels("US15004ConfirmCustomerTest").get(0);
		MongoConnector.cleanCollection(getClass().getSimpleName());

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15004.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void us15004CheckMailchimpConfigWithZeroRevenuesTest() {

		mailchimpLoginSteps.loginOnMailchimp(Credentials.MAILCHIMP_USERNAME, Credentials.MAILCHIMP_PASSWORD);
		mailchimpListsSteps.goToDesiredList(listName);
		mailchimpSearchSteps.searchForSubscriber(dataModel.getEmailName());
		grabbedSubscriberModel = mailchimpSubscriberProfileSteps.grabSubribersData();
		expectedSubscriberModel = mailchimpValidationWorkflows.populatePlaceCustomerOrderSubscriber(dataModel, dateModel, product, shippingModel, koboCode);
		mailchimpValidationWorkflows.validateNewCustomerOrderWithKoboMailchimpPropertiesZeroRevenue(grabbedSubscriberModel, expectedSubscriberModel);
	}
}

package com.tests;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.mailchimp.MailchimpListsSteps;
import com.steps.external.mailchimp.MailchimpLoginSteps;
import com.steps.external.mailchimp.MailchimpSearchSteps;
import com.steps.external.mailchimp.MailchimpSubscriberProfileSteps;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.env.variables.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US13", type = "external")
@Story(Application.Distribution.CustomerLead.class)
@RunWith(ThucydidesRunner.class)
public class CheckMailchimpConfigTest extends BaseTest {

	@Steps
	public MailchimpLoginSteps mailchimpLoginSteps;
	@Steps
	public MailchimpListsSteps mailchimpListsSteps;
	@Steps
	public MailchimpSearchSteps mailchimpSearchSteps;
	@Steps
	public MailchimpSubscriberProfileSteps mailchimpSubscriberProfileSteps;
	
	SubscriberModel model = new SubscriberModel();

	private String listName = "staging_newsletter_all_subscribers";
	private String email = "simona.popa@evozon.com";

	@Test
	public void us13001ConfirmCustomerTest() {

		mailchimpLoginSteps.loginOnMailchimp(Credentials.MAILCHIMP_USERNAME, Credentials.MAILCHIMP_PASSWORD);
		mailchimpListsSteps.goToDesiredList(listName);
		mailchimpSearchSteps.searchForSubscriber(email);
		model = mailchimpSubscriberProfileSteps.grabSubribersData();
		
		System.out.println(" pt ioana   " + model.getEmail());

	}

}

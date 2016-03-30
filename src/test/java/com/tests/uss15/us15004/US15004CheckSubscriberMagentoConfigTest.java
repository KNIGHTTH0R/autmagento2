package com.tests.uss15.us15004;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.backend.BackEndSteps;
import com.steps.backend.newsletterSubscribers.NewsleterSubscribersSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.env.constants.JenkinsConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTags({ @WithTag(name = "US15.4 Validate Zzz Product JB for all order states", type = "Scenarios"),
	@WithTag(name = "US15.4 Check place a customer order details in mailchimp", type = "Scenarios") })
@Story(Application.Newsletter.US15_4.class)
@RunWith(SerenityRunner.class)
public class US15004CheckSubscriberMagentoConfigTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public NewsleterSubscribersSteps newsleterSubscribersSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private CustomerFormModel dataModel;

	@Before
	public void setUp() throws Exception {

		dataModel = MongoReader.grabCustomerFormModels("US15004OrderZzzProductsForCustomerTest").get(0);
		dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.MAILINATOR, ConfigConstants.EVOZON));

	}

	@Test
	public void us15004CheckSubscriberMagentoConfigTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.goToNewsletterSubribers();
		newsleterSubscribersSteps.checkSubscriberDetails(dataModel.getEmailName());
		ApacheHttpHelper.sendGet(JenkinsConstants.EXPORT_JOB_TRIGGER_URL);

	}

}

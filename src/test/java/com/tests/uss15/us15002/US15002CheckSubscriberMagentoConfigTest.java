package com.tests.uss15.us15002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.backend.BackEndSteps;
import com.steps.backend.newsletterSubscribers.NewsleterSubscribersSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US7", type = "backend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US15002CheckSubscriberMagentoConfigTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public NewsleterSubscribersSteps newsleterSubscribersSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public String stylistEmail;

	@Before
	public void setUp() throws Exception {

		stylistEmail = MongoReader.grabCustomerFormModels("US15002KoboRegistrationNewsletterSubscribeTest").get(0).getEmailName();

	}

	@Test
	public void us15002CheckSubscriberMagentoConfigTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.goToNewsletterSubribers();
		newsleterSubscribersSteps.checkSubscriberDetails(stylistEmail);
		ApacheHttpHelper.sendGet(JenkinsConstants.EXPORT_JOB_TRIGGER_URL);
	}

}

package com.tests.us7.us7004;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.validations.StylistValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.4 Regular Customer Registration from Landing Page Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_4.class)
@RunWith(SerenityRunner.class)
public class US7004CheckCustomerActivationTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public StylistValidationSteps stylistValidationSteps;

	private String clientName;
	private String grabStatus;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7004RegularUserRegistrationLandingPageTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7004RegularUserRegistrationLandingPageTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us7004CheckCustomerActivationTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(clientName);
		backEndSteps.openCustomerDetails(clientName);
		grabStatus = backEndSteps.extractEmailConfirmationStatus();
		stylistValidationSteps.validateStatus(grabStatus, ConfigConstants.CONFIRMED);
		customVerifications.printErrors();
	}

}
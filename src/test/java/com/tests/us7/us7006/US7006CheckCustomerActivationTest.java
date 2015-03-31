package com.tests.us7.us7006;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.validations.StylistValidationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7006", type = "backend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7006CheckCustomerActivationTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public StylistValidationSteps stylistValidationSteps;

	public String clientName;
	public String grabStatus;
	public String expectedStatus;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7006UserRegistrationSpecificStylistLandingPageTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7006UserRegistrationSpecificStylistLandingPageTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");

		expectedStatus = "Bestätigt";
	}

	@Test
	public void us7006CheckCustomerActivationTest() {

		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(clientName);
		backEndSteps.openCustomerDetails(clientName);
		grabStatus = backEndSteps.extractEmailConfirmationStatus();

//		System.out.println("grabStatus: " + grabStatus);
//		System.out.println("expectedStatus: " + expectedStatus);
		stylistValidationSteps.validateStatus(grabStatus, expectedStatus);
		
		customVerifications.printErrors();
	}

}
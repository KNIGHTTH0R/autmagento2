package com.tests.us6;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.validations.StylistValidationSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US6001", type = "external")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US6001CheckStylistActivationTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public StylistValidationSteps stylistValidationSteps;

	public CustomerConfigurationModel customerConfigurationModel = new CustomerConfigurationModel();

	public String stylistEmail;
	public String validateEmail;
	public String validateAccount;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabStylistFormModels("US6001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabStylistFormModels("US6001StyleCoachRegistrationTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us000CheckCustomerActivation() {

		// Confirm Email
		emailClientSteps.openMailinator();
		validateEmail = emailClientSteps.grabEmail(stylistEmail.replace("@" + Constants.WEB_MAIL, ""));
		System.out.println(validateEmail);
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		validateAccount = backEndSteps.extractEmailConfirmationStatus();		
		stylistValidationSteps.validateProducts(validateEmail, Constants.CONFIRMED);
		customVerifications.printErrors();
	}


}
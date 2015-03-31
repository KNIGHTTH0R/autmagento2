package com.tests.us0;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;

@WithTag(name = "US000", type = "external")
//@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US000CheckCustomerActivationTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps 
	public CustomVerification customVerifications;

	public CustomerConfigurationModel customerConfigurationModel = new CustomerConfigurationModel();

	public String clientName;
	public String validateEmail;
	public String validateAccount;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US000CreateCustomerTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US000CreateCustomerTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us000CheckCustomerActivation() {

		// Confirm Email
		emailClientSteps.openMailinator();
		validateEmail = emailClientSteps.grabEmail(clientName.replace("@" + Constants.WEB_MAIL, ""));
		System.out.println(validateEmail);
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(clientName);
		backEndSteps.openCustomerDetails(clientName);
		validateAccount = backEndSteps.extractEmailConfirmationStatus();

		System.out.println("validateAccount: " + validateAccount);
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {

		boolean accountActive = false;
		boolean emailActive = false;
		try {
			if (validateEmail.contains("Willkommen")) {
				emailActive = true;
			}
			// protect config file from special chars in German -- Bestätigt
			if (validateAccount.contains("Bestätigt")) {
				accountActive = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		customerConfigurationModel.setEmailActive(String.valueOf(emailActive));
		customerConfigurationModel.setAccountActive(String.valueOf(accountActive));

		MongoWriter.saveCustomerConfigurationModel(customerConfigurationModel, getClass().getSimpleName());
	}

}
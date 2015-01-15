package com.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.BackEndSteps;
import com.steps.EmailClientSteps;
import com.tools.Constants;
import com.tools.requirements.Application;

@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class CheckCustomerActivationTest extends BaseTest{



	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public EmailClientSteps emailClientSteps;

	public String clientName;
	public String validateEmail;
	public String validateAccount;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "Customer.properties");
			prop.load(input);
			clientName = prop.getProperty("clientName");

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
	public void checkCustomerActivation() {

		// Confirm Email
		emailClientSteps.openMailinator();
		validateEmail = emailClientSteps.grabEmail(clientName.replace("@"
				+ Constants.WEB_MAIL, ""));

		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.redirectToManageCustomers();
		backEndSteps.searchForEmail(clientName);
		backEndSteps.openCustomerDetails(clientName);
		validateAccount = backEndSteps.extractEmailConfirmationStatus();
		
		System.out.println("validateAccount: " + validateAccount);
	}

	@After
	public void saveData() {
		Properties prop2 = new Properties();
		OutputStream output = null;
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

		try {
			output = new FileOutputStream(Constants.RESOURCES_PATH + "CustomerConfirmation.properties");

			prop2.setProperty("accountActive", String.valueOf(accountActive));
			prop2.setProperty("emailActive", String.valueOf(emailActive));

			prop2.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
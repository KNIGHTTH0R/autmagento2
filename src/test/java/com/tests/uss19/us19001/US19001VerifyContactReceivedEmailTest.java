package com.tests.uss19.us19001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.steps.EmailSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.EmailConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US10", type = "external")
@Story(Application.StyleParty.class)
@RunWith(SerenityRunner.class)
public class US19001VerifyContactReceivedEmailTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailSteps emailSteps;

	private String emailUsername, emailPassword,username,password;
	public CustomerFormModel contactData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss19" + File.separator + "us19001.properties");

			prop.load(input);
			emailUsername = prop.getProperty("emailUsername");
			emailPassword = prop.getProperty("emailPassword");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			System.out.println(emailUsername);
			System.out.println(emailPassword);

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

		contactData = MongoReader.grabCustomerFormModels("US19001UnregisteredConnectWithMeOnMasterSearchByPlzTest").get(0);

		EmailCredentialsModel emailData = new EmailCredentialsModel();

		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(emailUsername);
		emailData.setPassword(emailPassword);

		gmailConnector = new GmailConnector(emailData);

	}

	@Test
	public void us19001VerifyContactReceivedEmailTest() {

		frontEndSteps.performLogin(username, password);
		System.out.println(contactData.getFirstName() + " " + contactData.getLastName());
		gmailConnector.searchForMail("", contactData.getFirstName() + " " + contactData.getLastName(), false);

	}
}

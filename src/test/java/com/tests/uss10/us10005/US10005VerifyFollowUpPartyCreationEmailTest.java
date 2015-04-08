package com.tests.uss10.us10005;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.steps.EmailSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.EmailConstants;
import com.tools.data.UrlModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.persistance.MongoWriter;

@WithTag(name = "US10005", type = "external")
// @Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US10005VerifyFollowUpPartyCreationEmailTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailSteps emailSteps;

	private String email, password, emailPassword;

	UrlModel urlModel = new UrlModel();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			email = prop.getProperty("customerUsername");
			password = prop.getProperty("customerPassword");
			emailPassword = prop.getProperty("customerPassword");

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

		EmailCredentialsModel emailData = new EmailCredentialsModel();

		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);

		gmailConnector = new GmailConnector(emailData);

	}

	@Test
	public void us10005VerifyFollowUpPartyCreationEmailTest() {

		frontEndSteps.performLogin(email, password);

		String message = gmailConnector.searchForMail("", Constants.PARTY_CREATION_EMAIL_SUBJECT, true);
		urlModel.setUrl(emailSteps.extractUrlFromEmailMessage(message,"customer/party/confirm"));

	}

	@After
	public void saveData() {
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + Constants.GRAB);
	}
}

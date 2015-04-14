package com.tests.uss10.us10006;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
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
import com.tools.CustomVerification;
import com.tools.EmailConstants;
import com.tools.SoapKeys;
import com.tools.data.UrlModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.env.stagingaut.ContextConstants;
import com.tools.env.stagingaut.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US10006", type = "external")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10006VerifyInviteForStylistHostEmailTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;

	private String email, password, emailPassword;
	private UrlModel urlModel = new UrlModel();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);

			email = prop.getProperty("username");
			password = prop.getProperty("password");
			emailPassword = prop.getProperty("emailPassword");

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
	public void us10006VerifyInviteForStylistHostEmailTest() {

		frontEndSteps.performLogin(email, password);

		String message = gmailConnector.searchForMail("", ContextConstants.PARTY_CREATION_EMAIL_SUBJECT, true);
		urlModel.setUrl(emailSteps.extractUrlFromEmailMessage(message, "customer/party/confirm"));
		System.out.println(urlModel.getUrl());
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

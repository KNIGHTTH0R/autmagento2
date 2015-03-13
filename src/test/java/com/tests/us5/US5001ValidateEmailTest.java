package com.tests.us5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.steps.EmailSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProfileSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.EmailConstants;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.requirements.Application;

@WithTag(name = "US5000", type = "external")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US5001ValidateEmailTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;

	private String fbUser, pippaPass;
	private String emailUser, emailPass, genName;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(Constants.RESOURCES_PATH + Constants.US_05_FOLDER + File.separator + "us0005.properties");
			prop.load(input);
			fbUser = prop.getProperty("fbUser");
			pippaPass = prop.getProperty("pippaPass");
			emailUser = prop.getProperty("emailUser");
			emailPass = prop.getProperty("emailPass");
			genName = prop.getProperty("genName");

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
		emailData.setUsername(emailUser);
		emailData.setPassword(emailPass);

		gmailConnector = new GmailConnector(emailData);
	}

	@Test
	public void us5001ValidateEmailTest() {
		frontEndSteps.performLogin(fbUser, pippaPass);

		String message = gmailConnector.searchForMail("", "Willkommen, " + genName, false);

		System.out.println(message);
		emailSteps.validateEmailContent(pippaPass, message);

		customVerifications.printErrors();
	}

}
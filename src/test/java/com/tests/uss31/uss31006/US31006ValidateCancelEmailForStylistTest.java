package com.tests.uss31.uss31006;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.constants.EmailConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US31.1 TP execution cron - Manual", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_3.class)
@RunWith(SerenityRunner.class)
public class US31006ValidateCancelEmailForStylistTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public EmailSteps emailSteps;

	private String email, password, emailPassword;
	private TermPurchaseOrderModel termPurchaseModel = new TermPurchaseOrderModel();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3007.properties");
			prop.load(input);
			email = prop.getProperty("username");
			password = prop.getProperty("password");
			emailPassword = prop.getProperty("emailPass");


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

		termPurchaseModel = MongoReader.grabTermPurchaseOrderModel("US31006ValidateCanceledAndReleasedByAdminOrdersInTpGridTest"+"TP1").get(0);

		EmailCredentialsModel emailData = new EmailCredentialsModel();

		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);

		gmailConnector = new GmailConnector(emailData);
		
	}

	@Test
	public void us31006ValidateCancelEmailForStylistTest() throws ParseException {
		frontEndSteps.performLogin(email, password);

		String message = gmailConnector.searchForMail("", "Bestellung stornieren", true);
		System.out.println(message);
		emailSteps.validateEmailContent("von Admin storniert", message);
		emailSteps.validateEmailContent(termPurchaseModel.getIncrementId(), message);


	}
}

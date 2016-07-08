package com.tests.uss31.uss31001;

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
import com.steps.frontend.ProfileSteps;
import com.tests.BaseTest;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.env.constants.EmailConstants;
import com.tools.env.constants.FilePaths;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US3.2 Shop for myself VAT valid and no SMB billing DE and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_2.class)
@RunWith(SerenityRunner.class)
public class US31001ValidateCancelEmailForStylist1Test extends BaseTest {

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

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_31_FOLDER + File.separator + "us31001.properties");
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

		termPurchaseModel = MongoReader.grabTermPurchaseOrderModel("US31001ValidateCanceledAndReleasedOrdersInTpGridTest"  + "TP1").get(0);

		EmailCredentialsModel emailData = new EmailCredentialsModel();

		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);

		gmailConnector = new GmailConnector(emailData);
		
	}

	@Test
	public void us31001ValidateCancelEmailForStylist1Test() throws ParseException {
		frontEndSteps.performLogin(email, password);

		String message = gmailConnector.searchForMail("", "Bestellung stornieren", true);
		System.out.println(message);
		emailSteps.validateEmailContent("da ein oder mehrere Artikel ausverkauft sind storniert", message);
		emailSteps.validateEmailContent(termPurchaseModel.getIncrementId(), message);


	}
}

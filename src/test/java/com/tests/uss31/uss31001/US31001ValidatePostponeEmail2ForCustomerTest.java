package com.tests.uss31.uss31001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Locale;
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
import com.tools.constants.EmailConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US3.2 Shop for myself VAT valid and no SMB billing DE and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_2.class)
@RunWith(SerenityRunner.class)
public class US31001ValidatePostponeEmail2ForCustomerTest extends BaseTest {

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
			email = prop.getProperty("customerUsername");
			password = prop.getProperty("customerPassword");
			emailPassword = prop.getProperty("customerEmailPassword");

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

		termPurchaseModel = MongoReader.grabTermPurchaseOrderModel("US31001ValidatePostponedOrdersInTpGrid2Test"+ "TP5").get(0);

		EmailCredentialsModel emailData = new EmailCredentialsModel();

		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);

		gmailConnector = new GmailConnector(emailData);
		
		System.out.println("dsdsdsdsd");
		System.out.println("SDSDSD " + DateUtils.parseDate(termPurchaseModel.getExecutionDate(), "dd-MM-yyyy", "dd MMM yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
	}

	@Test
	public void us31001ValidatePostponeEmail2ForCustomerTest() throws ParseException {
	
		frontEndSteps.performLogin(email, password);

		String message = gmailConnector.searchForMail("", "Bestellung verschieben", true);
		System.out.println(message);
		emailSteps.validateEmailContent("wegen niedrigem Warenbestand verschoben.", message);
		emailSteps.validateEmailContent(termPurchaseModel.getIncrementId(), message);
	
		emailSteps
				.validateEmailContent(
						DateUtils.parseDate(termPurchaseModel.getExecutionDate(), "yyyy-MM-dd", "dd MMM yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build()),
						message);

	}

}

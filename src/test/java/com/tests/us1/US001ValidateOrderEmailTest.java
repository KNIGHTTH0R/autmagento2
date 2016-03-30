package com.tests.us1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.env.constants.EmailConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US1 Shop for myself", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US1.class)
@RunWith(SerenityRunner.class)
public class US001ValidateOrderEmailTest extends BaseTest {

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

	private String email, emailPass;
	private String username, password;
	private List<OrderModel> orderModel = new ArrayList<OrderModel>();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us1" + File.separator + "us001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			email = prop.getProperty("email");
			emailPass = prop.getProperty("emailPass");

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

		orderModel = MongoReader.getOrderModel("US001StyleCoachShoppingTest" + SoapKeys.GRAB);

		EmailCredentialsModel emailData = new EmailCredentialsModel();
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPass);  
		gmailConnector = new GmailConnector(emailData);
	}

	@Test
	public void us001ValidateOrderEmailTest() {
		frontEndSteps.performLogin(username, password);

		String message = gmailConnector.searchForMail("", orderModel.get(0).getOrderId(), false);
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);

		customVerifications.printErrors();
	}

}

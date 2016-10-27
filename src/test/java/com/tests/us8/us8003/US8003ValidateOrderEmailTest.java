package com.tests.us8.us8003;

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
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EmailConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US8.3 Customer Buy With 40% Discount,JB and Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.RegularCart.US8_3.class)
@RunWith(SerenityRunner.class)
public class US8003ValidateOrderEmailTest extends BaseTest{
	
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
	
	private String email, password, emailPassword;
	private List<OrderModel> orderModel = new ArrayList<OrderModel>();
	
	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8003.properties");
			prop.load(input);
			email = prop.getProperty("email");
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
		
		orderModel = MongoReader.getOrderModel("US8003CustomerBuyWithForthyDiscountsJbAndBuy3Get1Test" + SoapKeys.GRAB);
		
		EmailCredentialsModel emailData = new EmailCredentialsModel();
		
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);
        
		gmailConnector = new GmailConnector(emailData);
	}
	
	@Test
	public void us8003ValidateOrderEmailTest() {
		frontEndSteps.performLogin(email, password);
		
		String message = gmailConnector.searchForMail("", orderModel.get(0).getOrderId(), false);
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);
		
		customVerifications.printErrors();
	
	}

}

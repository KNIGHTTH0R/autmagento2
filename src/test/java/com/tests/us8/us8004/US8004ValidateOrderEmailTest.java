package com.tests.us8.us8004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import com.tools.CustomVerification;
import com.tools.EmailConstants;
import com.tools.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US8.4 Customer Buy With Kobo", type = "Scenarios")
@Story(Application.RegularCart.US8_4.class)
@RunWith(ThucydidesRunner.class)
public class US8004ValidateOrderEmailTest extends BaseTest{
	
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

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8004.properties");
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
		
		orderModel = MongoReader.getOrderModel("US8004CustomerBuyWithContactBoosterTest" + SoapKeys.GRAB);
		
		EmailCredentialsModel emailData = new EmailCredentialsModel();
		
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);
		System.out.println(email);
		System.out.println(password);
		System.out.println(emailPassword);
        
		gmailConnector = new GmailConnector(emailData);
	}
	
	@Test
	public void us8004ValidateOrderEmailTest() {
		frontEndSteps.performLogin(email, password);
		
		String message = gmailConnector.searchForMail("", orderModel.get(0).getOrderId(), false);
		System.out.println(message);
		System.out.println(orderModel.get(0).getOrderId());
		System.out.println(orderModel.get(0).getTotalPrice());
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);
		
		customVerifications.printErrors();
	
	}

}

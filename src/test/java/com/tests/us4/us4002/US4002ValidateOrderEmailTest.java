package com.tests.us4.us4002;

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

@WithTag(name = "US4.2 Shop for myself with Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_2.class)
@RunWith(ThucydidesRunner.class)
public class US4002ValidateOrderEmailTest extends BaseTest{
	
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

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us4" + File.separator + "us4002.properties");
			prop.load(input);
			email = prop.getProperty("email");
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
		
		orderModel = MongoReader.getOrderModel("US4002ShopForMyselfWithBuy3GetOneTest" + SoapKeys.GRAB);
		
		EmailCredentialsModel emailData = new EmailCredentialsModel();
		
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);
        
		gmailConnector = new GmailConnector(emailData);
	}
	
	@Test
	public void us4002ValidateOrderEmailTest() {
		frontEndSteps.performLogin(email, password);
		
		String message = gmailConnector.searchForMail("", orderModel.get(0).getOrderId(), false);
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);
		customVerifications.printErrors();
	
	}

}

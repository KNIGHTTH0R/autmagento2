package com.tests.uss34.us34001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.steps.EmailSteps;
import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EmailConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.1 Shop for myself with any bonnus applied",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US3001ValidateClosedOrderEmailsTestVDV extends BaseTest{
	
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps 
	public CustomVerification customVerifications;
	public String message;
	private String username, emailPassword;
	private List<OrderModel> orderModel = new ArrayList<OrderModel>();
	
	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			emailPassword = prop.getProperty("Emailpassword");

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
		
		orderModel = MongoReader.getOrderModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.GRAB);
		
		EmailCredentialsModel emailData = new EmailCredentialsModel();
		
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(username);
		emailData.setPassword(emailPassword);
        
		gmailConnector = new GmailConnector(emailData);
		System.out.println(orderModel.get(0).getOrderId());
	
	}
	
	@Test
	public void us3001ValidateClosedOrderEmailsTestVDV() {
		//frontEndSteps.performLogin(username, password);
	    message = gmailConnector.searchForMail("", "Rechnung", true);
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);
		
		emailSteps.waitABit(2000);
		
		 message = gmailConnector.searchForMail("", "Versand", true);
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);
		
		emailSteps.waitABit(2000);

		 message = gmailConnector.searchForMail("", "Gutschrift", true);
		emailSteps.validateEmailContent(orderModel.get(0).getOrderId(), message);
	
		
	//	gmailConnector.deleteInbox();
		customVerifications.printErrors();
	}

}

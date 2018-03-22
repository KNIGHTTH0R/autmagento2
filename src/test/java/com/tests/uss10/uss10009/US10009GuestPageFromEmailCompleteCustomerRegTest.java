package com.tests.uss10.uss10009;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.steps.EmailSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.PartyHostGuestSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EmailConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;


@WithTag(name = "US10.9 From Email Grab Guest Link Page and Than complete customer Registration",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_2.class)
@RunWith(SerenityRunner.class)
public class US10009GuestPageFromEmailCompleteCustomerRegTest extends BaseTest{
	
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
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public PartyHostGuestSteps partyHostGuestSteps;
	
	private AddressModel addressModel;
	private CustomerFormModel customerData;
	
	private String email, password, emailPassword;
	private List<OrderModel> orderModel = new ArrayList<OrderModel>();
	
	@Before
	public void setUp() throws Exception {
		
		customerData =new CustomerFormModel();
		addressModel=new AddressModel();
		
		/*customerData.setFirstName("edi");
		customerData.setLastName("wilkman");
		customerData.setEmailName("test2231@evozon.com");*/
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			email = prop.getProperty("customerUsername");
			password = prop.getProperty("customerPassword");
			emailPassword = prop.getProperty("customerPassword");

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
		
	//	orderModel = MongoReader.getOrderModel("US3002SfmValidVatNoSmbBillingDeShippingAtTest" + SoapKeys.GRAB);
		
		EmailCredentialsModel emailData = new EmailCredentialsModel();
		
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);
        
		gmailConnector = new GmailConnector(emailData);
		MongoConnector.cleanCollection(getClass().getSimpleName());

	}
	
	@Test
	public void us10009GuestPageFromEmailCompleteCustomerRegTest() {
		String message = gmailConnector.searchForMail("", "PIPPA&JEAN Einladung zur Style Party", false);
		System.out.println("index: "+message.indexOf("http://vdv-qa.pippajean.com/p/g/"));
		
		String linkparty=message.substring(message.indexOf("http://vdv-qa.pippajean.com/p"), message.indexOf("/g/")+9);
		headerSteps.navigate(linkparty);
		
		
		partyHostGuestSteps.acceptInvitation();
		partyHostGuestSteps.fillAcceptInvitationForm(customerData);
		partyHostGuestSteps.clickOnCustomerRegistrationLink(); 
		partyDetailsSteps.switchToNewestOpenedTab();
		customerRegistrationSteps.fillCreateCustomerFormUnderScContext(customerData, addressModel, "emx");
		customerRegistrationSteps.verifyCustomerCreation();

		//System.out.println(linkparty.substring(linkparty.indexOf('"'), linkparty));
		
	//	emailSteps.validateEmailContent("PIPPA&JEAN Einladung zur Style Party", message);
		gmailConnector.deleteInbox();
		customVerifications.printErrors();
	
	}
	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerData, getClass().getSimpleName());
		MongoWriter.saveAddressModel(addressModel, getClass().getSimpleName());
	}

}

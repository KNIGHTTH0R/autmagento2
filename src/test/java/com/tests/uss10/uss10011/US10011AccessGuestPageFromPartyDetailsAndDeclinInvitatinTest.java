package com.tests.uss10.uss10011;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.steps.EmailSteps;
import com.steps.external.faceboook.FacebookRegistrationSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.PartyHostGuestSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EmailConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;


@WithTag(name = "US10.9 From Email Grab Guest Link Page and Than complete customer Registration",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_2.class)
@RunWith(SerenityRunner.class)
public class US10011AccessGuestPageFromPartyDetailsAndDeclinInvitatinTest extends BaseTest{
	
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
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;
	
	private AddressModel addressModel;
	private CustomerFormModel customerData;
	
	private String email, password, emailPassword;
//	private List<OrderModel> orderModel = new ArrayList<OrderModel>();
	
	private UrlModel urlModel = new UrlModel();
	@Before
	public void setUp() throws Exception {
		
		customerData =new CustomerFormModel();
		addressModel=new AddressModel();
		
		customerData.setFirstName("Valeria");
		customerData.setLastName("Rawqa");
		customerData.setEmailName("rawa_trm_vdv@evozon.com");
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			email = prop.getProperty("username");
			password = prop.getProperty("password");
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
		
		urlModel = MongoReader.grabUrlModels("US10011CreatePartyWithNewContactTest").get(0);

		EmailCredentialsModel emailData = new EmailCredentialsModel();
		
		emailData.setHost(EmailConstants.RECEIVING_HOST);
		emailData.setProtocol(EmailConstants.PROTOCOL);
		emailData.setUsername(email);
		emailData.setPassword(emailPassword);
        
		gmailConnector = new GmailConnector(emailData);
	}
	
	@Test
	public void us10011AccessGuestPageFromPartyDetailsAndDeclinInvitatinTest() {
		frontEndSteps.performLogin(email, password);
		customerRegistrationSteps.navigate(urlModel.getUrl());
		String partyLink=partyDetailsSteps.grabPartyLink();
		headerSteps.performLogOut();
		headerSteps.navigate(partyLink);
		//partyDetailsSteps.clickOnPartyLink();
		//partyDetailsSteps.switchToNewestOpenedTab();
		partyHostGuestSteps.openGuestPartyPage();
		
		partyHostGuestSteps.acceptInvitation();
		partyHostGuestSteps.fillAcceptInvitationForm(customerData);
		
		partyHostGuestSteps.declineInvitation();
		
		partyHostGuestSteps.clickOnCustomerRegistrationLink(); 
		partyDetailsSteps.switchToNewestOpenedTab();
		customerRegistrationSteps.verifyPrepopulatedFields(customerData, addressModel);
		
		customVerifications.printErrors();
	
	}

}

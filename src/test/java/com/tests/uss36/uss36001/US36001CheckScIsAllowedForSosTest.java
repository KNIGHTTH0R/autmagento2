package com.tests.uss36.uss36001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.customer.CustomerDetailsBackendSteps;
import com.steps.backend.stylecoach.StylecoachSalesOnSpeedBackendSteps;
import com.steps.frontend.ContactDetailsSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.steps.frontend.profile.SosSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US8.1 Customer Buy With Forthy Discounts And Jb Test", type = "Scenarios")
@Story(Application.RegularCart.US8_1.class)
@RunWith(SerenityRunner.class)
public class US36001CheckScIsAllowedForSosTest extends BaseTest{
	
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public ProfileNavSteps profileNavSteps;
	@Steps 
	public LoungeSteps loungeSteps;
	@Steps 
	public SosSteps sosSteps;
	@Steps 
	public BackEndSteps backEndSteps;
	@Steps 
	public ContactDetailsSteps contactDetailsSteps;
	@Steps 
	public CustomerDetailsBackendSteps customerDetailsBackendSteps;
	@Steps 
	public StylecoachSalesOnSpeedBackendSteps stylecoachSalesOnSpeedBackendSteps;
	
	
	private static OrderModel orderModel = new OrderModel();
	private String username, password;
	

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss36" + File.separator + "us36001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");			

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


		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
		
		
	}
	
	@Test
	public void us36001CheckScIsAllowedForSosTest() throws Exception {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(username);
		backEndSteps.openCustomerDetails(username);
		customerDetailsBackendSteps.clickOnSalesOnSpeedInfo();
		stylecoachSalesOnSpeedBackendSteps.selectAllowSosSync("Ja");
		stylecoachSalesOnSpeedBackendSteps.checkSosPassword();
		stylecoachSalesOnSpeedBackendSteps.checkIsPresentResetAccountButton();
		stylecoachSalesOnSpeedBackendSteps.checkIsPresentResetContactButton();
		
		frontEndSteps.performLogin(username,password);
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.goToProfile();
		profileNavSteps.selectMenu(ContextConstants.SALESONSPEED_INFO);
		sosSteps.verifySosMessage();
		headerSteps.goToLounge();
		loungeSteps.goToContactsList();
		contactDetailsSteps.checkIsPresentSosButton();
	    
		sosSteps.getSosUserEmail();
		sosSteps.getSosPass();
		
		
		customVerifications.printErrors();
	}
}

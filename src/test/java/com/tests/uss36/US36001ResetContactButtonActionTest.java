package com.tests.uss36;

import org.junit.After;
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
import com.tools.constants.Credentials;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.SosContactModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US36001ResetContactButtonActionTest extends BaseTest {
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public CustomerDetailsBackendSteps customerDetailsBackendSteps;
	@Steps 
	public ProfileNavSteps profileNavSteps;
	@Steps 
	public LoungeSteps loungeSteps;
	@Steps 
	public SosSteps sosSteps;
	@Steps 
	public ContactDetailsSteps contactDetailsSteps;
	@Steps 
	public StylecoachSalesOnSpeedBackendSteps stylecoachSalesOnSpeedBackendSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	
	SosContactModel sosContact=new SosContactModel();
	String sosPassword;
	String username, password;
	public CustomerFormModel stylistRegistrationData;

	@Before
	public void setUp() {

		
		int size = MongoReader.grabCustomerFormModels("US36001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US36001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		username=stylistRegistrationData.getEmailName();
		password=stylistRegistrationData.getPassword();
	
		MongoConnector.cleanCollection(getClass().getSimpleName());




	}

	/**
	 * BackEnd steps in this test
	 * @throws Exception 
	 */
	@Test
	public void us36001ResetContactButtonActionTest() throws Exception {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(username);
		backEndSteps.openCustomerDetails(username);
		
		String stylistId=stylecoachSalesOnSpeedBackendSteps.grabStyleCoachID();
		
		customerDetailsBackendSteps.clickOnSalesOnSpeedInfo();
		
		stylecoachSalesOnSpeedBackendSteps.clickOnResetContactsButton();
		stylecoachSalesOnSpeedBackendSteps.validateSuccessMessage();
		sosPassword=stylecoachSalesOnSpeedBackendSteps.checkSosPassword();
		

//		
		sosContact.setSosPassword(sosPassword);
		sosContact.setSosUserEmail(username);
		sosContact.setStylistId(stylistId);
	
	
	}

	@After
	public void saveData() {
		MongoWriter.saveSosCustomerFormModel(sosContact, getClass().getSimpleName());
	}
}
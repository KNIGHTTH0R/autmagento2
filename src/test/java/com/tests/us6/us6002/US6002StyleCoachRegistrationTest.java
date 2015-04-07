package com.tests.us6.us6002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;


@WithTag(name = "US6", type = "frontend")
@Story(Application.Registration.Stylist.class)
@RunWith(ThucydidesRunner.class)
public class US6002StyleCoachRegistrationTest extends BaseTest{
	
	@Steps
	public HeaderSteps headerSteps;	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public EmailClientSteps emailClientSteps;	
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public static DateModel formDate = new DateModel();
	public AddressModel stylistAddressModel;
	public StylistPropertiesModel expectedBeforeLinkConfirmationStylistData = new StylistPropertiesModel();
	
	public CustomerFormModel stylistData = new CustomerFormModel("");
	public String birthDate;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		stylistAddressModel = new AddressModel();
		birthDate = "Feb,1970,12";
		expectedBeforeLinkConfirmationStylistData =  new StylistPropertiesModel(Constants.NOT_CONFIRMED, Constants.JEWELRY_INITIAL_VALUE, Constants.GENERAL);
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
		int size = MongoReader.grabCustomerFormModels("US6002CreateCustomerTest").size();
		if (size > 0) {
			stylistData = MongoReader.grabCustomerFormModels("US6002CreateCustomerTest").get(0);
		} else
			Assert.assertTrue("Failure: No test data has been found.", false);
	}
	
	@Test
	public void us6002StyleCoachRegistrationTest() {
		
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistData.getEmailName());
		backEndSteps.openCustomerDetails(stylistData.getEmailName());
		StylistPropertiesModel grabBeforeLinkConfirmationStylistData =  backEndSteps.grabCustomerConfiguration();
		
		//confirmation link
		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(stylistData.getEmailName().replace("@" + Constants.WEB_MAIL, ""),"Benutzerkonto");
		
		headerSteps.navigateToRegisterFormAndLogout();
		stylistRegistrationSteps.clickLoginLinkFromMessage();
		customerRegistrationSteps.performLogin(stylistData.getEmailName(), stylistData.getPassword());
		String formCreationDate = stylistRegistrationSteps.fillStylistRegistrationPredefinedInfoForm(stylistData.getFirstName(), stylistAddressModel, birthDate);
		formDate.setDate(formCreationDate);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabBeforeLinkConfirmationStylistData, expectedBeforeLinkConfirmationStylistData);	
		customerAndStylistRegistrationWorkflows.validateStylistProperties("BEFORE CONFIRMATION LINK");
		
		customVerifications.printErrors();		
	}
	
	@After
	public void saveData() {
		MongoWriter.saveDateModel(formDate, getClass().getSimpleName());
	}
}

package com.tests.us7.us7002;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US7.2 Regular Customer Registration on Context Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_2.class)
@RunWith(SerenityRunner.class)
public class US7002CheckCustomerActivation extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private StylistPropertiesModel expectedCustomerData = new StylistPropertiesModel();
	private String stylistEmail;

	@Before
	public void setUp() throws Exception {
		int size = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		expectedCustomerData =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		
	}
	@Test
	public void us7002CheckCustomerActivation() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		
		StylistPropertiesModel grabCustomerData =  backEndSteps.grabCustomerConfiguration();		
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabCustomerData, expectedCustomerData);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(grabCustomerData);
		PrintUtils.printStylistPropertiesModel(expectedCustomerData);
			
		customVerifications.printErrors();
	}


}


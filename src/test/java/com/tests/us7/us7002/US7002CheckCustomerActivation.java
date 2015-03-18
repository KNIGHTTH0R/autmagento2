package com.tests.us7.us7002;


import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US7002", type = "backend")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US7002CheckCustomerActivation extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public StylistPropertiesModel expectedCustomerData = new StylistPropertiesModel();
	public String stylistEmail;

	@Before
	public void setUp() throws Exception {
		int size = MongoReader.grabCustomerFormModels("US7002RegularCustomerRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7002RegularCustomerRegistrationTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		expectedCustomerData =  new StylistPropertiesModel(Constants.CONFIRMED, Constants.JEWELRY_INITIAL_VALUE, Constants.GENERAL);
		
	}
	@Test
	public void us7002CheckCustomerActivation() {
		
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
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


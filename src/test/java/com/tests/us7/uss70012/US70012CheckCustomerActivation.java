package com.tests.us7.uss70012;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.12 Kobo Campaign Registration On Context Test ", type = "Scenarios")
@Story(Application.KoboCampaign.US7_11.class)
@RunWith(SerenityRunner.class)
public class US70012CheckCustomerActivation extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private StylistPropertiesModel afterLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	private String stylistEmail;

	@Before
	public void setUp() throws Exception {
		

		int size = MongoReader.grabCustomerFormModels("US70012KoboCampaignRegistrationUnderContextTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US70012KoboCampaignRegistrationUnderContextTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		afterLinkConfirmationStylistExpectedProperties =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		
	}
	@Test
	public void us70012CheckCustomerActivation() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		
		StylistPropertiesModel afterLinkConfirmationStylistProperties =  backEndSteps.grabCustomerConfiguration();		
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(afterLinkConfirmationStylistProperties, afterLinkConfirmationStylistExpectedProperties);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
			
		customVerifications.printErrors();
	}


}


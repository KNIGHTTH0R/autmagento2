package com.tests.us7.uss70011;


import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

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

@WithTag(name = "US7.11 Kobo Campaign Registration On Master Test ", type = "Scenarios")
@Story(Application.KoboCampaign.US7_11.class)
@RunWith(ThucydidesRunner.class)
public class US70011CheckCustomerActivation extends BaseTest {

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
		

		int size = MongoReader.grabCustomerFormModels("US70011KoboCampaignRegistrationOnMasterTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US70011KoboCampaignRegistrationOnMasterTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		afterLinkConfirmationStylistExpectedProperties =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		
	}
	@Test
	public void us70011CheckCustomerActivation() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		
		StylistPropertiesModel afterLinkConfirmationStylistProperties =  backEndSteps.grabCustomerConfiguration();		
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(afterLinkConfirmationStylistProperties, afterLinkConfirmationStylistExpectedProperties);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(afterLinkConfirmationStylistProperties);
		PrintUtils.printStylistPropertiesModel(afterLinkConfirmationStylistExpectedProperties);
			
		customVerifications.printErrors();
	}


}


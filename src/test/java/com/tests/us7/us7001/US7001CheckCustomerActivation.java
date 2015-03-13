package com.tests.us7.us7001;


import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.StylistValidationSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.backend.StylistRegistrationAndActivationDateModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US7001", type = "external,backend")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US7001CheckCustomerActivation extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps	
	public OrdersSteps ordersSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public StylistValidationSteps stylistValidationSteps;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public CustomerConfigurationModel customerConfigurationModel = new CustomerConfigurationModel();
	
	public StylistPropertiesModel beforeLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public StylistPropertiesModel afterLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public StylistPropertiesModel afterOrderPaidStylistExpectedProperties = new StylistPropertiesModel();
	public StylistRegistrationAndActivationDateModel datesModel = new StylistRegistrationAndActivationDateModel();

	public String stylistEmail;

	@Before
	public void setUp() throws Exception {
		

		int size = MongoReader.grabStylistFormModels("US7001RegularCustomerRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabStylistFormModels("US7001RegularCustomerRegistrationTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		afterLinkConfirmationStylistExpectedProperties =  new StylistPropertiesModel(Constants.CONFIRMED, Constants.JEWELRY_INITIAL_VALUE, Constants.GENERAL);
		
	}
	@Test
	public void us7001CheckCustomerActivation() {
		
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
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


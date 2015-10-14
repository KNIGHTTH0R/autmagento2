package com.tests.us6.us6002b;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US6", type = "backend")
@Story(Application.Registration.Stylist.class)
@RunWith(ThucydidesRunner.class)
public class US6002bCheckStylistActivationTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public StylistPropertiesModel expectedAfterLinkConfirmationStylistData = new StylistPropertiesModel();
	public StylistPropertiesModel expectedAfterOrderPaidStylistData = new StylistPropertiesModel();
	public RegistrationActivationDateModel expectedDateModel = new RegistrationActivationDateModel();

	public CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {
		
		String formDateCreation = MongoReader.grabStylistDateModels("US6002bScRegExistingCustForbiddenCountryTest").get(0).getDate();
		int size = MongoReader.grabCustomerFormModels("US6002bCreateCustomerTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US6002bCreateCustomerTest").get(0);
		} else
			System.out.println("The database has no entries");

		
		expectedAfterLinkConfirmationStylistData =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		expectedAfterOrderPaidStylistData =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_FINAL_VALUE, ConfigConstants.STYLIST);
		expectedDateModel = new RegistrationActivationDateModel(formDateCreation,formDateCreation);
		
	}

	@Test
	public void us6002bCheckStylistActivationTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		
		StylistPropertiesModel grabAfterLinkConfirmationStylistData =  backEndSteps.grabCustomerConfiguration();

		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByName(stylistRegistrationData.getFirstName());
		backEndSteps.openOrderDetails(stylistRegistrationData.getFirstName());		
		ordersSteps.markOrderAsPaid();	
		
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		
		StylistPropertiesModel grabAfterOrderPaidStylistData =  backEndSteps.grabCustomerConfiguration();	
		RegistrationActivationDateModel grabDatesModel = backEndSteps.grabStylistRegistrationAndConfirmationDates();
	
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabAfterLinkConfirmationStylistData, expectedAfterLinkConfirmationStylistData);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(grabAfterLinkConfirmationStylistData);
		PrintUtils.printStylistPropertiesModel(expectedAfterLinkConfirmationStylistData);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabAfterOrderPaidStylistData, expectedAfterOrderPaidStylistData);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER MARK AS PAID");
		PrintUtils.printStylistPropertiesModel(grabAfterOrderPaidStylistData);
		PrintUtils.printStylistPropertiesModel(expectedAfterOrderPaidStylistData);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistDates(grabDatesModel,expectedDateModel);
		customerAndStylistRegistrationWorkflows.validateStylistDAtes("VALIDATE REGISTRATION AND ACTIVATION DATES");
		
		customVerifications.printErrors();
	}

}

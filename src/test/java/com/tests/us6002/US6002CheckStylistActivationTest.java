package com.tests.us6002;

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

@WithTag(name = "US6002", type = "external,backend")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US6002CheckStylistActivationTest extends BaseTest {

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
	
	
	public StylistPropertiesModel afterLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public StylistPropertiesModel afterOrderPaidStylistExpectedProperties = new StylistPropertiesModel();
	public StylistRegistrationAndActivationDateModel datesModel = new StylistRegistrationAndActivationDateModel();

	public String customerType;
	public String stylistFirstName;
	public String stylistEmail;
	public String confirmationEmail;
	public String validateAccount;
	public String jewelryValue;
	public String date;
	public String email2;

	@Before
	public void setUp() throws Exception {
		
		date = MongoReader.grabStylistDateModels("US6002StyleCoachRegistrationTest").get(0).getDate();
		int size = MongoReader.grabStylistFormModels("US6002CreateCustomerTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabStylistFormModels("US6002CreateCustomerTest").get(0).getEmailName();
			stylistFirstName = MongoReader.grabStylistFormModels("US6002CreateCustomerTest").get(0).getFirstName();
			System.out.println("email " + stylistEmail);
			System.out.println("name " + stylistFirstName);
		} else
			System.out.println("The database has no entries");

		
		afterLinkConfirmationStylistExpectedProperties =  new StylistPropertiesModel(Constants.CONFIRMED, Constants.JEWELRY_INITIAL_VALUE, Constants.GENERAL);
		afterOrderPaidStylistExpectedProperties =  new StylistPropertiesModel(Constants.CONFIRMED, Constants.JEWELRY_FINAL_VALUE, Constants.STYLIST);
		datesModel = new StylistRegistrationAndActivationDateModel(date,date);
		
	}


	@Test
	public void us6002CheckStylistActivationTest() {

		
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		
		StylistPropertiesModel afterLinkConfirmationStylistProperties =  backEndSteps.grabCustomerConfiguration();

		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByName(stylistFirstName);
		backEndSteps.openOrderDetails(stylistFirstName);		
		ordersSteps.markOrderAsPaid();	
		
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		
		StylistPropertiesModel afterOrderPaidStylistProperties =  backEndSteps.grabCustomerConfiguration();	
		StylistRegistrationAndActivationDateModel grabbeddatesModel = backEndSteps.grabStylistRegistrationAndConfirmationDates();
	
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(afterLinkConfirmationStylistProperties, afterLinkConfirmationStylistExpectedProperties);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(afterLinkConfirmationStylistProperties);
		PrintUtils.printStylistPropertiesModel(afterLinkConfirmationStylistExpectedProperties);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(afterOrderPaidStylistProperties, afterOrderPaidStylistExpectedProperties);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER MARK AS PAID ");
		PrintUtils.printStylistPropertiesModel(afterOrderPaidStylistProperties);
		PrintUtils.printStylistPropertiesModel(afterOrderPaidStylistExpectedProperties);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistDates(grabbeddatesModel,datesModel);
		customerAndStylistRegistrationWorkflows.validateStylistDAtes("VALIDATE REGISTRATION AND ACTIVATION DATES");
		
		customVerifications.printErrors();
	}


}

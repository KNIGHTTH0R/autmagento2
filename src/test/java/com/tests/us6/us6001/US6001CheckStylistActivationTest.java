package com.tests.us6.us6001;


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
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US6001", type = "external,backend")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US6001CheckStylistActivationTest extends BaseTest {

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
	
	public StylistPropertiesModel expectedBeforeLinkConfirmationStylistData = new StylistPropertiesModel();
	public StylistPropertiesModel expectedAfterLinkConfirmationStylistData = new StylistPropertiesModel();
	public StylistPropertiesModel expectedOrderPaidStylistData = new StylistPropertiesModel();
	public RegistrationActivationDateModel datesExpected = new RegistrationActivationDateModel();

	public CustomerFormModel stylistRegistrationData = new CustomerFormModel("");
	public String grabConfirmationEmail;
	public String formCreationDate;

	@Before
	public void setUp() throws Exception {
		
		formCreationDate = MongoReader.grabStylistDateModels("US6001StyleCoachRegistrationTest").get(0).getDate();
		int size = MongoReader.grabCustomerFormModels("US6001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US6001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		expectedBeforeLinkConfirmationStylistData =  new StylistPropertiesModel(Constants.NOT_CONFIRMED, Constants.JEWELRY_INITIAL_VALUE, Constants.GENERAL);
		expectedAfterLinkConfirmationStylistData =  new StylistPropertiesModel(Constants.CONFIRMED, Constants.JEWELRY_INITIAL_VALUE, Constants.GENERAL);
		expectedOrderPaidStylistData =  new StylistPropertiesModel(Constants.CONFIRMED, Constants.JEWELRY_FINAL_VALUE, Constants.STYLIST);
		datesExpected = new RegistrationActivationDateModel(formCreationDate,formCreationDate);
		
	}

	@Test
	public void us6001CheckCustomerActivationTest() {
		
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		String customerURL = backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		
		StylistPropertiesModel grabBeforeLinkConfirmationStylistData =  backEndSteps.grabCustomerConfiguration();
		
		//external 
		emailClientSteps.openMailinator();
		grabConfirmationEmail  = emailClientSteps.grabEmail(stylistRegistrationData.getEmailName().replace("@" + Constants.WEB_MAIL, ""),"Benutzerkonto");
		
		backEndSteps.navigate(customerURL);
		StylistPropertiesModel grabAfterLinkConfirmationStylistData =  backEndSteps.grabCustomerConfiguration();

		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByName(stylistRegistrationData.getFirstName());
		backEndSteps.openOrderDetails(stylistRegistrationData.getFirstName());		
		ordersSteps.markOrderAsPaid();	
		
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		
		StylistPropertiesModel grabOrderPaidStylistData =  backEndSteps.grabCustomerConfiguration();	
		RegistrationActivationDateModel grabDatesModel = backEndSteps.grabStylistRegistrationAndConfirmationDates();

		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabBeforeLinkConfirmationStylistData, expectedBeforeLinkConfirmationStylistData);	
		customerAndStylistRegistrationWorkflows.validateStylistProperties("BEFORE CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(grabBeforeLinkConfirmationStylistData);
		PrintUtils.printStylistPropertiesModel(expectedBeforeLinkConfirmationStylistData);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabAfterLinkConfirmationStylistData, expectedAfterLinkConfirmationStylistData);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(grabAfterLinkConfirmationStylistData);
		PrintUtils.printStylistPropertiesModel(expectedAfterLinkConfirmationStylistData);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabOrderPaidStylistData, expectedOrderPaidStylistData);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER MARK AS PAID ");
		PrintUtils.printStylistPropertiesModel(grabOrderPaidStylistData);
		PrintUtils.printStylistPropertiesModel(expectedOrderPaidStylistData);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistDates(grabDatesModel,datesExpected);
		customerAndStylistRegistrationWorkflows.validateStylistDAtes("VALIDATE REGISTRATION AND ACTIVATION DATES");
		
		customVerifications.printErrors();
	}


}

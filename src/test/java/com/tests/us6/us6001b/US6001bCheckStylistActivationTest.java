package com.tests.us6.us6001b;

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
import com.tools.CustomVerification;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US6.1b Sc Registration New Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(ThucydidesRunner.class)
public class US6001bCheckStylistActivationTest extends BaseTest {

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
	
//	private StylistPropertiesModel expectedBeforeLinkConfirmationStylistData = new StylistPropertiesModel();
	private StylistPropertiesModel expectedAfterLinkConfirmationStylistData = new StylistPropertiesModel();
	private StylistPropertiesModel expectedOrderPaidStylistData = new StylistPropertiesModel();
	private RegistrationActivationDateModel datesExpected = new RegistrationActivationDateModel();

	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");
	private String formCreationDate;

	@Before
	public void setUp() throws Exception {
		
		formCreationDate = MongoReader.grabDateModels("US6001bScRegistrationNewCustForbiddenCountryTest").get(0).getDate();
		int size = MongoReader.grabCustomerFormModels("US6001bScRegistrationNewCustForbiddenCountryTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US6001bScRegistrationNewCustForbiddenCountryTest").get(0);
		} else
			System.out.println("The database has no entries");

//		expectedBeforeLinkConfirmationStylistData =  new StylistPropertiesModel(ConfigConstants.NOT_CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		expectedAfterLinkConfirmationStylistData =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.STYLIST);
		expectedOrderPaidStylistData =  new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_FINAL_VALUE, ConfigConstants.STYLIST);
		datesExpected = new RegistrationActivationDateModel(formCreationDate,formCreationDate);
		
	}

	@Test
	public void us6001bCheckStylistActivationTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		
//		StylistPropertiesModel grabBeforeLinkConfirmationStylistData =  backEndSteps.grabCustomerConfiguration();
//		
//		//external 
//		emailClientSteps.openMailinator();
//		emailClientSteps.grabEmail(stylistRegistrationData.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""),ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
//		
//		backEndSteps.navigate(customerURL);
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

//		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabBeforeLinkConfirmationStylistData, expectedBeforeLinkConfirmationStylistData);	
//		customerAndStylistRegistrationWorkflows.validateStylistProperties("BEFORE CONFIRMATION LINK");
//		PrintUtils.printStylistPropertiesModel(grabBeforeLinkConfirmationStylistData);
//		PrintUtils.printStylistPropertiesModel(expectedBeforeLinkConfirmationStylistData);
		
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


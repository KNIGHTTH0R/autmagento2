package com.tests.us6.us6002b;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US6.2b SC Registration Existing Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_2.class)
@RunWith(SerenityRunner.class)
public class US6002bCreateCustomerTest extends BaseTest{

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;

	private CustomerFormModel customerData;
	private AddressModel customerAddressData;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		customerData = new CustomerFormModel();
		customerAddressData = new AddressModel();
		customerAddressData.setPostCode(ContextConstants.NOT_PREFEERD_WEBSITE_POST_CODE);
		customerAddressData.setCountryName(ContextConstants.NOT_PREFERED_LANGUAGE);
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us6002bCreateCustomerTest() {
		System.out.println(customerData.getEmailName());
		customerRegistrationSteps.fillCreateCustomerForm(customerData, customerAddressData);
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerData, getClass().getSimpleName());
	}

}
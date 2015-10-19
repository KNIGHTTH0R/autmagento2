package com.tests.us6.us6001b;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US6.1b Sc Registration New Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(ThucydidesRunner.class)
public class US6001bScRegistrationNewCustForbiddenCountryTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomVerification customVerification;

	private CustomerFormModel customerFormData;
	private DateModel customerFormDate = new DateModel();
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		customerFormAddress.setCountryName(ContextConstants.NOT_PREFERED_LANGUAGE);
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us6001bScRegistrationNewCustForbiddenCountryTest(){ 
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerFormFirstWithForbiddenCountry(customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);
		customVerification.printErrors();
		
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());

	}
}

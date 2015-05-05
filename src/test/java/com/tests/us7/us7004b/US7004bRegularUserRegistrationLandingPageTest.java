package com.tests.us7.us7004b;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.pages.frontend.registration.landing.LandingCustomerAllocationPage.StyleMode;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;


@WithTag(name = "US7", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7004bRegularUserRegistrationLandingPageTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7004bRegularUserRegistrationLandingPageTest() {

		customerRegistrationSteps.fillLandingPageForm(dataModel, addressModel);
		customerRegistrationSteps.selectStylistOption(StyleMode.DefaultStylist, "", "");
		customerRegistrationSteps.submitStylistSelection();
		String email = customerRegistrationSteps.fillThankYouForm(dataModel.getPassword());
		
		customerRegistrationSteps.verifyCustomerEmail(dataModel.getEmailName(), email);
		customerRegistrationSteps.verifySuccessLink();
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}
	
	
}

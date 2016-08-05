package com.tests.us7.us7004b;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.pages.frontend.registration.landing.LandingCustomerAllocationPage.StyleMode;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7.4b Regular Customer Registration from Landing Page Not Pref Country Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_4.class)
@RunWith(SerenityRunner.class)
public class US7004bRegCustRegLandingPageNotPrefCountryTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	

	@Before
	public void setUp() throws Exception {
		
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		addressModel.setPostCode(ContextConstants.NOT_PREFEERD_WEBSITE_POST_CODE);
		addressModel.setCountryName(ContextConstants.NOT_PREFERED_LANGUAGE);
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7004bRegCustRegLandingPageNotPrefCountryTest() {

		customerRegistrationSteps.fillLandingPageForm(dataModel, addressModel);
		customerRegistrationSteps.selectStylistOption(StyleMode.DefaultStylist, "", "",addressModel);
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

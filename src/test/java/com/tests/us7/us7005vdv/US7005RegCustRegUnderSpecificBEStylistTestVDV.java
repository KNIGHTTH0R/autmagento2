package com.tests.us7.us7005vdv;

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
import com.steps.backend.BackEndSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.Credentials;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7.4b Regular Customer Registration from Landing Page Not Pref Country Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_4.class)
@RunWith(SerenityRunner.class)
public class US7005RegCustRegUnderSpecificBEStylistTestVDV extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps
	public BackEndSteps backEndSteps;
	
	
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	

	@Before
	public void setUp() throws Exception {
		
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
	public void us7005RegCustRegUnderSpecificBEStylistTestVDV() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		backEndSteps.clickOnPippajeanStylistTab();
		backEndSteps.expendCustomerDistributionTab();
		backEndSteps.selectDistributedOnSpecificSc("Ja");
		backEndSteps.selectSpecifiSC("emilian melian / Ref: emx / CustId: 20");
		backEndSteps.saveConfiguration();
		
	/*	customerRegistrationSteps.fillCreateCustomerUnderMasterForm(dataModel, addressModel);
		//customerRegistrationSteps.fillCreateCustomerFormAnReturnFoundByNameStylecoaches(dataModel, addressModel, "sim", "sim");
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();*/
		
	
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}
	
	
}

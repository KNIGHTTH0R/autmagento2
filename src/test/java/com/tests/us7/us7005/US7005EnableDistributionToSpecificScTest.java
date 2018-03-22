package com.tests.us7.us7005;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.4b Regular Customer Registration from Landing Page Not Pref Country Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_4.class)
@RunWith(SerenityRunner.class)
public class US7005EnableDistributionToSpecificScTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps
	public BackEndSteps backEndSteps;
	
	
	

	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7005EnableDistributionToSpecificScTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		backEndSteps.clickOnPippajeanStylistTab();
		backEndSteps.expendCustomerDistributionTab();
		backEndSteps.selectDistributedOnSpecificSc("Ja");
		backEndSteps.selectSpecifiSC("emilian melian / Ref: emx / CustId: 20");
		backEndSteps.saveConfiguration();
		
		
	
	}

	@After
	public void saveData() {
	}
	
	
}

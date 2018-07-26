package com.tests.us7.us7001vdv.copy;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US7001ValidateBoutiqueTestVDV extends BaseTest {
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String stylistEmail;
	private String stylistPassword;

	@Before
	public void setUp() throws Exception {
		

		int size = MongoReader.grabCustomerFormModels("US7001RegularCustRegOnMasterTestVDV").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7001RegularCustRegOnMasterTestVDV").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7001RegularCustRegOnMasterTestVDV").get(0).getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		
		
	}
	@Test
	public void us7001ValidateBoutiqueTestVDV() {
		
		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);	
		headerSteps.goToProfile();
		System.out.println(headerSteps.getBoutiqueName());
		System.out.println(dashboardSteps.getStyleCoachFirstNameFromProfile());
	
		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), dashboardSteps.getStyleCoachFirstNameFromProfile());		

	}

}


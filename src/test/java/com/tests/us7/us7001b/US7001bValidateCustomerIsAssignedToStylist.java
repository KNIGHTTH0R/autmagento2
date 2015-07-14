package com.tests.us7.us7001b;


import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7001bValidateCustomerIsAssignedToStylist extends BaseTest {
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	public String stylistEmail;
	public String stylistPassword;

	@Before
	public void setUp() throws Exception {
		

		int size = MongoReader.grabCustomerFormModels("US7001bRegularCustomerRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7001bRegularCustomerRegistrationTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7001bRegularCustomerRegistrationTest").get(0).getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");		
		
	}
	@Test
	public void us7001bValidateCustomerIsAssignedToStylist() {
		
		customerRegistrationSteps.performLoginOnPreferedWebsite(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		System.out.println(headerSteps.getBoutiqueName());
		System.out.println(dashboardSteps.getStyleCoachFirstNameFromProfile());	
		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), dashboardSteps.getStyleCoachFirstNameFromProfile());		

	}

}


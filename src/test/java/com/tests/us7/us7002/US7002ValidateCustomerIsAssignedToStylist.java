package com.tests.us7.us7002;


import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7002ValidateCustomerIsAssignedToStylist extends BaseTest {
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;

	public String stylistEmail;
	public String stylistPassword;
	public String context;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7002RegularCustomerRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7002RegularCustomerRegistrationTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7002RegularCustomerRegistrationTest").get(0).getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		context = "simona";
	}
	
	@Test
	public void us7002ValidateCustomerIsAssignedToStylist() {

		customerRegistrationSteps.performLoginUnderContext(stylistEmail, stylistPassword,context);
		headerSteps.goToProfile();
	
		headerSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), headerSteps.getStyleCoachFirstNameFromProfile());

	}


}


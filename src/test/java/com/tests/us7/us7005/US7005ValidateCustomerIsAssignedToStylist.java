package com.tests.us7.us7005;

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

@WithTag(name = "US7.2 Regular Customer Registration on Context Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_2.class)
@RunWith(SerenityRunner.class)
public class US7005ValidateCustomerIsAssignedToStylist extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String stylistEmail;
	private String stylistPassword;
	private String context;
	private String boutique;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7005RegCustRegUnderSpecificBEStylistTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7005RegCustRegUnderSpecificBEStylistTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7005RegCustRegUnderSpecificBEStylistTest").get(0).getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		context = "/emx";
		boutique="emilian";
	}

	@Test
	public void us7005ValidateCustomerIsAssignedToStylist() {

		customerRegistrationSteps.performLoginUnderContext(stylistEmail, stylistPassword, context);
		headerSteps.goToProfile();
		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), boutique);
		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), dashboardSteps.getStyleCoachFirstNameFromProfile());

	}

}

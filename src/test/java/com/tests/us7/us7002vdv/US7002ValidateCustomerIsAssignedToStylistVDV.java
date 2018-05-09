package com.tests.us7.us7002vdv;

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
public class US7002ValidateCustomerIsAssignedToStylistVDV extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String stylistEmail;
	private String stylistPassword;
	private String context;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTestVDV").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTestVDV").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7002RegularCustRegistrationOnContextTestVDV").get(0).getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		context = "/emc";
	}

	@Test
	public void us7002ValidateCustomerIsAssignedToStylistVDV() {

		customerRegistrationSteps.performLoginUnderContext(stylistEmail, stylistPassword, context);
		headerSteps.goToProfile();
		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), dashboardSteps.getStyleCoachFirstNameFromProfile());

	}

}

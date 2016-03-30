package com.tests.uss17.us17002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17.1 Check reassigned duplicate contacts and customer associated contacts when new SC is selected", type = "Scenarios")
@Story(Application.MassAction.US17_1.class)
@RunWith(SerenityRunner.class)
public class US17002ConfirmCustomerInBackendTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private CustomerFormModel stylistRegistrationData;

	@Before
	public void setUp() throws Exception {

		stylistRegistrationData = MongoReader.grabCustomerFormModels("US17002RegularCustomerRegistrationTest").get(0);
	}

	@Test
	public void us17002ConfirmCustomerInBackendTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		backEndSteps.confirmCustomer();
	}

}

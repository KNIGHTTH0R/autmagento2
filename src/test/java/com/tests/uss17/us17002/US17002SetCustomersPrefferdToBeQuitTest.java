package com.tests.uss17.us17002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.customer.CustomerDetailsBackendSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17002SetCustomersPrefferdToBeQuitTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomerDetailsBackendSteps customerDetailsBackendSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {
		stylistRegistrationData = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest").get(0);
	}

	@Test
	public void us17002MarkSecondStarterKitOrderAsPaidTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		customerDetailsBackendSteps.markStylecoachAsQuit();
	}

}

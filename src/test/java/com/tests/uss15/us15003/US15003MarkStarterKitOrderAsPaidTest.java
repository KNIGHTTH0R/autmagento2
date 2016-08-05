package com.tests.uss15.us15003;

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
import com.tools.constants.Credentials;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US15.3 Check SC kobo subscription and SFM order details in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_3.class)
@RunWith(SerenityRunner.class)
public class US15003MarkStarterKitOrderAsPaidTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {

		stylistRegistrationData = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").get(0);

	}

	@Test
	public void us15003MarkStarterKitOrderAsPaidTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByName(stylistRegistrationData.getFirstName());
		backEndSteps.openOrderDetails(stylistRegistrationData.getFirstName());
		ordersSteps.markOrderAsPaid();
	}

}

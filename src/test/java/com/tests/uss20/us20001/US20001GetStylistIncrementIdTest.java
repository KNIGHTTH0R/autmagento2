package com.tests.uss20.us20001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.StylistValidationSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US20.1 Verify new SC and updated SC details in Commission", type = "Scenarios")
@Story(Application.StylecoachInfo.US20_1.class)
@RunWith(ThucydidesRunner.class)
public class US20001GetStylistIncrementIdTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public StylistValidationSteps stylistValidationSteps;
	@Steps
	private CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	String incrementId;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US20001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US20001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us20001GetStylistIncrementIdTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		System.out.println(stylistRegistrationData.getEmailName());
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());
		incrementId = backEndSteps.extractCustomerIncrementId();

	}

	@After
	public void tearDown() {
		MongoWriter.saveIncrementId(incrementId, getClass().getSimpleName());
	}

}

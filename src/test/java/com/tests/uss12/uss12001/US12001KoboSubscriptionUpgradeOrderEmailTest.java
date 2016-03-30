package com.tests.uss12.uss12001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(SerenityRunner.class)
public class US12001KoboSubscriptionUpgradeOrderEmailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private static OrderModel orderModel = new OrderModel();
	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		orderModel = MongoReader.grabOrderModels("US12001KoboSubscriptionTest" + SoapKeys.GRAB).get(0);

	}

	@Test
	public void us12001KoboSubscriptionUpgradeOrderEmailTest() {

		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistRegistrationData.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""), orderModel.getOrderId());

	}

}

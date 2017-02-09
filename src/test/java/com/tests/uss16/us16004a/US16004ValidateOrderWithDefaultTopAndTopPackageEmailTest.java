package com.tests.uss16.us16004a;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US16004ValidateOrderWithDefaultTopAndTopPackageEmailTest extends BaseTest{

	@Steps
	public EmailClientSteps emailClientSteps;

	private static OrderModel orderModel = new OrderModel();
	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US16003StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US16003StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		orderModel = MongoReader.grabOrderModels("US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest" + SoapKeys.GRAB).get(0);

	}

	@Test
	public void us16004ValidateOrderWithDefaultTopAndTopPackageEmailTest() {

		emailClientSteps.validateThatEmailIsReceived(stylistRegistrationData.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""), orderModel.getOrderId());

	}
}

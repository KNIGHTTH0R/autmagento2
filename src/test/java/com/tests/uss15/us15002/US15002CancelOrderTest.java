package com.tests.uss15.us15002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.constants.EnvironmentConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US15.2 Check registered user with kobo all states in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_2.class)
@RunWith(SerenityRunner.class)
public class US15002CancelOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.grabOrderModels("US15002SubscribedCustomerBuyWithContactBoosterTest").get(0);
		System.out.println(orderModel.getOrderId());
	}

	@Test
	public void us15002CancelOrderTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
		ordersSteps.cancelOrder();
		ApacheHttpHelper.sendGet(EnvironmentConstants.EXPORT_JOB_TRIGGER_URL, EnvironmentConstants.USERNAME,
				EnvironmentConstants.PASSWORD);

	}
}

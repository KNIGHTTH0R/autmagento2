package com.tests.uss15.us15004;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.data.backend.OrderModel;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTags({ @WithTag(name = "US15.4 Validate Zzz Product JB for all order states", type = "Scenarios"),
	@WithTag(name = "US15.4 Check place a customer order details in mailchimp", type = "Scenarios") })
@Story(Application.Newsletter.US15_4.class)
@RunWith(ThucydidesRunner.class)
public class US15004UncancelOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	
	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.grabOrderModels("US15004OrderZzzProductsForCustomerTest").get(0);
		System.out.println(orderModel.getOrderId());
	}

	@Test
	public void us15004UncancelOrderTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
		ordersSteps.uncancelOrder();
		ordersSteps.completeOrder();
		ApacheHttpHelper.sendGet(JenkinsConstants.EXPORT_JOB_TRIGGER_URL);

	}
}

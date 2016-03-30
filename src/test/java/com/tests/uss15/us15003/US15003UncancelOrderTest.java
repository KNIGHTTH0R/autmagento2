package com.tests.uss15.us15003;

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
import com.tools.data.backend.OrderModel;
import com.tools.env.constants.Credentials;
import com.tools.env.constants.JenkinsConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US15.3 Check SC kobo subscription and SFM order details in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_3.class)
@RunWith(SerenityRunner.class)
public class US15003UncancelOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	
	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.grabOrderModels("US15003SubscribedStyleCoachCheckoutProcessTest").get(0);
		System.out.println(orderModel.getOrderId());
	}

	@Test
	public void us15003UncancelOrderTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
		ordersSteps.uncancelOrder();
		ordersSteps.completeOrder();
		ApacheHttpHelper.sendGet(JenkinsConstants.EXPORT_JOB_TRIGGER_URL);

	}
}

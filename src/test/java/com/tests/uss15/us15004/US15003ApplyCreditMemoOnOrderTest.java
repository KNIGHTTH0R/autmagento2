package com.tests.uss15.us15004;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
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

@WithTag(name = "US6", type = "backend")
@Story(Application.Registration.Stylist.class)
@RunWith(ThucydidesRunner.class)
public class US15003ApplyCreditMemoOnOrderTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	private OrderModel orderModel;

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.grabOrderModels("US15003SubscribedStyleCoachCheckoutProcessTest").get(0);
	}

	@Test
	public void us15003ApplyCreditMemoOnOrderTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
		ordersSteps.refundOrder();
		ApacheHttpHelper.sendGet(JenkinsConstants.EXPORT_JOB_TRIGGER_URL);
	}

}

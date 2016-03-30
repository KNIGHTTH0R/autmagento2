package com.tests.uss12.uss12001;

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
import com.tools.data.backend.OrderModel;
import com.tools.env.constants.Credentials;
import com.tools.env.constants.SoapKeys;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(SerenityRunner.class)
public class US12001CancelCreditMemoForKoboUpgradeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	
	private OrderModel orderModel;

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.grabOrderModels("US12001KoboSubscriptionUpgradeTest" + SoapKeys.GRAB).get(0);
	}

	@Test
	public void us12001CancelCreditMemoForKoboUpgradeTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCreditMemo();
		backEndSteps.searchCreditMemoByorderId(orderModel.getOrderId());
		backEndSteps.openCreditMemoDetails(orderModel.getOrderId());
		backEndSteps.cancelCreditMemo();
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
		ordersSteps.markOrderAsPaid();
	}

}

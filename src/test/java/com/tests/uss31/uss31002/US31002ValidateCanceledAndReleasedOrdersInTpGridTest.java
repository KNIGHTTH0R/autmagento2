package com.tests.uss31.uss31002;

import java.text.ParseException;

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
import com.steps.backend.termPurchase.TermPurchaseGridSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.constants.EnvironmentConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US31.1 TP execution cron - Automated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_2.class)
@RunWith(SerenityRunner.class)
public class US31002ValidateCanceledAndReleasedOrdersInTpGridTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public TermPurchaseGridSteps termPurchaseGridSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public HostOrderProductsWorkflows hostOrderProductsWorkflows;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public TermPurcaseOrderValidationWorkflows termPurcaseOrderValidationWorkflows;

	TermPurchaseOrderModel expectedModel2;
	TermPurchaseOrderModel expectedModel3;

	OrderModel orderModelListTp2 = new OrderModel();
	OrderModel orderModelListTp3 = new OrderModel();

	@Before
	public void setUp() throws ParseException {

		orderModelListTp2 = MongoReader.getOrderModel("US31002PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		orderModelListTp3 = MongoReader.getOrderModel("US31002PartyHostBuysForCustomerTpTest" + "TP3").get(0);

	}

	@Test
	public void us31002ValidateCanceledAndReleasedOrdersInTpGridTest() throws ParseException {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp2.getOrderId());
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp3.getOrderId());

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.getOrderId());
		ordersSteps.openOrder(orderModelListTp2.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.CANCELED);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp3.getOrderId());
		ordersSteps.openOrder(orderModelListTp3.getOrderId());
		// TODO change PAYMENT_IN_PROGRESS in PAYMENT_COMPLETE
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(),
				ConfigConstants.PAYMENT_IN_PROGRESS);

		customVerifications.printErrors();

	}

	public void runCron() throws Exception {
		// script for updating deliveryDates
		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp2.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);

		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT,
				EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
	}

}

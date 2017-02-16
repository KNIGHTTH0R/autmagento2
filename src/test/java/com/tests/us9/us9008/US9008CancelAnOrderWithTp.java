package com.tests.us9.us9008;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US9008CancelAnOrderWithTp extends BaseTest{
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	
	private static OrderModel orderModel = new OrderModel();
	//List<OrderModel> orderModelListTp2 = new ArrayList<OrderModel>();

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.getOrderModel("US9008PlaceHostOrderWithTpTest" + "TP2").get(0);
		System.out.println(orderModel.getOrderId());
	}

	@Test
	public void us9008bCancelOrderAllowedTopAndCustomPackageTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
		ordersSteps.cancelOrder();

	}
}

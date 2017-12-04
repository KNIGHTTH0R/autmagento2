package com.tests.uss41.us41001_Regular.us41001RegularOrderDetails;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US15.2 Check registered user with kobo all states in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_2.class)
@RunWith(SerenityRunner.class)
public class US41001RegularCreateInvoiceAndShipmentTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US41001CustomerBuyWithForthyDiscountsAndJbTest" + SoapKeys.GRAB);
		

		if (orderModelList.size() == 1) {

			orderModel = orderModelList.get(0);
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}
		
	}

	@Test
	public void us41001RegularCreateInvoiceAndShipmentTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModel.getOrderId());
		backEndSteps.openOrderDetails(orderModel.getOrderId());
	
		ordersSteps.createOrderInvoice();
		ordersSteps.createOrderShipment();
		
		
	}

}

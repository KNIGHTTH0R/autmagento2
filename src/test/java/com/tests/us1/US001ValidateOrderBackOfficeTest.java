package com.tests.us1;

import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.OrderModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US001", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private String orderId = "staging100050872";

	
	@Before
	public void setUp(){
		List<OrderModel> orderModel = MongoReader.getOrderModel("US001StyleCoachShoppingTest");
		
		if(orderModel.size() == 1){
			
			orderId = orderModel.get(0).getOrderId();
		}else{
			Assert.assertTrue("Failure: Could not retrieve orderId. ",  orderModel.size() == 1);
		}
	}
	
	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us001ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);


		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderData();
		OrderTotalsModel ordertotal = ordersSteps.grabTotals();

		PrintUtils.printOrderTotals(ordertotal);
//		backEndSteps.grabOrder
//		backEndSteps.grabOrderTotals();
		
//		PrintUtils.printOrderItemsList(orderItemsList);
	}
}

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
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.OrderItemModel;

import com.tools.data.OrderTotalsModel;

import com.tools.data.OrderModel;
import com.tools.persistance.MongoReader;

import com.tools.requirements.Application;

@WithTag(name = "US001", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	private String orderId = "staging100050872";

	
//	@Before
//	public void setUp(){
//		List<OrderModel> orderModel = MongoReader.getOrderModel("US001ValidateUserProfileOrderTest");
//		
//		if(orderModel.size() == 1){
//			
//			orderId = orderModel.get(0).getOrderId();
//		}else{
//			Assert.assertTrue("Failure: Could not retrieve orderId. ",  orderModel.size() == 1);
//		}
//	}
	
	
	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us001ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.redirectToSalesOrders();
		backEndSteps.findOrderByOrderId(orderId);
		backEndSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = backEndSteps.grabOrderData();
		OrderTotalsModel ordertotal = backEndSteps.grabTotals();
		System.out.println("-----START-----");
		PrintUtils.printOrderTotals(ordertotal);
//		backEndSteps.grabOrder
//		backEndSteps.grabOrderTotals();
		
//		PrintUtils.printOrderItemsList(orderItemsList);
	}
}

package com.tests.us2;

import java.util.ArrayList;
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
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.OrderModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US001", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US002ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;

	private String orderId;
	public List<CartTotalsModel> cartTotals = new ArrayList<CartTotalsModel>();
	public List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();

	
	@Before
	public void setUp(){
		
		
		List<OrderModel> orderModel = MongoReader.getOrderModel("US002CartSegmentationLogicTest");

		if (orderModel.size() == 1) {

			orderId = orderModel.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModel.size() == 1);
		}
		

		cartTotals = MongoReader.grabTotalsModels("US002CartSegmentationLogicTest");
		if (cartTotals.size() == 1) {

			orderId = orderModel.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not validate cartTotals section. " + cartTotals , cartTotals.size() == 1);
		}
		
		productsList = MongoReader.grabProductBasicModel("US002CartSegmentationLogicTest");		
	
	}
	
	
	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us002ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);


		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderData();
		OrderTotalsModel ordertotal = ordersSteps.grabTotals();
		OrderInfoModel orderInfo = ordersSteps.grabOrderInfo();
		PrintUtils.printOrderItemsList(orderItemsList);
		PrintUtils.printOrderTotals(ordertotal);
		PrintUtils.printOrderInfo(orderInfo);
		orderValidationSteps.validateTotals("TOTALS VALIVATION", ordertotal, cartTotals.get(0));
		orderValidationSteps.validateProducts(productsList, orderItemsList);
		
//		backEndSteps.grabOrder
//		backEndSteps.grabOrderTotals();
		
//		PrintUtils.printOrderItemsList(orderItemsList);
	}
}

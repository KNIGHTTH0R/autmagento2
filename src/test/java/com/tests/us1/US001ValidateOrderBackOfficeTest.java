package com.tests.us1;

import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.OrderItemModel;
import com.tools.data.OrderTotalsModel;
import com.tools.requirements.Application;

@WithTag(name = "US001", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	private String orderId = "staging100050793";

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
		PrintUtils.printOrderTotals(ordertotal);
//		backEndSteps.grabOrder
//		backEndSteps.grabOrderTotals();
		
		PrintUtils.printOrderItemsList(orderItemsList);
	}
}

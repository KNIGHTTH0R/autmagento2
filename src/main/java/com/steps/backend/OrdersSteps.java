package com.steps.backend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.data.OrderTotalsModel;
import com.tools.data.backend.OrderItemModel;

public class OrdersSteps extends AbstractSteps{

	private static final long serialVersionUID = 1940802567090001853L;
	
	
	@Step
	public void findOrderByOrderId(String orderId) {
		orderListPage().inputOderId(orderId);
		orderListPage().clickOnSearch();
//		waitABit(5000);
	}

	@Step
	public void openOrder(String orderId) {
		orderListPage().openOrderDetails(orderId);
//		waitABit(Constants.TIME_CONSTANT);
	}

	@Step
	public List<OrderItemModel> grabOrderData() {
		return orderItemsPage().grabOrderItems();
	}
	@Step
	public OrderTotalsModel grabTotals() {
		return orderTotalsPage().grabTotals();
	}

}

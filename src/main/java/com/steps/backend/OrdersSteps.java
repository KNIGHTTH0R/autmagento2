package com.steps.backend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.requirements.AbstractSteps;

public class OrdersSteps extends AbstractSteps {

	private static final long serialVersionUID = 1940802567090001853L;

	@Step
	public void findOrderByOrderId(String orderId) {
		orderListPage().inputOderId(orderId);
	}

	@Step
	public void openOrder(String orderId) {
		waitABit(TimeConstants.TIME_CONSTANT);
		orderListPage().openOrderDetails(orderId);
	}

	@Step
	public List<OrderItemModel> grabOrderProducts() {
		return orderItemsPage().grabOrderItems();
	}

	@Step
	public OrderTotalsModel grabTotals() {
		return orderTotalsPage().grabTotals();
	}

	@Step
	public OrderInfoModel grabOrderInfo() {
		return orderInfoPage().grabOrderInfo();
	}

	@Step
	public void markOrderAsPaid() {
		ordersActionsPage().markOrderAsPaid();
		ordersActionsPage().waitForLoading();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	@Step
	public void cancelOrder() {
		ordersActionsPage().cancelOrder();
		ordersActionsPage().waitForLoading();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	@Step
	public void uncancelOrder() {
		ordersActionsPage().uncancelOrder();
		ordersActionsPage().waitForLoading();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	@Step
	public void completeOrder() {
		ordersActionsPage().clickInvoiceButton();
		ordersActionsPage().checkCreateShippment();
		ordersActionsPage().submitInvoice();
		ordersActionsPage().verifyInvoiceShippingSubmitedMessage();
		
	}

	@Step
	public void refundOrder() {
		ordersActionsPage().clickcreditMemoButton();
		waitABit(1000);
		ordersActionsPage().refundOffline();
		ordersActionsPage().verifyRefundedSuccessMessage();
		waitABit(2000);
	}

}

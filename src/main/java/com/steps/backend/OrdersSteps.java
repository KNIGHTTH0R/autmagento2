package com.steps.backend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.env.constants.TimeConstants;
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
	public List<OrderItemModel> grabOrderData() {
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
		waitABit(TimeConstants.WAIT_TIME_LONG);
	}

	@Step
	public void completeOrder() {
		ordersActionsPage().clickInvoiceButton();
		ordersActionsPage().checkCreateShippment();
		ordersActionsPage().submitInvoice();
		ordersActionsPage().verifyInvoiceShippingSubmitedMessage();
		waitABit(5000);
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

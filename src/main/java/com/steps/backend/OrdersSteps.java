package com.steps.backend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class OrdersSteps extends AbstractSteps {

	private static final long serialVersionUID = 1940802567090001853L;

	@Step
	public void findOrderByOrderId(String orderId) {
		orderListPage().inputOderId(orderId);
		orderListPage().clickOnSearch();
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
	public void createOrderShipment() {
		ordersActionsPage().clickShipButton();
		ordersActionsPage().checkShipmentMail();
		ordersActionsPage().submitShipment();
		ordersActionsPage().verifyShippingSubmitedMessage();

	}
	@Step
	public void validateCompleteStatus(){
		ordersActionsPage().verifyCompleteStatus();
	}
	
	
	@Step
	public void createOrderInvoice(){
		ordersActionsPage().clickInvoiceButton();
		//ordersActionsPage().checkCreateShippment();
		ordersActionsPage().submitInvoice();
		ordersActionsPage().verifyInvoiceSubmitedMessage();
	}

	@Step
	public void refundOrder() {
		ordersActionsPage().clickcreditMemoButton();
		waitABit(1000);
		ordersActionsPage().refundOffline();
		ordersActionsPage().verifyRefundedSuccessMessage();
		waitABit(2000);
	}

	@Step
	public void selectMenu(String tabName) {
		orderDetailsNavPage().selectMenu(tabName);
	}

	@Step
	public void verifyAuthorization(String shopperReference) {
		orderNotificationPage().verifyAuthorization(shopperReference);
	}

	@Step
	public void verifyCapture(String shopperReference) {
		orderNotificationPage().verifyCapture(shopperReference);
	}

	@Step
	public void verifyCaptureOrder(String shopperReference) {
		//orderNotificationPage().verifyCapture(shopperReference);
	}

	
	@Step
	public void openDocumentsSection() {
		// TODO Auto-generated method stub
		ordersActionsPage().openDocumentsSection();
	}

	@Step
	public void valdateOriginalInvoiceIsReceived(String orderId, boolean isReceived) {
		ordersActionsPage().valdateOriginalInvoiceIsReceived(orderId,isReceived);
	}
	
	@Step
	public void valdateOriginalReturnIsReceived(String orderId, boolean isReceived) {
		ordersActionsPage().valdateOriginalReturnIsReceived(orderId,isReceived);
	}
	
	@Step
	public void validateSecoundReturnIsReceived(String orderId, boolean isReceived,int noOfDoc) {
		ordersActionsPage().valdateExcangeReturnIsReceived(orderId,isReceived,noOfDoc);
	}

	public void validateEchangeDocIsReceived(String orderId, boolean isReceived) {
		ordersActionsPage().validateEchangeDocIsReceived(orderId,isReceived);		
	}

	public void sendIndividualDocumentToSpecificReceiver(String documentName,String receiver) {
	
		ordersActionsPage().sendIndividualDocumentToSpecificReceiver(documentName);
		ordersActionsPage().selectReceiverMethod("additional_email_receiver");
		ordersActionsPage().insertReceiver(receiver);
		ordersActionsPage().clickSubmitEmail();
	}
	public void sendIndividualDocumentToReceiver(String documentName,String receiver) {
		
		ordersActionsPage().sendIndividualDocumentToSpecificReceiver(documentName);
		System.out.println("done");
		ordersActionsPage().selectReceiverMethod(receiver);
		System.out.println("done1");
		ordersActionsPage().clickSubmitEmail();
		System.out.println("done2");
	}
}

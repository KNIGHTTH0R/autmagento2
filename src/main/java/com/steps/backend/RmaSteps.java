package com.steps.backend;

import java.util.List;

import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class RmaSteps extends AbstractSteps {

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
	public List<OrderItemModel> grabInvoiceOrderItems() {
		return orderItemsPage().grabInvoiceOrderItems();
	}

	@Step
	public List<OrderItemModel> grabCreditMomoOrderItems() {
		return orderItemsPage().grabCreditMomoOrderItems();
	}

	
	
	@Step
	public List<OrderItemModel> grabShipmentOrderItems() {
		return orderItemsPage().grabShipmentOrderItems();
	}

	@Step
	public OrderTotalsModel grabTotals() {
		return orderTotalsPage().grabTotals();
	}
	
	

	@Step
	public OrderTotalsModel grabInvoiceTotals() {
		return orderTotalsPage().grabInvoiceTotals();
	}

	@Step
	public OrderTotalsModel grabCreditmemoTotals() {
		return orderTotalsPage().grabInvoiceTotals();
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
	public void clickShipButton() {
		ordersActionsPage().clickShipButton();
	}

	@Step
	public void submitShipment() {
		ordersActionsPage().checkShipmentMail();
		ordersActionsPage().submitShipment();
		ordersActionsPage().verifyShippingSubmitedMessage();
	}

	@Step
	public void createOrderShipment() {
		ordersActionsPage().clickShipButton();
		ordersActionsPage().checkShipmentMail();
		ordersActionsPage().submitShipment();
		ordersActionsPage().verifyShippingSubmitedMessage();

	}

	@Step
	public void validateCompleteStatus() {
		ordersActionsPage().verifyCompleteStatus();
	}

	@Step
	public void clickInvoiceButton() {
		ordersActionsPage().clickInvoiceButton();
	}

	@Step
	public void submitInvoice() {
		ordersActionsPage().checkInvoiceMail();
		ordersActionsPage().submitInvoice();
		ordersActionsPage().verifyInvoiceSubmitedMessage();
	}

	@Step
	public void verifyInvoiceSubmitedMessage() {
		ordersActionsPage().verifyInvoiceSubmitedMessage();
	}

	@Step
	public void createOrderInvoice() {
		ordersActionsPage().clickInvoiceButton();
		ordersActionsPage().checkCreateShippment();
		ordersActionsPage().submitInvoice();
		ordersActionsPage().verifyInvoiceSubmitedMessage();
	}
	
	@Step
	public void clickcreditMemoButton() {
		ordersActionsPage().clickcreditMemoButton();
	}
	
	
	@Step
	public void submitMemoButton() {
		ordersActionsPage().checkInvoiceMail();

		ordersActionsPage().refundOffline();
		ordersActionsPage().verifyRefundedSuccessMessage();
		waitABit(2000);	
		}

	
	
	@Step
	public void refundOffline() {
		ordersActionsPage().refundOffline();
		waitABit(2000);	
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
		rmaItemsPage().selectMenu(tabName);
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
		// orderNotificationPage().verifyCapture(shopperReference);
	}

	@Step
	public void openDocumentsSection() {
		// TODO Auto-generated method stub
		ordersActionsPage().openDocumentsSection();
	}

	@Step
	public void valdateOriginalInvoiceIsReceived(String orderId, boolean isReceived) {
		ordersActionsPage().valdateOriginalInvoiceIsReceived(orderId, isReceived);
	}

	@Step
	public void valdateOriginalReturnIsReceived(String orderId, boolean isReceived) {
		ordersActionsPage().valdateOriginalReturnIsReceived(orderId, isReceived);
	}

	@Step
	public void validateSecoundReturnIsReceived(String orderId, boolean isReceived, int noOfDoc) {
		ordersActionsPage().valdateExcangeReturnIsReceived(orderId, isReceived, noOfDoc);
	}

	public void validateEchangeDocIsReceived(String orderId, boolean isReceived) {
		ordersActionsPage().validateEchangeDocIsReceived(orderId, isReceived);
	}

	public void sendIndividualDocumentToSpecificReceiver(String documentName, String receiver) {

		ordersActionsPage().sendIndividualDocumentToSpecificReceiver(documentName);
		ordersActionsPage().selectReceiverMethod("additional_email_receiver");
		ordersActionsPage().insertReceiver(receiver);
		ordersActionsPage().clickSubmitEmail();
	}

	public void sendIndividualDocumentToReceiver(String documentName, String receiver) {

		ordersActionsPage().sendIndividualDocumentToSpecificReceiver(documentName);
		System.out.println("done");
		ordersActionsPage().selectReceiverMethod(receiver);
		System.out.println("done1");
		ordersActionsPage().clickSubmitEmail();
		System.out.println("done2");
	}

	public void addTrakingNumber(String trNumber) {
		ordersActionsPage().clickTrakingNumberBtn();
		ordersActionsPage().selectTrakingNoMethod("ups");
		ordersActionsPage().insertTrackingNo(trNumber);
	}

	public void refundQty(String prodCode, String quantity) {
		 orderItemsPage().refundQty(prodCode,quantity);
		
	}

	public void updateQty() {
		// TODO Auto-generated method stub
		 orderItemsPage().updateSty();

	}

	public void authorizedQty(String prodCode, String qty, String status) {
		rmaItemsPage().authorizedQty(prodCode,qty,status);
	}
	
	public void receivedQty(String prodCode, String qty, String status) {
		rmaItemsPage().receivedQty(prodCode,qty,status);
	}
	
	public void approvedQty(String prodCode, String qty, String status) {
		rmaItemsPage().approvedQty(prodCode,qty,status);
	}
	
	public void saveAndContinue(){
		rmaItemsPage().clickSaveAndContinue();
	}

	
}

package com.tools.data;

public class OrderModel {

	private String orderId;
	private String date;
	private String invoiceContact;
	private String deliveryContact;
	private String totalPrice;
	private String status;
	private String actions;

	public String getOrderId() {
		return orderId;
	}

	public String getDate() {
		return date;
	}

	public String getInvoiceContact() {
		return invoiceContact;
	}

	public String getDeliveryContact() {
		return deliveryContact;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public String getActions() {
		return actions;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setInvoiceContact(String invoiceContact) {
		this.invoiceContact = invoiceContact;
	}

	public void setDeliveryContact(String deliveryContact) {
		this.deliveryContact = deliveryContact;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

}

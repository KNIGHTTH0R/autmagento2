package com.tools.data.backend;


/**
 * This model is used in the Backend section, on the order details. The Order Details section 
 * should be mapped with this model.
 * @author voicu.vac
 *
 */
public class OrderModel {

	private String orderId;
	private String date;
	private String invoiceContact;
	private String deliveryContact;
	private String totalPrice;
	private String status;
	
	public OrderModel(){
		setOrderId("");
		setDate("");
		setInvoiceContact("");
		setDeliveryContact("");
		setTotalPrice("");
		setStatus("");
	}

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
}

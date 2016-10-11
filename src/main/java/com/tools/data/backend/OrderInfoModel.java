package com.tools.data.backend;

public class OrderInfoModel {

	private String orderDate;
	private String orderStatus;
	private String aquiredBy;
	private String orderIP;
	private String deliveryDate;
	private String pspReference;
	
	

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPspReference() {
		return pspReference;
	}

	public void setPspReference(String pspReference) {
		this.pspReference = pspReference;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAquiredBy() {
		return aquiredBy;
	}

	public void setAquiredBy(String aquiredBy) {
		this.aquiredBy = aquiredBy;
	}

	public String getOrderIP() {
		return orderIP;
	}

	public void setOrderIP(String orderIP) {
		this.orderIP = orderIP;
	}

}

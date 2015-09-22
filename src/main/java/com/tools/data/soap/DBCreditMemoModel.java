package com.tools.data.soap;

public class DBCreditMemoModel {

	private String stylistId;
	private String createdAt;
	private String state;
	private String totalIpRefunded;
	private String orderIncrementId;
	private String orderCreatedAt;
	private String orderPaidAt;

	public String getStylistId() {
		return stylistId;
	}

	public void setStylistId(String stylistId) {
		this.stylistId = stylistId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTotalIpRefunded() {
		return totalIpRefunded;
	}

	public void setTotalIpRefunded(String totalIpRefunded) {
		this.totalIpRefunded = totalIpRefunded;
	}

	public String getOrderIncrementId() {
		return orderIncrementId;
	}

	public void setOrderIncrementId(String orderIncrementId) {
		this.orderIncrementId = orderIncrementId;
	}

	public String getOrderCreatedAt() {
		return orderCreatedAt;
	}

	public void setOrderCreatedAt(String orderCreatedAt) {
		this.orderCreatedAt = orderCreatedAt;
	}

	public String getOrderPaidAt() {
		return orderPaidAt;
	}

	public void setOrderPaidAt(String orderPaidAt) {
		this.orderPaidAt = orderPaidAt;
	}

}

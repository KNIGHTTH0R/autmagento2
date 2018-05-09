package com.tools.data.soap;

public class DBCreditMemoModel implements Cloneable {

	private String stylistId;
	private String createdAt;
	private String updatedAt;
	private String state;
	private String totalIpRefunded;
	private String orderIncrementId;
	private String orderCreatedAt;
	private String orderPaidAt;
	private String grandTotal;

	
	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

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

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "DBCreditMemoModel [stylistId=" + stylistId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", state=" + state + ", totalIpRefunded=" + totalIpRefunded + ", orderIncrementId=" + orderIncrementId
				+ ", orderCreatedAt=" + orderCreatedAt + ", orderPaidAt=" + orderPaidAt + ", grandTotal=" + grandTotal
				+ "]";
	}
	
	

}

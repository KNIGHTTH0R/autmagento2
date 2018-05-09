package com.tools.data;

public class IpOverViewReturnsListModel {
	String orderId;
	String refundDate;
	String refundType;
	String amount;
	String ip;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "IpOverViewReturnsListModel [orderId=" + orderId + ", refundDate=" + refundDate + ", refundType="
				+ refundType + ", amount=" + amount + ", ip=" + ip + "]";
	}
	
	

}

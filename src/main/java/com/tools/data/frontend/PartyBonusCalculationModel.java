package com.tools.data.frontend;

public class PartyBonusCalculationModel {

	private String orderId;
	private String total;
	private String ip;
	private String percent;
	private String orderStatus;
	private String date;
	private String ipThisParty;
	private String ipInProgress;
	private String orderNo;
	private String shippingValue;;
	
	 
	
	public String getShippingValue() {
		return shippingValue;
	}

	public void setShippingValue(String shippingValue) {
		this.shippingValue = shippingValue;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIpThisParty() {
		return ipThisParty;
	}

	public void setIpThisParty(String ipThisParty) {
		this.ipThisParty = ipThisParty;
	}

	public String getIpInProgress() {
		return ipInProgress;
	}

	public void setIpInProgress(String ipInProgress) {
		this.ipInProgress = ipInProgress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


}

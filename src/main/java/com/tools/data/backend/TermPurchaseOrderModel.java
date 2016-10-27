package com.tools.data.backend;

public class TermPurchaseOrderModel {

	private String incrementId;
	private String executionDate;
	private String productSku;
	private String boughtQty;
	private String inStock;
	private String isDiscontinued;
	private String productQty;
	private String minimumQty;
	private String earliestAv;
	private String scheduledPaymentStatus;
	private String orderStatus;
	private String recomandation;
	private String reason;

	public String getIncrementId() {
		return incrementId;
	}

	public void setIncrementId(String incrementId) {
		this.incrementId = incrementId;
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getBoughtQty() {
		return boughtQty;
	}

	public void setBoughtQty(String boughtQty) {
		this.boughtQty = boughtQty;
	}

	public String getInStock() {
		return inStock;
	}

	public void setInStock(String inStock) {
		this.inStock = inStock;
	}

	public String getIsDiscontinued() {
		return isDiscontinued;
	}

	public void setIsDiscontinued(String isDiscontinued) {
		this.isDiscontinued = isDiscontinued;
	}

	public String getProductQty() {
		return productQty;
	}

	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}

	public String getMinimumQty() {
		return minimumQty;
	}

	public void setMinimumQty(String minimumQty) {
		this.minimumQty = minimumQty;
	}

	public String getEarliestAv() {
		return earliestAv;
	}

	public void setEarliestAv(String earliestAv) {
		this.earliestAv = earliestAv;
	}

	public String getScheduledPaymentStatus() {
		return scheduledPaymentStatus;
	}

	public void setScheduledPaymentStatus(String scheduledPaymentStatus) {
		this.scheduledPaymentStatus = scheduledPaymentStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRecomandation() {
		return recomandation;
	}

	public void setRecomandation(String recomandation) {
		this.recomandation = recomandation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}

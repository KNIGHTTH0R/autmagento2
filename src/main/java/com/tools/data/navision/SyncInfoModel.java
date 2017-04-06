package com.tools.data.navision;

public class SyncInfoModel {

	private String sku;
	private String quantity;
	private String isInStock;
	private String minumimQuantity;
	private String isDiscontinued;
	private String totalQuantity;
	private String maxPercentToBorrow;
	private String earliestAvailability;
	private String pendingQuantity;

	public SyncInfoModel() {
		sku = "null";
		quantity = "null";
		isInStock = "null";
		minumimQuantity = "";
		isDiscontinued = "null";
		totalQuantity = "null";
		maxPercentToBorrow = "null";
		//earliestAvailability = "1753-01-01";
		pendingQuantity = "null";

	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMinumimQuantity() {
		return minumimQuantity;
	}

	public void setMinumimQuantity(String minumimQuantity) {
		this.minumimQuantity = minumimQuantity;
	}

	public String getIsDiscontinued() {
		return isDiscontinued;
	}

	public void setIsDiscontinued(String isDiscontinued) {
		this.isDiscontinued = isDiscontinued;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getMaxPercentToBorrow() {
		return maxPercentToBorrow;
	}

	public void setMaxPercentToBorrow(String maxPercentToBorrow) {
		this.maxPercentToBorrow = maxPercentToBorrow;
	}

	public String getEarliestAvailability() {
		return earliestAvailability;
	}

	public void setEarliestAvailability(String earliestAvailability) {
		this.earliestAvailability = earliestAvailability;
	}

	public String getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(String pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public String getIsInStock() {
		return isInStock;
	}

	public void setIsInStock(String isInStock) {
		this.isInStock = isInStock;
	}

	@Override
	public String toString() {
		return "SyncInfoModel [sku=" + sku + ", quantity=" + quantity + ", isInStock=" + isInStock
				+ ", minumimQuantity=" + minumimQuantity + ", isDiscontinued=" + isDiscontinued + ", totalQuantity="
				+ totalQuantity + ", maxPercentToBorrow=" + maxPercentToBorrow + ", earliestAvailability="
				+ earliestAvailability + ", pendingQuantity=" + pendingQuantity + "]";
	}

}

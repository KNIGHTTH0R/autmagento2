package com.tools.data.navision;

public class SyncInfoModel {

	private String sku;
	private String quantity;
	private String minumimQuantity;
	private String isDiscontinued;
	private String totalQuantity;
	private String maxPercentToBorrow;
	private String earliestAvailability;

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

}

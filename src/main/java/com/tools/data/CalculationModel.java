package com.tools.data;

import java.math.BigDecimal;

public class CalculationModel {

	private String tableType;
	private BigDecimal retailPrice;
	private BigDecimal askingPrice;
	private BigDecimal finalPrice;
	private int ipPoints;
	

	public String getTableType() {
		return tableType;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public BigDecimal getAskingPrice() {
		return askingPrice;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public int getIpPoints() {
		return ipPoints;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setAskingPrice(BigDecimal askingPrice) {
		this.askingPrice = askingPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setIpPoints(int ipPoints) {
		this.ipPoints = ipPoints;
	}

}

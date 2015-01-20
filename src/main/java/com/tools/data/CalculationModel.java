package com.tools.data;

public class CalculationModel {

	private String tableType;
	private double retailPrice;
	private double askingPrice;
	private double finalPrice;
	private int ipPoints;

	public String getTableType() {
		return tableType;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public double getAskingPrice() {
		return askingPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public int getIpPoints() {
		return ipPoints;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setAskingPrice(double askingPrice) {
		this.askingPrice = askingPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setIpPoints(int ipPoints) {
		this.ipPoints = ipPoints;
	}

}

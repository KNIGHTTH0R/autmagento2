package com.tools.data;

import java.math.BigDecimal;

/**
 * This model will hold Segmentation totals. Segmentation - tableType. All
 * values are calculated with all products in that segment.
 * {@link CalculationModel} is used in lists of {@link CalculationModel}. The
 * list represents all the sections in the cart = whole cart.
 * 
 * @author voicu.vac
 *
 */
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

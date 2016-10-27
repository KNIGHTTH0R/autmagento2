package com.tools.data;


/**
 * This model will include the calculation details. When calculating prices and
 * discounts this model will hold all the intermediary values obtained during
 * calculus.
 * 
 * @author voicu.vac
 *
 */
public class BorrowCartCalcDetailsModel {

	private String totalAmount;
	private String subTotal;
	private String tax;
	private String ipPoints;

	public String getTotalAmount() {
		return totalAmount;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

}

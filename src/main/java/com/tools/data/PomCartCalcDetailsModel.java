package com.tools.data;


/**
 * This model will include the calculation details. When calculating prices and
 * discounts this model will hold all the intermediary values obtained during
 * calculus.
 * 
 * @author voicu.vac
 *
 */
public class PomCartCalcDetailsModel {

	private String totalAmount;
	private String subTotal;
	private String tax;
	private String pomDiscount;

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

	public String getPomDiscount() {
		return pomDiscount;
	}

	public void setPomDiscount(String pomDiscount) {
		this.pomDiscount = pomDiscount;
	}


}

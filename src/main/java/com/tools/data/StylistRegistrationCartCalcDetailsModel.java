package com.tools.data;

/**
 * This model will include the calculation details. When calculating prices and
 * discounts this model will hold all the intermediary values obtained during
 * calculus.
 */

public class StylistRegistrationCartCalcDetailsModel {

	private String totalAmount;
	private String subTotal;
	private String tax;
	private String voucherDiscount;
	private String shipping;

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

	public String getVoucherDiscount() {
		return voucherDiscount;
	}

	public void setVoucherDiscount(String voucherDiscount) {
		this.voucherDiscount = voucherDiscount;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

}

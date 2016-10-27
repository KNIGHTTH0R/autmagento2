package com.tools.data.frontend;

public class ShippingModel {

	private String subTotal;
	private String discountPrice;
	private String shippingPrice;
	private String totalAmount;
	private String tax;

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public String getShippingPrice() {
		return shippingPrice;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}

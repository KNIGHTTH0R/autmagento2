package com.tools.data;

public class CartTotalsModel {

	private String subtotal;
	private String jewelryBonus;
	private String discount;
	private String tax;
	private String shipping;
	private String totalAmount;
	private String ipPoints;

	public String getSubtotal() {
		return subtotal;
	}

	public String getJewelryBonus() {
		return jewelryBonus;
	}

	public String getDiscount() {
		return discount;
	}

	public String getShipping() {
		return shipping;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public void setJewelryBonus(String jewelryBonus) {
		this.jewelryBonus = jewelryBonus;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

}

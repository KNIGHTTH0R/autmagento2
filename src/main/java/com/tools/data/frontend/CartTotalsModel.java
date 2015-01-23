package com.tools.data.frontend;

public class CartTotalsModel {

	private String subtotal;
	private String jewelryBonus;
	private String discount;
	private String discount25;
	private String discount50;
	private String discount3To1;
	private String tax;
	private String shipping;
	private String totalAmount;
	private String ipPoints;

	public CartTotalsModel() {
		setSubtotal("");
		setJewelryBonus("");
		setDiscount("");
		setDiscount25("");
		setDiscount50("");
		setDiscount3To1("");
		setTax("");
		setShipping("");
		setTotalAmount("");
		setIpPoints("");
	}

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

	public String getDiscount25() {
		return discount25;
	}

	public String getDiscount50() {
		return discount50;
	}

	public String getDiscount3To1() {
		return discount3To1;
	}

	public void setDiscount25(String discount25) {
		this.discount25 = discount25;
	}

	public void setDiscount50(String discount50) {
		this.discount50 = discount50;
	}

	public void setDiscount3To1(String discount3To1) {
		this.discount3To1 = discount3To1;
	}

}

package com.tools.data;

public class CartProductModel {

	private String name;
	private String prodCode;
	private String quantity;
	private String unitPrice;
	private String productsPrice;
	private String finalPrice;
	private String priceIP;
	private String discountClass;
	

	public String getDiscountClass() {
		return discountClass;
	}

	public void setDiscountClass(String discountClass) {
		this.discountClass = discountClass;
	}

	public String getName() {
		return name;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public String getProductsPrice() {
		return productsPrice;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setProductsPrice(String productsPrice) {
		this.productsPrice = productsPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getPriceIP() {
		return priceIP;
	}

	public void setPriceIP(String priceIP) {
		this.priceIP = priceIP;
	}

}

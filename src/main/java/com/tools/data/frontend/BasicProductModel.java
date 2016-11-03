package com.tools.data.frontend;

public class BasicProductModel {

	private String name;
	private String prodCode;
	private String quantity;
	private String unitPrice;
	private String specialPrice;
	private String productsPrice;
	private String finalPrice;
	private String priceIP;
	private String discountClass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductsPrice() {
		return productsPrice;
	}

	public void setProductsPrice(String productsPrice) {
		this.productsPrice = productsPrice;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getPriceIP() {
		return priceIP;
	}

	public void setPriceIP(String priceIP) {
		this.priceIP = priceIP;
	}

	public String getDiscountClass() {
		return discountClass;
	}

	public void setDiscountClass(String discountClass) {
		this.discountClass = discountClass;
	}
	
	public String getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}

	public BasicProductModel newProductObject(BasicProductModel model) {
		BasicProductModel newProduct = new BasicProductModel();
		newProduct.setName(model.getName());
		newProduct.setProdCode(model.getProdCode());
		newProduct.setQuantity(model.getQuantity());
		newProduct.setUnitPrice(model.getUnitPrice());
		newProduct.setProductsPrice(model.getProductsPrice());
		newProduct.setFinalPrice(model.getFinalPrice());
		newProduct.setPriceIP(model.getPriceIP());
		newProduct.setDiscountClass(model.getDiscountClass());
		newProduct.setSpecialPrice(model.getSpecialPrice());

		return newProduct;

	}

}

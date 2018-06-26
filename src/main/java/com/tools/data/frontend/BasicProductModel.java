package com.tools.data.frontend;

import java.math.BigDecimal;

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
	private String deliveryDate;
	private String earliestAvailability;
	private String isMarketing;
	private BigDecimal isBuy3Get1;
	private BigDecimal finalPriceWithBuy3;
	private String discount20;
	private String discountJb;
	private String discountMarketing;
	private String tax;
	private String colour;
	private String size;
	
	
	
	
	
	
	
	public BasicProductModel() {
	
		this.discount20 = "0";
		this.discountJb = "0";
		this.discountMarketing = "0";
	}

	
	
	public String getColour() {
		return colour;
	}



	public void setColour(String colour) {
		this.colour = colour;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public String getDiscountJb() {
		return discountJb;
	}

	public void setDiscountJb(String discountJb) {
		this.discountJb = discountJb;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getDiscountMarketing() {
		return discountMarketing;
	}

	public void setDiscountMarketing(String discountMarketing) {
		this.discountMarketing = discountMarketing;
	}

	public String getDiscount20() {
		return discount20;
	}

	public void setDiscount20(String discount20) {
		this.discount20 = discount20;
	}

	public BigDecimal getFinalPriceWithBuy3() {
		return finalPriceWithBuy3;
	}

	public void setFinalPriceWithBuy3(BigDecimal finalPriceWithBuy3) {
		this.finalPriceWithBuy3 = finalPriceWithBuy3;
	}

	public BigDecimal getIsBuy3Get1() {
		return isBuy3Get1;
	}

	public void setIsBuy3Get1(BigDecimal discount) {
		this.isBuy3Get1 = discount;
	}

	public String getIsMarketing() {
		return isMarketing;
	}

	public void setIsMarketing(String isMarketing) {
		this.isMarketing = isMarketing;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEarliestAvailability() {
		return earliestAvailability;
	}

	public void setEarliestAvailability(String earliestAvailability) {
		this.earliestAvailability = earliestAvailability;
	}

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

	
	@Override
	public String toString() {
		return "BasicProductModel [name=" + name + ", prodCode=" + prodCode + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", specialPrice=" + specialPrice + ", productsPrice=" + productsPrice + ", finalPrice="
				+ finalPrice + ", priceIP=" + priceIP + ", discountClass=" + discountClass + ", deliveryDate="
				+ deliveryDate + ", earliestAvailability=" + earliestAvailability + ", isMarketing=" + isMarketing
				+ ", isBuy3Get1=" + isBuy3Get1 + ", finalPriceWithBuy3=" + finalPriceWithBuy3 + ", discount20="
				+ discount20 + ", discountJb=" + discountJb + ", discountMarketing=" + discountMarketing + ", tax="
				+ tax + "]";
	}



	
	
}

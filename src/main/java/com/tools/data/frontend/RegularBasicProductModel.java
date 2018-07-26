package com.tools.data.frontend;

public class RegularBasicProductModel {

	private String name;
	private String prodCode;
	private String quantity;
	private String unitPrice;
	private String finalPrice;
	private String bonusType;
	private String bunosValue;
	private String ipPoints;
	private String deliveryDate;
	private String endPrice;
	
	
	

	public RegularBasicProductModel() {
		this.endPrice = "0";
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
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

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public RegularBasicProductModel newProductObject(RegularBasicProductModel model) {
		RegularBasicProductModel newProduct = new RegularBasicProductModel();
		newProduct.setName(model.getName());
		newProduct.setProdCode(model.getProdCode());
		newProduct.setQuantity(model.getQuantity());
		newProduct.setUnitPrice(model.getUnitPrice());
		newProduct.setFinalPrice(model.getFinalPrice());

		return newProduct;

	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getBunosValue() {
		return bunosValue;
	}

	public void setBunosValue(String bunosValue) {
		this.bunosValue = bunosValue;
	}

	@Override
	public String toString() {
		return "RegularBasicProductModel [name=" + name + ", prodCode=" + prodCode + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", finalPrice=" + finalPrice + ", bonusType=" + bonusType
				+ ", bunosValue=" + bunosValue + ", ipPoints=" + ipPoints + ", deliveryDate=" + deliveryDate + "]";
	}

	
}

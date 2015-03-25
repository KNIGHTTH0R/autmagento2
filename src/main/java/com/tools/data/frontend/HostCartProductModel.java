package com.tools.data.frontend;

/**
 * Cart product model, should be used when grabbing products from the cart.
 * DiscountClass field should be populated with the Segment the product is found
 * in (Eg. "Marketing discount", "25%" etc...)
 * 
 * @author voicu.vac
 *
 */
public class HostCartProductModel {

	private String name;
	private String prodCode;
	private String quantity;
	private String unitPrice;;
	private String finalPrice;
	private String bonusType;
	private String ipPoints;

	public String getName() {
		return name;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
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

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

}

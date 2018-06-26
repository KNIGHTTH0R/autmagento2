package com.tools.data.frontend;

public class HostBasicProductModel {

	private String name;
	private String prodCode;
	private String quantity;
	private String unitPrice;
	private String finalPrice;
	private String finalPriceDiscounted;
	private String bonusType;
	private String bunosValue;
	private String discountValue;
	private String ipPoints;
	private String ipPointsDiscounted;
	private String deliveryDate;
	private String earliestAvailability;
	private String productTaxAmount;
	private String rowTotal;
	
	
	private String remainingQty;
	private String refundedQty;
	
	private String refundedIp;
	private String remainingIp;
	
	private String refundedJb;
	private String remainingJb;
	
	private String refundedMb;
	private String remainingMb;
	
	private String refundedFd;
	private String remainingFd;
	
	
	
	
	
	
	
	public String getProductTaxAmount() {
		return productTaxAmount;
	}

	public void setProductTaxAmount(String productTaxAmount) {
		this.productTaxAmount = productTaxAmount;
	}

	public String getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(String rowTotal) {
		this.rowTotal = rowTotal;
	}

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	public String getFinalPriceDiscounted() {
		return finalPriceDiscounted;
	}

	public void setFinalPriceDiscounted(String finalPriceDiscounted) {
		this.finalPriceDiscounted = finalPriceDiscounted;
	}

	public String getIpPointsDiscounted() {
		return ipPointsDiscounted;
	}

	public void setIpPointsDiscounted(String ipPointsDiscounted) {
		this.ipPointsDiscounted = ipPointsDiscounted;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
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

	public HostBasicProductModel newProductObject(HostBasicProductModel model) {
		HostBasicProductModel newProduct = new HostBasicProductModel();
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

	public String getIpPoints() {
		return ipPoints;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

	public String getEarliestAvailability() {
		return earliestAvailability;
	}

	public void setEarliestAvailability(String earliestAvailability) {
		this.earliestAvailability = earliestAvailability;
	}
	

	public String getRemainingQty() {
		return remainingQty;
	}

	public void setRemainingQty(String remainingQty) {
		this.remainingQty = remainingQty;
	}

	public String getRefundedQty() {
		return refundedQty;
	}

	public void setRefundedQty(String refundedQty) {
		this.refundedQty = refundedQty;
	}

	public String getRefundedIp() {
		return refundedIp;
	}

	public void setRefundedIp(String refundedIp) {
		this.refundedIp = refundedIp;
	}

	public String getRemainingIp() {
		return remainingIp;
	}

	public void setRemainingIp(String remainingIp) {
		this.remainingIp = remainingIp;
	}

	public String getRefundedJb() {
		return refundedJb;
	}

	public void setRefundedJb(String refundedJb) {
		this.refundedJb = refundedJb;
	}

	public String getRemainingJb() {
		return remainingJb;
	}

	public void setRemainingJb(String remainingJb) {
		this.remainingJb = remainingJb;
	}

	public String getRefundedMb() {
		return refundedMb;
	}

	public void setRefundedMb(String refundedMb) {
		this.refundedMb = refundedMb;
	}

	public String getRemainingMb() {
		return remainingMb;
	}

	public void setRemainingMb(String remainingMb) {
		this.remainingMb = remainingMb;
	}

	public String getRefundedFd() {
		return refundedFd;
	}

	public void setRefundedFd(String refundedFd) {
		this.refundedFd = refundedFd;
	}

	public String getRemainingFd() {
		return remainingFd;
	}

	public void setRemainingFd(String remainingFd) {
		this.remainingFd = remainingFd;
	}

	@Override
	public String toString() {
		return "HostBasicProductModel [name=" + name + ", prodCode=" + prodCode + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", finalPrice=" + finalPrice + ", finalPriceDiscounted="
				+ finalPriceDiscounted + ", bonusType=" + bonusType + ", bunosValue=" + bunosValue + ", discountValue="
				+ discountValue + ", ipPoints=" + ipPoints + ", ipPointsDiscounted=" + ipPointsDiscounted
				+ ", deliveryDate=" + deliveryDate + ", earliestAvailability=" + earliestAvailability
				+ ", productTaxAmount=" + productTaxAmount + ", rowTotal=" + rowTotal + ", remainingQty=" + remainingQty
				+ ", refundedQty=" + refundedQty + ", refundedIp=" + refundedIp + ", remainingIp=" + remainingIp
				+ ", refundedJb=" + refundedJb + ", remainingJb=" + remainingJb + ", refundedMb=" + refundedMb
				+ ", remainingMb=" + remainingMb + ", refundedFd=" + refundedFd + ", remainingFd=" + remainingFd + "]";
	}



	
}

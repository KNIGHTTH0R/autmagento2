package com.tools.data.frontend;

public class ReturnProductModel {

	private String name;
	private String prodCode;
	private String returnedQty;
	private String size;
	private String colour;
	private String reason;
	private String specifiedAmount;
	private String status;
	private String paidPrice;

	
	public ReturnProductModel() {
		this.returnedQty="Keine Angabe";
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

	public String getReturnedQty() {
		return returnedQty;
	}

	public void setReturnedQty(String returnedQty) {
		this.returnedQty = returnedQty;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSpecifiedAmount() {
		return specifiedAmount;
	}

	public void setSpecifiedAmount(String specifiedAmount) {
		this.specifiedAmount = specifiedAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(String paidPrice) {
		this.paidPrice = paidPrice;
	}

}

package com.tools.data.frontend;

public class StylistInventoryModel {
	private String productSku;
	private String productPrice;
	private String packageSku;
	private String qtyBorrowed;
	private String qtyReturned;
	private String dateToReturn;
	private String status;

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getPackageSku() {
		return packageSku;
	}

	public void setPackageSku(String packageSku) {
		this.packageSku = packageSku;
	}

	public String getQtyBorrowed() {
		return qtyBorrowed;
	}

	public void setQtyBorrowed(String qtyBorrowed) {
		this.qtyBorrowed = qtyBorrowed;
	}

	public String getQtyReturned() {
		return qtyReturned;
	}

	public void setQtyReturned(String qtyReturned) {
		this.qtyReturned = qtyReturned;
	}

	public String getDateToReturn() {
		return dateToReturn;
	}

	public void setDateToReturn(String dateToReturn) {
		this.dateToReturn = dateToReturn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StylistInventoryModel [productSku=" + productSku + ", productPrice=" + productPrice + ", packageSku="
				+ packageSku + ", qtyBorrowed=" + qtyBorrowed + ", qtyReturned=" + qtyReturned + ", dateToReturn="
				+ dateToReturn + ", status=" + status + "]";
	}

}

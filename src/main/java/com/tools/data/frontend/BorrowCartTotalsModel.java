package com.tools.data.frontend;


/**
 * Model used to hold Totals Section data from the Cart Page.
 * 
 * @author voicu.vac
 *
 */
public class BorrowCartTotalsModel {

	private String subtotal;
	private String tax;
	private String totalAmount;
	private String ipPoints;

	public BorrowCartTotalsModel() {
		setSubtotal("");
		setTax("");
		setTotalAmount("");
		setIpPoints("");

	}

	public String getSubtotal() {
		return subtotal;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

}

package com.tools.data;

public class IpOverViewSummaryModel {

	private String paidOrdersPreviosMonth;
	private String paidOrdersThisMonth;
	private String reverseChargebackThisMonth;
	private String chargebacksThisMonth;
	private String returnsThisMonth;
	private String manualIpCorrection;
	private String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPaidOrdersPreviosMonth() {
		return paidOrdersPreviosMonth;
	}

	public void setPaidOrdersPreviosMonth(String paidOrdersPreviosMonth) {
		this.paidOrdersPreviosMonth = paidOrdersPreviosMonth;
	}

	public String getPaidOrdersThisMonth() {
		return paidOrdersThisMonth;
	}

	public void setPaidOrdersThisMonth(String paidOrdersThisMonth) {
		this.paidOrdersThisMonth = paidOrdersThisMonth;
	}

	public String getReverseChargebackThisMonth() {
		return reverseChargebackThisMonth;
	}

	public void setReverseChargebackThisMonth(String reverseChargebackThisMonth) {
		this.reverseChargebackThisMonth = reverseChargebackThisMonth;
	}

	public String getChargebacksThisMonth() {
		return chargebacksThisMonth;
	}

	public void setChargebacksThisMonth(String chargebacksThisMonth) {
		this.chargebacksThisMonth = chargebacksThisMonth;
	}

	public String getReturnsThisMonth() {
		return returnsThisMonth;
	}

	public void setReturnsThisMonth(String returnsThisMonth) {
		this.returnsThisMonth = returnsThisMonth;
	}

	public String getManualIpCorrection() {
		return manualIpCorrection;
	}

	public void setManualIpCorrection(String manualIpCorrection) {
		this.manualIpCorrection = manualIpCorrection;
	}

	@Override
	public String toString() {
		return "IpOverViewSummaryModel [paidOrdersPreviosMonth=" + paidOrdersPreviosMonth + ", paidOrdersThisMonth=" + paidOrdersThisMonth + ", reverseChargebackThisMonth="
				+ reverseChargebackThisMonth + ", chargebacksThisMonth=" + chargebacksThisMonth + ", returnsThisMonth=" + returnsThisMonth + ", manualIpCorrection="
				+ manualIpCorrection + ", total=" + total + "]";
	}

}

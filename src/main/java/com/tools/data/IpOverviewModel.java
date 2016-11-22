package com.tools.data;

import java.util.List;

public class IpOverviewModel {
	private String paidOrdersPreviosMonth;
	private String paidOrdersThisMonth;
	private String reverseChargebackThisMonth;
	private String chargebacksThisMonth;
	private String returnsThisMonth;
	private String manualIpCorrection;
	private String totalIp;
	private String ipThisMonth;
	private String ipLastMonth;
	private String openChargebacks;
	private String ipTPOrdersThisMonth;
	private String ipTPOrdersLastMonth;
	private String openIpTotal;
	private List<IpOverViewPayedOrdersModel> payedOrders;
	private List<IpOverViewReturnsListModel> returns;
	private List<IpOverViewReturnsListModel> manualCorections;

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

	public String getTotalIp() {
		return totalIp;
	}

	public void setTotalIp(String totalIp) {
		this.totalIp = totalIp;
	}

	public String getIpThisMonth() {
		return ipThisMonth;
	}

	public void setIpThisMonth(String ipThisMonth) {
		this.ipThisMonth = ipThisMonth;
	}

	public String getIpLastMonth() {
		return ipLastMonth;
	}

	public void setIpLastMonth(String ipLastMonth) {
		this.ipLastMonth = ipLastMonth;
	}

	public String getOpenChargebacks() {
		return openChargebacks;
	}

	public void setOpenChargebacks(String openChargebacks) {
		this.openChargebacks = openChargebacks;
	}

	public String getIpTPOrdersThisMonth() {
		return ipTPOrdersThisMonth;
	}

	public void setIpTPOrdersThisMonth(String ipTPOrdersThisMonth) {
		this.ipTPOrdersThisMonth = ipTPOrdersThisMonth;
	}

	public String getIpTPOrdersLastMonth() {
		return ipTPOrdersLastMonth;
	}

	public void setIpTPOrdersLastMonth(String ipTPOrdersLastMonth) {
		this.ipTPOrdersLastMonth = ipTPOrdersLastMonth;
	}

	public String getOpenIpTotal() {
		return openIpTotal;
	}

	public void setOpenIpTotal(String openIpTotal) {
		this.openIpTotal = openIpTotal;
	}

	public List<IpOverViewPayedOrdersModel> getPayedOrders() {
		return payedOrders;
	}

	public void setPayedOrders(List<IpOverViewPayedOrdersModel> payedOrders) {
		this.payedOrders = payedOrders;
	}

	public List<IpOverViewReturnsListModel> getReturns() {
		return returns;
	}

	public void setReturns(List<IpOverViewReturnsListModel> returns) {
		this.returns = returns;
	}

	public List<IpOverViewReturnsListModel> getManualCorections() {
		return manualCorections;
	}

	public void setManualCorections(List<IpOverViewReturnsListModel> manualCorections) {
		this.manualCorections = manualCorections;
	}

}

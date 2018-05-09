package com.tools.data;

import java.util.List;

public class IpOverviewModel {
	
	
	

	@Override
	public String toString() {
		return "IpOverviewModel [paidOrdersPreviosMonth=" + paidOrdersPreviosMonth + ", paidOrdersThisMonth="
				+ paidOrdersThisMonth + ", reverseChargebackThisMonth=" + reverseChargebackThisMonth
				+ ", chargebacksThisMonth=" + chargebacksThisMonth + ", returnsThisMonth=" + returnsThisMonth
				+ ", manualIpCorrection=" + manualIpCorrection + ", totalIp=" + totalIp + ", ipThisMonth=" + ipThisMonth
				+ ", ipLastMonth=" + ipLastMonth + ", openChargebacks=" + openChargebacks + ", ipTPOrdersThisMonth="
				+ ipTPOrdersThisMonth + ", ipTPOrdersLastMonth=" + ipTPOrdersLastMonth + ", openIpTotal=" + openIpTotal
				+ "]";
	}

	private String paidOrdersPreviosMonth;
	private String paidOrdersThisMonth;
	private String reverseChargebackThisMonth;
	private String chargebacksThisMonth;
	private String returnsThisMonth;
	private String manualIpCorrection;
	private String manualIpCorrectionTop;
	private String totalIp;
	private String ipThisMonth;
	private String ipLastMonth;
	private String openChargebacks;
	private String ipTPOrdersThisMonth;
	private String ipTPOrdersLastMonth;
	private String openIpTotal;
	private String stylistId;
	private String ipTop;
	private String ip30Top;
	private List<IpOverViewPayedOrdersModel> payedOrders;
	private List<IpOverViewReturnsListModel> returns;
	private List<IpOverViewReturnsListModel> manualCorections;

	
	
	
	
	public String getManualIpCorrectionTop() {
		return manualIpCorrectionTop;
	}

	public void setManualIpCorrectionTop(String manualIpCorrectionTop) {
		this.manualIpCorrectionTop = manualIpCorrectionTop;
	}

	public String getIpTop() {
		return ipTop;
	}

	public void setIpTop(String ipTop) {
		this.ipTop = ipTop;
	}

	public String getIp30Top() {
		return ip30Top;
	}

	public void setIp30Top(String ip30Top) {
		this.ip30Top = ip30Top;
	}

	public String getStylistId() {
		return stylistId;
	}

	public void setStylistId(String stylistId) {
		this.stylistId = stylistId;
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

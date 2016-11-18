package com.tools.data;

public class IpOverViewOpenIpsModel {
	private String ipThisMonth;
	private String ipLastMonth;
	private String openChargebacks;
	private String ipTPOrdersThisMonth;
	private String ipTPOrdersLastMonth;
	private String openIpTotal;

	
	
	public String getOpenIpTotal() {
		return openIpTotal;
	}

	public void setOpenIpTotal(String openIpTotal) {
		this.openIpTotal = openIpTotal;
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

}

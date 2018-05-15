package com.tools.data.frontend;

public class ClosedPartyPerformanceModel {

	private String noOfOrders;
	private String retail;
	private String retailNoShipping;
	private String ip;
	private String ipInPayment;
	private String jewelryBonus;
	private String fourthyDiscounts;
	private String ipThisParty;
	private String total;
	private String guestsNo;
	private String shippingTotal;;

	
	

	public String getRetailNoShipping() {
		return retailNoShipping;
	}

	public void setRetailNoShipping(String retailNoShipping) {
		this.retailNoShipping = retailNoShipping;
	}

	public String getShippingTotal() {
		return shippingTotal;
	}

	public void setShippingTotal(String shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	public String getGuestsNo() {
		return guestsNo;
	}

	public void setGuestsNo(String guestsNo) {
		this.guestsNo = guestsNo;
	}

	public String getIpThisParty() {
		return ipThisParty;
	}

	public void setIpThisParty(String ipThisParty) {
		this.ipThisParty = ipThisParty;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getNoOfOrders() {
		return noOfOrders;
	}

	public void setNoOfOrders(String noOfOrders) {
		this.noOfOrders = noOfOrders;
	}

	public String getRetail() {
		return retail;
	}

	public void setRetail(String retail) {
		this.retail = retail;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpInPayment() {
		return ipInPayment;
	}

	public void setIpInPayment(String ipInPayment) {
		this.ipInPayment = ipInPayment;
	}

	public String getJewelryBonus() {
		return jewelryBonus;
	}

	public void setJewelryBonus(String jewelryBonus) {
		this.jewelryBonus = jewelryBonus;
	}

	public String getFourthyDiscounts() {
		return fourthyDiscounts;
	}

	public void setFourthyDiscounts(String fourthyDiscounts) {
		this.fourthyDiscounts = fourthyDiscounts;
	}

	@Override
	public String toString() {
		return "ClosedPartyPerformanceModel [noOfOrders=" + noOfOrders + ", retail=" + retail + ", retailNoShipping="
				+ retailNoShipping + ", ip=" + ip + ", ipInPayment=" + ipInPayment + ", jewelryBonus=" + jewelryBonus
				+ ", fourthyDiscounts=" + fourthyDiscounts + ", ipThisParty=" + ipThisParty + ", total=" + total
				+ ", guestsNo=" + guestsNo + ", shippingTotal=" + shippingTotal + "]";
	}

	
	
	
}

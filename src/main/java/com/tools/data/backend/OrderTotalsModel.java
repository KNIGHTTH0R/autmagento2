package com.tools.data.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderTotalsModel {

	private String subtotal;
	private String shipping;
	private Map<String, String> discountList = new HashMap<String, String>();
	private String tax;
	private String totalAmount;
	private String totalPaid;
	private String totalRefunded;
	private String totalPayable;
	private String totalIP;
	private String totalFortyDiscounts;
	private String totalBonusJeverly;
	private String totalMarketingBonus;
	private String totalOrder;
	private String discount;
	private String totalIpRefunded;
	private String refundToStoreCredit;
	private String jewerlryCreditRefunded;
	private String marketingCreditRefunded;
	private String fortyCreditRefunded;
	


	
	
	
	
	public OrderTotalsModel() {
		this.fortyCreditRefunded = "0";
		this.totalIP="0";
		this.totalIpRefunded="0";
	}

	public String getJewerlryCreditRefunded() {
		return jewerlryCreditRefunded;
	}

	public void setJewerlryCreditRefunded(String jewerlryCreditRefunded) {
		this.jewerlryCreditRefunded = jewerlryCreditRefunded;
	}

	public String getMarketingCreditRefunded() {
		return marketingCreditRefunded;
	}

	public void setMarketingCreditRefunded(String marketingCreditRefunded) {
		this.marketingCreditRefunded = marketingCreditRefunded;
	}

	public String getFortyCreditRefunded() {
		return fortyCreditRefunded;
	}

	public void setFortyCreditRefunded(String fortyCreditRefunded) {
		this.fortyCreditRefunded = fortyCreditRefunded;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTotalIpRefunded() {
		return totalIpRefunded;
	}

	public void setTotalIpRefunded(String totalIpRefunded) {
		this.totalIpRefunded = totalIpRefunded;
	}

	public String getRefundToStoreCredit() {
		return refundToStoreCredit;
	}

	public void setRefundToStoreCredit(String refundToStoreCredit) {
		this.refundToStoreCredit = refundToStoreCredit;
	}

	public Map<String, String> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(Map<String, String> discountList) {
		this.discountList = discountList;
	}

	public String getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(String totalOrder) {
		this.totalOrder = totalOrder;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public void addDiscount(String key, String value) {
		discountList.put(key, value);
	}

	public void setDiscountMap(Map<String, String> discountList) {
		this.discountList = discountList;
	}

	public String getDiscount(String key) {
		return discountList.get(key);
	}

	public Map<String, String> getDiscountsMap() {
		return discountList;
	}

	public List<String> getDiscountsList() {
		List<String> resultList = new ArrayList<String>();

		for (String string : discountList.keySet()) {
			resultList.add(discountList.get(string));
		}
		return resultList;
	}

	public String getDiscountSumString() {
		double result = 0;
		for (String string : discountList.keySet()) {
			result += Double.parseDouble(discountList.get(string));
		}
		return String.valueOf(result);
	}

	public double getDiscountSumDouble() {
		double result = 0;
		for (String string : discountList.keySet()) {
			result += Double.parseDouble(discountList.get(string));
		}
		return result;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(String totalPaid) {
		this.totalPaid = totalPaid;
	}

	public String getTotalRefunded() {
		return totalRefunded;
	}

	public void setTotalRefunded(String totalRefunded) {
		this.totalRefunded = totalRefunded;
	}

	public String getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(String totalPayable) {
		this.totalPayable = totalPayable;
	}

	public String getTotalIP() {
		return totalIP;
	}

	public void setTotalIP(String totalIP) {
		this.totalIP = totalIP;
	}

	public String getTotalFortyDiscounts() {
		return totalFortyDiscounts;
	}

	public void setTotalFortyDiscounts(String totalFortyDiscounts) {
		this.totalFortyDiscounts = totalFortyDiscounts;
	}

	public String getTotalBonusJeverly() {
		return totalBonusJeverly;
	}

	public void setTotalBonusJeverly(String totalBonusJeverly) {
		this.totalBonusJeverly = totalBonusJeverly;
	}

	public String getTotalMarketingBonus() {
		return totalMarketingBonus;
	}

	public void setTotalMarketingBonus(String totalMarketingBonus) {
		this.totalMarketingBonus = totalMarketingBonus;
	}

	@Override
	public String toString() {
		return "OrderTotalsModel [subtotal=" + subtotal + ", shipping=" + shipping + ", discountList=" + discountList
				+ ", tax=" + tax + ", totalAmount=" + totalAmount + ", totalPaid=" + totalPaid + ", totalRefunded="
				+ totalRefunded + ", totalPayable=" + totalPayable + ", totalIP=" + totalIP + ", totalFortyDiscounts="
				+ totalFortyDiscounts + ", totalBonusJeverly=" + totalBonusJeverly + ", totalMarketingBonus="
				+ totalMarketingBonus + ", totalOrder=" + totalOrder + ", discount=" + discount + ", totalIpRefunded="
				+ totalIpRefunded + ", refundToStoreCredit=" + refundToStoreCredit + ", jewerlryCreditRefunded="
				+ jewerlryCreditRefunded + ", marketingCreditRefunded=" + marketingCreditRefunded
				+ ", fortyCreditRefunded=" + fortyCreditRefunded + "]";
	}

	

	// public String getDiscount() {
	// return discount;
	// }
	// public void setDiscount(String discount) {
	// this.discount = discount;
	// }

	
	
}

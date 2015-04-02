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

	// public String getDiscount() {
	// return discount;
	// }
	// public void setDiscount(String discount) {
	// this.discount = discount;
	// }

}

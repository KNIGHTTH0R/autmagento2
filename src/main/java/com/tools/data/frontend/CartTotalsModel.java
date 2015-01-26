package com.tools.data.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartTotalsModel {

	private String subtotal;
	private String jewelryBonus;
	private String tax;
	private String shipping;
	private String totalAmount;
	private String ipPoints;
	private Map<String, String> discountList = new HashMap<String, String>();

	public CartTotalsModel() {
		setSubtotal("");
		setJewelryBonus("");
		setTax("");
		setShipping("");
		setTotalAmount("");
		setIpPoints("");
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

	public String getSubtotal() {
		return subtotal;
	}

	public String getJewelryBonus() {
		return jewelryBonus;
	}

	public String getShipping() {
		return shipping;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public void setJewelryBonus(String jewelryBonus) {
		this.jewelryBonus = jewelryBonus;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	// public String getDiscount25() {
	// return discount25;
	// }
	//
	// public String getDiscount50() {
	// return discount50;
	// }
	//
	// public String getDiscount3To1() {
	// return discount3To1;
	// }
	//
	// public void setDiscount25(String discount25) {
	// this.discount25 = discount25;
	// }
	//
	// public void setDiscount50(String discount50) {
	// this.discount50 = discount50;
	// }
	//
	// public void setDiscount3To1(String discount3To1) {
	// this.discount3To1 = discount3To1;
	// }
	//
	// public String getDiscount() {
	// return discount;
	// }
	//
	// public void setDiscount(String discount) {
	// this.discount = discount;
	// }

}

package com.tools.data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * This model will include the calculation details. When calculating prices and
 * discounts this model will hold all the intermediary values obtained during
 * calculus.
 * 
 * @author voicu.vac
 *
 */
public class HostCartCalcDetailsModel {

	private String totalAmount;
	private String subTotal;
	private String tax;
	private String ipPoints;
	private Map<String, String> segmentTotals = new HashMap<String, String>();

	public String getTotalAmount() {
		return totalAmount;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void addSegment(String key, String value) {
		segmentTotals.put(key, value);
	}

	public String findSegment(String key) {
		return segmentTotals.get(key);
	}

	public String calculateSegmentsTotal() {
		BigDecimal resultSum = BigDecimal.ZERO;

		for (String key : segmentTotals.keySet()) {
			resultSum = resultSum.add(BigDecimal.valueOf(Double.parseDouble(segmentTotals.get(key))));
		}

		return resultSum.toString();
	}

	public Map<String, String> getSegments() {
		return segmentTotals;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public void addSegments(Map<String, String> map) {
		this.segmentTotals = map;
	}

	public String getIpPoints() {
		return ipPoints;
	}

	public void setIpPoints(String ipPoints) {
		this.ipPoints = ipPoints;
	}

	@Override
	public String toString() {
		return "HostCartCalcDetailsModel [totalAmount=" + totalAmount + ", subTotal=" + subTotal + ", tax=" + tax
				+ ", ipPoints=" + ipPoints + ", segmentTotals=" + segmentTotals + "]";
	}

	
	
}

package com.tools.data;

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
public class CalcDetailsModel {

	private String jewelryBonus;
	private String marketingBonus;
	private String totalAmount;
	private String subTotal;
	private String ipPoints;
	private String tax;
	private Map<String, String> segmentTotals = new HashMap<String, String>();
	private Map<String, String> calculationsList = new HashMap<String, String>();

	public String getJewelryBonus() {
		return jewelryBonus;
	}

	public String getMarketingBonus() {
		return marketingBonus;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public String getIpPoints() {
		return ipPoints;
	}
	
	public void addSegment(String key, String value){
		segmentTotals.put(key, value);
	}
	
	public String findSegment(String key){
		return segmentTotals.get(key);
	}
	
	public void addCalculation(String key, String value){
		calculationsList.put(key, value);
	}
	
	public String findCalculation(String key){
		return calculationsList.get(key);
	}
	
	public Map<String, String> getSegments(){
		return segmentTotals;
	}
	
	public Map<String, String> getCalculations(){
		return calculationsList;
	}

	public void setJewelryBonus(String jewelryBonus) {
		this.jewelryBonus = jewelryBonus;
	}

	public void setMarketingBonus(String marketingBonus) {
		this.marketingBonus = marketingBonus;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
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

	public void addCalculation(Map<String, String> map) {
		this.calculationsList = map; 
	}
	
	public void addSegments(Map<String, String> map) {
		this.segmentTotals = map; 
	}

}

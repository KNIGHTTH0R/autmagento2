package com.tools.data.navision;

public class SalesOrderInfoModel {
	private String sku;
	
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "SalesOrderInfoModel [sku=" + sku + "]";
	}

	
}

package com.tools.data.soap;

public class NavOrderLinesModel {

	private String no;
	private String type;
	private boolean bomItem;
	private String bomItemNo;
	private String shopParentItemNo;
	private String varianteCode;
	private String shippingAmount;
	private String lineDiscountAmount;

	
	public String getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(String lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public String getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(String shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public String getVarianteCode() {
		return varianteCode;
	}

	public void setVarianteCode(String varianteCode) {
		this.varianteCode = varianteCode;
	}

	public String getShopParentItemNo() {
		return shopParentItemNo;
	}

	public void setShopParentItemNo(String shopParentItemNo) {
		this.shopParentItemNo = shopParentItemNo;
	}

	public boolean isBomItem() {
		return bomItem;
	}

	public void setIsBomItem(boolean bomItem) {
		this.bomItem = bomItem;
	}

	public String getNo() {
		return no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getBomItemNo() {
		return bomItemNo;
	}

	public void setBomItemNo(String bomItemNo) {
		this.bomItemNo = bomItemNo;
	}

	public void setNo(String no) {
		this.no = no;
	}

}

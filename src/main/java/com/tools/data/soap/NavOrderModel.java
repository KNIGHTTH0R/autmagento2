package com.tools.data.soap;

import java.util.List;

public class NavOrderModel {

	private String incrementId;
	private String navGrandTotal;
	private String postingDate;
	private String orderDate;
	private String externalDocumentNo;
	private String vatProdPostingGroup;
	private String calculatedGrandTotal;

	private String salesPersonCode;
	private String youRefercences;
	private String sellToContactNo;
	private String sellToCustomerNo;
	private String shopShipmentMethod;
	private String shopPaymentMethod;
	private String shopOrderType;
	private String shopCartType;
	private String shippingAmount;
	private String partyId;
	private String isAlreadyShipped;
	private String magentoGrandTotal;
	private String shopIsPom;
	private String shopWebsiteCode;
	private String shopStoreLanguage;
	private String koboSingleArticle;
	private String prepmtPmtDiscountDate;
	private String baseGrandTotal;
	private String discountAmount;

	private String billToName;
	private String billToAddress;
	private String billToPostCode;
	private String billToCity;
	private String billToCountryRegionCode;
	private String shipToName;
	private String shipToAddress;
	private String shipToPostCode;
	private String shipToCity;
	private String shipToCountryRegionCode;
	private String sellToCustomerName;// include firstname +last name
	private String sellToAddress;
	private String containsBom;

	// not in both env
	private String status;
	private String totalIp;
	private String shippingType;

	private List<NavOrderLinesModel> lines;

	
	public String getCalculatedGrandTotal() {
		return calculatedGrandTotal;
	}

	public void setCalculatedGrandTotal(String calculatedGrandTotal) {
		this.calculatedGrandTotal = calculatedGrandTotal;
	}

	public String getVatProdPostingGroup() {
		return vatProdPostingGroup;
	}

	public void setVatProdPostingGroup(String vatProdPostingGroup) {
		this.vatProdPostingGroup = vatProdPostingGroup;
	}

	public String getExternalDocumentNo() {
		return externalDocumentNo;
	}

	public void setExternalDocumentNo(String externalDocumentNo) {
		this.externalDocumentNo = externalDocumentNo;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getNavGrandTotal() {
		return navGrandTotal;
	}

	public void setNavGrandTotal(String navGrandTotal) {
		this.navGrandTotal = navGrandTotal;
	}

	public String getSellToContactNo() {

		return sellToContactNo;
	}

	public void setSellToContactNo(String sellToContactNo) {
		this.sellToContactNo = sellToContactNo;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getSalesPersonCode() {
		return salesPersonCode;
	}

	public void setSalesPersonCode(String salesPersonCode) {
		this.salesPersonCode = salesPersonCode;
	}



	public String getSellToCustomerNo() {
		return sellToCustomerNo;
	}

	public void setSellToCustomerNo(String sellToCustomerNo) {
		this.sellToCustomerNo = sellToCustomerNo;
	}

	public String getShopShipmentMethod() {
		return shopShipmentMethod;
	}

	public void setShopShipmentMethod(String shopShipmentMethod) {
		this.shopShipmentMethod = shopShipmentMethod;
	}

	public String getYouRefercences() {
		return youRefercences;
	}

	public void setYouRefercences(String youRefercences) {
		this.youRefercences = youRefercences;
	}

	public String getShopPaymentMethod() {
		return shopPaymentMethod;
	}

	public void setShopPaymentMethod(String shopPaymentMethod) {
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalIp() {
		return totalIp;
	}

	public void setTotalIp(String totalIp) {
		this.totalIp = totalIp;
	}

	public String getShopOrderType() {
		return shopOrderType;
	}

	public void setShopOrderType(String shopOrderType) {
		this.shopOrderType = shopOrderType;
	}

	public String getShopCartType() {
		return shopCartType;
	}

	public void setShopCartType(String shopCartType) {
		this.shopCartType = shopCartType;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getIsAlreadyShipped() {
		return isAlreadyShipped;
	}

	public void setIsAlreadyShipped(String isAlreadyShipped) {
		this.isAlreadyShipped = isAlreadyShipped;
	}

	public String getShopIsPom() {
		return shopIsPom;
	}

	public void setShopIsPom(String shopIsPom) {
		this.shopIsPom = shopIsPom;
	}

	public String getShopWebsiteCode() {
		return shopWebsiteCode;
	}

	public void setShopWebsiteCode(String shopWebsiteCode) {
		this.shopWebsiteCode = shopWebsiteCode;
	}

	public String getShopStoreLanguage() {
		return shopStoreLanguage;
	}

	public void setShopStoreLanguage(String shopStoreLanguage) {
		this.shopStoreLanguage = shopStoreLanguage;
	}

	public String getKoboSingleArticle() {
		return koboSingleArticle;
	}

	public void setKoboSingleArticle(String koboSingleArticle) {
		this.koboSingleArticle = koboSingleArticle;
	}

	public String getPrepmtPmtDiscountDate() {
		return prepmtPmtDiscountDate;
	}

	public void setPrepmtPmtDiscountDate(String prepmtPmtDiscountDate) {
		this.prepmtPmtDiscountDate = prepmtPmtDiscountDate;
	}

	public String getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(String shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public String getBaseGrandTotal() {
		return baseGrandTotal;
	}

	public void setBaseGrandTotal(String baseGrandTotal) {
		this.baseGrandTotal = baseGrandTotal;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getBillToName() {
		return billToName;
	}

	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}

	public String getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}

	public String getBillToPostCode() {
		return billToPostCode;
	}

	public void setBillToPostCode(String billToPostCode) {
		this.billToPostCode = billToPostCode;
	}

	public String getBillToCity() {
		return billToCity;
	}

	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}

	public String getBillToCountryRegionCode() {
		return billToCountryRegionCode;
	}

	public void setBillToCountryRegionCode(String billToCountryRegionCode) {
		this.billToCountryRegionCode = billToCountryRegionCode;
	}

	public String getShipToName() {
		return shipToName;
	}

	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getShipToPostCode() {
		return shipToPostCode;
	}

	public void setShipToPostCode(String shipToPostCode) {
		this.shipToPostCode = shipToPostCode;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToCountryRegionCode() {
		return shipToCountryRegionCode;
	}

	public void setShipToCountryRegionCode(String shipToCountryRegionCode) {
		this.shipToCountryRegionCode = shipToCountryRegionCode;
	}

	public String getSellToCustomerName() {
		return sellToCustomerName;
	}

	public void setSellToCustomerName(String sellToCustomerName) {
		this.sellToCustomerName = sellToCustomerName;
	}

	public String getSellToAddress() {
		return sellToAddress;
	}

	public void setSellToAddress(String sellToAddress) {
		this.sellToAddress = sellToAddress;
	}

	public String getMagentoGrandTotal() {
		return magentoGrandTotal;
	}

	public void setMagentoGrandTotal(String magentoGrandTotal) {
		this.magentoGrandTotal = magentoGrandTotal;
	}

	public String getIncrementId() {
		return incrementId;
	}

	public void setIncrementId(String incrementId) {
		this.incrementId = incrementId;
	}

	public List<NavOrderLinesModel> getLines() {
		return lines;
	}

	public void setLines(List<NavOrderLinesModel> lines) {
		this.lines = lines;
	}

	public String getContainsBom() {
		return containsBom;
	}

	public void setContainsBom(String containsBom) {
		this.containsBom = containsBom;
	}

	@Override
	public String toString() {
		return "NavOrderModel [lines=" + lines
				+ "]";
	}

	



}

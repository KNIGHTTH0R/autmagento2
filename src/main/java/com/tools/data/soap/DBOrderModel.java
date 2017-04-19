package com.tools.data.soap;

import java.util.List;

import com.tools.data.navision.SalesOrderInfoModel;

public class DBOrderModel {

	private String incrementId;
	private String createdAt;

	private String customerId;
	private String paymentCompleteAt;
	private String stylistCustomerId;
	private String orderId;
	private String paymentMethodTypet;
	private String shippingType;
	private String orderType;
	private String cartType;
	private String stylePartyId;
	private String grandTotal;
	private String isPreshipped;
	private String isPom;
	private String websiteCode;
	private String storeLanguage;
	private String koboSingleArticle;
	private String updatedNav;
	private String stylistId;
	private String orderCurrencyCode;
	
	//should be calculated maybe;
	private String baseSubtotal;
	private String taxAmount;
	private String taxPrecent;
	
	
	private String customerFirstName;
	private String customerLastName;
	private String customerName;

	// from billing list details
	private String billToFirstName;
	private String billToLastName;
	private String billToPostcode;
	private String billToStreetAddress;
	private String billToCity;
	private String billCountryId;
	private String billToHousNumber;

	// from shipping list details
	private String shipToFirstName;
	private String shipToLastName;
	private String shipToPostcode;
	private String shipToStreetAddress;
	private String shipToCity;
	private String shipCountryId;
	private String shipToHousNumber;


	// in nav should be extreacted from lines
	private String shippingInclTax;
	private String discountAmount;

	private String paidAt;
	private String status;

	private String totalIp;
	private String totalIpRefunded;
	private String termPurchaseType;
	private String orderCustomerName;
	private String scheduledDeliveryDate;
	private List<SalesOrderInfoModel> itemInfo;
	private List<String> itemSku;
	private String shippingAmount;
	private String originalPrice;
	private String qtyOrdered;
	private String calculatedGrandTotal;
	private String calculatedTaxAmount;
	private String baseDiscountAmount;
	private String jewelryCreditsUsed;
	private String marketingCreditsUsed;
	private String fiftyDiscountsUsed;
	private String fiftyDiscountsAmount;
	private String orderCustomerEmail;
	private String vatNumber;
	private String smallBusinessMan;
	private String banckAccountNumber;
	private String languageCode;
	private String magentoGrandTotal;
	private String magentoTaxAmmount;
	private String contactid;
	private String zeroVat;
	

	
	
	public String getZeroVat() {
		return zeroVat;
	}

	public void setZeroVat(String zeroVat) {
		this.zeroVat = zeroVat;
	}

	public String getContactid() {
		return contactid;
	}

	public void setContactid(String contactid) {
		this.contactid = contactid;
	}

	public String getMagentoTaxAmmount() {
		return magentoTaxAmmount;
	}

	public void setMagentoTaxAmmount(String magentoTaxAmmount) {
		this.magentoTaxAmmount = magentoTaxAmmount;
	}

	public String getMagentoGrandTotal() {
		return magentoGrandTotal;
	}

	public void setMagentoGrandTotal(String magentoGrandTotal) {
		this.magentoGrandTotal = magentoGrandTotal;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTaxPrecent() {
		return taxPrecent;
	}

	public void setTaxPrecent(String taxPrecent) {
		this.taxPrecent = taxPrecent;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPaymentCompleteAt() {
		return paymentCompleteAt;
	}

	public void setPaymentCompleteAt(String paymentCompleteAt) {
		this.paymentCompleteAt = paymentCompleteAt;
	}

	public String getStylistCustomerId() {
		return stylistCustomerId;
	}

	public void setStylistCustomerId(String stylistCustomerId) {
		this.stylistCustomerId = stylistCustomerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentMethodTypet() {
		return paymentMethodTypet;
	}

	public void setPaymentMethodTypet(String paymentMethodTypet) {
		this.paymentMethodTypet = paymentMethodTypet;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getStylePartyId() {
		return stylePartyId;
	}

	public void setStylePartyId(String stylePartyId) {
		this.stylePartyId = stylePartyId;
	}

	public String getIsPreshipped() {
		return isPreshipped;
	}

	public void setIsPreshipped(String isPreshipped) {
		this.isPreshipped = isPreshipped;
	}

	public String getIsPom() {
		return isPom;
	}

	public void setIsPom(String isPom) {
		this.isPom = isPom;
	}

	public String getWebsiteCode() {
		return websiteCode;
	}

	public void setWebsiteCode(String websiteCode) {
		this.websiteCode = websiteCode;
	}

	public String getStoreLanguage() {
		return storeLanguage;
	}

	public void setStoreLanguage(String storeLanguage) {
		this.storeLanguage = storeLanguage;
	}

	public String getKoboSingleArticle() {
		return koboSingleArticle;
	}

	public void setKoboSingleArticle(String koboSingleArticle) {
		this.koboSingleArticle = koboSingleArticle;
	}

	public String getUpdatedNav() {
		return updatedNav;
	}

	public void setUpdatedNav(String updatedNav) {
		this.updatedNav = updatedNav;
	}

	public String getOrderCurrencyCode() {
		return orderCurrencyCode;
	}

	public void setOrderCurrencyCode(String orderCurrencyCode) {
		this.orderCurrencyCode = orderCurrencyCode;
	}

	public String getBaseSubtotal() {
		return baseSubtotal;
	}

	public void setBaseSubtotal(String baseSubtotal) {
		this.baseSubtotal = baseSubtotal;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getBillToFirstName() {
		return billToFirstName;
	}

	public void setBillToFirstName(String billToFirstName) {
		this.billToFirstName = billToFirstName;
	}

	public String getBillToLastName() {
		return billToLastName;
	}

	public void setBillToLastName(String billToLastName) {
		this.billToLastName = billToLastName;
	}

	public String getBillToPostcode() {
		return billToPostcode;
	}

	public void setBillToPostcode(String billToPostcode) {
		this.billToPostcode = billToPostcode;
	}

	public String getBillToStreetAddress() {
		return billToStreetAddress;
	}

	public void setBillToStreetAddress(String billToStreetAddress) {
		this.billToStreetAddress = billToStreetAddress;
	}

	public String getBillToCity() {
		return billToCity;
	}

	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}

	public String getBillCountryId() {
		return billCountryId;
	}

	public void setBillCountryId(String billCountryId) {
		this.billCountryId = billCountryId;
	}

	public String getShipToFirstName() {
		return shipToFirstName;
	}

	public void setShipToFirstName(String shipToFirstName) {
		this.shipToFirstName = shipToFirstName;
	}

	public String getShipToLastName() {
		return shipToLastName;
	}

	public void setShipToLastName(String shipToLastName) {
		this.shipToLastName = shipToLastName;
	}

	public String getShipToPostcode() {
		return shipToPostcode;
	}

	public void setShipToPostcode(String shipToPostcode) {
		this.shipToPostcode = shipToPostcode;
	}

	public String getShipToStreetAddress() {
		return shipToStreetAddress;
	}

	public void setShipToStreetAddress(String shipToStreetAddress) {
		this.shipToStreetAddress = shipToStreetAddress;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipCountryId() {
		return shipCountryId;
	}

	public void setShipCountryId(String shipCountryId) {
		this.shipCountryId = shipCountryId;
	}

	public String getShippingInclTax() {
		return shippingInclTax;
	}

	public void setShippingInclTax(String shippingInclTax) {
		this.shippingInclTax = shippingInclTax;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	

	public String getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(String shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public String getScheduledDeliveryDate() {
		return scheduledDeliveryDate;
	}

	public void setScheduledDeliveryDate(String scheduledDeliveryDate) {
		this.scheduledDeliveryDate = scheduledDeliveryDate;
	}

	public List<String> getItemSku() {
		return itemSku;
	}

	public void setItemSku(List<String> itemSku) {
		this.itemSku = itemSku;
	}

	public List<SalesOrderInfoModel> getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(List<SalesOrderInfoModel> itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getOrderCustomerName() {
		return orderCustomerName;
	}

	public void setOrderCustomerName(String orderCustomerName) {
		this.orderCustomerName = orderCustomerName;
	}

	public String getIncrementId() {
		return incrementId;
	}

	public void setIncrementId(String incrementId) {
		this.incrementId = incrementId;
	}

	public String getStylistId() {
		return stylistId;
	}

	public void setStylistId(String stylistId) {
		this.stylistId = stylistId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCartType() {
		return cartType;
	}

	public void setCartType(String cartType) {
		this.cartType = cartType;
	}

	public String getTotalIp() {
		return totalIp;
	}

	public void setTotalIp(String totalIp) {
		this.totalIp = totalIp;
	}

	public String getTotalIpRefunded() {
		return totalIpRefunded;
	}

	public void setTotalIpRefunded(String totalIpRefunded) {
		this.totalIpRefunded = totalIpRefunded;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}

	public String getTermPurchaseType() {
		return termPurchaseType;
	}

	public void setTermPurchaseType(String termPurchaseType) {
		this.termPurchaseType = termPurchaseType;
	}
	
	

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(String qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public String getCalculatedGrandTotal() {
		return calculatedGrandTotal;
	}

	public void setCalculatedGrandTotal(String calculatedGrandTotal) {
		this.calculatedGrandTotal = calculatedGrandTotal;
	}

	public String getBaseDiscountAmount() {
		return baseDiscountAmount;
	}

	public void setBaseDiscountAmount(String baseDiscountAmount) {
		this.baseDiscountAmount = baseDiscountAmount;
	}

	public String getJewelryCreditsUsed() {
		return jewelryCreditsUsed;
	}

	public void setJewelryCreditsUsed(String jewelryCreditsUsed) {
		this.jewelryCreditsUsed = jewelryCreditsUsed;
	}

	public String getMarketingCreditsUsed() {
		return marketingCreditsUsed;
	}

	public void setMarketingCreditsUsed(String marketingCreditsUsed) {
		this.marketingCreditsUsed = marketingCreditsUsed;
	}

	public String getFiftyDiscountsUsed() {
		return fiftyDiscountsUsed;
	}

	public void setFiftyDiscountsUsed(String fiftyDiscountsUsed) {
		this.fiftyDiscountsUsed = fiftyDiscountsUsed;
	}

	public String getFiftyDiscountsAmount() {
		return fiftyDiscountsAmount;
	}

	public void setFiftyDiscountsAmount(String fiftyDiscountsAmount) {
		this.fiftyDiscountsAmount = fiftyDiscountsAmount;
	}

	
	
//	@Override
//	public String toString() {
//		return "DBOrderModel [incrementId=" + incrementId + ", createdAt=" + createdAt + ", customerId=" + customerId
//				+ ", paymentCompleteAt=" + paymentCompleteAt + ", stylistCustomerId=" + stylistCustomerId + ", orderId="
//				+ orderId + ", paymentMethodTypet=" + paymentMethodTypet + ", shippingType=" + shippingType
//				+ ", orderType=" + orderType + ", cartType=" + cartType + ", stylePartyId=" + stylePartyId
//				+ ", grandTotal=" + grandTotal + ", isPreshipped=" + isPreshipped + ", isPom=" + isPom
//				+ ", websiteCode=" + websiteCode + ", storeLanguage=" + storeLanguage + ", koboSingleArticle="
//				+ koboSingleArticle + ", updatedNav=" + updatedNav + ", stylistId=" + stylistId + ", orderCurrencyCode="
//				+ orderCurrencyCode + ", baseSubtotal=" + baseSubtotal + ", taxAmount=" + taxAmount + ", taxPrecent="
//				+ taxPrecent + ", customerFirstName=" + customerFirstName + ", customerLastName=" + customerLastName
//				+ ", customerName=" + customerName + ", billToFirstName=" + billToFirstName + ", billToLastName="
//				+ billToLastName + ", billToPostcode=" + billToPostcode + ", billToStreetAddress=" + billToStreetAddress
//				+ ", billToCity=" + billToCity + ", billCountryId=" + billCountryId + ", shipToFirstName="
//				+ shipToFirstName + ", shipToLastName=" + shipToLastName + ", shipToPostcode=" + shipToPostcode
//				+ ", shipToStreetAddress=" + shipToStreetAddress + ", shipToCity=" + shipToCity + ", shipCountryId="
//				+ shipCountryId + ", shippingInclTax=" + shippingInclTax + ", discountAmount=" + discountAmount
//				+ ", paidAt=" + paidAt + ", status=" + status + ", totalIp=" + totalIp + ", totalIpRefunded="
//				+ totalIpRefunded + ", termPurchaseType=" + termPurchaseType + ", orderCustomerName="
//				+ orderCustomerName + ", scheduledDeliveryDate=" + scheduledDeliveryDate + ", itemInfo=" + itemInfo
//				+ ", itemSku=" + itemSku + ", shippingAmount=" + shippingAmount + "]";
//	}

	public String getBillToHousNumber() {
		return billToHousNumber;
	}

	public void setBillToHousNumber(String billToHousNumber) {
		this.billToHousNumber = billToHousNumber;
	}

	public String getShipToHousNumber() {
		return shipToHousNumber;
	}

	public void setShipToHousNumber(String shipToHousNumber) {
		this.shipToHousNumber = shipToHousNumber;
	}

	public String getOrderCustomerEmail() {
		return orderCustomerEmail;
	}

	public void setOrderCustomerEmail(String orderCustomerEmail) {
		this.orderCustomerEmail = orderCustomerEmail;
	}

	public String getCalculatedTaxAmount() {
		return calculatedTaxAmount;
	}

	public void setCalculatedTaxAmount(String calculatedTaxAmount) {
		this.calculatedTaxAmount = calculatedTaxAmount;
	}

	
	
	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getSmallBusinessMan() {
		return smallBusinessMan;
	}

	public void setSmallBusinessMan(String smallBusinessMan) {
		this.smallBusinessMan = smallBusinessMan;
	}

	public String getBanckAccountNumber() {
		return banckAccountNumber;
	}

	public void setBanckAccountNumber(String banckAccountNumber) {
		this.banckAccountNumber = banckAccountNumber;
	}

	@Override
	public String toString() {
		return "DBOrderModel [incrementId=" + incrementId +  ", itemInfo=" + itemInfo+ "]";
	}
	
}

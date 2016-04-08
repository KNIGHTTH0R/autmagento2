package com.tools.persistance;

import java.net.UnknownHostException;

import com.connectors.mongo.MongoConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.tools.data.BorrowCartCalcDetailsModel;
import com.tools.data.CalcDetailsModel;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.StylistDataModel;
import com.tools.data.StylistRegistrationCartCalcDetailsModel;
import com.tools.data.UrlModel;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.data.soap.CategoryModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.data.soap.ProductDetailedModel;

public class MongoWriter extends MongoConnector {

	public MongoWriter() throws UnknownHostException {
		super();
	}

	public static void saveEnvContext(String env, String context) {
		MongoConnector.cleanCollection(MongoTableKeys.TEST_CONFIG, MongoTableKeys.DEFAULT_CONFIG_MODEL);
		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCollection table = workingDB.getCollection(MongoTableKeys.DEFAULT_CONFIG_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.ENV_KEY, env);
		document.put(MongoTableKeys.CONTEXT_KEY, context);

		table.insert(document);
	}

	public static void saveToDictionary(String key, String value) {
		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCollection table = workingDB.getCollection(MongoTableKeys.DICTIONARY_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(key, value);

		table.insert(document);
	}

	public static void saveStoreUrl(String storeIds, String baseUrl, String soapUrl) {
		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCollection table = workingDB.getCollection(MongoTableKeys.STORE_CONFIG_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STORE_ID_KEY, storeIds);
		document.put(MongoTableKeys.BASE_URL_KEY, baseUrl);
		document.put(MongoTableKeys.SOAP_URL_KEY, soapUrl);

		table.insert(document);
	}

	public static void saveAddressModel(AddressModel dataModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.ADDRESS_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STREET_ADDRESS, dataModel.getStreetAddress());
		document.put(MongoTableKeys.STREET_NUMBER, dataModel.getStreetNumber());
		document.put(MongoTableKeys.HOME_TOWN, dataModel.getHomeTown());
		document.put(MongoTableKeys.POST_CODE, dataModel.getPostCode());
		document.put(MongoTableKeys.COUNTRY_NAME, dataModel.getCountryName());
		document.put(MongoTableKeys.PHONE_NUMBER, dataModel.getPhoneNumber());

		table.insert(document);
	}

	public static void saveStylistDataModel(StylistDataModel dataModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.VALIDATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STYLE_COACH_LEADS, dataModel.getStyleCoachLeads());
		document.put(MongoTableKeys.HOSTESS_LEADS, dataModel.getHostessLeads());
		document.put(MongoTableKeys.CUSTOMER_LEADS, dataModel.getCustomerLeads());
		document.put(MongoTableKeys.STYLE_COACH_LEADS_WEEK, dataModel.getStyleCoachLeadsWeek());
		document.put(MongoTableKeys.HOSTESS_LEADS_WEEK, dataModel.getHostessLeadsWeek());

		table.insert(document);
	}

	public static void saveCustomerFormModel(CustomerFormModel customerModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CUSTOMER_FORM_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STYLIST_FIRSTNAME, customerModel.getFirstName());
		document.put(MongoTableKeys.STYLIST_LASTNAME, customerModel.getLastName());
		document.put(MongoTableKeys.STYLIST_EMAIL, customerModel.getEmailName());
		document.put(MongoTableKeys.STYLIST_PASSWORD, customerModel.getPassword());

		table.insert(document);
	}

	public static void saveStockInfoModel(SyncInfoModel syncInfoModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.STOCK_INFO_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.SKU, syncInfoModel.getSku());
		document.put(MongoTableKeys.STOC_QUANTITY, syncInfoModel.getQuantity());
		document.put(MongoTableKeys.MINUMIM_QUANTITY, syncInfoModel.getMinumimQuantity());
		document.put(MongoTableKeys.IS_DISCONTINUED, syncInfoModel.getIsDiscontinued());
		document.put(MongoTableKeys.TOTAL_QUANTITY, syncInfoModel.getTotalQuantity());
		document.put(MongoTableKeys.MAX_PERCENT_TO_BORROW, syncInfoModel.getMaxPercentToBorrow());
		document.put(MongoTableKeys.EARLIEST_AVAILABILITY, syncInfoModel.getEarliestAvailability());
		document.put(MongoTableKeys.PENDING_QUANTITY, syncInfoModel.getPendingQuantity());

		table.insert(document);
	}

	public static void saveJewerlyHistoryModel(JewelryHistoryModel jewelryHistoryModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.JEWERLY_HISTORY_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.JB_HISTORY_TOTAL, jewelryHistoryModel.getTotalPoints());
		document.put(MongoTableKeys.JB_HISTORY_AMOUNT, jewelryHistoryModel.getAmountValue());
		document.put(MongoTableKeys.JB_HISTORY_DATE, jewelryHistoryModel.getDate());
		document.put(MongoTableKeys.JB_HISTORY_REASON, jewelryHistoryModel.getReason());

		table.insert(document);
	}

	public static void saveSubscriberModel(SubscriberModel subscriberModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.SUBSCRIBER_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.SUBSCRIBER_FIRSTNAME, subscriberModel.getFirstName());

		table.insert(document);
	}

	public static void saveDbStylistModel(DBStylistModel stylistModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.DB_STYLIST_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.DB_STYLIST_EMAIL, stylistModel.getEmail());
		// document.put(MongoTableKeys.DB_STYLIST_ID,
		// stylistModel.getStylistId());
		document.put(MongoTableKeys.DB_STYLIST_FIRSTNAME, stylistModel.getFirstName());
		document.put(MongoTableKeys.DB_STYLIST_LASTNAME, stylistModel.getLastName());
		document.put(MongoTableKeys.DB_TOTAL_SC_RECEIVED, stylistModel.getTotalSCReceived());
		document.put(MongoTableKeys.DB_TOTAL_HOST_RECEIVED, stylistModel.getTotalHostReceived());
		document.put(MongoTableKeys.DB_TOTAL_CUSTOMER_RECEIVED, stylistModel.getTotalCustomerReceived());
		document.put(MongoTableKeys.DB_TOTAL_SC_CURRENT_WEEK, stylistModel.getTotalSCCurrentWeek());
		document.put(MongoTableKeys.DB_TOTAL_HOST_CURRENT_WEEK, stylistModel.getTotalHostCurrentWeek());
		table.insert(document);
	}

	public static void saveDateModel(DateModel dateModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.DATE_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.REGISTRATION_DATE, dateModel.getDate());

		table.insert(document);
	}

	public static void saveCoordinatesModel(CoordinatesModel dateModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.COORDINATES_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.LATTITUDE, dateModel.getLattitude());
		document.put(MongoTableKeys.LONGITUDE, dateModel.getLongitude());

		table.insert(document);
	}

	public static void saveCustomerConfigurationModel(CustomerConfigurationModel customerConfigurationModel,
			String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CUSTOMER_CONFIGURATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.EMAIL_ACTIVE, customerConfigurationModel.getEmailActive());
		document.put(MongoTableKeys.ACCOUNT_ACTIVE, customerConfigurationModel.getAccountActive());

		table.insert(document);
	}

	public static void saveOrderModel(OrderModel orderModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.ORDER_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.ORDER_ID, orderModel.getOrderId());
		document.put(MongoTableKeys.DATE, orderModel.getDate());
		document.put(MongoTableKeys.INVOICE_CONTACT, orderModel.getInvoiceContact());
		document.put(MongoTableKeys.DELIVERY_CONTACT, orderModel.getDeliveryContact());
		document.put(MongoTableKeys.DELIVERY_DATE, orderModel.getDeliveryDate());
		document.put(MongoTableKeys.TOTAL_PRICE, orderModel.getTotalPrice());
		document.put(MongoTableKeys.STATUS, orderModel.getStatus());

		table.insert(document);
	}

	public static void savePartyBonusCalculationModel(PartyBonusCalculationModel partyBonusCalculationModel,
			String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.PARTY_BONUS_CALCULATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.SUBTOTAL, partyBonusCalculationModel.getTotal());
		document.put(MongoTableKeys.PERCENTAGE, partyBonusCalculationModel.getPercent());
		document.put(MongoTableKeys.IP_POINTS, partyBonusCalculationModel.getIp());

		table.insert(document);
	}

	public static void saveKoboCode(String koboCode, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.KOBO_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.KOBO_CODE, koboCode);

		table.insert(document);
	}

	public static void saveStringValue(String value, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.STRING_VALUE);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STOC_QUANTITY, value);

		table.insert(document);
	}

	public static void saveIncrementId(String incrementId, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.INCREMENT_ID_TABLE);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.INCREMENT_ID, incrementId);

		table.insert(document);
	}

	public static void saveProductBasicModel(ProductBasicModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.PRODUCT_BASIC_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.NAME, product.getName());
		document.put(MongoTableKeys.TYPE, product.getType());
		document.put(MongoTableKeys.PRICE, product.getPrice());
		document.put(MongoTableKeys.QUANTITY, product.getQuantity());

		table.insert(document);
	}

	public static void saveProductDetailedModel(ProductDetailedModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.PRODUCT_DETAILED_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.PRODUCT_NAME, product.getName());
		document.put(MongoTableKeys.PRODUCT_CODE, product.getSku());

		table.insert(document);
	}

	public static void saveCategoryModel(CategoryModel category, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CATEGORY_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.CATEGORY_NAME, category.getName());
		document.put(MongoTableKeys.CATEGORY_URL, category.getUrlKey());
		document.put(MongoTableKeys.CATEGORY_ID, category.getId());

		table.insert(document);
	}

	public static void saveBasicProductModel(BasicProductModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.BASIC_PRODUCT_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.PRODUCT_NAME, product.getName());
		document.put(MongoTableKeys.PRODUCT_CODE, product.getProdCode());
		document.put(MongoTableKeys.PRODUCT_PRICE, product.getUnitPrice());
		document.put(MongoTableKeys.PRODUCT_QUANTITY, product.getQuantity());
		document.put(MongoTableKeys.PRODUCT_DISCOUNT_CLASS, product.getDiscountClass());
		document.put(MongoTableKeys.PRODUCT_ASKING_PRICE, product.getProductsPrice());
		document.put(MongoTableKeys.PRODUCT_FINAL_PRICE, product.getFinalPrice());
		document.put(MongoTableKeys.PRODUCT_IP_POINTS, product.getPriceIP());

		table.insert(document);
	}

	public static void saveRegularBasicProductModel(RegularBasicProductModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.REGULAR_BASIC_PRODUCT_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.PRODUCT_NAME, product.getName());
		document.put(MongoTableKeys.PRODUCT_CODE, product.getProdCode());
		document.put(MongoTableKeys.PRODUCT_PRICE, product.getUnitPrice());
		document.put(MongoTableKeys.PRODUCT_QUANTITY, product.getQuantity());
		document.put(MongoTableKeys.PRODUCT_FINAL_PRICE, product.getFinalPrice());

		table.insert(document);
	}

	public static void saveHostBasicProductModel(HostBasicProductModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.HOST_BASIC_PRODUCT_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.PRODUCT_NAME, product.getName());
		document.put(MongoTableKeys.PRODUCT_CODE, product.getProdCode());
		document.put(MongoTableKeys.PRODUCT_PRICE, product.getUnitPrice());
		document.put(MongoTableKeys.PRODUCT_QUANTITY, product.getQuantity());
		document.put(MongoTableKeys.PRODUCT_FINAL_PRICE, product.getFinalPrice());
		document.put(MongoTableKeys.PRODUCT_IP_POINTS, product.getIpPoints());

		table.insert(document);
	}

	public static void saveBorrowProductModel(BorrowProductModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.BORROW_BASIC_PRODUCT_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.PRODUCT_NAME, product.getName());
		document.put(MongoTableKeys.PRODUCT_CODE, product.getProdCode());
		document.put(MongoTableKeys.PRODUCT_PRICE, product.getUnitPrice());
		document.put(MongoTableKeys.PRODUCT_FINAL_PRICE, product.getFinalPrice());
		document.put(MongoTableKeys.PRODUCT_IP_POINTS, product.getIpPoints());

		table.insert(document);
	}

	public static void saveTotalsModel(CartTotalsModel cartTotalsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CART_TOTALS_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.SUBTOTAL, cartTotalsModel.getSubtotal());
		document.put(MongoTableKeys.JEWERLY_BONUS, cartTotalsModel.getJewelryBonus());
		document.put(MongoTableKeys.TAX, cartTotalsModel.getTax());
		document.put(MongoTableKeys.SHIPPING, cartTotalsModel.getShipping());
		document.put(MongoTableKeys.TOTAL_AMOUNT, cartTotalsModel.getTotalAmount());
		document.put(MongoTableKeys.IP_POINTS, cartTotalsModel.getIpPoints());
		document.put(MongoTableKeys.DISCOUNT_LIST, cartTotalsModel.getDiscountsMap());

		table.insert(document);
	}

	public static void saveCalcDetailsModel(CalcDetailsModel calcDetailsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CALC_DETAILS_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.JEWERLY_BONUS, calcDetailsModel.getJewelryBonus());
		document.put(MongoTableKeys.MARKETING_BONUS, calcDetailsModel.getMarketingBonus());
		document.put(MongoTableKeys.TOTAL_AMOUNT, calcDetailsModel.getTotalAmount());
		document.put(MongoTableKeys.SUBTOTAL, calcDetailsModel.getSubTotal());
		document.put(MongoTableKeys.TAX, calcDetailsModel.getTax());
		document.put(MongoTableKeys.IP_POINTS, calcDetailsModel.getIpPoints());
		// last two are maps
		document.put(MongoTableKeys.SEGMENTS, calcDetailsModel.getSegments());
		document.put(MongoTableKeys.CALCULATIONS, calcDetailsModel.getCalculations());

		table.insert(document);
	}

	public static void saveBorrowCartCalcDetailsModel(BorrowCartCalcDetailsModel calcDetailsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.BORROW_CART_CALC_DETAILS_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.TOTAL_AMOUNT, calcDetailsModel.getTotalAmount());
		document.put(MongoTableKeys.SUBTOTAL, calcDetailsModel.getSubTotal());
		document.put(MongoTableKeys.TAX, calcDetailsModel.getTax());
		document.put(MongoTableKeys.IP_POINTS, calcDetailsModel.getIpPoints());
		// last two are maps

		table.insert(document);
	}

	public static void saveStarterSetCartCalcDetailsModel(StylistRegistrationCartCalcDetailsModel calcDetailsModel,
			String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.STARTER_SET_CART_CALC_DETAILS_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.TOTAL_AMOUNT, calcDetailsModel.getTotalAmount());
		document.put(MongoTableKeys.SUBTOTAL, calcDetailsModel.getSubTotal());
		document.put(MongoTableKeys.TAX, calcDetailsModel.getTax());
		document.put(MongoTableKeys.DISCOUNT, calcDetailsModel.getVoucherDiscount());
		// last two are maps

		table.insert(document);
	}

	public static void saveRegularCartCalcDetailsModel(RegularCartCalcDetailsModel calcDetailsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.REGULAR_CART_CALC_DETAILS_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.TOTAL_AMOUNT, calcDetailsModel.getTotalAmount());
		document.put(MongoTableKeys.SUBTOTAL, calcDetailsModel.getSubTotal());
		document.put(MongoTableKeys.TAX, calcDetailsModel.getTax());
		document.put(MongoTableKeys.SEGMENTS, calcDetailsModel.getSegments());

		table.insert(document);
	}

	public static void saveHostCartCalcDetailsModel(HostCartCalcDetailsModel calcDetailsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.HOST_CART_CALC_DETAILS_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.TOTAL_AMOUNT, calcDetailsModel.getTotalAmount());
		document.put(MongoTableKeys.SUBTOTAL, calcDetailsModel.getSubTotal());
		document.put(MongoTableKeys.TAX, calcDetailsModel.getTax());
		document.put(MongoTableKeys.IP_POINTS, calcDetailsModel.getIpPoints());
		document.put(MongoTableKeys.SEGMENTS, calcDetailsModel.getSegments());

		table.insert(document);
	}

	public static void saveOrderInfoModel(OrderInfoModel orderInfoModel, String testName) {

		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.ORDER_INFO_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.ORDER_STATUS, orderInfoModel.getOrderStatus());
		document.put(MongoTableKeys.ORDER_DATE, orderInfoModel.getOrderDate());
		document.put(MongoTableKeys.IP_POINTS, orderInfoModel.getOrderIP());
		document.put(MongoTableKeys.AQUIRED_BY, orderInfoModel.getAquiredBy());

		table.insert(document);
	}

	public static void saveUrlModel(UrlModel urlModel, String testName) {

		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.URL_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.NAME, urlModel.getName());
		document.put(MongoTableKeys.URL_PATH, urlModel.getUrl());

		table.insert(document);
	}

	public static void saveClosedPartyPerformanceModel(ClosedPartyPerformanceModel model, String testName) {

		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CLOSED_PARTY_PERFORMANCE_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.NO_OF_ORDERS, model.getNoOfOrders());
		document.put(MongoTableKeys.RETAIL, model.getRetail());
		document.put(MongoTableKeys.IP, model.getIp());
		document.put(MongoTableKeys.JB, model.getJewelryBonus());
		document.put(MongoTableKeys.FORTY_DISCOUNTS, model.getFourthyDiscounts());

		table.insert(document);
	}

	public static void saveOrderTotalsModel(OrderTotalsModel orderTotalsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.ORDER_TOTALS_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.SUBTOTAL, orderTotalsModel.getSubtotal());
		document.put(MongoTableKeys.SHIPPING, orderTotalsModel.getShipping());
		document.put(MongoTableKeys.TAX, orderTotalsModel.getTax());
		document.put(MongoTableKeys.TOTAL_AMOUNT, orderTotalsModel.getTotalAmount());
		document.put(MongoTableKeys.TOTAL_PAID, orderTotalsModel.getTotalPaid());
		document.put(MongoTableKeys.TOTAL_REFUNDED, orderTotalsModel.getTotalRefunded());
		document.put(MongoTableKeys.TOTAL_PAYABLE, orderTotalsModel.getTotalPayable());
		document.put(MongoTableKeys.IP_POINTS, orderTotalsModel.getTotalIP());
		document.put(MongoTableKeys.FORTY_DISCOUNTS, orderTotalsModel.getTotalFortyDiscounts());
		document.put(MongoTableKeys.JEWERLY_BONUS, orderTotalsModel.getTotalBonusJeverly());
		document.put(MongoTableKeys.MARKETING_BONUS, orderTotalsModel.getTotalMarketingBonus());

		document.put(MongoTableKeys.SEGMENTS, orderTotalsModel.getDiscountsMap());

		table.insert(document);

	}

	public static void saveShippingModel(ShippingModel shippingModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.SHIPPING_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.DISCOUNT, shippingModel.getDiscountPrice());
		document.put(MongoTableKeys.SHIPPING, shippingModel.getShippingPrice());
		document.put(MongoTableKeys.SUBTOTAL, shippingModel.getSubTotal());
		document.put(MongoTableKeys.TOTAL_AMOUNT, shippingModel.getTotalAmount());
		document.put(MongoTableKeys.TAX, shippingModel.getTax());

		table.insert(document);
	}

	public static void saveRewardPointsModel(RewardPointsOfStylistModel rewardPointsOfStylistModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.REWARD_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.JEWERLY_BONUS, rewardPointsOfStylistModel.getJewelryBonus());
		document.put(MongoTableKeys.MARKETING_BONUS, rewardPointsOfStylistModel.getMarketingMaterialBonus());

		table.insert(document);
	}

	public static void saveLoungeIpPerformanceModel(LoungeIpPerformanceModel loungeIpPerformanceModel,
			String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.IP_PERFORMANCE_MODEL);

		BasicDBObject document = new BasicDBObject();

		document.put(MongoTableKeys.CAREER_LEVEL, loungeIpPerformanceModel.getCareerLevel());
		document.put(MongoTableKeys.PAY_LEVEL, loungeIpPerformanceModel.getPayLevel());
		document.put(MongoTableKeys.INDIVIDUAL_POINTS, loungeIpPerformanceModel.getIndividualPoints());
		document.put(MongoTableKeys.UNSAFE_INDIVIDUAL_POINTS, loungeIpPerformanceModel.getUnsafeIndividualPoints());
		document.put(MongoTableKeys.TEAM_POINTS, loungeIpPerformanceModel.getTeamPoints());
		document.put(MongoTableKeys.STYLECOACH_FIRST_LEVEL, loungeIpPerformanceModel.getStyleCoachFirstLevel());
		document.put(MongoTableKeys.GOLD_STYLECOACHES, loungeIpPerformanceModel.getGoldStyleCoaches());

		table.insert(document);
	}

}

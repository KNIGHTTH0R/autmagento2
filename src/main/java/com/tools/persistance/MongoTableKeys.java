package com.tools.persistance;

public class MongoTableKeys {

	// DB names suffix for

	// Keys used for total model FE
	public static final String DISCOUNT_KEY = "DISCOUNT";
	public static final String DISCOUNT_25_KEY = "25DISCOUNT";
	public static final String DISCOUNT_50_KEY = "50DISCOUNT";
	public static final String DISCOUNT_40_KEY = "40DISCOUNT";
	public static final String DISCOUNT_JEWELRY_BONUS = "JEWELRY_DISCOUNT";

	// Address model properties and keys
	public static final String ADDRESS_MODEL = "AddressModel";
	public static final String STREET_ADDRESS = "streetAddress";
	public static final String STREET_NUMBER = "streetNumber";
	public static final String HOME_TOWN = "homeTown";
	public static final String POST_CODE = "postCode";
	public static final String COUNTRY_NAME = "countryName";
	public static final String PHONE_NUMBER = "phoneNumber";

	// Validation model properties
	public static final String VALIDATION_MODEL = "ValidationModel";
	public static final String STYLE_COACH_LEADS = "styleCoachLeads";
	public static final String HOSTESS_LEADS = "hostessLeads";
	public static final String CUSTOMER_LEADS = "customerLeads";
	public static final String STYLE_COACH_LEADS_WEEK = "styleCoachLeadsWeek";
	public static final String HOSTESS_LEADS_WEEK = "hostessLeadsWeek";

	// Customer model properties
	// public static final String CUSTOMER_MODEL = "CustomerModel";
	public static final String CLIENT_NAME = "clientName";

	// Stylist model properties
	public static final String STYLIST_MODEL = "StylistModel";
	public static final String STYLIST_FIRSTNAME = "clientFirstname";
	public static final String STYLIST_LASTNAME = "clientLastname";
	public static final String STYLIST_EMAIL = "clientEmail";
	public static final String STYLIST_PASSWORD = "clientPassword";

	// JewerlyHistoryModel
	public static final String JEWERLY_HISTORY_MODEL = "JewerlyHistoryModel";
	public static final String JB_HISTORY_TOTAL = "jBHistoryTotal";
	public static final String JB_HISTORY_AMOUNT = "jBHistoryAmount";
	public static final String JB_HISTORY_DATE = "jBHistoryDate";
	public static final String JB_HISTORY_REASON = "jBHistoryReason";

	// Stylist model properties
	public static final String DATE_MODEL = "DateModel";
	public static final String REGISTRATION_DATE = "date";

	// Customer configuration model
	public static final String CUSTOMER_CONFIGURATION_MODEL = "CustomerConfigurationModel";
	public static final String EMAIL_ACTIVE = "emailActive";
	public static final String ACCOUNT_ACTIVE = "accountActive";

	// Kobo code
	public static final String KOBO_MODEL = "KoboModel";
	public static final String KOBO_CODE = "KoboCode";

	// increment id

	public static final String INCREMENT_ID_TABLE = "IncrementIdTable";
	public static final String INCREMENT_ID = "IncrementId";

	// party bonus calculation model
	public static final String PARTY_BONUS_CALCULATION_MODEL = "PartyBonusCalculationModel";
	public static final String PERCENTAGE = "Percentage";

	// OrderModel
	public static final String ORDER_MODEL = "OrderModel";
	public static final String ORDER_ID = "orderId";
	public static final String DATE = "date";
	public static final String INVOICE_CONTACT = "invoiceTo";
	public static final String DELIVERY_CONTACT = "deliveryTo";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String STATUS = "status";

	// CartTotalsModel
	public static final String CART_TOTALS_MODEL = "CartTotalsModel";
	public static final String SUBTOTAL = "subtotal";
	public static final String JEWERLY_BONUS = "jewelryBonus";
	public static final String DISCOUNT_3_PLUS_1 = "discount3To1";
	public static final String TAX = "tax";
	public static final String SHIPPING = "shipping";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String IP_POINTS = "ipPoints";
	public static final String DISCOUNT_LIST = "discounts";

	public static final String PRODUCT_BASIC_MODEL = "ProductBasicModel";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String PRICE = "price";
	public static final String QUANTITY = "quantity";

	public static final String REGULAR_BASIC_PRODUCT_MODEL = "RegularBasicProductModel";
	public static final String BASIC_PRODUCT_MODEL = "BasicProductModel";
	public static final String PRODUCT_NAME = "name";
	public static final String PRODUCT_CODE = "code";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_QUANTITY = "quantity";
	public static final String PRODUCT_DISCOUNT_CLASS = "discountClass";
	public static final String PRODUCT_ASKING_PRICE = "askingPrice";
	public static final String PRODUCT_FINAL_PRICE = "finalPrice";
	public static final String PRODUCT_IP_POINTS = "ipPoints";

	// CalcDetailsModel
	public static final String CALC_DETAILS_MODEL = "CalcDetailsModel";
	public static final String REGULAR_CART_CALC_DETAILS_MODEL = "RegularCartCalcDetailsModel";
	public static final String BORROW_CART_CALC_DETAILS_MODEL = "BorrowCartCalcDetailsModel";
	public static final String MARKETING_BONUS = "MarketingBonus";
	public static final String SEGMENTS = "Segments";
	public static final String CALCULATIONS = "Calculations";

	// OrderInfoModel
	public static final String ORDER_INFO_MODEL = "OrderInfoModel";
	public static final String ORDER_STATUS = "OrderStatus";
	public static final String ORDER_DATE = "OrderDate";
	public static final String AQUIRED_BY = "AquiredBy";

	// OrderTotalsModel
	public static final String ORDER_TOTALS_MODEL = "OrderTotalsModel";
	public static final String TOTAL_PAID = "TotalPaid";
	public static final String TOTAL_REFUNDED = "TotalRefunded";
	public static final String TOTAL_PAYABLE = "TotalPayable";
	public static final String FORTY_DISCOUNTS = "FortyDiscounts";

	// ShippingModel
	public static final String SHIPPING_MODEL = "ShippingModel";
	public static final String DISCOUNT = "discount";

	// URLModel
	public static final String URL_MODEL = "UrlModel";
	public static final String URL_PATH = "url:";

	// ClosedPartyPerformanceModel

	public static final String CLOSED_PARTY_PERFORMANCE_MODEL = "closedPartyPerformanceModel";
	public static final String NO_OF_ORDERS = "closedPartyPerformanceModel";
	public static final String RETAIL = "closedPartyPerformanceModel";
	public static final String IP = "closedPartyPerformanceModel";
	public static final String JB = "closedPartyPerformanceModel";
	public static final String FOURTHY_DISCOUNT = "closedPartyPerformanceModel";

	// subscriber model
	public static final String SUBSCRIBER_MODEL = "SubscriberModel";
	public static final String SUBSCRIBER_FIRSTNAME = "SubscriberFirstname";

	public static final String CUSTOMER_FORM_MODEL = "CustomerFormModel";
	public static final String HOST_CART_CALC_DETAILS_MODEL = "HostCartCalcDetailsModel";
	public static final String HOST_BASIC_PRODUCT_MODEL = "HostBasicProductModel";
	public static final String BORROW_BASIC_PRODUCT_MODEL = "BorrowBasicProductModel";

	// DBStylistModel
	public static final String DB_STYLIST_MODEL = "dbStylistModel";
	public static final String DB_STYLIST_EMAIL = "stylistEmail";
	public static final String DB_TOTAL_SC_RECEIVED = "totalSCReceived";
	public static final String DB_TOTAL_HOST_RECEIVED = "totalHostReceived";
	public static final String DB_TOTAL_CUSTOMER_RECEIVED = "totalCustomerReceived";
	public static final String DB_TOTAL_SC_CURRENT_WEEK = "totalSCCurrentWeek";
	public static final String DB_TOTAL_HOST_CURRENT_WEEK = "totalHostCurrentWeek";

	// Default settings
	public static final String TEST_CONFIG = "DefaultConfig";
	public static final String DEFAULT_CONFIG_MODEL = "DefaultConfigModel";
	public static final String ENV_KEY = "env";
	public static final String CONTEXT_KEY = "context";
	public static final String BASE_URL_KEY = "baseUrl";
	public static final String STORE_ID_KEY = "storeIds";
	public static final String DICTIONARY_MODEL = "DictionaryModel";
	public static final String STORE_CONFIG_MODEL = "StoreConfigModel";
	public static final String SOAP_URL_KEY = "soapUrl";

	public static final String COORDINATES_MODEL = "CoordinatesModel";
	public static final String LATTITUDE = "Lattitude";
	public static final String LONGITUDE = "Longitude";

}

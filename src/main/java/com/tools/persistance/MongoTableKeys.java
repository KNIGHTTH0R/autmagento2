package com.tools.persistance;

public class MongoTableKeys {
	
	//DB names suffix for 

//	public static final String GRAB = "-GRB";
//	public static final String CALC = "-CLC";
	
	//Keys used for total model FE
	public static final String DISCOUNT_KEY = "DISCOUNT";
	public static final String DISCOUNT_25_KEY = "25DISCOUNT";
	public static final String DISCOUNT_50_KEY = "50DISCOUNT";
	  
    //Address model properties and keys
	public static final String ADDRESS_MODEL = "AddressModel";
	public static final String STREET_ADDRESS = "streetAddress";
	public static final String STREET_NUMBER = "streetNumber";
	public static final String HOME_TOWN = "homeTown";
	public static final String POST_CODE = "postCode";
	public static final String COUNTRY_NAME = "countryName";
	public static final String PHONE_NUMBER = "phoneNumber";
	
	//Validation model properties
	public static final String VALIDATION_MODEL = "ValidationModel";
	public static final String STYLE_COACH_LEADS = "styleCoachLeads";
	public static final String HOSTESS_LEADS = "hostessLeads";
	public static final String CUSTOMER_LEADS = "customerLeads";
	public static final String STYLE_COACH_LEADS_WEEK = "styleCoachLeadsWeek";
	public static final String HOSTESS_LEADS_WEEK = "hostessLeadsWeek";
	
	//Customer model properties
	public static final String CUSTOMER_MODEL = "CustomerModel";
	public static final String CLIENT_NAME = "clientName";
	
	//Stylist model properties
	public static final String STYLIST_MODEL = "StylistModel";
	public static final String STYLIST_FIRSTNAME = "clientFirstname";
	public static final String STYLIST_LASTNAME = "clientLastname";
	public static final String STYLIST_EMAIL = "clientEmail";
	public static final String STYLIST_PASSWORD = "clientPassword";
	
	//Customer configuration model
	public static final String CUSTOMER_CONFIGURATION_MODEL = "CustomerConfigurationModel";
	public static final String EMAIL_ACTIVE = "emailActive";
	public static final String ACCOUNT_ACTIVE = "accountActive";
	
	//OrderModel
	public static final String ORDER_MODEL = "OrderModel";
	public static final String ORDER_ID = "orderId";
	public static final String DATE = "date";
	public static final String INVOICE_CONTACT = "invoiceTo";
	public static final String DELIVERY_CONTACT = "deliveryTo";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String STATUS = "status";
	
	//CartTotalsModel
	public static final String CART_TOTALS_MODEL = "CartTotalsModel";
	public static final String SUBTOTAL =  "subtotal";
	public static final String JEWERLY_BONUS = "jewelryBonus";
//	public static final String DISCOUNT = "discount";
//	public static final String DISCOUNT_25 = "discount25";
//	public static final String DISCOUNT_50 = "discount50";
	public static final String DISCOUNT_3_PLUS_1 = "discount3To1";
	public static final String TAX = "tax";
	public static final String SHIPPING = "shipping";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String IP_POINTS = "ipPoints";
	public static final String DISCOUNT_LIST = "discounts";
	
	//CartproductModel
	public static final String PRODUCT_BASIC_MODEL = "ProductBasicModel";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String PRICE = "price";
	public static final String QUANTITY= "quantity";
	//CartproductModel
	public static final String BASIC_PRODUCT_MODEL = "BasicProductModel";
	public static final String PRODUCT_NAME = "name";
	public static final String PRODUCT_CODE = "code";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_QUANTITY= "quantity";
	public static final String PRODUCT_DISCOUNT_CLASS= "discountClass";
	public static final String PRODUCT_ASKING_PRICE= "askingPrice";
	public static final String PRODUCT_FINAL_PRICE= "finalPrice";
	public static final String PRODUCT_IP_POINTS= "ipPoints";
	
	//CalcDetailsModel
	public static final String CALC_DETAILS_MODEL = "CalcDetailsModel";
	public static final String MARKETING_BONUS = "MarketingBonus";
	public static final String SEGMENTS = "Segments";
	public static final String CALCULATIONS = "Calculations";
	
	
	//OrderInfoModel
	public static final String ORDER_INFO_MODEL = "OrderInfoModel";
	public static final String ORDER_STATUS = "OrderStatus";
	public static final String ORDER_DATE = "OrderDate";
	public static final String AQUIRED_BY = "AquiredBy";
	
	//OrderTotalsModel
	public static final String ORDER_TOTALS_MODEL = "OrderTotalsModel";
	public static final String TOTAL_PAID = "TotalPaid";
	public static final String TOTAL_REFUNDED = "TotalRefunded";
	public static final String TOTAL_PAYABLE = "TotalPayable";
	public static final String FORTY_DISCOUNTS = "FortyDiscounts";
	
	//ShippingModel
	public static final String SHIPPING_MODEL = "ShippingModel";
	public static final String DISCOUNT = "discount";
	
	//URLModel
	public static final String URL_MODEL = "UrlModel";
	public static final String URL_PATH = "url:";


}

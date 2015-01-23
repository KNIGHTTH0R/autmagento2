package com.tools.persistance;

public class MongoTableKeys {
	  
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
	public static final String DISCOUNT = "discount";
	public static final String DISCOUNT_25 = "discount25";
	public static final String DISCOUNT_50 = "discount50";
	public static final String DISCOUNT_3_PLUS_1 = "discount3To1";
	public static final String TAX = "tax";
	public static final String SHIPPING = "shipping";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String IP_POINTS = "ipPoints";
	
	//CartproductModel
	public static final String PRODUCT_BASIC_MODEL = "ProductBasicModel";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String PRICE = "price";
	public static final String QUANTITY= "quantity";

}

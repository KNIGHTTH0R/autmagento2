package com.tools.persistance;

public class MongoConstants {

    public static final String MONGO_URL = "localhost";
    public static final int MONGO_PORT = 27017;
    public static final String DEV_MONGO_DB = "AuPjDb";
    public static final String COMMA_SEPARATOR = ",";
    
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
	
}

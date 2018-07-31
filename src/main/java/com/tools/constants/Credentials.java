package com.tools.constants;

import com.tools.persistance.MongoReader;

public class Credentials {

	// SOAP user authentication
	public static final String LOGIN_USER_SOAP = "stagingaut";
	public static final String LOGIN_PASS_SOAP = "stagingaut1";
	
	public static final String BE_USER=MongoReader.geteEnvironmentConstantsItem("ADMIN_USER");
	public static final String BE_PASS=MongoReader.geteEnvironmentConstantsItem("ADMIN_PASSWORD");

	public static final String BE_STYLIST = "simona.popa@evozon.com";

	// mailchimp credentials
	public static final String MAILCHIMP_USERNAME = "phptest2";
	public static final String MAILCHIMP_PASSWORD = "mailchimp123";

	public static final String API_KEY = "shop";
	public static final String API_SECRET = "aut.atmyhome.secret";
	
	
/*	public static final String API_KEY = "shop";
	public static final String API_SECRET = "W3#x@qRkY2";*/
}

package com.tools.env.variables;

import java.io.File;

import com.tools.persistance.MongoReader;

/**
 * Need to make soap and environment always point to the same environment.
 * Issues might arise.
 * 
 * @author voicu.vac
 *
 */
public class UrlConstants {
	
//	public static final String RESOURCES_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "staging-aut" + File.separator;
	public static String RESOURCES_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + MongoReader.getEnvironment() + File.separator + MongoReader.getContext() + File.separator;
	public static String CONTEXT_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "context" + File.separator;

	//Staging AUT uRLs 

	public static final String URL_WEB_MAIL = "http://mailinator.com/";
	
	//Frontend Composed URLS
	public static final String BASE_URL_BE = "/index.php/admin/";
	public static final String BASE_URL_AUT = "/customer/account/login/";
	public static final String WISHLIST_URL = "/wishlist/";
	public static final String KOBO_CAMPAIGN = "/kobo-campaign/";

//	public static final String PROFILE_HISTORY_URL = "simona/sales/order/history/";
	public static final String PROFILE_HISTORY_URL = "/sales/order/history/";
	public static final String STYLISTS_CUSTOMER_ORDER_REPORT = "/stylereports/order/myorders/?type=customerorders";
	public static final String CART_PAGE_URL = "simona/checkout/cart/";
	
	
//	public static final String URL_CART_SUCCESS = "simona/simplecheckout/process/success/";
//	public static final String PARTY_DETAILS_URL = "qateam/stylist/party/details/id/";
//	public static final String BASE_URL_FE = "customer/account/create/";
	

	//SOAP API
	public static final String API_URI = "index.php/api/v2_soap/index/";

	//Static Pages
	public static final String REGISTER_LANDING_PAGE = "/register-landing-page";
	public static final String LANDING_PAGE = "/contact-landing-page";


}

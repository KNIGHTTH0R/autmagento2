package com.tools.env.constants;

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
	public static String RESOURCES_PATH_COMMON = "src" + File.separator + "main" + File.separator + "resources" + File.separator + MongoReader.getEnvironment() + File.separator;
	public static String CONTEXT_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "context" + File.separator;
	public static String RESOURCES = "resources" + File.separator;

	//Staging AUT uRLs 

	public static final String URL_WEB_MAIL = "http://mailinator.com/";
	public static final String MAILINATOR_IMBOX_SUFFIX = "inbox2.jsp?public_to=";
	
	//mailchimp
	
	public static final String URL_CHIMPMAIL = "https://login.mailchimp.com/";
	
	//unbounce
	public static final String URL_UNBOUNCE = "http://unbouncepages.com/mihai-aut/";
	
	//Frontend Composed URLS
	public static final String BASE_URL_BE = "/index.php/admin/";
	public static final String BASE_URL_AUT = "/customer/account/login/";
	public static final String WISHLIST_URL = "/wishlist/";
	public static final String KOBO_CAMPAIGN = "/kobo-campaign/";

	public static final String PROFILE_HISTORY_URL = "/sales/order/history/";
	public static final String STYLISTS_CUSTOMER_ORDER_REPORT = "/stylereports/order/myorders/?type=customerorders";
	public static final String STYLISTS_REPORTS = "/stylereports/";
	public static final String CART_PAGE_URL = "simona/checkout/cart/";
	
	//SOAP API
	public static final String API_URI = "index.php/api/v2_soap/index/";

	//Static Pages
	public static final String REGISTER_LANDING_PAGE = "/register-landing-page";
	public static final String LANDING_PAGE = "/contact-landing-page";
	
	//commision
	public static final String COMMISION_WEB_BASE = "http://commission-staging-aut.pippajean.com/api/";
	public static final String COMMISION_STYLIST_SUFFIX = "stylist.json/";
	public static final String COMMISION_PARTY_SUFFIX = "party.json/";
	public static final String TEAM_PERFORMANCE_SUFFIX = "performance-team.json/";
	public static final String COMMISSION_REPORTS_URL = "https://commission-staging-aut.pippajean.com/report";
	
	//navision
	public static final String IMPORT_INTERFACE_URL = "http://Navision:_N1v2s34N_@148.251.178.207/PjOrderImport/Home/ImportierenAut";
	public static final String INTERFACE_USERNAME = "Navision";
	public static final String INTERFACE_PASSWORD = "_N1v2s34N_";

}

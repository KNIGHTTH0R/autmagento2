package com.tools;

import java.io.File;

/**
 * Need to make soap and environment always point to the same environment.
 * Issues might arise.
 * 
 * @author voicu.vac
 *
 */
public class Constants {

//	public static final String BASE_URL_BE = "http://staging.pippajean.com/index.php/admin/";
//	public static final String BASE_URL_FE = "http://staging.pippajean.com/customer/account/create/";
//	public static final String BASE_URL = "https://staging.pippajean.com/customer/account/login/";
//	public static final String BASE_URL_AUT = "https://staging.pippajean.com/customer/account/login/";
//	public static final String URL_CART_SUCCESS = "https://staging.pippajean.com/simona/simplecheckout/process/success/";
//	public static final String PROFILE_HISTORY_URL = "https://staging.pippajean.com/simona/sales/order/history/";
//	public static final String CART_PAGE_URL = "http://staging.pippajean.com/simona/checkout/cart/";
//	public static final String BE_URL_RULE_BUY3GET1 = "https://staging.pippajean.com/index.php/admin/promo_quote/edit/id/5466/key/";
	
	
	public static final String BASE_URL_BE = "http://pippajean:sn0wMob1l!@staging-aut.pippajean.com/index.php/admin/";
//	public static final String BASE_URL_FE = "http://staging-aut.pippajean.com/welcome-shop-landing";
	public static final String BASE_URL_FE = "http://pippajean:sn0wMob1l!@staging-aut.pippajean.com/customer/account/create/";
	public static final String BASE_FE_URL = "http://staging-aut.pippajean.com/";
	public static final String BASE_URL = "https://staging-aut.pippajean.com/customer/account/login/";
	public static final String BASE_URL_AUT = "https://staging-aut.pippajean.com/customer/account/login/";
	public static final String URL_CART_SUCCESS = "https://staging-aut.pippajean.com/simona/simplecheckout/process/success/";
	public static final String PROFILE_HISTORY_URL = "https://staging-aut.pippajean.com/simona/sales/order/history/";
	public static final String CART_PAGE_URL = "http://staging-aut.pippajean.com/simona/checkout/cart/";
	public static final String BE_URL_RULE_BUY3GET1 = "https://staging-aut.pippajean.com/index.php/admin/promo_quote/edit/id/5466/key/";

	
	// SOAP user authentication
	public static final String LOGIN_USER_SOAP = "stagingaut";
	public static final String LOGIN_PASS_SOAP = "stagingaut1";
	// public static final String LOGIN_PASS = "YYz66iiaalop";

	// SOAP URIs
	// public static final String API_URI =
	// "http://staging-aut.pippajean.com/index.php/api/v2_soap?wsdl=1";
	// public static final String API_URI =
	// "https://staging-aut.pippajean.com/index.php/api/v2_soap/";
	public static final String API_URI = "https://staging-aut.pippajean.com/index.php/api/v2_soap/index/";
	// public static final String API_URI =
	// "https://staging-aut.pippajean.com/index.php/api/v2_soap/index/";

	public static final String URL_WEB_MAIL = "http://mailinator.com/";

	public static final String RESOURCES_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
	public static final String BE_USER = "admin";
//	public static final String BE_PASS = "admin123";
	 public static final String BE_PASS = "admin1234";
	public static final String BE_STYLIST = "simona.popa@evozon.com";
	public static final String WEB_MAIL = "mailinator.com";

	public static final long WAIT_TIME_MAIL = 5000;
	public static final long TIME_CONSTANT = 3000;
	public static final long WAIT_TIME_LONG = 8000;

	public static final String DATE_SEPARATOR = ",";
	public static final String ADDRESS_SEPARATOR = ",";
	public static final String LINE_SEPARATOR = "\n";
	public static final String COMMA_SEPARATOR = ",";
	public static final String PROFILE_HISTORY = "MEINE BESTELLUNGEN";
	public static final String US_03_FOLDER = "us3";
	public static final String US_05_FOLDER = "us5";

	// discounts
	public static final String DISCOUNT_0 = "00";
	public static final String DISCOUNT_25 = "25";
	public static final String DISCOUNT_50 = "50";
	public static final String DISCOUNT_BUY_3_GET_1 = "3+1";
	

	public static final String GRAB = "Grab";
	public static final String CALC = "Calc";
	
	//statuses
	public static final String CONFIRMED = "Bestätigt";
	public static final String NOT_CONFIRMED = "Nicht bestätigt, kann sich nicht anmelden";
	//jewelry bonus values
	public static final String JEWELRY_INITIAL_VALUE = "0.0000";
	public static final String JEWELRY_FINAL_VALUE = "400.0000";
	//customer types
	public static final String GENERAL = "General";
	public static final String STYLIST = "Stylist";
	//messages
	public static final String EXISTING_ACCOUNT_MESSAGE = "WICHTIG: Wenn Du bereits einen PIPPA&JEAN Benutzerkonto hast, dann melde dich bitte hier an, bevor Du die Style Coach Registrierung ausführst!";
	public static final String STYLE_COACH_REG_PAGE_TITLE = "REGISTRIERE DICH ALS STYLE COACH UND ERWERBE DAS STARTER-SET";
	public static final String VOUCHER_DISCOUNT_INCOMPATIBLE = "Gutscheine können nicht in Verbindung mit Schmuckbonus verwendet werden.";
	//BONUSES
	public static final String JEWELRY_BONUS = "Schmuck Bonus";
	public static final String DISCOUNT_40_BONUS = "40% Rabatt Gutschein";
	public static final String REGULAR_PRICE = "Regulärer Preis";
	public static final String VOUCHER_DISCOUNT = "Voucher Discount";
	

	
}

package com.tools.env.variables;

import com.tools.persistance.MongoReader;

public class ContextConstants {


	public static String COUNTRY_NAME = MongoReader.getDictionaryItem("COUNTRY_NAME");
	//messages
	public static String EXISTING_ACCOUNT_MESSAGE = MongoReader.getDictionaryItem("EXISTING_ACCOUNT_MESSAGE");
	public static String STYLE_COACH_REG_PAGE_TITLE = MongoReader.getDictionaryItem("STYLE_COACH_REG_PAGE_TITLE");
	public static String VOUCHER_DISCOUNT_INCOMPATIBLE = MongoReader.getDictionaryItem("VOUCHER_DISCOUNT_INCOMPATIBLE");
	public static String PARTY_PLANNED = MongoReader.getDictionaryItem("PARTY_PLANNED");
	public static String PARTY_ACTIVE = MongoReader.getDictionaryItem("PARTY_ACTIVE");
	public static String PARTY_CLOSED = MongoReader.getDictionaryItem("PARTY_CLOSED");
	public static String BOUNUS_SUCCESS_MESSAGE = MongoReader.getDictionaryItem("BOUNUS_SUCCESS_MESSAGE");
	public static String INVITE_EMAIL_SUBJECT = MongoReader.getDictionaryItem("INVITE_EMAIL_SUBJECT");
	public static String PARTY_CREATION_EMAIL_SUBJECT = MongoReader.getDictionaryItem("PARTY_CREATION_EMAIL_SUBJECT");
	public static String PROFILE_HISTORY = MongoReader.getDictionaryItem("PROFILE_HISTORY");
	
	
	public static String EMPTY_CART = MongoReader.getDictionaryItem("EMPTY_CART");
	public static String SUCCES_MESSAGE = MongoReader.getDictionaryItem("SUCCES_MESSAGE");
	public static String CREATE_ACCOUNT_SUCCESS_MESSAGE = MongoReader.getDictionaryItem("CREATE_ACCOUNT_SUCCESS_MESSAGE");
	public static String PRODUCT_ADDED_INTO_CART = MongoReader.getDictionaryItem("PRODUCT_ADDED_INTO_CART");
	public static String SUCCESFULL_REGISTRATION = MongoReader.getDictionaryItem("SUCCESFULL_REGISTRATION");
	public static String MEIN_STYLE_COACH = MongoReader.getDictionaryItem("MEIN_STYLE_COACH");
	

	public static String INVITE_HOSTESS = MongoReader.getDictionaryItem("INVITE_HOSTESS");
	public static String UPDATE_PARTY = MongoReader.getDictionaryItem("UPDATE_PARTY");
	public static String DELETE_PARTY = MongoReader.getDictionaryItem("DELETE_PARTY");
	public static String INVITE_GUEST = MongoReader.getDictionaryItem("INVITE_GUEST");
	public static String CREATE_FOLLOW_UP_PARTY = MongoReader.getDictionaryItem("CREATE_FOLLOW_UP_PARTY");
	public static String ORDER_FOR_CUSTOMER = MongoReader.getDictionaryItem("ORDER_FOR_CUSTOMER");
	public static String CLOSE_PARTY = MongoReader.getDictionaryItem("CLOSE_PARTY");

	public static String ARTICLE_NUMBER = MongoReader.getDictionaryItem("ARTICLE_NUMBER");
	public static String PROCESS = MongoReader.getDictionaryItem("PROCESS");
	
	public static String JEWELRY_BONUS = MongoReader.getDictionaryItem("JEWELRY_BONUS");
	public static String DISCOUNT_40_BONUS = MongoReader.getDictionaryItem("DISCOUNT_40_BONUS");
	public static String REGULAR_PRICE = MongoReader.getDictionaryItem("REGULAR_PRICE");
	public static String VOUCHER_DISCOUNT = MongoReader.getDictionaryItem("VOUCHER_DISCOUNT");
	
	public static String ZWISCHENSUMME = MongoReader.getDictionaryItem("ZWISCHENSUMME");
	public static String SCHMUCK_BONUS = MongoReader.getDictionaryItem("SCHMUCK_BONUS");
	public static String MARKETING_BONUS = MongoReader.getDictionaryItem("MARKETING_BONUS");
	public static String STEUER = MongoReader.getDictionaryItem("STEUER");
	public static String VERSANDKOSTENFREI = MongoReader.getDictionaryItem("VERSANDKOSTENFREI");
	public static String RABATT = MongoReader.getDictionaryItem("DESCUENTO");
	public static String GESAMTBETRAG = MongoReader.getDictionaryItem("GESAMTBETRAG");
	public static String IP_PUNKTE = MongoReader.getDictionaryItem("IP_PUNKTE");
	
	public static String CONFIRM_ACCOUNT_MAIL_SUBJECT = MongoReader.getDictionaryItem("CONFIRM_ACCOUNT_MAIL_SUBJECT");
	public static String NEWSLETTER_MAIL_SUBJECT = MongoReader.getDictionaryItem("NEWSLETTER_MAIL_SUBJECT");
	public static String WELCOME_MAIL_SUBJECT = MongoReader.getDictionaryItem("WELCOME_MAIL_SUBJECT");

}

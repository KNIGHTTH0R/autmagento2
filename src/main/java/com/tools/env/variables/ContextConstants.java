package com.tools.env.variables;

import com.tools.persistance.MongoReader;

public class ContextConstants {
	
	
	public static String JEWELRY_BONUS = MongoReader.getDictionaryItem("JEWELRY_BONUS");
//	public static String JEWELRY_BONUS = "Schmuckbonus";
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
	
	
	public static String EMPTY_CART = "WARENKORB IST LEER";
	public static String SUCCES_MESSAGE = "Wir haben dir eine eMail mit mit den Details zur Bestellung geschickt.";
	public static String PRODUCT_ADDED_INTO_CART = "Der Artikel wurde in den Warenkorb gelegt. Du kannst deinen Einkauf fortsetzen.";
	public static String SUCCESFULL_REGISTRATION = "DEINE REGISTRIERUNG WAR ERFOLGREICH!";
	public static String MEIN_STYLE_COACH = "MEIN STYLE COACH";
	

	public static String INVITE_HOSTESS = "GASTGEBERIN EINLADEN";
	public static String UPDATE_PARTY = "Style Party bearbeiten";
	public static String DELETE_PARTY = "Style Party löschen";
	public static String INVITE_GUEST = "GÄSTE EINLADEN";
	public static String CREATE_FOLLOW_UP_PARTY = "LEGE EINE FOLGEPARTY AN";
	public static String ORDER_FOR_CUSTOMER = "FÜR EINE KUNDIN BESTELLEN";
	public static String CLOSE_PARTY = "STYLE PARTY SCHLIESSEN";

	public static String ARTICLE_NUMBER = "Artikelnummer: ";
	public static String PROCESS = "Vorgang";
}

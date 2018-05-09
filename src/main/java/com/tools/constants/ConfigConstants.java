package com.tools.constants;

import com.tools.persistance.MongoReader;

public class ConfigConstants {

	// statuses
	public static final String CONFIRMED = "Bestätigt";
	public static final String NOT_CONFIRMED = "Nicht bestätigt, kann sich nicht anmelden";

	// jewelry bonus values
	public static final String JEWELRY_INITIAL_VALUE = "0.0000";
	public static final String JEWELRY_FINAL_VALUE = "400.0000";

	// customer types
	public static final String GENERAL = "General";
	public static final String STYLIST = "Stylist";

	// discounts
	public static final String DISCOUNT_0 = "00";
	public static final String DISCOUNT_20 = "20";
	public static final String DISCOUNT_50 = "50";
	public static final String DISCOUNT_BUY_3_GET_1 = "3+1";

	// BONUSES
	public static final String JEWELRY_BONUS = "Schmuck Bonus";
	public static final String DISCOUNT_40_BONUS = "40% Rabatt Gutschein";
	public static final String REGULAR_PRICE = "Regulärer Preis";
	public static final String VOUCHER_DISCOUNT = "Voucher Discount";
	public static final String VOUCHER_SHIPPING = "Voucher On Shipping";

	// Webmail suffix
//	public static final String WEB_MAIL = "mailinator.com";
//	public static final String WEB_MAIL = "yopmail.com";
	//public static final String WEB_MAIL = "rhyta.com";
	
//	public static final String WEB_MAIL = "gustr.com";
//	public static final String WEB_MAIL = "maildrop.cc";
	//public static final String WEB_MAIL = "dispostable.com";
	///public static final String WEB_MAIL = "mailnesia.com";
//	public static final String WEB_MAIL = "ipdeer.com";
//	public static final String WEB_MAIL = "golemico.com";
	public static final String WEB_MAIL = "mailtrix.net";

	
	
	public static final String EVOZON = "evozon.com";
	public static final String GMAIL = "gmail.com";
	
	
	public static final String MASTER_STYLE_COACH = "";

	public static final String NO = "No";
	public static final String YES = "Yes";
	public static final String CONTACT = "contact";
	public static final String USER = "user";
	public static final String ZERO = "0";

	// backend stylecoach tab
	public static final String CANCEL_SC_AND_REASSIGN_CONTACTS = "Cancel SC and reassign SC Contacts";
	public static final String NOT_ACTIVE = "Not active";
	public static final String ACTIVE = "Active";
	public static final String INACTIVE = "Inactive";
	public static final String REASSIGNED_PREFIX = "gesperrt_";
	
	//unbounce success registration page
	
	public static final String UNBOUNCE_SUCCESS_MESSAGE = "Vielen Dank für dein Interesse";
	//backend horizontal menu cons
	
	public static final String PROMOTIONEN = "Promotionen";
	public static final String WARENKORB_PREISGEBOTE = "Warenkorb Preisgebote";
	public static final String STYLECOACH = "Stylecoach";
	public static final String STYLE_PARTIES = "Style Parties";
	public static final String STYLECOACH_LIST = "Stylecoach List";
	public static final String KUNDEN = "Kunden";
	public static final String KUNDEN_VERWALTEN = "Kunden verwalten";
	public static final String NEWSLETTER = "Newsletter";
	public static final String NEWSLETTER_BEZIEHER = "Newsletter Bezieher";
	public static final String AUFTRÄGE = "Aufträge";
	public static final String GUTSCHRIFTEN = "Gutschriften";
	public static final String VERKÄUFE = "Verkäufe";
	public static final String KATALOG = "Katalog";
	public static final String PRODUKTE_VERWALTEN = "Produkte verwalten";
	
	
	public static final String BUNDLEARTIKEL = "Bundleartikel";
	public static final String BUNDLEARTIKEL_NAME = "AutomationTest";
	public static final String LOADING = "Bitte warten ...";
	
	//term purchase grid - Reasons
	public static final String REASON_POSTPONED_BY_ADMIN = "postponed by admin";
	public static final String REASON_POSTPONED_NOT_ENOUGH_STOCK = "postponed because there is not enough stock";
	public static final String REASON_POSTPONED_EARLIEST_AV_CHANGED = "postponed because earliest availability date of product has changed";
	public static final String REASON_CANCEL_SOLD_OUT = "canceled because at least one item is sold out";
	public static final String REASON_CANCEL_POSTPONING_LIMIT_REACHED = "canceled because postponing limit was reached";
	public static final String REASON_CANCEL_PAYMENT_FAILED = "canceled because payment failed";
	
	//term purchase grid - Recomandations
	public static final String NO_RECOMMENDATION = "Keine Empfehlung";
	public static final String RECOMMENDATION_TO_CANCEL = "Stonierung empfohlen";
	public static final String RECOMMENDATION_TO_POSTPONE = "Verschiebung empfohlen";
	public static final String RECOMMENDATION_TO_RELEASE = "Freigabe empfohlen";
	
	//term purchase grid - Order statuses
	public static final String TP_GRID_PAYMENT_ON_HOLD = "Payment on Hold";
	public static final String TP_GRID_PAYMENT_COMPLETE = "Payment Complete";
	public static final String TP_GRID_CANCELED = "Canceled";
	public static final String TP_GRID_COMPLETE = "Complete";
	public static final String TP_GRID_CLOSED = "Closed";
	
	//term purchase grid - Order statuses
	public static final String PAYMENT_ON_HOLD = "Zahlung geplant";
	public static final String PAYMENT_COMPLETE = "Zahlung erfolgreich";
	public static final String CANCELED = "Storniert";
	public static final String COMPLETE = "Complete";
	public static final String CLOSED = "Closed";
	public static final String PAYMENT_IN_PROGRESS = "Zahlung wird geprüft";
	
	//term purchase grid - Scheduled Payment statuses
	public static final String PENDING = "Offene Bestellung";
	public static final String POSTPONED = "Postponed";

	//Term purchase grid -Actions
	public static final String POSTPONE = "Postpone Order";
	public static final String CANCEL = "Cancel Order";
	public static final String RELEASE = "Release Order";
	public static final String POSTPONE_ONE_WEEK = "1 week";
	public static final String POSTPONE_TWO_WEEKS = "2 weeks";
	public static final String POSTPONE_THREE_WEEKS = "3 weeks";
	public static final String POSTPONE_FOUR_WEEKS = "4 weeks";
	public static final String POSTPONE_FIVE_WEEKS = "5 weeks";
	public static final String POSTPONE_SIX_WEEKS = "6 weeks";
	public static final String POSTPONE_SEVEN_WEEKS = "7 weeks";
	public static final String POSTPONE_EIGHT_WEEKS = "8 weeks";
	
	//Term purchase grid -messages
	
	public static final String POSTPONE_SUCCESS_MESSAGE = "1 order(s) have been postponed.";
	public static final String CANCEL_SUCCESS_MESSAGE = "1 Bestellung(en) wurde(n) storniert.";
	public static final String RELEASE_SUCCESS_MESSAGE = "1 order(s) have been released.";
	public static final String RELEASE_ERROR_MESSAGE = "No order(s) were released";
	
	//Execution type
	public static final String MANUAL = "Manuell";
	public static final String FULL_AUTOMATED = "Full automated";
	public static final String SEMIAUTOMATED = "Semi-automated";
	public static final String AUTOMATED_ONLY_RELEASE = "Automated - Only release";
	
	//Order details tab
	public static final String ADYEN_NOTIFICATION_TAB ="Adyen Order Notifications";
	public static final String ADYEN_AUTHORISATION ="AUTHORISATION";
	public static final String ADYEN_CAPTURE ="CAPTURE";
	
	//term purchse config
	
	public static final String FOR_ALL_PRODUCTS ="For all products";
	public static final String ONLY_FOR_NOT_AVAILABLE ="Only for products which are temporarily not available";
	public static final String DISABLED_FOR_ALL ="Not available";
	public static final String MONDAY ="Montag";
	public static final String TUESDAY ="Dienstag";
	public static final String WEDNESDAY ="Mittwoch";
	public static final String THURSDAY ="Donnerstag";
	public static final String FRIDAY ="Freitag";
	public static final String SATURDAY ="Samstag";
	public static final String SUNDAY ="Sonntag";
	

	
	
	

	
	
}

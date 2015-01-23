package com.tools.persistance;

import java.net.UnknownHostException;
import java.util.List;

import com.connectors.mongo.MongoConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.tools.data.OrderModel;
import com.tools.data.StylistDataModel;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.ProductBasicModel;

public class MongoWriter extends MongoConnector {

	public MongoWriter() throws UnknownHostException {
		super();
	}

	public static void saveAddressModel(AddressModel dataModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.ADDRESS_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STREET_ADDRESS, dataModel.getStreetAddress());
		document.put(MongoTableKeys.STREET_NUMBER, dataModel.getStreetNumber());
		document.put(MongoTableKeys.HOME_TOWN, dataModel.getHomeTown());
		document.put(MongoTableKeys.POST_CODE, dataModel.getPostCode());
		document.put(MongoTableKeys.COUNTRY_NAME, dataModel.getCountryName());
		document.put(MongoTableKeys.PHONE_NUMBER, dataModel.getPhoneNumber());

		table.insert(document);
	}

	public static void saveStylistDataModel(StylistDataModel dataModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.VALIDATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.STYLE_COACH_LEADS, dataModel.getStyleCoachLeads());
		document.put(MongoTableKeys.HOSTESS_LEADS, dataModel.getHostessLeads());
		document.put(MongoTableKeys.CUSTOMER_LEADS, dataModel.getCustomerLeads());
		document.put(MongoTableKeys.STYLE_COACH_LEADS_WEEK, dataModel.getStyleCoachLeadsWeek());
		document.put(MongoTableKeys.HOSTESS_LEADS_WEEK, dataModel.getHostessLeadsWeek());

		table.insert(document);
	}

	public static void saveCustomerFormModel(CustomerFormModel customerModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CUSTOMER_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.CLIENT_NAME, customerModel.getEmailName());

		table.insert(document);
	}

	public static void saveCustomerConfigurationModel(CustomerConfigurationModel customerConfigurationModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CUSTOMER_CONFIGURATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.EMAIL_ACTIVE, customerConfigurationModel.getEmailActive());
		document.put(MongoTableKeys.ACCOUNT_ACTIVE, customerConfigurationModel.getAccountActive());

		table.insert(document);
	}

	public static void saveOrderModel(OrderModel orderModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.ORDER_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.ORDER_ID, orderModel.getOrderId());
		document.put(MongoTableKeys.DATE, orderModel.getDate());
		document.put(MongoTableKeys.INVOICE_CONTACT, orderModel.getInvoiceContact());
		document.put(MongoTableKeys.DELIVERY_CONTACT, orderModel.getDeliveryContact());
		document.put(MongoTableKeys.TOTAL_PRICE, orderModel.getTotalPrice());
		document.put(MongoTableKeys.STATUS, orderModel.getStatus());
		
		table.insert(document);
	}
	
	public static void saveProductBasicModel(ProductBasicModel product, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.PRODUCT_BASIC_MODEL);
		
		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.NAME, product.getName());
		document.put(MongoTableKeys.TYPE, product.getType());
		document.put(MongoTableKeys.PRICE, product.getPrice());
		document.put(MongoTableKeys.QUANTITY, product.getQuantity());	
		
		table.insert(document);
	}
	
	public static void saveTotalsModel(CartTotalsModel cartTotalsModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoTableKeys.CART_TOTALS_MODEL);
		
		BasicDBObject document = new BasicDBObject();
		document.put(MongoTableKeys.SUBTOTAL, cartTotalsModel.getSubtotal());
		document.put(MongoTableKeys.JEWERLY_BONUS, cartTotalsModel.getJewelryBonus());
		document.put(MongoTableKeys.DISCOUNT, cartTotalsModel.getDiscount());
		document.put(MongoTableKeys.DISCOUNT_25, cartTotalsModel.getDiscount25());
		document.put(MongoTableKeys.DISCOUNT_50, cartTotalsModel.getDiscount50());
		document.put(MongoTableKeys.DISCOUNT_3_PLUS_1, cartTotalsModel.getDiscount3To1());
		document.put(MongoTableKeys.TAX, cartTotalsModel.getTax());
		document.put(MongoTableKeys.SHIPPING, cartTotalsModel.getShipping());
		document.put(MongoTableKeys.TOTAL_AMOUNT, cartTotalsModel.getTotalAmount());
		document.put(MongoTableKeys.IP_POINTS, cartTotalsModel.getIpPoints());		
		
		table.insert(document);
	}

}

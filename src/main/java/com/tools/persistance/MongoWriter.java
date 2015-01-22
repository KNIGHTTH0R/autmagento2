package com.tools.persistance;

import java.net.UnknownHostException;

import com.connectors.mongo.MongoConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.tools.data.AddressModel;
import com.tools.data.CustomerConfigurationModel;
import com.tools.data.CustomerFormModel;
import com.tools.data.OrderModel;
import com.tools.data.StylistDataModel;

public class MongoWriter extends MongoConnector {

	public MongoWriter() throws UnknownHostException {
		super();
	}

	public static void saveAddressModel(AddressModel dataModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoConstants.ADDRESS_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoConstants.STREET_ADDRESS, dataModel.getStreetAddress());
		document.put(MongoConstants.STREET_NUMBER, dataModel.getStreetNumber());
		document.put(MongoConstants.HOME_TOWN, dataModel.getHomeTown());
		document.put(MongoConstants.POST_CODE, dataModel.getPostCode());
		document.put(MongoConstants.COUNTRY_NAME, dataModel.getCountryName());
		document.put(MongoConstants.PHONE_NUMBER, dataModel.getPhoneNumber());

		table.insert(document);
	}

	public static void saveStylistDataModel(StylistDataModel dataModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoConstants.VALIDATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoConstants.STYLE_COACH_LEADS, dataModel.getStyleCoachLeads());
		document.put(MongoConstants.HOSTESS_LEADS, dataModel.getHostessLeads());
		document.put(MongoConstants.CUSTOMER_LEADS, dataModel.getCustomerLeads());
		document.put(MongoConstants.STYLE_COACH_LEADS_WEEK, dataModel.getStyleCoachLeadsWeek());
		document.put(MongoConstants.HOSTESS_LEADS_WEEK, dataModel.getHostessLeadsWeek());

		table.insert(document);
	}

	public static void saveCustomerFormModel(CustomerFormModel customerModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoConstants.CUSTOMER_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoConstants.CLIENT_NAME, customerModel.getEmailName());

		table.insert(document);
	}

	public static void saveCustomerConfigurationModel(CustomerConfigurationModel customerConfigurationModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoConstants.CUSTOMER_CONFIGURATION_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoConstants.EMAIL_ACTIVE, customerConfigurationModel.getEmailActive());
		document.put(MongoConstants.ACCOUNT_ACTIVE, customerConfigurationModel.getAccountActive());

		table.insert(document);
	}

	public static void saveOrderModel(OrderModel orderModel, String testName) {
		workingDB = mongoClient.getDB(testName);
		DBCollection table = workingDB.getCollection(MongoConstants.ORDER_MODEL);

		BasicDBObject document = new BasicDBObject();
		document.put(MongoConstants.ORDER_ID, orderModel.getOrderId());
		document.put(MongoConstants.DATE, orderModel.getDate());
		document.put(MongoConstants.INVOICE_CONTACT, orderModel.getInvoiceContact());
		document.put(MongoConstants.DELIVERY_CONTACT, orderModel.getDeliveryContact());
		document.put(MongoConstants.TOTAL_PRICE, orderModel.getTotalPrice());
		document.put(MongoConstants.STATUS, orderModel.getStatus());
		
		table.insert(document);
	}

}

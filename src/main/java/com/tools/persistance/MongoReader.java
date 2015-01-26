package com.tools.persistance;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.connectors.mongo.MongoConnector;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tools.data.OrderModel;
import com.tools.data.StylistDataModel;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.ProductBasicModel;

public class MongoReader extends MongoConnector {

	public MongoReader() throws UnknownHostException {
		super();
	}

	public static List<OrderModel> getOrderModel(String testName) {

		DBObject dbObject = null;
		List<OrderModel> itemList = new ArrayList<OrderModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.ORDER_MODEL).find();
		
		try {
			while (cursor.hasNext()) {
				OrderModel result = new OrderModel();
				dbObject = cursor.next();
				
				result.setOrderId(MongoUtils.checkField(dbObject, MongoTableKeys.ORDER_ID));
				result.setDate(MongoUtils.checkField(dbObject, MongoTableKeys.DATE));
				result.setInvoiceContact(MongoUtils.checkField(dbObject, MongoTableKeys.INVOICE_CONTACT));
				result.setDeliveryContact(MongoUtils.checkField(dbObject, MongoTableKeys.DELIVERY_CONTACT));
				result.setTotalPrice(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_PRICE));
				result.setStatus(MongoUtils.checkField(dbObject, MongoTableKeys.STATUS));
				
				itemList.add(result);
			}
		} finally {
			cursor.close();
		}

		return itemList;
	}

	public static List<AddressModel> grabAddressModels(String testName) {
		DBObject dbObject = null;
		List<AddressModel> itemList = new ArrayList<AddressModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.ADDRESS_MODEL).find();

		try {
			while (cursor.hasNext()) {
				AddressModel result = new AddressModel();
				dbObject = cursor.next();

				result.setStreetAddress(MongoUtils.checkField(dbObject, MongoTableKeys.STREET_ADDRESS));
				result.setStreetNumber(MongoUtils.checkField(dbObject, MongoTableKeys.STREET_NUMBER));
				result.setHomeTown(MongoUtils.checkField(dbObject, MongoTableKeys.HOME_TOWN));
				result.setPostCode(MongoUtils.checkField(dbObject, MongoTableKeys.POST_CODE));
				result.setCountryName(MongoUtils.checkField(dbObject, MongoTableKeys.COUNTRY_NAME));
				result.setPhoneNumber(MongoUtils.checkField(dbObject, MongoTableKeys.PHONE_NUMBER));

				itemList.add(result);
			}
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<StylistDataModel> grabStylistDataModels(String testName) {
		DBObject dbObject = null;
		List<StylistDataModel> itemList = new ArrayList<StylistDataModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.VALIDATION_MODEL).find();

		try {
			while (cursor.hasNext()) {
				StylistDataModel result = new StylistDataModel();
				dbObject = cursor.next();

				result.setStyleCoachLeads(MongoUtils.checkField(dbObject, MongoTableKeys.STYLE_COACH_LEADS));
				result.setHostessLeads(MongoUtils.checkField(dbObject, MongoTableKeys.HOSTESS_LEADS));
				result.setCustomerLeads(MongoUtils.checkField(dbObject, MongoTableKeys.CUSTOMER_LEADS));
				result.setStyleCoachLeadsWeek(MongoUtils.checkField(dbObject, MongoTableKeys.STYLE_COACH_LEADS_WEEK));
				result.setHostessLeadsWeek(MongoUtils.checkField(dbObject, MongoTableKeys.HOSTESS_LEADS_WEEK));

				itemList.add(result);
			}
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<CustomerFormModel> grabCustomerFormModels(String testName) {
		DBObject dbObject = null;
		List<CustomerFormModel> itemList = new ArrayList<CustomerFormModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.CUSTOMER_MODEL).find();

		try {
			while (cursor.hasNext()) {
				CustomerFormModel result = new CustomerFormModel();
				dbObject = cursor.next();

				result.setEmailName(MongoUtils.checkField(dbObject, MongoTableKeys.CLIENT_NAME));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<CustomerConfigurationModel> grabCustomerConfigurationModels(String testName) {
		DBObject dbObject = null;
		List<CustomerConfigurationModel> itemList = new ArrayList<CustomerConfigurationModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.CUSTOMER_CONFIGURATION_MODEL).find();

		try {
			while (cursor.hasNext()) {
				CustomerConfigurationModel result = new CustomerConfigurationModel();
				dbObject = cursor.next();

				result.setEmailActive(MongoUtils.checkField(dbObject, MongoTableKeys.EMAIL_ACTIVE));
				result.setAccountActive(MongoUtils.checkField(dbObject, MongoTableKeys.ACCOUNT_ACTIVE));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}
	public static List<ProductBasicModel> grabProductBasicModel(String testName) {
		DBObject dbObject = null;
		List<ProductBasicModel> itemList = new ArrayList<ProductBasicModel>();
		
		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.PRODUCT_BASIC_MODEL).find();
		
		try {
			while (cursor.hasNext()) {
				ProductBasicModel result = new ProductBasicModel();
				dbObject = cursor.next();
				
				result.setName(MongoUtils.checkField(dbObject, MongoTableKeys.NAME));
				result.setType(MongoUtils.checkField(dbObject, MongoTableKeys.TYPE));
				result.setPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRICE));
				result.setQuantity(MongoUtils.checkField(dbObject, MongoTableKeys.QUANTITY));			
				
				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}
	
	@SuppressWarnings("unchecked")
	public static List<CartTotalsModel> grabTotalsModels(String testName) {
		DBObject dbObject = null;
		List<CartTotalsModel> itemList = new ArrayList<CartTotalsModel>();
		
		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.CART_TOTALS_MODEL).find();
		
		try {
			while (cursor.hasNext()) {
				CartTotalsModel result = new CartTotalsModel();
				dbObject = cursor.next();
				
				result.setSubtotal(MongoUtils.checkField(dbObject, MongoTableKeys.SUBTOTAL));
				result.setJewelryBonus(MongoUtils.checkField(dbObject, MongoTableKeys.JEWERLY_BONUS));
				result.setTax(MongoUtils.checkField(dbObject, MongoTableKeys.TAX));
				result.setShipping(MongoUtils.checkField(dbObject, MongoTableKeys.SHIPPING));
				result.setTotalAmount(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_AMOUNT));
				result.setIpPoints(MongoUtils.checkField(dbObject, MongoTableKeys.IP_POINTS));
				
				result.setDiscountMap((Map<String, String>) dbObject.get(MongoTableKeys.DISCOUNT_LIST));
				
				
				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}
	
	
}




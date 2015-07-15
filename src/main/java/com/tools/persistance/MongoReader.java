package com.tools.persistance;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.connectors.mongo.MongoConnector;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tools.data.BorrowCartCalcDetailsModel;
import com.tools.data.CalcDetailsModel;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.StylistDataModel;
import com.tools.data.UrlModel;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.soap.DBStylistModel;

public class MongoReader extends MongoConnector {

	public MongoReader() throws UnknownHostException {
		super();
	}

	public static String getEnvironment() {

		DBObject dbObject = null;
		String env = "";

		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.DEFAULT_CONFIG_MODEL).find();

		try {
			while (cursor.hasNext()) {
				dbObject = cursor.next();
				env = MongoUtils.checkField(dbObject, MongoTableKeys.ENV_KEY);
			}
		} finally {
			cursor.close();
		}

		System.out.println("Environment Set: " + env);

		return env;
	}

	public static String getContext() {

		DBObject dbObject = null;
		String context = "";

		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.DEFAULT_CONFIG_MODEL).find();

		try {
			while (cursor.hasNext()) {
				dbObject = cursor.next();
				context = MongoUtils.checkField(dbObject, MongoTableKeys.CONTEXT_KEY);
				if (!context.isEmpty()) {
					break;
				}
			}
		} finally {
			cursor.close();
		}

		return context;
	}

	/**
	 * Get value of Dictionary item. Return Empty string if none is found.
	 * 
	 * @param key
	 * @return
	 */
	public static String getDictionaryItem(String key) {

		DBObject dbObject = null;
		String value = "";

		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.DICTIONARY_MODEL).find();

		try {
			while (cursor.hasNext()) {
				dbObject = cursor.next();
				value = MongoUtils.checkField(dbObject, key);
				if (!value.isEmpty()) {
					break;
				}
			}
		} finally {
			cursor.close();
		}

		return value;
	}

	public static String getBaseURL() {

		DBObject dbObject = null;
		String baseUrl = "";

		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.STORE_CONFIG_MODEL).find();

		try {
			while (cursor.hasNext()) {
				dbObject = cursor.next();
				baseUrl = MongoUtils.checkField(dbObject, MongoTableKeys.BASE_URL_KEY);
				if (!baseUrl.isEmpty()) {
					break;
				}
			}
		} finally {
			cursor.close();
		}

		return baseUrl;
	}

	public static String getSoapURL() {

		DBObject dbObject = null;
		String baseUrl = "";

		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.STORE_CONFIG_MODEL).find();

		try {
			while (cursor.hasNext()) {
				dbObject = cursor.next();
				baseUrl = MongoUtils.checkField(dbObject, MongoTableKeys.SOAP_URL_KEY);
				if (!baseUrl.isEmpty()) {
					break;
				}
			}
		} finally {
			cursor.close();
		}

		return baseUrl;
	}

	public static String getStoreIds() {

		DBObject dbObject = null;
		String storeIds = "";

		workingDB = mongoClient.getDB(MongoTableKeys.TEST_CONFIG);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.STORE_CONFIG_MODEL).find();

		try {
			while (cursor.hasNext()) {
				dbObject = cursor.next();
				storeIds = MongoUtils.checkField(dbObject, MongoTableKeys.STORE_ID_KEY);
				if (!storeIds.isEmpty()) {
					break;
				}
			}
		} finally {
			cursor.close();
		}

		return storeIds;
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

	public static List<UrlModel> grabUrlModels(String testName) {
		DBObject dbObject = null;
		List<UrlModel> itemList = new ArrayList<UrlModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.URL_MODEL).find();

		try {
			while (cursor.hasNext()) {
				UrlModel result = new UrlModel();
				dbObject = cursor.next();

				result.setName(MongoUtils.checkField(dbObject, MongoTableKeys.NAME));
				result.setUrl(MongoUtils.checkField(dbObject, MongoTableKeys.URL_PATH));

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
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.CUSTOMER_FORM_MODEL).find();

		try {
			while (cursor.hasNext()) {
				CustomerFormModel result = new CustomerFormModel();
				dbObject = cursor.next();

				result.setFirstName(MongoUtils.checkField(dbObject, MongoTableKeys.STYLIST_FIRSTNAME));
				result.setLastName(MongoUtils.checkField(dbObject, MongoTableKeys.STYLIST_LASTNAME));
				result.setEmailName(MongoUtils.checkField(dbObject, MongoTableKeys.STYLIST_EMAIL));
				result.setPassword(MongoUtils.checkField(dbObject, MongoTableKeys.STYLIST_PASSWORD));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<DBStylistModel> grabDbStylistModels(String testName) {
		DBObject dbObject = null;
		List<DBStylistModel> itemList = new ArrayList<DBStylistModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.DB_STYLIST_MODEL).find();

		try {
			while (cursor.hasNext()) {
				DBStylistModel result = new DBStylistModel();
				dbObject = cursor.next();

				result.setEmail(MongoUtils.checkField(dbObject, MongoTableKeys.DB_STYLIST_EMAIL));
				result.setTotalSCReceived(MongoUtils.checkField(dbObject, MongoTableKeys.DB_TOTAL_SC_RECEIVED));
				result.setTotalHostReceived(MongoUtils.checkField(dbObject, MongoTableKeys.DB_TOTAL_HOST_RECEIVED));
				result.setTotalCustomerReceived(MongoUtils.checkField(dbObject, MongoTableKeys.DB_TOTAL_CUSTOMER_RECEIVED));
				result.setTotalSCCurrentWeek(MongoUtils.checkField(dbObject, MongoTableKeys.DB_TOTAL_SC_CURRENT_WEEK));
				result.setTotalHostCurrentWeek(MongoUtils.checkField(dbObject, MongoTableKeys.DB_TOTAL_HOST_CURRENT_WEEK));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<DateModel> grabStylistDateModels(String testName) {
		DBObject dbObject = null;
		List<DateModel> itemList = new ArrayList<DateModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.DATE_MODEL).find();

		try {
			while (cursor.hasNext()) {
				DateModel result = new DateModel();
				dbObject = cursor.next();

				result.setDate(MongoUtils.checkField(dbObject, MongoTableKeys.REGISTRATION_DATE));

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

	public static List<BasicProductModel> grabBasicProductModel(String testName) {
		DBObject dbObject = null;
		List<BasicProductModel> itemList = new ArrayList<BasicProductModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.BASIC_PRODUCT_MODEL).find();

		try {
			while (cursor.hasNext()) {
				BasicProductModel result = new BasicProductModel();
				dbObject = cursor.next();

				result.setName(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_NAME));
				result.setProdCode(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_CODE));
				result.setUnitPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_PRICE));
				result.setQuantity(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_QUANTITY));
				result.setProductsPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_ASKING_PRICE));
				result.setFinalPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_FINAL_PRICE));
				result.setPriceIP(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_IP_POINTS));
				result.setDiscountClass(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_DISCOUNT_CLASS));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<BorrowProductModel> grabBorrowProductModel(String testName) {
		DBObject dbObject = null;
		List<BorrowProductModel> itemList = new ArrayList<BorrowProductModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.BORROW_BASIC_PRODUCT_MODEL).find();

		try {
			while (cursor.hasNext()) {
				BorrowProductModel result = new BorrowProductModel();
				dbObject = cursor.next();

				result.setName(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_NAME));
				result.setProdCode(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_CODE));
				result.setUnitPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_PRICE));
				result.setFinalPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_FINAL_PRICE));
				result.setIpPoints(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_IP_POINTS));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<RegularBasicProductModel> grabRegularBasicProductModel(String testName) {
		DBObject dbObject = null;
		List<RegularBasicProductModel> itemList = new ArrayList<RegularBasicProductModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.REGULAR_BASIC_PRODUCT_MODEL).find();

		try {
			while (cursor.hasNext()) {
				RegularBasicProductModel result = new RegularBasicProductModel();
				dbObject = cursor.next();

				result.setName(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_NAME));
				result.setProdCode(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_CODE));
				result.setUnitPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_PRICE));
				result.setQuantity(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_QUANTITY));
				result.setFinalPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_FINAL_PRICE));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<HostBasicProductModel> grabHostBasicProductModel(String testName) {
		DBObject dbObject = null;
		List<HostBasicProductModel> itemList = new ArrayList<HostBasicProductModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.HOST_BASIC_PRODUCT_MODEL).find();

		try {
			while (cursor.hasNext()) {
				HostBasicProductModel result = new HostBasicProductModel();
				dbObject = cursor.next();

				result.setName(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_NAME));
				result.setProdCode(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_CODE));
				result.setUnitPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_PRICE));
				result.setQuantity(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_QUANTITY));
				result.setFinalPrice(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_FINAL_PRICE));
				result.setIpPoints(MongoUtils.checkField(dbObject, MongoTableKeys.PRODUCT_IP_POINTS));
				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	// @SuppressWarnings("unchecked")
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

				result.setDiscountMap(((Map<String, String>) dbObject.get(MongoTableKeys.DISCOUNT_LIST)));

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
	public static List<CalcDetailsModel> grabCalcDetailsModels(String testName) {
		DBObject dbObject = null;
		List<CalcDetailsModel> itemList = new ArrayList<CalcDetailsModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.CALC_DETAILS_MODEL).find();

		try {
			while (cursor.hasNext()) {
				CalcDetailsModel result = new CalcDetailsModel();
				dbObject = cursor.next();

				result.setMarketingBonus(MongoUtils.checkField(dbObject, MongoTableKeys.MARKETING_BONUS));
				result.setJewelryBonus(MongoUtils.checkField(dbObject, MongoTableKeys.JEWERLY_BONUS));
				result.setTax(MongoUtils.checkField(dbObject, MongoTableKeys.TAX));
				result.setSubTotal(MongoUtils.checkField(dbObject, MongoTableKeys.SUBTOTAL));
				result.setTotalAmount(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_AMOUNT));
				result.setIpPoints(MongoUtils.checkField(dbObject, MongoTableKeys.IP_POINTS));

				result.addCalculation(((Map<String, String>) dbObject.get(MongoTableKeys.DISCOUNT_LIST)));
				result.addSegments(((Map<String, String>) dbObject.get(MongoTableKeys.SEGMENTS)));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;

	}

	public static List<BorrowCartCalcDetailsModel> grabBorrowCartCalcDetailsModels(String testName) {
		DBObject dbObject = null;
		List<BorrowCartCalcDetailsModel> itemList = new ArrayList<BorrowCartCalcDetailsModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.BORROW_CART_CALC_DETAILS_MODEL).find();

		try {
			while (cursor.hasNext()) {
				BorrowCartCalcDetailsModel result = new BorrowCartCalcDetailsModel();
				dbObject = cursor.next();

				result.setTax(MongoUtils.checkField(dbObject, MongoTableKeys.TAX));
				result.setSubTotal(MongoUtils.checkField(dbObject, MongoTableKeys.SUBTOTAL));
				result.setTotalAmount(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_AMOUNT));
				result.setIpPoints(MongoUtils.checkField(dbObject, MongoTableKeys.IP_POINTS));

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
	public static List<RegularCartCalcDetailsModel> grabRegularCartCalcDetailsModels(String testName) {
		DBObject dbObject = null;
		List<RegularCartCalcDetailsModel> itemList = new ArrayList<RegularCartCalcDetailsModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.REGULAR_CART_CALC_DETAILS_MODEL).find();

		try {
			while (cursor.hasNext()) {
				RegularCartCalcDetailsModel result = new RegularCartCalcDetailsModel();
				dbObject = cursor.next();

				result.setTax(MongoUtils.checkField(dbObject, MongoTableKeys.TAX));
				result.setSubTotal(MongoUtils.checkField(dbObject, MongoTableKeys.SUBTOTAL));
				result.setTotalAmount(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_AMOUNT));
				result.addSegments(((Map<String, String>) dbObject.get(MongoTableKeys.SEGMENTS)));

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
	public static List<HostCartCalcDetailsModel> grabHostCartCalcDetailsModels(String testName) {
		DBObject dbObject = null;
		List<HostCartCalcDetailsModel> itemList = new ArrayList<HostCartCalcDetailsModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.HOST_CART_CALC_DETAILS_MODEL).find();

		try {
			while (cursor.hasNext()) {
				HostCartCalcDetailsModel result = new HostCartCalcDetailsModel();
				dbObject = cursor.next();

				result.setTax(MongoUtils.checkField(dbObject, MongoTableKeys.TAX));
				result.setSubTotal(MongoUtils.checkField(dbObject, MongoTableKeys.SUBTOTAL));
				result.setTotalAmount(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_AMOUNT));
				result.setIpPoints(MongoUtils.checkField(dbObject, MongoTableKeys.IP_POINTS));
				result.addSegments(((Map<String, String>) dbObject.get(MongoTableKeys.SEGMENTS)));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;

	}

	public static List<ShippingModel> grabShippingModel(String testName) {
		DBObject dbObject = null;
		List<ShippingModel> itemList = new ArrayList<ShippingModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.SHIPPING_MODEL).find();

		try {
			while (cursor.hasNext()) {
				ShippingModel result = new ShippingModel();
				dbObject = cursor.next();

				result.setDiscountPrice(MongoUtils.checkField(dbObject, MongoTableKeys.DISCOUNT));
				result.setShippingPrice(MongoUtils.checkField(dbObject, MongoTableKeys.SHIPPING));
				result.setSubTotal(MongoUtils.checkField(dbObject, MongoTableKeys.SUBTOTAL));
				result.setTotalAmount(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_AMOUNT));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;
	}

	public static List<OrderModel> grabOrderModels(String testName) {
		DBObject dbObject = null;
		List<OrderModel> itemList = new ArrayList<OrderModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.ORDER_MODEL).find();

		try {
			while (cursor.hasNext()) {
				OrderModel result = new OrderModel();
				dbObject = cursor.next();

				result.setOrderId(MongoUtils.checkField(dbObject, MongoTableKeys.ORDER_ID));
				result.setStatus(MongoUtils.checkField(dbObject, MongoTableKeys.STATUS));
				result.setTotalPrice(MongoUtils.checkField(dbObject, MongoTableKeys.TOTAL_PRICE));
				result.setDeliveryContact(MongoUtils.checkField(dbObject, MongoTableKeys.DELIVERY_CONTACT));
				result.setInvoiceContact(MongoUtils.checkField(dbObject, MongoTableKeys.INVOICE_CONTACT));
				result.setDate(MongoUtils.checkField(dbObject, MongoTableKeys.DATE));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;

	}

	public static List<JewelryHistoryModel> grabJewerlyHistoryModels(String testName) {
		DBObject dbObject = null;
		List<JewelryHistoryModel> itemList = new ArrayList<JewelryHistoryModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.JEWERLY_HISTORY_MODEL).find();

		try {
			while (cursor.hasNext()) {
				JewelryHistoryModel result = new JewelryHistoryModel();
				dbObject = cursor.next();

				result.setTotalPoints(MongoUtils.checkField(dbObject, MongoTableKeys.JB_HISTORY_TOTAL));
				result.setAmountValue(MongoUtils.checkField(dbObject, MongoTableKeys.JB_HISTORY_AMOUNT));
				result.setDate(MongoUtils.checkField(dbObject, MongoTableKeys.JB_HISTORY_DATE));
				result.setReason(MongoUtils.checkField(dbObject, MongoTableKeys.JB_HISTORY_REASON));

				itemList.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return itemList;

	}

	public static String grabKoboModel(String testName) {
		DBObject dbObject = null;
		String koboVoucher = "";

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoTableKeys.KOBO_MODEL).find();

		try {
			while (cursor.hasNext()) {

				dbObject = cursor.next();
				koboVoucher = MongoUtils.checkField(dbObject, MongoTableKeys.KOBO_CODE);
				if (!koboVoucher.isEmpty()) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return koboVoucher;

	}
}

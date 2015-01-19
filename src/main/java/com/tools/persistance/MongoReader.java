package com.tools.persistance;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.connectors.mongo.MongoConnector;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tools.data.AddressModel;
import com.tools.data.StylistDataModel;

public class MongoReader extends MongoConnector {

	public MongoReader() throws UnknownHostException {
		super();
	}

	public static List<AddressModel> grabAddressModels(String testName) {
		DBObject dbObject = null;
		List<AddressModel> itemList = new ArrayList<AddressModel>();

		workingDB = mongoClient.getDB(testName);
		DBCursor cursor = workingDB.getCollection(MongoConstants.ADDRESS_MODEL)
				.find();

		try {
			while (cursor.hasNext()) {
				AddressModel result = new AddressModel();
				dbObject = cursor.next();

				result.setStreetAddress(MongoUtils.checkField(dbObject,
						MongoConstants.STREET_ADDRESS));
				result.setStreetNumber(MongoUtils.checkField(dbObject,
						MongoConstants.STREET_NUMBER));
				result.setHomeTown(MongoUtils.checkField(dbObject,
						MongoConstants.HOME_TOWN));
				result.setPostCode(MongoUtils.checkField(dbObject,
						MongoConstants.POST_CODE));
				result.setCountryName(MongoUtils.checkField(dbObject,
						MongoConstants.COUNTRY_NAME));
				result.setPhoneNumber(MongoUtils.checkField(dbObject,
						MongoConstants.PHONE_NUMBER));

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
		DBCursor cursor = workingDB.getCollection(
				MongoConstants.VALIDATION_MODEL).find();

		try {
			while (cursor.hasNext()) {
				StylistDataModel result = new StylistDataModel();
				dbObject = cursor.next();

				result.setStyleCoachLeads(MongoUtils.checkField(dbObject,
						MongoConstants.STYLE_COACH_LEADS));
				result.setHostessLeads(MongoUtils.checkField(dbObject,
						MongoConstants.HOSTESS_LEADS));
				result.setCustomerLeads(MongoUtils.checkField(dbObject,
						MongoConstants.CUSTOMER_LEADS));
				result.setStyleCoachLeadsWeek(MongoUtils.checkField(dbObject,
						MongoConstants.STYLE_COACH_LEADS_WEEK));
				result.setHostessLeadsWeek(MongoUtils.checkField(dbObject,
						MongoConstants.HOSTESS_LEADS_WEEK));

				itemList.add(result);
			}
		} finally {
			cursor.close();
		}
		return itemList;
	}
}

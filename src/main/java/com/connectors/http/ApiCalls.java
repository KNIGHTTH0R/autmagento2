package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.env.constants.Separators;
import com.tools.geolocation.DistanceCalculator;
import com.tools.persistance.MongoReader;
import com.tools.utils.DateUtils;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;
import com.tools.utils.FormatterUtils;

/**
 * @author mihaibarta
 *
 */

public class ApiCalls {

	// public static void main(String args[]) throws Exception {
	// createApiProduct(createProductModel());
	// }

	public static ProductDetailedModel createPomProductModel() {
		ProductDetailedModel result = createProductModel();
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createMarketingProductModel() {
		ProductDetailedModel result = createProductModel();
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("15");
		categoriesIds.add("15");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createBundleProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setType("bundle");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createGroupedProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setType("grouped");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createConfigurableProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setType("configurable");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createVirtualProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setType("virtual");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createGiftProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setType("giftcard");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createDownloadableProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setType("downloadable");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createZzzProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setSet("15");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createNotAvailableYetProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setSet("15");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		StockDataModel stockModel = new StockDataModel();
		stockModel.setQty("0");
		stockModel.setIsInStock("1");
		stockModel.setManageStock("1");
		stockModel.setUseConfigManageStock("1");
		stockModel.setMinQty("-10");
		stockModel.setUseConfigMinQty("0");
		stockModel.setMinSaleQty("");
		stockModel.setUseConfigMinSaleQty("1");
		stockModel.setMaxSaleQty("");
		stockModel.setUseConfigMaxSaleQty("");
		stockModel.setIsQtyDecimal("0");
		stockModel.setBackorders("1");
		stockModel.setUseConfigBackorders("0");
		stockModel.setNotifyStockQty("");
		stockModel.setUseConfigNotifyStockQty("1");
		stockModel.setIsDiscontinued("1");
		stockModel.setEarliestAvailability(FormatterUtils.getCustomDate("yyyy-MM-dd", 360000));
		stockModel.setMaximumPercentageToBorrow("");
		stockModel.setUseConfigMaximumPercentageToBorrow("80");
		result.setStockData(stockModel);

		return result;
	}

	public static ProductDetailedModel createStarterKitProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setSet("13");
		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("52");
		categoriesIds.add("52");
		result.setCategoryIdsArray(categoriesIds);

		return result;
	}

	public static ProductDetailedModel createProductModel() {
		String name = FieldGenerators.generateRandomString(9, Mode.ALPHA_CAPS);
		ProductDetailedModel product = new ProductDetailedModel();

		product.setType("simple");
		product.setSet("4");
		product.setName(name);
		product.setDescription("description");
		product.setShortDescription("desc");
		product.setUrlPath(name);
		product.setWeight("2");
		product.setSku(FieldGenerators.generateRandomString(9, Mode.ALPHA_CAPS));
		product.setPrice(FieldGenerators.generateRandomString(2, Mode.NUMERIC));
		product.setStatus("1");
		product.setUrlKey(name);
		product.setVisibility("4");
		product.setHasOptions("");
		product.setGiftMessageAvailable("");
		product.setSpecialPrice("");
		product.setSpecialFromDate("");
		product.setSpecialToDate("");
		product.setTaxClassId("2");
		product.setMetaTitle("");
		product.setMetaKeyword("");
		product.setMetaDescription("");
		product.setCustomDesign("");
		product.setCustomLayoutUpdate("");
		product.setOptionsContainer("");
		product.setStore("0");
		product.setIp("50");

		product.setNewsFromDate(FormatterUtils.getCustomDate("yyyy.MM.dd", 3600));
		product.setNewsToDate(FormatterUtils.getCustomDate("yyyy.MM.dd", 86400));

		List<String> webSiteIds = new ArrayList<String>();

		// Made the store allocation to be environment dependent
		String tempStore = MongoReader.getStoreIds();

		if (!tempStore.isEmpty() && tempStore != null) {
			String strSplitter[] = tempStore.split(Separators.COMMA_SEPARATOR);
			for (String string : strSplitter) {
				webSiteIds.add(string);
			}
		}

		product.setWebsiteIdsArray(webSiteIds);

		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("43");
		categoriesIds.add("5");
		product.setCategoryIdsArray(categoriesIds);

		List<String> cartIds = new ArrayList<String>();
		cartIds.add("4");
		cartIds.add("3");
		product.setAvailableCartsArray(cartIds);

		StockDataModel stockModel = new StockDataModel();
		stockModel.setQty("1000");
		stockModel.setIsInStock("1");
		stockModel.setManageStock("1");
		stockModel.setUseConfigManageStock("1");
		stockModel.setMinQty("");
		stockModel.setUseConfigMinQty("1");
		stockModel.setMinSaleQty("");
		stockModel.setUseConfigMinSaleQty("1");
		stockModel.setMaxSaleQty("");
		stockModel.setUseConfigMaxSaleQty("");
		stockModel.setIsQtyDecimal("0");
		stockModel.setBackorders("");
		stockModel.setUseConfigBackorders("1");
		stockModel.setNotifyStockQty("");
		stockModel.setUseConfigNotifyStockQty("1");
		stockModel.setIsDiscontinued("0");
		stockModel.setEarliestAvailability("");
		stockModel.setMaximumPercentageToBorrow("");
		stockModel.setUseConfigMaximumPercentageToBorrow("80");
		product.setStockData(stockModel);

		product.setJewerlyBonusValue("50.00");
		product.setJewelryBonusCart("3,4");

		return product;

	}

	public static StockDataModel createNotAvailableForTheMomentStockData() {

		StockDataModel stockModel = new StockDataModel();

		stockModel.setQty("0");
		stockModel.setIsInStock("0");
		stockModel.setManageStock("1");
		stockModel.setUseConfigManageStock("1");
		stockModel.setMinQty("");
		stockModel.setUseConfigMinQty("1");
		stockModel.setMinSaleQty("");
		stockModel.setUseConfigMinSaleQty("1");
		stockModel.setMaxSaleQty("");
		stockModel.setUseConfigMaxSaleQty("");
		stockModel.setIsQtyDecimal("0");
		stockModel.setBackorders("");
		stockModel.setUseConfigBackorders("1");
		stockModel.setNotifyStockQty("");
		stockModel.setUseConfigNotifyStockQty("1");
		stockModel.setIsDiscontinued("0");
		stockModel.setEarliestAvailability("");
		stockModel.setMaximumPercentageToBorrow("");
		stockModel.setUseConfigMaximumPercentageToBorrow("80");

		return stockModel;
	}

	public static StockDataModel createNotAvailableStockData() {

		StockDataModel stockModel = new StockDataModel();

		stockModel.setQty("0");
		stockModel.setIsInStock("0");
		stockModel.setManageStock("1");
		stockModel.setUseConfigManageStock("1");
		stockModel.setMinQty("");
		stockModel.setUseConfigMinQty("1");
		stockModel.setMinSaleQty("");
		stockModel.setUseConfigMinSaleQty("1");
		stockModel.setMaxSaleQty("");
		stockModel.setUseConfigMaxSaleQty("");
		stockModel.setIsQtyDecimal("0");
		stockModel.setBackorders("");
		stockModel.setUseConfigBackorders("1");
		stockModel.setNotifyStockQty("");
		stockModel.setUseConfigNotifyStockQty("1");
		stockModel.setIsDiscontinued("1");
		stockModel.setEarliestAvailability("");
		stockModel.setMaximumPercentageToBorrow("");
		stockModel.setUseConfigMaximumPercentageToBorrow("80");

		return stockModel;
	}

	public static String createApiProduct(ProductDetailedModel product) {

		String resultID = null;
		try {
			SOAPMessage response = HttpSoapConnector.soapCreateProduct(product);
			resultID = extractResult(response);
			System.out.println("resultID: " + resultID);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultID;
	}

	public static String createJbZzzApiProduct(ProductDetailedModel product) {

		String resultID = null;
		try {
			SOAPMessage response = HttpSoapConnector.soapCreateJbZzzProduct(product);
			resultID = extractResult(response);
			System.out.println("resultID: " + resultID);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultID;
	}

	public static String getStylistInfo(String id) {

		String resultID = null;
		try {
			SOAPMessage response = HttpSoapConnector.soapGetStylistInfo(id);
			resultID = extractResult(response);
			System.out.println("resultID: " + resultID);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultID;
	}

	public static List<DBStylistModel> getStylistList(String filter, String operand, String filterValue) {

		List<DBStylistModel> stylistList = new ArrayList<DBStylistModel>();

		try {
			SOAPMessage response = HttpSoapConnector.soapGetStylistList(filter, operand, filterValue);
			System.out.println(response);
			try {
				stylistList = extractStylistData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stylistList;
	}

	public static SyncInfoModel getMagProductInfo(String productId) {

		SyncInfoModel result = new SyncInfoModel();

		try {
			SOAPMessage response = HttpSoapConnector.soapProductInfo(productId);
			System.out.println(response.toString());
			try {
				result = extractProductInfo(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("result").item(0).getFirstChild().getNodeValue();
	}

	private static List<DBStylistModel> extractStylistData(SOAPMessage response) throws Exception {

		List<DBStylistModel> stylistModelList = new ArrayList<DBStylistModel>();

		NodeList stylistList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < stylistList.getLength(); i++) {
			DBStylistModel model = new DBStylistModel();
			NodeList childNodes = stylistList.item(i).getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("stylist_id")) {
					model.setStylistId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_id")) {
					model.setCustomerId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("parent_id")) {
					model.setParentId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("activated_at")) {
					model.setActivatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("created_at")) {
					model.setCreatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("updated_at")) {
					model.setUpdatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_firstname")) {
					model.setFirstName(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_lastname")) {
					model.setLastName(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_street")) {
					model.setStreet(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_house_number")) {
					model.setHouseNumber(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_postcode")) {
					model.setPostCode(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("bank_account_vat_payer")) {
					model.setVatPayer(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("bank_account_vat_number")) {
					model.setVatNumber(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_preferred_website")) {
					model.setWebsite(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("status")) {
					model.setStatus(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_email")) {
					model.setEmail(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("billing_latitude")) {
					model.setLattitude(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("billing_longitude")) {
					model.setLongitude(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("qualified_sc_lead_retrieval")) {
					model.setQualifiedSC(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("qualified_host_lead_retrieval")) {
					model.setQualifiedHost(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("qualified_customer_retrieval")) {
					model.setQualifiedCustomer(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_sc_leads_received")) {
					model.setTotalSCReceived(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_host_leads_received")) {
					model.setTotalHostReceived(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_customers_received")) {
					model.setTotalCustomerReceived(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_sc_leads_rec_curr_week")) {
					model.setTotalSCCurrentWeek(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_host_leads_rec_curr_week")) {
					model.setTotalHostCurrentWeek(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("max_sc_leads_per_week")) {
					model.setMaxSCPerWeek(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("lead_retrieval_paused")) {
					model.setLeadRetrievalPaused(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("sc_lead_range")) {
					model.setStyleCoachLeadRange(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("host_lead_range")) {
					model.setHostLeadRange(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("sc_quite_date")) {
					model.setStylistQuiteDate(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("sc_contract_status")) {
					model.setStylistContractStatus(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("paused_from")) {
					model.setPausedFrom(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("paused_to")) {
					model.setPausedTo(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("date_of_birth")) {
					String[] parts = childNodes.item(j).getTextContent().split(" ");
					model.setBirthDate(parts[0]);
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("has_slogan")) {
					model.setSlogan(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("has_avatar")) {
					model.setAvatar(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("is_confirmed")) {
					model.setConfirmed(childNodes.item(j).getTextContent());
				}
			}
			stylistModelList.add(model);
		}
		return stylistModelList;

	}

	private static SyncInfoModel extractProductInfo(SOAPMessage response) throws Exception {

		SyncInfoModel result = new SyncInfoModel();

		NodeList productInfoList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		Node productInfo = productInfoList.item(0);
		NodeList childNodes = productInfo.getChildNodes();
		for (int j = 0; j < childNodes.getLength(); j++) {

			result.setEarliestAvailability("1753-01-01");

			if (childNodes.item(j).getNodeName().equalsIgnoreCase("sku")) {
				result.setSku(childNodes.item(j).getTextContent());
			}
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("qty")) {
				result.setQuantity(childNodes.item(j).getTextContent());
			}
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("is_discontinued")) {
				result.setIsDiscontinued(childNodes.item(j).getTextContent());
			}
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("qty_pending")) {
				result.setPendingQuantity(childNodes.item(j).getTextContent());
			}
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("earliest_availability")) {

				String[] parts = childNodes.item(j).getTextContent().split(" ");
				result.setEarliestAvailability(parts[0]);
			}
		}
		return result;

	}

	public static List<DBStylistModel> getCompatibleStylistsInRangeFromList(CoordinatesModel coordinatesModel, String range, String filter, String operand, String operand2,
			String filterValue, int mode) {

		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();

		initialList = getStylistList(filter, operand, filterValue);
		initialListPart2 = getStylistList(filter, operand2, filterValue);
		initialList.addAll(initialListPart2);

		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();

		for (DBStylistModel dbStylistModel : initialList) {

			switch (mode) {

			case 1:
				if (!isStylistIncompatibleForCustomerRetrieval(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;

			case 2:
				if (!isStylistIncompatibleForHost(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;

			case 3:
				if (!isStylistIncompatibleForSCRetrieval(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;

			case 4:
				if (!isStylistIncompatibleForDistributionDuringCheckout(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;
			}
		}

		return getFirstFiveStylistInRangeOrClosest(coordinatesModel, compatibleList, range);

	}

	public static List<DBStylistModel> getCompatibleStylistsForDysks(CoordinatesModel coordinatesModel, String range, String filter, String operand, String operand2,
			String filterValue, int mode) {

		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();

		initialList = getStylistList(filter, operand, filterValue);
		initialListPart2 = getStylistList(filter, operand2, filterValue);
		initialList.addAll(initialListPart2);

		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();

		for (DBStylistModel dbStylistModel : initialList) {

			switch (mode) {

			case 1:
				if (!isStylistIncompatibleForCustomerRetrievalAssignation(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;

			case 2:
				if (!isStylistIncompatibleForHostAssignation(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;

			case 3:
				if (!isStylistIncompatibleForSCRetrievalAssignation(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;

			case 4:
				if (!isStylistIncompatibleForDistributionDuringCheckout(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);

				}
				break;
			}
		}

		return getFiveStylistsFromListForeachAgeCategoryIfExist(coordinatesModel, compatibleList, range);

	}

	public static List<DBStylistModel> getDykscStylistByName(String firstName, String lastName, String filter, String operand, String operand2, String filterValue, int mode) {

		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();

		initialList = getStylistList(filter, operand, filterValue);
		initialListPart2 = getStylistList(filter, operand2, filterValue);
		initialList.addAll(initialListPart2);

		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();

		for (DBStylistModel dbStylistModel : initialList) {

			if (!isStylistIncompatibleForDykscSearchByName(dbStylistModel, firstName, lastName)) {

				compatibleList.add(dbStylistModel);

			}
		}
		return compatibleList;
	}

	private static boolean isStylistIncompatibleForCustomerRetrieval(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedCustomer().contentEquals("0") || stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0") || !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3");

	}

	private static boolean isStylistIncompatibleForCustomerRetrievalAssignation(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedCustomer().contentEquals("0") || stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0") || !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3") || !stylistModel.getConfirmed().contentEquals("1") || !stylistModel.getAvatar().contentEquals("1")
				|| !stylistModel.getSlogan().contentEquals("1") || stylistModel.getBirthDate().contentEquals("");

	}

	private static boolean isStylistIncompatibleForSCRetrieval(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getStylistId().contentEquals("1") || stylistModel.getQualifiedSC().contentEquals("0") || !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0") || stylistModel.getStylistContractStatus().contentEquals("3")
				|| Integer.parseInt(stylistModel.getTotalSCCurrentWeek()) >= Integer.parseInt(stylistModel.getMaxSCPerWeek());
	}

	private static boolean isStylistIncompatibleForSCRetrievalAssignation(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getStylistId().contentEquals("1") || stylistModel.getQualifiedSC().contentEquals("0") || !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0") || stylistModel.getStylistContractStatus().contentEquals("3")
				|| Integer.parseInt(stylistModel.getTotalSCCurrentWeek()) >= Integer.parseInt(stylistModel.getMaxSCPerWeek()) || !stylistModel.getConfirmed().contentEquals("1")
				|| !stylistModel.getAvatar().contentEquals("1") || !stylistModel.getSlogan().contentEquals("1") || stylistModel.getBirthDate().contentEquals("");
	}

	private static boolean isStylistIncompatibleForHost(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedHost().contentEquals("0") || stylistModel.getStylistId().contentEquals("1") || !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0") || stylistModel.getStylistContractStatus().contentEquals("3");
	}

	private static boolean isStylistIncompatibleForHostAssignation(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedHost().contentEquals("0") || stylistModel.getStylistId().contentEquals("1") || !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0") || stylistModel.getStylistContractStatus().contentEquals("3")
				|| !stylistModel.getConfirmed().contentEquals("1") || !stylistModel.getAvatar().contentEquals("1") || !stylistModel.getSlogan().contentEquals("1")
				|| stylistModel.getBirthDate().contentEquals("");
	}

	private static boolean isStylistIncompatibleForDistributionDuringCheckout(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getQualifiedCustomer().contentEquals("0")
				|| stylistModel.getStylistId().contentEquals("1");
	}

	private static boolean isStylistIncompatibleForDykscSearchByName(DBStylistModel stylistModel, String firstName, String lastName) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1") || stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getConfirmed().contentEquals("1") || !stylistModel.getAvatar().contentEquals("1") || !stylistModel.getSlogan().contentEquals("1")
				|| stylistModel.getBirthDate().contentEquals("") || !stylistModel.getFirstName().contains(firstName) || !stylistModel.getLastName().contains(lastName);
	}

	public static boolean isStylistInRange(CoordinatesModel coordinateaModel, DBStylistModel dBStylistModel, String range) {
		double distance = DistanceCalculator.getDistance(Double.parseDouble(coordinateaModel.getLattitude()), Double.parseDouble(coordinateaModel.getLongitude()),
				Double.parseDouble(dBStylistModel.getLattitude()), Double.parseDouble(dBStylistModel.getLongitude()), "K");
		return distance <= Double.parseDouble(range);
	}

	public static List<DBStylistModel> sortStylistListByRange(List<DBStylistModel> stylistsList) {

		Collections.sort(stylistsList, new Comparator<DBStylistModel>() {

			public int compare(DBStylistModel stylist1, DBStylistModel stylist2) {
				double delta = Double.parseDouble(stylist1.getDistanceFromCoordinates()) - Double.parseDouble(stylist2.getDistanceFromCoordinates());
				if (delta > 0)
					return 1;
				if (delta < 0)
					return -1;
				return 0;
			}
		});

		return stylistsList;
	}

	/**
	 * Returns the compatible stylists ordered by distance.If more than 5
	 * stylists are found,the top five are taken.If no stylist is found in
	 * range,the closest stylist is taken
	 * 
	 * @param coordinatesModel
	 * @param stylistsList
	 * @param range
	 * @return compatibleStylistsInRange
	 */
	public static List<DBStylistModel> getFirstFiveStylistInRangeOrClosest(CoordinatesModel coordinatesModel, List<DBStylistModel> stylistsList, String range) {

		stylistsList = sortStylistListByRange(stylistsList);

		List<DBStylistModel> compatibleStylists = new ArrayList<DBStylistModel>();

		for (DBStylistModel stylist : stylistsList) {
			if (isStylistInRange(coordinatesModel, stylist, range)) {
				compatibleStylists.add(stylist);
			}
		}
		if (compatibleStylists.size() > 5) {
			compatibleStylists = compatibleStylists.subList(0, 5);

		} else if (compatibleStylists.size() == 0) {
			compatibleStylists.add(stylistsList.get(0));

		}

		return compatibleStylists;
	}

	/**
	 * Returns 5 stylecoaces if more than 5 are found. If less than five are
	 * found,all the stylecoaches will be returned. If more than five are found,
	 * each age category will have 1 stylecoach if there is a stylecoach for
	 * that category,the rest of available places will be filled in with
	 * compatible stylecoaches in range (random)
	 * 
	 * @param coordinatesModel
	 * @param stylistsList
	 * @param range
	 * @return compatibleStylistsInRange
	 */

	public static List<DBStylistModel> getFiveStylistsFromListForeachAgeCategoryIfExist(CoordinatesModel coordinatesModel, List<DBStylistModel> stylistsList, String range) {

		stylistsList = sortStylistListByRange(stylistsList);

		List<DBStylistModel> category30Age = new ArrayList<DBStylistModel>();
		List<DBStylistModel> category45Age = new ArrayList<DBStylistModel>();
		List<DBStylistModel> category100Age = new ArrayList<DBStylistModel>();

		List<DBStylistModel> compatibleStylists = new ArrayList<DBStylistModel>();

		for (DBStylistModel stylist : stylistsList) {

			if (isStylistInRange(coordinatesModel, stylist, range)) {

				if (calculateStylistAge(stylist) <= 30) {
					category30Age.add(stylist);

				} else if (calculateStylistAge(stylist) > 30 && calculateStylistAge(stylist) <= 45) {
					category45Age.add(stylist);

				} else if (calculateStylistAge(stylist) > 45 && calculateStylistAge(stylist) <= 100) {
					category100Age.add(stylist);
				}

				compatibleStylists.add(stylist);
			}
		}
		if (compatibleStylists.size() > 5) {

			compatibleStylists.clear();

			while (compatibleStylists.size() < 5) {

				if (category30Age.size() > 0) {
					compatibleStylists.add(category30Age.get(0));
					category30Age.remove(0);
				}
				if (category45Age.size() > 0) {
					compatibleStylists.add(category45Age.get(0));
					category30Age.remove(0);
				}
				if (category100Age.size() > 0) {
					compatibleStylists.add(category100Age.get(0));
					category30Age.remove(0);
				}
				// this is because we can reach the 5 elements in the first if
				// and until we enter in while loop,we can have 6 or 7
				// elements
				compatibleStylists.subList(0, 5);
			}

		} else if (compatibleStylists.size() == 0) {

			compatibleStylists.add(stylistsList.get(0));

		}

		return compatibleStylists;
	}

	public static String calculateDistanceFromCustomersCoordinates(CoordinatesModel coordinateaModel, DBStylistModel dBStylistModel) {
		double distance = DistanceCalculator.getDistance(Double.parseDouble(coordinateaModel.getLattitude()), Double.parseDouble(coordinateaModel.getLongitude()),
				Double.parseDouble(dBStylistModel.getLattitude()), Double.parseDouble(dBStylistModel.getLongitude()), "K");

		return String.valueOf(distance);
	}

	public static int calculateStylistAge(DBStylistModel stylist) {
		return DateUtils.getAge(stylist.getBirthDate());
	}

	public static void main(String[] args) {
		SyncInfoModel syncInfoModel = ApiCalls.getMagProductInfo("4835");
		System.out.println(syncInfoModel.getQuantity());
	}

}

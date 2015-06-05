package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.env.constants.Separators;
import com.tools.geolocation.DistanceCalculator;
import com.tools.persistance.MongoReader;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;
import com.tools.utils.FormatterUtils;

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
		System.out.println("---------------------------------");
		System.out.println("Stores: " + tempStore);
		System.out.println("---------------------------------");

		if (!tempStore.isEmpty() && tempStore != null) {
			String strSplitter[] = tempStore.split(Separators.COMMA_SEPARATOR);
			for (String string : strSplitter) {
				webSiteIds.add(string);
			}
		}

		// webSiteIds.add("1");
		// webSiteIds.add("0");
		// webSiteIds.add("2");
		product.setWebsiteIdsArray(webSiteIds);

		List<String> categoriesIds = new ArrayList<String>();
		categoriesIds.add("43");
		categoriesIds.add("5");
		product.setCategoryIdsArray(categoriesIds);

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

		return product;

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

	public static String getStylistInfo(String id) {

		// List<CustomerFormModel> stylistList = new
		// ArrayList<CustomerFormModel>();

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stylistList;
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

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_firstname")) {
					model.setFirstName(childNodes.item(j).getTextContent());
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
			}
			stylistModelList.add(model);
		}
		return stylistModelList;

	}

	public static List<DBStylistModel> getCompatibleStylistsInRangeFromList(CoordinatesModel coordinateaModel, String range, String filter, String operand, String filterValue,
			int mode) {

		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();
		for (DBStylistModel dbStylistModel : getStylistList(filter, operand, filterValue)) {
			switch (mode) {

			case 1:
				if (!isStylistIncompatibleForCustomerRetrieval(dbStylistModel) && isStylistInRange(coordinateaModel, dbStylistModel, range)) {
					compatibleList.add(dbStylistModel);
					break;
				}

			case 2:
				if (!isStylistIncompatibleForHost(dbStylistModel) && isStylistInRange(coordinateaModel, dbStylistModel, range)) {
					compatibleList.add(dbStylistModel);
					break;
				}

			case 3:
				if (!isStylistIncompatibleForSCRetrieval(dbStylistModel) && isStylistInRange(coordinateaModel, dbStylistModel, range)) {
					compatibleList.add(dbStylistModel);
					break;
				}
			}
		}
		if (compatibleList.size() > 5) {
			compatibleList = compatibleList.subList(0, 5);
		}
		return compatibleList;
	}

	private static boolean isStylistIncompatibleForCustomerRetrieval(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedCustomer().contentEquals("0");
	}

	private static boolean isStylistIncompatibleForSCRetrieval(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedSC().contentEquals("0") || Integer.parseInt(stylistModel.getTotalSCCurrentWeek()) >= Integer.parseInt(stylistModel.getMaxSCPerWeek());
	}

	private static boolean isStylistIncompatibleForHost(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedHost().contentEquals("0");
	}

	public static boolean isStylistInRange(CoordinatesModel coordinateaModel, DBStylistModel dBStylistModel, String range) {
		double distance = DistanceCalculator.getDistance(Double.parseDouble(coordinateaModel.getLattitude()), Double.parseDouble(coordinateaModel.getLongitude()),
				Double.parseDouble(dBStylistModel.getLattitude()), Double.parseDouble(dBStylistModel.getLongitude()), "K");
		return distance <= Double.parseDouble(range);
	}

}

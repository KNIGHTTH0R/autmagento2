package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.env.constants.Separators;
import com.tools.persistance.MongoReader;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;
import com.tools.utils.FormatterUtils;

public class CreateProduct {

	public static void main(String args[]) throws Exception {
		createApiProduct(createProductModel());
	}

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

	public static List<DBStylistModel> getStylistList() {

		List<DBStylistModel> stylistList = new ArrayList<DBStylistModel>();

		String resultID = null;
		try {
			SOAPMessage response = HttpSoapConnector.soapGetStylistList();
			try {
				stylistList = extractStylistData(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("resultID: " + resultID);
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

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("stylist_id")) {
					model.setId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("status")) {
					model.setStatus(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_email")) {
					model.setEmail(childNodes.item(j).getTextContent());
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

			}
			stylistModelList.add(model);
		}

		return stylistModelList;

	}

	public static List<DBStylistModel> getActiveStylistsFromList(List<DBStylistModel> allStylistsList) {
		List<DBStylistModel> activeStylistsList = new ArrayList<DBStylistModel>();
		for (DBStylistModel stylist : allStylistsList) {
			if (stylist.getStatus().contentEquals("1")) {
				if (stylist.getStreet() != null || stylist.getPostCode() != null) {
					activeStylistsList.add(stylist);
				}
			}
		}
		return activeStylistsList;
	}

}

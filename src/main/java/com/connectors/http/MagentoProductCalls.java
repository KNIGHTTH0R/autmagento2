package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tools.constants.Separators;
import com.tools.data.navision.SyncInfoModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.persistance.MongoReader;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;
import com.tools.utils.FormatterUtils;

/**
 * @author mihaibarta
 *
 */

public class MagentoProductCalls {

	public static void main(String args[]) {
		SyncInfoModel model = MagentoProductCalls.getMagProductInfo("1292");
		System.out.println(model.getMinumimQuantity());
		System.out.println(model.getQuantity());
		System.out.println(model.getPendingQuantity());
	}

	public static ProductDetailedModel createPomProductModel() {
		ProductDetailedModel result = createProductModel();
		result.setName("POM_AUT_" + result.getName());
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
		stockModel.setEarliestAvailability(FormatterUtils.getCustomDate("yyyy-MM-dd", 86000));
		stockModel.setAllowedTermPurchase("0");
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
		String sku = FieldGenerators.generateRandomString(9, Mode.ALPHA_CAPS);
		ProductDetailedModel product = new ProductDetailedModel();

		product.setType("simple");
		product.setSet("4");
		product.setName(name);
		product.setDescription("description");
		product.setShortDescription("desc");
		product.setUrlPath(sku);
		product.setWeight("2");
		product.setSku(sku);
		product.setPrice(FieldGenerators.generateRandomString(2, Mode.NUMERIC));
		product.setStatus("1");
		product.setUrlKey(sku);
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

		String tempStore = MongoReader.getStoreIds();

		if (!tempStore.isEmpty() && tempStore != null) {
			String strSplitter[] = tempStore.split(Separators.COMMA_SEPARATOR);
			for (String string : strSplitter) {
				webSiteIds.add(string);
			}
		}

		product.setWebsiteIdsArray(webSiteIds);

		List<String> categoriesIds = new ArrayList<String>();
		/*
		 * categoriesIds.add("43"); categoriesIds.add("5");
		 * categoriesIds.add("5"); categoriesIds.add("5");
		 * categoriesIds.add("5");
		 */

		categoriesIds.add("5");
		categoriesIds.add("6");
		categoriesIds.add("8");
		categoriesIds.add("7");
		categoriesIds.add("43");
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
		stockModel.setAllowedTermPurchase("0");
		stockModel.setMaximumPercentageToBorrow("");
		stockModel.setUseConfigMaximumPercentageToBorrow("80");

		product.setStockData(stockModel);

		product.setJewerlyBonusValue("50.00");
		product.setJewelryBonusCart("3,4");

		return product;

	}

	public static ProductDetailedModel updateProductStockModel() {
		ProductDetailedModel product = new ProductDetailedModel();

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

	public static StockDataModel createNotAvailableYetStockData(String earliestAvailability) {

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
		stockModel.setEarliestAvailability(earliestAvailability);
		stockModel.setAllowedTermPurchase("0");
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

	public static String updateApiProduct(ProductDetailedModel product, String productId) {

		String resultID = null;
		try {
			SOAPMessage response = HttpSoapConnector.soapUpdateProduct(product, productId);
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

	public static String deleteApiProduct(String productId) {

		String resultID = null;
		try {
			SOAPMessage response = DeleteProduct.deleteProduct(productId);
			resultID = extractResult(response);
			System.out.println("result: " + resultID);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultID;
	}

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("result").item(0).getFirstChild().getNodeValue();
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
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("is_in_stock")) {
				result.setIsInStock(childNodes.item(j).getTextContent());
			}
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("min_qty")) {
				result.setMinumimQuantity(childNodes.item(j).getTextContent());
			}
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("earliest_availability")) {

				String[] parts = childNodes.item(j).getTextContent().split(" ");
				result.setEarliestAvailability(parts[0]);
			}
		}
		return result;

	}

}

package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.env.constants.Separators;
import com.tools.persistance.MongoReader;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FormatterUtils;
import com.tools.utils.FieldGenerators.Mode;

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

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("result").item(0).getFirstChild().getNodeValue();
	}

}

package com.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;

import com.connectors.http.HttpSoapConnector;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;

public class DoomTest {

	/**
	 * Sample to create a basic product through the API.
	 * @param args
	 * @throws SOAPException
	 * @throws IOException
	 */
	public static void main(String args[]) throws SOAPException, IOException{
		ProductDetailedModel product = new ProductDetailedModel();
		List<String> webSiteIds = new ArrayList<String>();
		List<String> categoriesIds = new ArrayList<String>();
		StockDataModel productStock = new StockDataModel();
		productStock.setQty("1000");
		productStock.setIsInStock("1");
		productStock.setManageStock("1");
		productStock.setUseConfigManageStock("0");
		productStock.setUseConfigMinQty("1");
		productStock.setUseConfigMinSaleQty("1");
		productStock.setIsQtyDecimal("0");
		productStock.setUseConfigBackorders("1");
		productStock.setUseConfigNotifyStockQty("1");
		productStock.setUseConfigMaximumPercentageToBorrow("80");
		productStock.setIsDiscontinued("0");
		
		product.setType("simple");
		product.setSet("4");
		product.setSku("qwe123");
		product.setName("doodle");
		product.setDescription("Doodle one");
		product.setShortDescription("one");
		product.setUrlPath("doodleOneOOne");
		product.setWeight("2");
		product.setStatus("1");
		product.setUrlKey("doodleOneOOne");
		product.setVisibility("4");
		product.setPrice("100");
		product.setTaxClassId("2");
		
		product.setStockData(productStock);
		product.setStore("0");
		
		webSiteIds.add("1");
		webSiteIds.add("0");
		webSiteIds.add("2");
		categoriesIds.add("43");
		categoriesIds.add("5");
		product.setWebsiteIdsArray(webSiteIds);
		product.setCategoryIdsArray(categoriesIds);
		
		SOAPMessage response = HttpSoapConnector.soapCreateProduct(product);
		System.out.println("OUT");
		response.writeTo(System.out);
	}
}

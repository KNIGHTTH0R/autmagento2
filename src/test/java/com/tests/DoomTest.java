package com.tests;

import java.io.IOException;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.jruby.RubyProcess.Sys;

import com.connectors.http.HttpSoapConnector;
import com.tools.Constants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;

public class DoomTest {

	
	public static void main(String args[]) throws SOAPException, IOException{
		ProductDetailedModel product = new ProductDetailedModel();
		List<String> webSiteIds = new ArrayList<String>();
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
		
		webSiteIds.add("1");
		product.setType("simple");
		product.setSet("4");
		product.setSku("MM2211554477");
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
		product.setWebsiteIdsArray(webSiteIds);
		product.setStockData(productStock);
		product.setStore("0");
		
//		System.setProperty("http.proxyHost", "localhost");
//		System.setProperty("http.proxyPort", "8888");
		
//		System.getProperties().put("proxySet", "true");
//		System.getProperties().put("https.proxyHost", "localhost");  
//		System.getProperties().put("https.proxyPort", 8888);
//		System.getProperties().put("http.proxyHost", "localhost");  
//		System.getProperties().put("http.proxyPort", 8888);
		
		
		//TODO work on this
		System.setProperty("javax.net.ssl.keyStore", Constants.RESOURCES_PATH + "keystore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "");
		System.setProperty("javax.net.ssl.keyStoreType", "JKS");
		
		SOAPMessage response = HttpSoapConnector.soapCreateProduct(product);

		
		System.out.println("OUT");
		response.writeTo(System.out);

	}
}

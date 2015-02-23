package com.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.connectors.http.HttpSoapConnector;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.utils.RandomGenerators;

public class CreateProduct {	


	public static void createProduct(String sku, String name, String price) {

		ProductDetailedModel product = new ProductDetailedModel();
		List<String> webSiteIds = new ArrayList<String>();
		List<String> categoriesIds = new ArrayList<String>();

		webSiteIds.add("1");
		webSiteIds.add("0");
		webSiteIds.add("2");
		categoriesIds.add("43");
		categoriesIds.add("5");
		product.setSku(sku);
		product.setName(name);
		product.setPrice(price);
		product.setWebsiteIdsArray(webSiteIds);
		product.setCategoryIdsArray(categoriesIds);

		try {
			SOAPMessage response = HttpSoapConnector.soapCreateProduct(product);
			System.out.println("OUT");
			response.writeTo(System.out);
		} catch (SOAPException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}


}

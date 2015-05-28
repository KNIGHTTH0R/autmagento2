package com.poc;

import com.connectors.http.ApiCalls;
import com.tools.data.soap.ProductDetailedModel;

public class CreateProductTest {

	
	public static void main(String args[]) throws Exception{
		ProductDetailedModel genProduct1 = ApiCalls.createProductModel();
		genProduct1.setPrice("42.90");
		ApiCalls.createApiProduct(genProduct1);
	}
}

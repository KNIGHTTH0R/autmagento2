package com.poc;

import com.connectors.http.MagentoProductCalls;
import com.tools.data.soap.ProductDetailedModel;

public class CreateProductTest {

	
	public static void main(String args[]) throws Exception{
		ProductDetailedModel genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("42.90");
		MagentoProductCalls.createApiProduct(genProduct1);
	}
}

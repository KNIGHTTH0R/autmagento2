package com.poc;

import com.connectors.http.CreateProduct;
import com.tools.data.soap.ProductDetailedModel;

public class CreateProductTest {

	
	public static void main(String args[]) throws Exception{
		ProductDetailedModel genProduct1 = CreateProduct.createProductModel();
		genProduct1.setPrice("31.36587");
		CreateProduct.createApiProduct(genProduct1);
	}
}

package com.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.tools.constants.SoapKeys;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.AddProductsWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class CreateProductsTestRegularCart extends BaseTest {

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	private ProductDetailedModel genProduct4 = new ProductDetailedModel();
	private ProductDetailedModel genProduct5 = new ProductDetailedModel();
	private ProductDetailedModel genProduct6 = new ProductDetailedModel();
	private ProductDetailedModel genProduct7 = new ProductDetailedModel();
	private ProductDetailedModel genProduct8 = new ProductDetailedModel();
	private ProductDetailedModel genProduct9 = new ProductDetailedModel();
	private ProductDetailedModel genProduct10 = new ProductDetailedModel();
	
	String voucherValue;

	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	public static List<ProductDetailedModel> productsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void createProductsTest() {
		// 1
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);
		productsList.add(genProduct1);

		// 27
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct2);
		productsList.add(genProduct2);

		// 6
		// product for REGULAR CART - us8001
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("10.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		productsList.add(genProduct3);

		// 7
		genProduct4 = MagentoProductCalls.createPomProductModel();
		genProduct4.setPrice("49.90");
		voucherValue = genProduct8.getPrice();
		MagentoProductCalls.createApiProduct(genProduct4);
		productsList.add(genProduct4);

		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("5.00");
		MagentoProductCalls.createApiProduct(genProduct5);
		productsList.add(genProduct5);

		//8
		// products with TP for regular orders - us8007
		genProduct6 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct6.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct6);
		productsList.add(genProduct6);


		//9
		genProduct7 = MagentoProductCalls.createProductModel();
		genProduct7.setPrice("5.00");
		genProduct7.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct7);
		productsList.add(genProduct7);

		genProduct8 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct8.setPrice("29.00");
		genProduct8.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct8);
		productsList.add(genProduct8);
		
		genProduct9 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct9.setPrice("19.90");
		genProduct9.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct9);
		productsList.add(genProduct9);
		
		genProduct10 = MagentoProductCalls.createProductModel();
		genProduct10.setPrice("50.00");
		genProduct10.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct10);
		productsList.add(genProduct10);
		
		
		

	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

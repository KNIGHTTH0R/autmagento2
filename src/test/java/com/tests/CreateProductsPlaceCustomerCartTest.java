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
public class CreateProductsPlaceCustomerCartTest extends BaseTest {

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	private ProductDetailedModel genProduct4 = new ProductDetailedModel();
	private ProductDetailedModel genProduct5 = new ProductDetailedModel();
	private ProductDetailedModel genProduct6 = new ProductDetailedModel();
	private ProductDetailedModel genProduct7 = new ProductDetailedModel();
	private ProductDetailedModel genProduct8 = new ProductDetailedModel();
	private ProductDetailedModel genProduct9 = new ProductDetailedModel();
	
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
		// products for Shop for myself cart- us3001
		// and regular cart-us8001, us8002,us8003,us8004

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct1);
		productsList.add(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setIp("25");
		genProduct2.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct2);
		productsList.add(genProduct2);

		// 3
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setIp("25");
		genProduct3.setPrice("29.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		productsList.add(genProduct3);

		// 6
		// product for REGULAR CART - us8001
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("10.00");
		genProduct4.setIp("8");
		MagentoProductCalls.createApiProduct(genProduct4);
		productsList.add(genProduct4);

		// 14
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("29.90");
		genProduct5.setIp("25");
		MagentoProductCalls.createApiProduct(genProduct5);
		productsList.add(genProduct5);

		// 15
		genProduct6 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct6.setPrice("29.00");
		genProduct6.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct6);
		productsList.add(genProduct6);

		// 16
		genProduct7 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct7.setPrice("9.90");
		genProduct7.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct7);
		productsList.add(genProduct7);

		//11
		// products with TP for regular cart - us8009
		genProduct8 = MagentoProductCalls.createProductModel();
		genProduct8.setPrice("29.90");
		genProduct8.setIp("25");
		genProduct8.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct8);
		productsList.add(genProduct8);

		//12
		genProduct9 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct9.setPrice("10.00");
		genProduct9.setIp("8");
		MagentoProductCalls.createApiProduct(genProduct9);
		productsList.add(genProduct9);

	
	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

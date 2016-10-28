package com.tests;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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

@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class CreateProductsTest extends BaseTest {

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
	private ProductDetailedModel genProduct11 = new ProductDetailedModel();
	private ProductDetailedModel genProduct12 = new ProductDetailedModel();
	private ProductDetailedModel genProduct13 = new ProductDetailedModel();
	private ProductDetailedModel genProduct14 = new ProductDetailedModel();
	private ProductDetailedModel genProduct15 = new ProductDetailedModel();
	private ProductDetailedModel genProduct16 = new ProductDetailedModel();
	private ProductDetailedModel genProduct17 = new ProductDetailedModel();
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
		//and regular cart-us8001, us8002,us8003,us8004
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setIp("84");
		genProduct1.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct1);
		productsList.add(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setIp("25");
		genProduct2.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct2);
		productsList.add(genProduct2);

		genProduct3 = MagentoProductCalls.createMarketingProductModel();
		genProduct3.setIp("0");
		genProduct3.setPrice("229.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		productsList.add(genProduct3);
		
		//products for shop for myself cart - us3005
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setIp("25");
		genProduct4.setPrice("29.90");
		MagentoProductCalls.createApiProduct(genProduct4);
		productsList.add(genProduct4);
		
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setIp("60");
		genProduct5.setPrice("34.90");
		MagentoProductCalls.createApiProduct(genProduct5);
		productsList.add(genProduct5);
		
		genProduct6 = MagentoProductCalls.createMarketingProductModel();
		genProduct6.setPrice("5.00");
		MagentoProductCalls.createApiProduct(genProduct6);
		productsList.add(genProduct6);
		
		//product for REGULAR CART - us8001
		genProduct7 = MagentoProductCalls.createProductModel();
		genProduct7.setPrice("10.00");
		genProduct7.setIp("8");
		MagentoProductCalls.createApiProduct(genProduct7);
		productsList.add(genProduct7);
		
		genProduct8 = MagentoProductCalls.createPomProductModel();
		genProduct8.setPrice("49.90");
		voucherValue = genProduct8.getPrice();
		MagentoProductCalls.createApiProduct(genProduct8);
		productsList.add(genProduct8);
		
		//products with TP for regular orders - us8007
		genProduct9 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct9.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct9);
		productsList.add(genProduct9);

		genProduct10 = MagentoProductCalls.createProductModel();
		genProduct10.setPrice("5.00");
		genProduct10.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct10);
		productsList.add(genProduct10);

		genProduct11 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct11.setPrice("19.90");
		genProduct11.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct11);
		productsList.add(genProduct11);
		
		//products with TP for regular cart - us8009
		genProduct12 = MagentoProductCalls.createProductModel();
		genProduct12.setPrice("50.00");
		genProduct12.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct12);
		productsList.add(genProduct12);
		
		genProduct13 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct13.setPrice("10.00");
		genProduct13.setIp("8");
		MagentoProductCalls.createApiProduct(genProduct13);
		productsList.add(genProduct13);
		
		genProduct14 = MagentoProductCalls.createProductModel();
		genProduct14.setPrice("5.00");
		MagentoProductCalls.createApiProduct(genProduct14);
		productsList.add(genProduct14);
		
		genProduct15 = MagentoProductCalls.createProductModel();
		genProduct15.setPrice("29.90");
		genProduct15.setIp("25");
		MagentoProductCalls.createApiProduct(genProduct15);
		productsList.add(genProduct15);
		
		genProduct16 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct16.setPrice("29.00");
		genProduct16.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct16);
		productsList.add(genProduct16);

		genProduct17 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct17.setPrice("9.90");
		genProduct17.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct17);
		productsList.add(genProduct17);
		
		System.out.println("size " + productsList.size());
		System.out.println(productsList.get(0));
		
	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

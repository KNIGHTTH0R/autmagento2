package com.tests;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.joda.time.Interval;
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
public class CreateProductsSFMTest extends BaseTest {

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
		genProduct1.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct1);
		productsList.add(genProduct1);

		//1
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setIp("25");
		genProduct2.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct2);
		productsList.add(genProduct2);
		
		//2
		genProduct3 = MagentoProductCalls.createMarketingProductModel();
		genProduct3.setIp("0");
		genProduct3.setPrice("229.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		productsList.add(genProduct3);
		
		//19
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("49.90");
	//	genProduct1.setSpecialPrice("40.00");
		genProduct4.setIp("84");
		MagentoProductCalls.createApiProduct(genProduct4);
		productsList.add(genProduct4);

		//20
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct5);
		productsList.add(genProduct5);
		
		//21
		genProduct6 = MagentoProductCalls.createProductModel();
		genProduct6.setPrice("49.50");
		MagentoProductCalls.createApiProduct(genProduct6);
		productsList.add(genProduct6);

		//22
		genProduct7 = MagentoProductCalls.createProductModel();
		genProduct7.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct7);
		productsList.add(genProduct7);
		
		//25
		genProduct8 = MagentoProductCalls.createProductModel();
		genProduct8.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct8);
		productsList.add(genProduct8);
		
		//26
		genProduct9 = MagentoProductCalls.createProductModel();
		genProduct9.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct9);
		productsList.add(genProduct9);
		
		
		//27
		genProduct10 = MagentoProductCalls.createProductModel();
		genProduct10.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct10);
		productsList.add(genProduct10);

		

		
		//28
		genProduct11 = MagentoProductCalls.createProductModel();
		genProduct11.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct11);
		productsList.add(genProduct1);
		
		//29
		genProduct12 = MagentoProductCalls.createProductModel();
		genProduct12.setPrice("49.00");
		MagentoProductCalls.createApiProduct(genProduct12);
		productsList.add(genProduct12);
		
		//30
		genProduct13 = MagentoProductCalls.createProductModel();
		genProduct13.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct13);
		productsList.add(genProduct13);
		
		
	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

	}


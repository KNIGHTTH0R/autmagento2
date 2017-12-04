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
public class CreateProductsTestHostCart extends BaseTest {

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
		
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);
		productsList.add(genProduct1);
		
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct2);
		productsList.add(genProduct2);
		
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("100.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		productsList.add(genProduct3);
		
		
		//3
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setIp("25");
		genProduct4.setPrice("29.00");
		MagentoProductCalls.createApiProduct(genProduct4);
		productsList.add(genProduct4);
		
		//5
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("5.00");
		MagentoProductCalls.createApiProduct(genProduct5);
		productsList.add(genProduct5);
		
		
		//6
		genProduct6 = MagentoProductCalls.createProductModel();
		genProduct6.setPrice("10.00");
		genProduct6.setIp("8");
		MagentoProductCalls.createApiProduct(genProduct6);
		productsList.add(genProduct6);
		
		//19
		genProduct7 = MagentoProductCalls.createProductModel();
		genProduct7.setPrice("49.90");
		genProduct7.setIp("84");
		MagentoProductCalls.createApiProduct(genProduct7);
		productsList.add(genProduct7);
		
		
		genProduct8 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct8.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct8);
		productsList.add(genProduct8);
		
		
		genProduct9 = MagentoProductCalls.createProductModel();
		genProduct9.setPrice("100.00");
		genProduct9.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct9);
		productsList.add(genProduct9);
		
		
		genProduct10 =  MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct10.getStockData().setAllowedTermPurchase("1");
		genProduct10.setPrice("50.00");
		genProduct10.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct10);
		productsList.add(genProduct10);
		
		
		genProduct11 = MagentoProductCalls.createProductModel();
		genProduct11.setPrice("29.90");
		genProduct11.setIp("25");
		genProduct11.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct11);
		productsList.add(genProduct11);
		
		
		genProduct12 = MagentoProductCalls.createProductModel();
		genProduct12.setPrice("89.00");
		genProduct12.setSpecialPrice("55.00");
		MagentoProductCalls.createApiProduct(genProduct12);
		genProduct12.setPrice(genProduct12.getSpecialPrice());
		productsList.add(genProduct12);
		
		genProduct13 = MagentoProductCalls.createProductModel();
		genProduct13.setPrice("19.00");
		MagentoProductCalls.createApiProduct(genProduct13);
		productsList.add(genProduct13);
		
		genProduct14 = MagentoProductCalls.createProductModel();
		genProduct14.setPrice("24.90");
		MagentoProductCalls.createApiProduct(genProduct14);
		productsList.add(genProduct14);

	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

	}


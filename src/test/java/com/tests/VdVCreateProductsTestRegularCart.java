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
public class VdVCreateProductsTestRegularCart extends BaseTest {

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
		
		////vdv
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("33.95");
		genProduct1.setSku("0541220PCS38");
		genProduct1.setColor("peachy skin");
		genProduct1.setProductSize("38");
		genProduct1.setIp("29");
		genProduct1.setParentProductSku("primadonna-twist-happiness-slip-0541220");
		productsList.add(genProduct1);
		
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("33.95");
		genProduct2.setSku("0541220PCS40");
		genProduct2.setColor("peachy skin");
		genProduct2.setProductSize("40");
		genProduct2.setIp("29");
		genProduct2.setParentProductSku("primadonna-twist-happiness-slip-0541220");
		productsList.add(genProduct2);
		
		
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("33.95");
		genProduct3.setSku("0541220PCS42");
		genProduct3.setIp("29");
		genProduct3.setColor("peachy skin");
		genProduct3.setProductSize("42");
		genProduct3.setParentProductSku("primadonna-twist-happiness-slip-0541220");
		productsList.add(genProduct3);
		
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("33.95");
		genProduct4.setSku("0541220PCS44");
		genProduct4.setColor("peachy skin");
		genProduct4.setProductSize("44");
		genProduct4.setIp("29");
		genProduct4.setParentProductSku("primadonna-twist-happiness-slip-0541220");
		productsList.add(genProduct4);
		
		
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("33.95");
		genProduct5.setSku("0541220PCS46");
		genProduct5.setColor("peachy skin");
		genProduct5.setProductSize("46");
		genProduct5.setIp("29");
		genProduct5.setParentProductSku("primadonna-twist-happiness-slip-0541220");
		productsList.add(genProduct5);
		
		
		genProduct6 = MagentoProductCalls.createProductModel();
		genProduct6.setPrice("33.95");
		genProduct6.setSku("0541220PCS48");
		genProduct6.setColor("peachy skin");
		genProduct6.setProductSize("48");
		genProduct6.setIp("29");
		genProduct6.setParentProductSku("primadonna-twist-happiness-slip-0541220");
		productsList.add(genProduct6);
		
		
		///vdv
		
		
		

	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

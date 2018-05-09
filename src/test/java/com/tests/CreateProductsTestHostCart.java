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

@WithTag(name = "Create Products for Host", type = "Scenarios")
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
		genProduct1.setPrice("89.95 ");
		genProduct1.setIp("76");
		genProduct1.setSku("0101337NATA080");
		genProduct1.setName("Marie Jo JANE Push-up natur 80A");
		genProduct1.setColor("natur");
		genProduct1.setProductSize("80A");
		genProduct1.setParentProductSku("marie-jo-jane-push-up-0101337");
		productsList.add(genProduct1);
		
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("89.95");
		genProduct2.setName("Marie Jo JANE Push-up schwarz 75A");
		genProduct2.setIp("76");
		genProduct2.setSku("0101337ZWAA075");
		genProduct2.setColor("schwarz");
		genProduct2.setProductSize("75A");
		genProduct2.setParentProductSku("marie-jo-jane-push-up-0101337");
		productsList.add(genProduct2);
		
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("89.95");
		genProduct3.setName("Marie Jo JANE Push-up brazilian garden 80C");
		genProduct3.setIp("76");
		genProduct3.setSku("0101337BGAC080");
		genProduct3.setColor("brazilian garden");
		genProduct3.setProductSize("80C");
		genProduct3.setParentProductSku("marie-jo-jane-push-up-0101337");
		productsList.add(genProduct3);
		
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("27.95");
		genProduct4.setIp("23");
		genProduct4.setSku("0662650VEN38");
		genProduct4.setName("PrimaDonna DIVINE String venus 38");
		genProduct4.setColor("venus");
		genProduct4.setProductSize("38");
		genProduct4.setParentProductSku("primadonna-divine-string-0662650");
		productsList.add(genProduct4);
		
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("89.90");
		genProduct5.setName("PrimaDonna DIVINE Trägerloser BH schwarz 80E");
		genProduct5.setIp("76");
		genProduct5.setSku("0262659ZWAE080");
		genProduct5.setColor("schwarz");
		genProduct5.setProductSize("80E");
		genProduct5.setParentProductSku("primadonna-divine-tragerloser-bh-0262659");
		productsList.add(genProduct5);

	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

	}


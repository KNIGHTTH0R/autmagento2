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
import com.workflows.frontend.AddProductsWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "Create products for SFM", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class CreateProductsSFMTest extends BaseTest {

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();


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

		//// vdv
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("27.95");
		genProduct1.setIp("23");
		genProduct1.setSku("0662650VEN38");
		genProduct1.setName("PrimaDonna DIVINE String venus 38");
		genProduct1.setColor("venus");
		genProduct1.setProductSize("38");
		genProduct1.setParentProductSku("primadonna-divine-string-0662650");
		productsList.add(genProduct1);
		
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("89.95");
		genProduct2.setName("PrimaDonna DIVINE Trägerloser BH schwarz 80E");
		genProduct2.setIp("76");
		genProduct2.setSku("0262659ZWAE080");
		genProduct2.setColor("schwarz");
		genProduct2.setProductSize("80E");
		genProduct2.setParentProductSku("primadonna-divine-tragerloser-bh-0262659");
		productsList.add(genProduct2);
		
		

	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

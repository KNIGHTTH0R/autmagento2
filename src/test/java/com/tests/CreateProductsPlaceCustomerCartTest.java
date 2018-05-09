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

@WithTag(name = "Create Products for Place Customer", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class CreateProductsPlaceCustomerCartTest extends BaseTest {

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	
	
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
		//// vdv
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("85.95");
		genProduct1.setIp("72");
		genProduct1.setSku("0101338ZWAB085");
		genProduct1.setName("Marie Jo JANE Trägerloser BH natur 85B");
		genProduct1.setColor("natur");
		genProduct1.setProductSize("85B");
		genProduct1.setParentProductSku("marie-jo-jane-tragerloser-bh-0101338");
		productsList.add(genProduct1);
		
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("85.95");
		genProduct2.setName("Marie Jo JANE Trägerloser BH schwarz 75B");
		genProduct2.setIp("72");
		genProduct2.setSku("0101338ZWAB075");
		genProduct2.setColor("schwarz");
		genProduct2.setProductSize("75B");
		genProduct2.setParentProductSku("marie-jo-jane-tragerloser-bh-0101338");
		productsList.add(genProduct2);
		
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("131.95 ");
		genProduct3.setName("PrimaDonna PERLE Shapewear Body caffé latte 85C");
		genProduct3.setIp("111");
		genProduct3.setSku("0462342CALC085");
		genProduct3.setColor("caffé latte");
		genProduct3.setProductSize("85C");
		genProduct3.setParentProductSku("primadonna-perle-shapewear-body-0462342");
		productsList.add(genProduct3);

		
	
	}

	@After
	public void saveData() {
		for (ProductDetailedModel product : productsList) {
			MongoWriter.saveProductDetailedModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

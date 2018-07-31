package com.tests.uss26.uss26002;

import java.io.IOException;
import java.text.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.products.BackendProductDetailsSteps;
import com.steps.backend.products.BackendProductListSteps;
import com.steps.frontend.UserRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.SoapKeys;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US26.2 Create products used in mobile version(desktop) of availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_2.class)
@RunWith(SerenityRunner.class)
public class US26002CreateProductsForAvListTest extends BaseTest {

	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BackendProductDetailsSteps backProductSteps;
	@Steps
	public BackendProductListSteps backProductListSteps;

//	private String stylistUsername, stylistPassword;
	private ProductDetailedModel genProduct1, genProduct2, genProduct3, genProduct4;

	StockDataModel stockData;
	
	String incrementIdProd4;
	@Before
	public void setUp() throws Exception {


		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setName("AV_REPORT_AUT_" + genProduct1.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd"));
		stockData.setMinQty("-10");
		stockData.setIsInStock("1");
		stockData.setIsDiscontinued("0");
		genProduct1.setStockData(stockData);
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setName("AV_REPORT_AUT_" + genProduct2.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData("");
		stockData.setQty("-5");
		stockData.setMinQty("-10");
		stockData.setIsInStock("0");
		stockData.setIsDiscontinued("0");
		genProduct2.setStockData(stockData);
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setName("AV_REPORT_AUT_" + genProduct3.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData("");
		stockData.setQty("-5");
		stockData.setMinQty("-10");
		stockData.setIsInStock("0");
		stockData.setIsDiscontinued("1");
		genProduct3.setStockData(stockData);
		MagentoProductCalls.createApiProduct(genProduct3);

		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setName("AV_REPORT_AUT_" + genProduct4.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData("");
		stockData.setQty("5");
		stockData.setMinQty("-10");
		stockData.setIsInStock("0");
		stockData.setIsDiscontinued("0");
		genProduct4.setStockData(stockData);
		incrementIdProd4=MagentoProductCalls.createApiProduct(genProduct4);
		
		stockData.setIsInStock("0");
		genProduct4.setStockData(stockData);
		MagentoProductCalls.updateApiProduct(genProduct4, incrementIdProd4);
		
		stockData.setIsInStock("1");
		genProduct4.setStockData(stockData);
		MagentoProductCalls.updateApiProduct(genProduct4, incrementIdProd4);
		
		stockData.setIsInStock("0");
		genProduct4.setStockData(stockData);
		MagentoProductCalls.updateApiProduct(genProduct4, incrementIdProd4);
		
		stockData.setIsInStock("1");
		genProduct4.setStockData(stockData);
		MagentoProductCalls.updateApiProduct(genProduct4, incrementIdProd4);
		

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us26002CreateProductsForAvListTest() throws IOException, ParseException {
		
		
		backEndSteps.performAdminLogin("oana.axente", "Camelia.90");
		backEndSteps.clickOnProducts();
		backProductListSteps.findProduct(genProduct1.getSku());
		backProductListSteps.openProductDetails(genProduct1.getSku());
		backProductSteps.addCatalogPage("5");
		backProductSteps.addCatalogRow("5");
		backProductSteps.saveAndContinue();

		backEndSteps.goToHomePage();
		backEndSteps.clickOnProducts();
		backProductListSteps.findProduct(genProduct2.getSku());
		backProductListSteps.openProductDetails(genProduct2.getSku());
		backProductSteps.addCatalogPage("5");
		backProductSteps.saveAndContinue();

		backEndSteps.goToHomePage();
		backEndSteps.clickOnProducts();
		backProductListSteps.findProduct(genProduct3.getSku());
		backProductListSteps.openProductDetails(genProduct3.getSku());
		backProductSteps.addCatalogPage("5");
		backProductSteps.saveAndContinue();
		
		
	
	}

	@After
	public void saveData() {

		MongoWriter.saveProductDetailedModel(genProduct1, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveProductDetailedModel(genProduct2, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveProductDetailedModel(genProduct3, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveProductDetailedModel(genProduct4, getClass().getSimpleName() + SoapKeys.GRAB);

	}
}

package com.tests.uss26.uss26002;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.AvailabilityReportSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.generalCalculation.StockAvailabilityCalculations;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US26.2 Verify products in mobile version(desktop) of availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_2.class)
@RunWith(SerenityRunner.class)
public class US26002ValidateProductsOnStockAvailabilityListTest extends BaseTest {

	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public AvailabilityReportSteps availabilitySteps;
	@Steps
	public CustomVerification customVerification;

	List<String> expectedCategories;
	List<ProductDetailedModel> grabTemporlyNotAvailableProducts = new ArrayList<ProductDetailedModel>();
	List<ProductDetailedModel> grabNotLogngerAvailableProduct = new ArrayList<ProductDetailedModel>();
	List<ProductDetailedModel> grabLessThanTwentyAvailableProducts = new ArrayList<ProductDetailedModel>();
	List<ProductDetailedModel> grabBackInStockProducts = new ArrayList<ProductDetailedModel>();
	List<ProductDetailedModel> grabStockReportProducts = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {

		StockAvailabilityCalculations.calculateStockAvailabilityData("1", "10000");

	}

	@Test
	public void us26002ValidateProductsOnAvailabilityListTest() throws IOException, ParseException {

		headerSteps.navigate("https://staging.pippajean.com/stock/?store=de_lang_de");
		headerSteps.waitABit(8000);

		/*// tab 2
		availabilitySteps.clickOnLessThenXProductsTab();
		availabilitySteps.verifyIfLessThenXPoductsTabIsSelected();
		grabLessThanTwentyAvailableProducts = availabilitySteps.grabLessThanTwentyAvailableProducts();*/
		// availabilitySteps.reloadReports();

	/*	headerSteps.refresh();
		// tab 1
		availabilitySteps.clickOnBackInStockTabProductsTab();
		// availabilitySteps.verifyIfBackInStockPoductsTabIsSelected();
		grabBackInStockProducts = availabilitySteps.grabBackInStockProducts();

		*/
		// tab 3
		availabilitySteps.clickOnTemporarlyNotAvailablePoductsTab();
		availabilitySteps.verifyIfTemporarlyNotAvailablePoductsTabIsSelected();
		grabTemporlyNotAvailableProducts = availabilitySteps.grabTemporlyNotAvailableProducts();
	/*	
		headerSteps.refresh();
		// tab4
		availabilitySteps.clickOnNotLongerAvailableProductsTab();
		// availabilitySteps.verifyIfNotLongerAvailablePoductsTabIsSelected();
		grabNotLogngerAvailableProduct = availabilitySteps.grabNotLogngerAvailableProduct();

		// all products tab
		headerSteps.refresh();
		grabStockReportProducts=availabilitySteps.grabStockReportProducts();
		// availabilitySteps.verifyIfStockPoductsTabIsSelected();
*/
		availabilitySteps.validateTemporarlyNotAvailablePoductsTab(
				StockAvailabilityCalculations.temporarlyNotAvailableProducts, grabTemporlyNotAvailableProducts);

		/*availabilitySteps.validateLessThanXProductsTab(StockAvailabilityCalculations.lessThanTwentyAvailableProducts,
				grabLessThanTwentyAvailableProducts);*/

	/*	availabilitySteps.validateBackInStockProductsTab(StockAvailabilityCalculations.backInStockProducts,
				grabBackInStockProducts);

		availabilitySteps.validateNotLongerAvailableProducts(StockAvailabilityCalculations.notLogngerAvailableProduct,
				grabNotLogngerAvailableProduct);

		availabilitySteps.validateStockReportProducts(StockAvailabilityCalculations.stockReportProducts,
				grabStockReportProducts);*/
		customVerification.printErrors();
	}
}

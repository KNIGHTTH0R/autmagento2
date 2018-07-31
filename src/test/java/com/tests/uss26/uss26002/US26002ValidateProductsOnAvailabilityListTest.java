package com.tests.uss26.uss26002;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.AvailabilityReportSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US26.2 Verify products in mobile version(desktop) of availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_2.class)
@RunWith(SerenityRunner.class)
public class US26002ValidateProductsOnAvailabilityListTest extends BaseTest {

	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public AvailabilityReportSteps availabilitySteps;

	List<String> expectedCategories;
	// private String stylistUsername, stylistPassword;
	List<ProductDetailedModel> productList = new ArrayList<ProductDetailedModel>();
	ProductDetailedModel product1 = new ProductDetailedModel();
	ProductDetailedModel product2 = new ProductDetailedModel();
	ProductDetailedModel product3 = new ProductDetailedModel();
	ProductDetailedModel product4 = new ProductDetailedModel();

	@Before
	public void setUp() throws Exception {
		productList = MongoReader.grabProductDetailedModel("US26002CreateProductsForAvListTest" + SoapKeys.GRAB);
		product1 = productList.get(0);
		product2 = productList.get(1);
		product3 = productList.get(2);
		product4 = productList.get(3);

		expectedCategories = Arrays.asList(ContextConstants.NECKLACES, ContextConstants.RINGS,
				ContextConstants.BRECELETES, ContextConstants.EARRINGS);
	}

	@Test
	public void us26002ValidateProductsOnAvailabilityListTest() throws IOException, ParseException {
		// frontEndSteps.performLogin(stylistUsername, stylistPassword);
		frontEndSteps.performLogin("irina.neagu@evozon.com", "irina1");
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnMobileVersionOfAvList();

		// validate temporarly not available tab
		availabilitySteps.clickOnTemporarlyNotAvailablePoductsTab();
		availabilitySteps.verifyIfTemporarlyNotAvailablePoductsTabIsSelected();
		availabilitySteps.reloadReports();
		availabilitySteps.verifyTpProductInTemporarlyNotAvailableTab(product1.getSku(),
				ContextConstants.CATALOG_PAGE + "5" + "," + ContextConstants.CATALOG_ROW + "5",
				product1.getStockData().getEarliestAvailability());
		availabilitySteps.verifyNoAsteriscNextToWillBeAnnouncedStatus(product2.getSku(),
				ContextConstants.CATALOG_P + "5", product2.getStockData().getEarliestAvailability());

		// validate not longer available tab
		availabilitySteps.clickOnNotLongerAvailableProductsTab();
		availabilitySteps.verifyIfNotLongerAvailablePoductsTabIsSelected();
		availabilitySteps.verifyProductInNotLongerAvailableTab(product3.getSku(), ContextConstants.CATALOG_P + "5");

		// validate back in stock tab
		availabilitySteps.clickOnBackInStockTabProductsTab();
		availabilitySteps.verifyIfBackInStockPoductsTabIsSelected();
		availabilitySteps.reloadReports();
		availabilitySteps.verifyProductInBackInStockTab(product4.getSku(), ContextConstants.CATALOG_P,
				expectedCategories);

		//availabilitySteps.verifyIfProductIsNotDisplayedinSelectedTab(product3.getSku());

	}
}

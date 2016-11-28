package com.steps.frontend.reports;

import java.text.ParseException;
import java.util.List;

import com.tools.data.soap.ProductDetailedModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class AvailabilityReportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickOnBackInStockTabProductsTab() {
		availabilityReportPage().clickOnBackInStockTabProducts();
	}

	@Step
	public void clickOnTemporarlyNotAvailablePoductsTab() {
		availabilityReportPage().clickOnTemporarlyNotAvailablePoducts();
	}

	@Step
	public void clickOnNotLongerAvailableProductsTab() {
		availabilityReportPage().clickOnNotLongerAvailableProducts();
	}

	@Step
	public void verifyIfBackInStockTabProductsTabIsSelected() {
		availabilityReportPage().verifyIfBackInStockTabProductsIsSelected();

	}

	@Step
	public void verifyIfTemporarlyNotAvailablePoductsTabIsSelected() {
		availabilityReportPage().verifyIfTemporarlyNotAvailablePoductsIsSelected();

	}

	@Step
	public void verifyIfBackInStockPoductsTabIsSelected() {
		availabilityReportPage().verifyIfBackInStockTabProductsIsSelected();

	}

	@Step
	public void verifyIfNotLongerAvailablePoductsTabIsSelected() {
		availabilityReportPage().verifyIfNotLongerAvailablePoductsTabIsSelected();

	}

	@Step
	public void verifyIfProductIsDisplayed(ProductDetailedModel model) {
		availabilityReportPage().verifyIfProductIsDisplayed(model);
	}

	@Step
	public void verifyProductInBackInStockTab(String sku, String catalogDetails, List<String> categoriesList) {
		availabilityReportPage().verifyProductInBackInStockTab(sku, catalogDetails, categoriesList);

	}

	@Step
	public void verifyTpProductInTemporarlyNotAvailableTab(String sku, String catalogDetails,
			String earliestAvailability) throws ParseException {
		availabilityReportPage().verifyTpProductInTemporarlyNotAvailableTab(sku, catalogDetails, earliestAvailability);

	}

	@Step
	public void verifyNoAsteriscNextToWillBeAnnouncedStatus(String sku, String catalogDetails,
			String earliestAvailability) throws ParseException {
		availabilityReportPage().verifyNoAsteriscNextToWillBeAnnouncedStatus(sku, catalogDetails, earliestAvailability);
	}

	@Step
	public void verifyProductInNotLongerAvailableTab(String sku, String catalogDetails) {
		availabilityReportPage().verifyProductInNotLongerAvailableTab(sku, catalogDetails);
	}

	@Step
	public void reloadReports() {
		availabilityReportPage().reloadReports();

	}

	public void verifyIfProductIsNotDisplayedinSelectedTab(String sku) {
		availabilityReportPage().verifyIfProductIsNotDisplayedinSelectedTab(sku);
		
	}

}

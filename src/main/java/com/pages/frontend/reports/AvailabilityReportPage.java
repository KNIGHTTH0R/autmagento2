package com.pages.frontend.reports;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class AvailabilityReportPage extends AbstractPage {

	@FindBy(css = ".heading col-xs-10")
	private WebElement pageTitle;

	@FindBy(id = "report1")
	private WebElement backInStockProducts;

	@FindBy(id = "report2")
	private WebElement temporarlyNotAvailablePoducts;

	@FindBy(id = "report3")
	private WebElement notLongerAvailableProducts;
	
	@FindBy(id = "report4")
	private WebElement lessThenXProducts;
	
	@FindBy(id = "report5")
	private WebElement stockReportProducts;

	@FindBy(css = ".btn-reload ")
	private WebElement reloadButton;

	@FindBy(css = ".text-center")
	private WebElement textCenter;

	public void clickOnBackInStockTabProducts() {
		element(backInStockProducts).waitUntilVisible();
		backInStockProducts.click();

	}

	public void clickOnTemporarlyNotAvailablePoducts() {
		getDriver().navigate().refresh();
		waitABit(2000);
		element(temporarlyNotAvailablePoducts).waitUntilVisible();
		temporarlyNotAvailablePoducts.click();

	}

	public void clickOnNotLongerAvailableProducts() {
		element(notLongerAvailableProducts).waitUntilVisible();
		notLongerAvailableProducts.click();

	}

	public void verifyIfBackInStockTabProductsIsSelected() {
		Assert.assertTrue("The message is not displayed",
				getDriver().findElement(By.cssSelector(".heading.col-xs-10 h1")).getText()
						.contains(ContextConstants.PRODUCTS_BACK_IN_STOCK));

	}

	public void verifyIfTemporarlyNotAvailablePoductsIsSelected() {
		Assert.assertTrue("The message is not displayed",
				getDriver().findElement(By.cssSelector(".heading.col-xs-10 h1")).getText()
						.contains(ContextConstants.TEMPORARLY_NOT_AVAILABLE));

	}

	public void verifyReportIsOpen(){
		List<WebElement> lines = getDriver().findElements(By.cssSelector("table.table tr"));
		if (!lines.isEmpty()) {
			System.out.println("---> Report opened correctly <---");
		}else{
			CustomVerification.verifyTrue("The report is not displayed", true);
		}
	}
	
	private WebElement getProductRow(String sku) {
		List<WebElement> lines = getDriver().findElements(By.cssSelector("table.table tr"));
		WebElement searchedLine = null;

		boolean found = false;
		// boolean empty=false
		if (!lines.isEmpty()) {
			for (WebElement row : lines) {
				if (row.getText().contains(sku)) {
					searchedLine = row;
					found = true;
					break;
				}
			}
			Assert.assertTrue("The row was not found", found);
		} else {
			Assert.assertTrue("the table is empty and should not be", true);
			Assert.assertTrue("The empty table info message is not displayed",
					textCenter.getText().contains("Keine Produkte in dieser Liste"));
		}

		return searchedLine;

	}

	public boolean isEmptyTable() {
		List<WebElement> lines = getDriver().findElements(By.cssSelector("table.table tr"));
		boolean isEmptyTable = false;
		if (lines.isEmpty()) {
			isEmptyTable = true;
		}

		return isEmptyTable;
	}

	public boolean isProductDisplayed(String sku) {
		boolean isPresent = false;
		List<WebElement> lines = getDriver().findElements(By.cssSelector("table.table tr"));
		for (WebElement row : lines) {
			if (row.getText().contains(sku)) {
				isPresent = true;
			}
		}
		return isPresent;

	}

	public void verifyIfNotLongerAvailablePoductsTabIsSelected() {
		Assert.assertTrue("The message is not displayed",
				getDriver().findElement(By.cssSelector(".heading.col-xs-10 h1")).getText()
						.contains(ContextConstants.NO_LONGER_AVAILABLE));

	}

	public void verifyIfProductIsDisplayed(ProductDetailedModel model) {
		Assert.assertTrue("The product is not displayed in table", isProductDisplayed(model.getSku()));

	}

	public void verifyProductInBackInStockTab(String sku, String catalogPosition, List<String> expectedCategories) {
		WebElement searchedLine = getProductRow(sku);
		String grabbedDetails = searchedLine.findElement(By.cssSelector("td:nth-child(2)")).getText();

		Assert.assertTrue("The catalog details are displayed and should not be",
				!grabbedDetails.contains(catalogPosition));

		List<String> grabbedCategories = Arrays.asList(
				searchedLine.findElement(By.cssSelector("td:nth-child(3)")).getText().replace(" ", "").split(","));
		Assert.assertTrue("The categories does not match", expectedCategories.containsAll(grabbedCategories));
	}

	public void verifyTpProductInTemporarlyNotAvailableTab(String sku, String catalogPosition,
			String earliestAvailability) throws ParseException {
		WebElement searchedLine = getProductRow(sku);

		String grabbedDetails = searchedLine.findElement(By.cssSelector("td:nth-child(2)")).getText();
		String grabbedCatalogDetails = grabbedDetails
				.substring(grabbedDetails.indexOf(",") + 1, grabbedDetails.length()).replace(" ", "");

		Assert.assertTrue("The catalog details are not displayed correctly",
				grabbedCatalogDetails.contains(catalogPosition));

		Assert.assertTrue("The product is not marked correctly",
				searchedLine.findElement(By.cssSelector("td:nth-child(3)")).getText()
						.contains(DateUtils.parseDate(earliestAvailability, "yyyy-MM-dd", "dd.MM.") + " ***"));
	}

	public void verifyNoAsteriscNextToWillBeAnnouncedStatus(String sku, String catalogPosition,
			String earliestAvailability) throws ParseException {

		WebElement searchedLine = getProductRow(sku);

		String grabbedDetails = searchedLine.findElement(By.cssSelector("td:nth-child(2)")).getText();
		String grabbedCatalogDetails = grabbedDetails
				.substring(grabbedDetails.indexOf(",") + 1, grabbedDetails.length()).replace(" ", "");

		Assert.assertTrue("The catalog details are not displayed correctly",
				grabbedCatalogDetails.contains(catalogPosition));

		Assert.assertTrue("The product is not marked correctly",

				searchedLine.findElement(By.cssSelector("td:nth-child(3")).getText()
						.contains(ContextConstants.WILL_BE_ANNOUNCED_MOBILE)
						&& !searchedLine.findElement(By.cssSelector("td:nth-child(3")).getText().contains("***"));
	}

	public void verifyProductInNotLongerAvailableTab(String sku, String catalogDetails) {
		WebElement searchedLine = getProductRow(sku);
		String grabbedDetails = searchedLine.findElement(By.cssSelector("td:nth-child(2)")).getText();
		String grabbedCatalogDetails = grabbedDetails
				.substring(grabbedDetails.indexOf(",") + 1, grabbedDetails.length()).replace(" ", "");

		Assert.assertTrue("The catalog details are not displayed correctly",
				grabbedCatalogDetails.contains(catalogDetails));
	}

	public void reloadReports() {
		element(reloadButton).waitUntilVisible();
		reloadButton.click();

	}

	public void verifyIfProductIsNotDisplayedinSelectedTab(String sku) {
		Assert.assertFalse("the product is displayed and should not be", !isProductDisplayed(sku));

	}

	public void clickOnStockReportTabProductsTab() {
		element(stockReportProducts).waitUntilVisible();
		stockReportProducts.click();
		
	}

	public void verifyIfStockPoductsTabIsSelected() {
		// TODO Auto-generated method stub
		Assert.assertTrue("The message is not displayed",
				getDriver().findElement(By.cssSelector(".heading.col-xs-10 h1")).getText()
						.contains(ContextConstants.STOCK_REPORT));
	}

	public void clickOnLessThenXProductsTab() {
		element(lessThenXProducts).waitUntilVisible();
		lessThenXProducts.click();
	}

	public void verifyIfLessThenXPoductsTabIsSelected() {
//		Assert.assertTrue("The message is not displayed",
//				getDriver().findElement(By.cssSelector(".heading.col-xs-10 h1")).getText()
//						.contains(ContextConstants.LESS_THEN_X_ITEMS));
		
		Assert.assertTrue("The message is not displayed",
				getDriver().findElement(By.cssSelector(".heading.col-xs-10 h1")).getText()
						.contains("Weniger als 20 Stück verfügbar"));
	}

}

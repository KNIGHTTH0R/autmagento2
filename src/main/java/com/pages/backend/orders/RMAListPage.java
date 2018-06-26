package com.pages.backend.orders;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.requirements.AbstractPage;

public class RMAListPage extends AbstractPage {

	@FindBy(id = "sales_order_grid_filter_real_order_id")
	private WebElement orderIdInput;

	@FindBy(id = "sales_order_grid_filter_billing_name")
	private WebElement orderNameInput;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	@FindBy(css = "table#rmaGrid_table tbody")
	private WebElement listContainer;
	
	@FindBy(css = "table#sales_invoice_grid_table tbody")
	private WebElement listInvocies;
	
	@FindBy(css = "table#sales_shipment_grid_table tbody")
	private WebElement listShipments;
	
	@FindBy(css = "table#sales_creditmemo_grid_table tbody")
	private WebElement listCreditMemo;

	@FindBy(id = "sales_invoice_grid_filter_order_increment_id")
	private WebElement invoiceOderIdInput;
	
	@FindBy(id = "sales_shipment_grid_filter_order_increment_id")
	private WebElement shipmentOderIdInput;
	
	@FindBy(id = "sales_creditmemo_grid_filter_order_increment_id")
	private WebElement creditMemoOderIdInput;
	
	public void inputOderId(String name) {
		WebElement from = getDriver().findElement(By.id("rmaGrid_filter_order_increment_id_from"));
		from.clear();
		element(from).typeAndEnter(name);
		
		
		WebElement to = getDriver().findElement(By.id("rmaGrid_filter_order_increment_id_to"));
		to.clear();
		element(to).typeAndEnter(name);
		
	}
	
	public void inputInvoiceOderId(String name) {
		evaluateJavascript("jQuery.noConflict();");
		element(invoiceOderIdInput).waitUntilVisible();
		invoiceOderIdInput.clear();
		element(invoiceOderIdInput).typeAndEnter(name);
	}
	
	public void inputShipmentOderId(String name) {
		WebElement element = getDriver().findElement(By.id("sales_shipment_grid_filter_order_increment_id"));
		element.clear();
		element(element).typeAndEnter(name);
	}
	
	public void inputCreditMemoOderId(String name) {
		WebElement element = getDriver().findElement(By.id("sales_creditmemo_grid_filter_order_increment_id"));
		element.clear();
		element(element).typeAndEnter(name);
	}

	public void inputOrderName(String name) {
		WebElement element = getDriver().findElement(By.id("sales_order_grid_filter_billing_name"));
		element.clear();
		element(element).typeAndEnter(name);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
	//	waitFor(ExpectedConditions.visibilityOf(orderNameInput));
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	public void clickOnInvoiceSearch() {
		evaluateJavascript("jQuery.noConflict();");
		waitFor(ExpectedConditions.visibilityOf(invoiceOderIdInput));
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}
	
	public void clickOnShipmentSearch() {
		evaluateJavascript("jQuery.noConflict();");
		waitFor(ExpectedConditions.visibilityOf(shipmentOderIdInput));
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}
	
	public void clickOnCreditMemoSearch() {
		evaluateJavascript("jQuery.noConflict();");
		waitFor(ExpectedConditions.visibilityOf(creditMemoOderIdInput));
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}
	 public void openRMADetails(String name) {
		  evaluateJavascript("jQuery.noConflict();");
		  element(listContainer).waitUntilVisible();
		  boolean found = false;
		  List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		  theFor: for (WebElement elementNow : listElements) {
		   if (elementNow.getText().contains(name)) {
			   clickElement(elementNow.findElement(By.cssSelector("td a[href*='admin/rma/']")));
		  //  elementNow.findElement(By.cssSelector("td a[href*='admin/sales_order']")).click();
		    found = true;
		    break theFor;
		   }
		  }
		  Assert.assertTrue("The RMA was not found",found);
		 }
	 
	 public void openInvoiceDetails(String name) {
		  evaluateJavascript("jQuery.noConflict();");
		  element(listInvocies).waitUntilVisible();
		  boolean found = false;
		  List<WebElement> listElements = listInvocies.findElements(By.tagName("tr"));
		  theFor: for (WebElement elementNow : listElements) {
		   if (elementNow.getText().contains(name)) {
			   clickElement(elementNow.findElement(By.cssSelector("a[href*='admin/sales_invoice']")));
		  //  elementNow.findElement(By.cssSelector("td a[href*='admin/sales_order']")).click();
		    found = true;
		    break theFor;
		   }
		  }
		  Assert.assertTrue("The order was not found",found);
		 }
	 
	 
	 public void openShipmentDetails(String name) {
		  evaluateJavascript("jQuery.noConflict();");
		  element(listShipments).waitUntilVisible();
		  boolean found = false;
		  List<WebElement> listElements = listShipments.findElements(By.tagName("tr"));
		  theFor: for (WebElement elementNow : listElements) {
		   if (elementNow.getText().contains(name)) {
			   clickElement(elementNow.findElement(By.cssSelector("a[href*='admin/sales_shipment']")));
		  //  elementNow.findElement(By.cssSelector("td a[href*='admin/sales_order']")).click();
		    found = true;
		    break theFor;
		   }
		  }
		  Assert.assertTrue("The order was not found",found);
		 }
	 
	 public void openCreditMemoDetails(String name) {
		  evaluateJavascript("jQuery.noConflict();");
		  element(listCreditMemo).waitUntilVisible();
		  boolean found = false;
		  List<WebElement> listElements = listCreditMemo.findElements(By.tagName("tr"));
		  theFor: for (WebElement elementNow : listElements) {
		   if (elementNow.getText().contains(name)) {
			   clickElement(elementNow.findElement(By.cssSelector("td a[href*='admin/sales_creditmemo']")));
		  //  elementNow.findElement(By.cssSelector("td a[href*='admin/sales_order']")).click();
		    found = true;
		    break theFor;
		   }
		  }
		  Assert.assertTrue("The order was not found",found);
		 }
}

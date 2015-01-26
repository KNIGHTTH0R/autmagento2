package com.pages.backend.orders;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

public class OrderListPage extends AbstractPage {

	@FindBy(id = "sales_order_grid_filter_real_order_id")
	private WebElement orderIdInput;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	@FindBy(css = "table#sales_order_grid_table tbody")
	private WebElement listContainer;

	public void inputOderId(String orderId) {
		evaluateJavascript("jQuery.noConflict();");
		waitFor(ExpectedConditions.visibilityOf(orderIdInput));
		orderIdInput.clear();
		element(orderIdInput).typeAndEnter(orderId);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	public void openOrderDetails(String orderId) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));

		theFor: for (WebElement elementNow : listElements) {
			String currentOrder = elementNow.findElement(By.cssSelector("*:nth-child(2)")).getText();
			WebElement currentLink = elementNow.findElement(By.cssSelector("*:nth-child(2)"));
			if (currentOrder.trim().contentEquals(orderId)) {
				currentLink.click();
				break theFor;
			}
		}
	}
}

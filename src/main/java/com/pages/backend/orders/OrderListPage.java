package com.pages.backend.orders;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.requirements.AbstractPage;

public class OrderListPage extends AbstractPage {

	@FindBy(id = "sales_order_grid_filter_real_order_id")
	private WebElement orderIdInput;

	@FindBy(id = "sales_order_grid_filter_billing_name")
	private WebElement orderNameInput;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	@FindBy(css = "table#sales_order_grid_table tbody")
	private WebElement listContainer;

	public void inputOderId(String name) {
		WebElement element = getDriver().findElement(By.id("sales_order_grid_filter_real_order_id"));
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
		waitFor(ExpectedConditions.visibilityOf(orderNameInput));
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

//	public void openOrderDetails(String name) {
//		evaluateJavascript("jQuery.noConflict();");
//		element(listContainer).waitUntilVisible();
//		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
//		theFor: for (WebElement elementNow : listElements) {
//			WebElement currentLink = elementNow.findElement(By.cssSelector("td a[href*='admin/sales_order']"));
//			if (listContainer.getText().contains(name)) {
//				System.out.println(listContainer.getText());
//				currentLink.click();
//				break theFor;
//			}
//		}
//	}
	public void openOrderDetails(String name) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		theFor: for (WebElement elementNow : listElements) {
			if (elementNow.getText().contains(name)) {
				System.out.println(elementNow.getText());
				elementNow.findElement(By.cssSelector("td a[href*='admin/sales_order']")).click();
				break theFor;
			}
		}
	}
}

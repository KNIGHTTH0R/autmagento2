package com.pages.backend.creditMemo;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.requirements.AbstractPage;

public class CreditMemoListPage extends AbstractPage {

	@FindBy(id = "sales_creditmemo_grid_filter_billing_name")
	private WebElement creditMemoBillingNameInput;

	@FindBy(id = "sales_creditmemo_grid_filter_order_increment_id")
	private WebElement creditMemoOrderId;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	@FindBy(css = "table#sales_creditmemo_grid_table tbody")
	private WebElement listContainer;

	public void inputCreditMemoBillingName(String name) {
		WebElement element = getDriver().findElement(By.id("sales_creditmemo_grid_filter_billing_name"));
		element.clear();
		element(element).typeAndEnter(name);
	}

	public void inputCreditMemoOrderId(String orderId) {
		WebElement element = getDriver().findElement(By.id("sales_creditmemo_grid_filter_order_increment_id"));
		element.clear();
		element(element).typeAndEnter(orderId);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		waitFor(ExpectedConditions.visibilityOf(creditMemoBillingNameInput));
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	public void openCreditMemoDetails(String name) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));

		theFor: for (WebElement elementNow : listElements) {
			WebElement currentLink = elementNow.findElement(By.cssSelector("td:nth-child(11) a"));
			if (listContainer.getText().contains(name)) {
				currentLink.click();
				break theFor;
			}
		}
	}
}

package com.pages.backend.products;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class BackendProductListPage extends AbstractPage {

	@FindBy(id = "productGrid_product_filter_sku")
	private WebElement productSkuInput;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	@FindBy(css = "table#productGrid_table tbody")
	private WebElement listContainer;

	public void inputSku(String sku) {
		evaluateJavascript("jQuery.noConflict();");
		productSkuInput.clear();
		element(productSkuInput).typeAndEnter(sku);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	public void openProductDetails(String sku) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		theFor: for (WebElement elementNow : listElements) {
			WebElement currentLink = elementNow.findElement(By.cssSelector("td:nth-child(12) a"));
			if (listContainer.getText().contains(sku)) {
				currentLink.click();
				break theFor;
			}
		}
	}
}

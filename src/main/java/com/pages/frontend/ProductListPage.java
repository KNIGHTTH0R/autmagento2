package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.data.frontend.ProductBasicModel;

public class ProductListPage extends AbstractPage {

	@FindBy(className = "category-products")
	private WebElement productCatalogContainer;

	public ProductBasicModel findProductAndClick(String productName) {
		element(productCatalogContainer).waitUntilVisible();
		List<WebElement> productsList = productCatalogContainer.findElements(By.cssSelector("li.item"));

		ProductBasicModel resultEntry = new ProductBasicModel();

		theFor: for (WebElement webElement : productsList) {
			String productText = webElement.findElement(By.cssSelector("a.prod-name")).getText();
			if (productText.contains(productName)) {

				resultEntry.setName(productText);
				resultEntry.setType(webElement.findElement(By.cssSelector("span.product-cat-code")).getText());
				resultEntry.setPrice(webElement.findElement(By.cssSelector("span.price")).getText());
				webElement.findElement(By.cssSelector("a.product-image")).click();

				break theFor;
			}
		}
		return resultEntry;
	}

}

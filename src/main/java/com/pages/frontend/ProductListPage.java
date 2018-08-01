package com.pages.frontend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class ProductListPage extends AbstractPage {

	@FindBy(css = ".products.list.items.product-items")
	private WebElement productCatalogContainer;

	public ProductBasicModel findProductAndClick(String productName) {
		element(productCatalogContainer).waitUntilVisible();
		List<WebElement> productsList = productCatalogContainer.findElements(By.cssSelector("li.item"));

		ProductBasicModel resultEntry = new ProductBasicModel();

		theFor: for (WebElement webElement : productsList) {
			String productText = webElement.findElement(By.cssSelector(".product-item-name")).getText();
			if (productText.contains(productName)) {
				resultEntry.setName(productText);
				//resultEntry.setType(webElement.findElement(By.cssSelector("span.product-cat-code")).getText());
				resultEntry.setPrice(FormatterUtils.cleanNumberToString(webElement.findElement(By.cssSelector("#product-price-1 span")).getText()));
				webElement.findElement(By.cssSelector("a.product.photo")).click();

				break theFor;
			}
		}
		return resultEntry;
	}

}

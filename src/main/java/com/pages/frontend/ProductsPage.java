package com.pages.frontend;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

public class ProductsPage extends AbstractPage {

	@FindBy(css = ".item")
	private WebElement product;

	private WebElement getSearchedProductElement(String productName) {
		List<WebElement> productsList = getDriver().findElements(
				By.cssSelector(".item"));
		boolean found = false;
		for (WebElement product : productsList) {
			WebElement productNameContainer = product.findElement(By
					.cssSelector(" .prod-name"));
			if (productNameContainer.getText().trim()
					.contentEquals(productName))
				found = true;
			return product;
		}

		Assert.assertTrue("The " + productName + " was not found", found);
		return null;

	}

	private String getProductPrice(String productName) {
		WebElement product = getSearchedProductElement(productName);
		return product.findElement(
				By.cssSelector(".regular-price.variable-text")).getText();
	}
}

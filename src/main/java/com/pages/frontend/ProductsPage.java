package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;

public class ProductsPage extends AbstractPage {
	
	@FindBy(className = "category-products")
	private WebElement productCatalogContainer;

	public void findProductAndClick(String productName) {
		element(productCatalogContainer).waitUntilVisible();
		List<WebElement> productsList = productCatalogContainer.findElements(By.cssSelector("li.item"));
		
		theFor:
		for (WebElement webElement : productsList) {
			String productText = webElement.findElement(By.cssSelector("a.prod-name")).getText();
			if(productText.contains(productName)){
				webElement.findElement(By.cssSelector("a.product-image")).click();

				System.out.println(" ****************** Found the product: " + productName);
				
				waitABit(6000);
				break theFor;
			}
		}
	}

}

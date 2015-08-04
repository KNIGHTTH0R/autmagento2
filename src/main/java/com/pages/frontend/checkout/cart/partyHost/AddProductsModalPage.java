package com.pages.frontend.checkout.cart.partyHost;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class AddProductsModalPage extends AbstractPage {

	@FindBy(id = "query")
	private WebElement productCodeInput;

	@FindBy(css = "#search-form button[type='submit']")
	private WebElement submitSearch;

	public void verifyProductPropertiesInModalWindow(String productCode, String productName) {
		List<WebElement> cartList = getDriver().findElements(By.cssSelector("#result-items-table tbody tr"));
		// remove header
		cartList.remove(0);
		boolean foundProduct = false;
		for (WebElement product : cartList) {
			if (product.findElement(By.cssSelector("td:nth-child(3)")).getText().contains(productCode)) {
				foundProduct = true;
				Assert.assertTrue("Product name is not correct !!!", product.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(productName));
				break;
			}
		}
		Assert.assertTrue("The product with the product code " + productCode + " was not found", foundProduct);
	}

	public void typeProductCode(String code) {
		element(productCodeInput).waitUntilVisible();
		productCodeInput.clear();
		productCodeInput.sendKeys(code);
	}

	public void submitSearch() {
		element(submitSearch).waitUntilVisible();
		submitSearch.click();
		waitABit(1000);
	}
}

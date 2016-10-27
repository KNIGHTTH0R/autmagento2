package com.pages.frontend.checkout.cart.partyHost;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class AddProductsModalPage extends AbstractPage {

	@FindBy(id = "query")
	private WebElement productCodeInput;

	@FindBy(css = "#search-form button[type='submit']")
	private WebElement submitSearch;
	@FindBy(css = "a.fancybox-item.fancybox-close")
	private WebElement closeFancyButton;

	public void verifyProductPropertiesInModalWindow(String productCode, String... searchedTerms) {
		List<WebElement> cartList = getDriver().findElements(By.cssSelector("#result-items-table tbody tr"));
		// remove header
		cartList.remove(0);
		boolean foundProduct = false;
		for (WebElement product : cartList) {

			if (product.findElement(By.cssSelector("td:nth-child(3)")).getText().contains(productCode)) {
				boolean matchesAll = true;
				for (String term : searchedTerms) {
					if (!product.getText().contains(term)) {
						matchesAll = false;
						break;
					}
				}
				Assert.assertTrue("The product was found but it does not contain all searched terms", matchesAll);

				if (matchesAll) {
					foundProduct = true;
					break;
				}
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
		waitABit(2000);
	}

	public void closeModal() {
		element(closeFancyButton).waitUntilVisible();
		closeFancyButton.click();
	}
}

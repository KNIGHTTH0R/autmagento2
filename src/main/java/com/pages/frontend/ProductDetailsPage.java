package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;

public class ProductDetailsPage extends AbstractPage {

	@FindBy(css = ".dp-bl.title.ff-Nb.size20")
	private WebElement titleContainer;

	@FindBy(css = ".price")
	private WebElement priceContainer;

	@FindBy(css = ".mini-products-list li")
	private WebElement miniCartContainer;

	@FindBy(css = ".sprite.shopping-bag")
	private WebElement shoppingBagButton;

	@FindBy(id = ".qty")
	private WebElement quantityInput;

	@FindBy(id = "#add-to-cart")
	private WebElement addToCartButton;
	
	@FindBy(css = ".inner-wrapper .button")
	private WebElement goToCartButton;

	@FindBy(css = ".required-entry.super-attribute-select.validation-failed")
	private WebElement selectInput;

	public void checkProductName(String productName) {
		element(titleContainer).waitUntilVisible();
		Assert.assertTrue("The product name is incorrect", titleContainer
				.getText().contains(productName));
	}

	public void checkProductPrice(String productPrice) {
		element(priceContainer).waitUntilVisible();
		Assert.assertTrue("The product name is incorrect", priceContainer
				.getText().contains(productPrice));
	}

	public void inputPrice(String qty) {
		element(quantityInput).waitUntilVisible();
		quantityInput.sendKeys(qty);
	}

	public void selectRegistrationType(String size) {
		element(selectInput).waitUntilVisible();
		element(selectInput).selectByVisibleText(size);
	}

	public void clickAddToCart() {
		element(addToCartButton).waitUntilVisible();
		addToCartButton.click();
	}
	public void clickGoToCart() {
		element(goToCartButton).waitUntilVisible();
		goToCartButton.click();
	}

	public void clickShoppingBag() {
		element(shoppingBagButton).waitUntilVisible();
		shoppingBagButton.click();
	}

	private WebElement getSearchedProductElement(String productName) {
		List<WebElement> productsList = getDriver().findElements(
				By.cssSelector(".mini-products-list li"));
		boolean found = false;
		for (WebElement product : productsList) {
			WebElement productNameContainer = product.findElement(By
					.cssSelector(" .product-name"));
			if (productNameContainer.getText().trim()
					.contentEquals(productName))
				found = true;
			return product;
		}

		Assert.assertTrue("The " + productName + " was not found", found);
		return null;

	}

	public void verifyProductDetailsInExpandedWrapper(String productName,
			String price, String qty) {
		WebElement product = getSearchedProductElement(productName);
		Assert.assertTrue("Product details are incorrect", product.getText()
				.contains(price) && product.getText().contains(qty));
	}

}

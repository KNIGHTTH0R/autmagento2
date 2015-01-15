package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.MathUtils;
import com.tools.StringUtils;

public class CartPage extends AbstractPage {

	@FindBy(css = ".item")
	private WebElement product;

	public double getCustomerDiscount() {
		String discountText = getDriver().findElement(
				By.cssSelector("fieldset .title")).getText();
		double discount = StringUtils
				.getFirstIntegerNumberFromString(discountText);
		return discount;
	}

	public String getPriceWithDiscount(double price, double discount) {
		double priceWithDiscount = price - (price * discount);
		return String.valueOf(MathUtils
				.roundDoubleToTwoDigits(priceWithDiscount));

	}

	private WebElement getSearchedProductElement(String productName) {
		List<WebElement> productsList = getDriver().findElements(
				By.cssSelector(".data-table.cart-table tr"));
		productsList.remove(0);
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

	public void verifyProductDetailsInCart(String productName, String qty,
			String unitPrice, String totalPrice, String finalPrice) {
		WebElement product = getSearchedProductElement(productName);
		String productQuantityText = product.findElement(
				By.cssSelector(" .input-text.qty")).getText();
		String productUnitPriceText = product.findElement(
				By.cssSelector("  td:nth-child(4) .cart-price")).getText();
		String productTotalPriceText = product.findElement(
				By.cssSelector(" .cart-price.variable-text")).getText();
		String productPriceWithDiscount = String.valueOf(StringUtils
				.getFirstDoubleNumberFromString(product.findElement(
						By.cssSelector(" .price.pink-text")).getText()));

		Assert.assertTrue("The quantity should be " + qty + " and it's"
				+ productQuantityText, qty.contentEquals(productUnitPriceText));

		Assert.assertTrue("The unit price should be " + unitPrice + " and it's"
				+ productUnitPriceText,
				unitPrice.contentEquals(productUnitPriceText));

		Assert.assertTrue("The total price should be " + totalPrice + " and it's"
				+ productTotalPriceText,
				totalPrice.contentEquals(productTotalPriceText));

		Assert.assertTrue("The quantity should be " + finalPrice + " and it's"
				+ productPriceWithDiscount,
				finalPrice.contentEquals(productPriceWithDiscount));

	}

}

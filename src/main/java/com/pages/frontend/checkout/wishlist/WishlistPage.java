package com.pages.frontend.checkout.wishlist;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class WishlistPage extends AbstractPage {

	@FindBy(css = "div.buttons-set.to-the-right button[type*='button']")
	private WebElement addAllToCArt;

	@FindBy(css = "div.my-wishlist")
	private WebElement wishlistContainer;

	public void addAllProductsToCArt() {
		if (element(addAllToCArt).isPresent()) {
			element(addAllToCArt).waitUntilVisible();
			addAllToCArt.click();
		}
	}

	public void removeProductsFromWishlist() {
		List<WebElement> productsList = getDriver().findElements(By.cssSelector("ul#wishlist-table li"));
		for (@SuppressWarnings("unused") WebElement product : productsList) {
			getDriver().findElement(By.cssSelector(".remove-link")).click();
			getAlert().accept();
			waitForPageToLoad();
		}
	}

	public void verifyThatProductCannotBeAddedToCart(String productName) {
		List<WebElement> productsList = getDriver().findElements(By.cssSelector("ul#wishlist-table li"));
		for (WebElement product : productsList) {
			if (product.getText().contains(productName)) {
				Assert.assertTrue(!product.getText().contains(ContextConstants.ADD_TO_CART));
				break;
			}
		}
	}

	public String getWishlistProductText(String productName) {
		List<WebElement> productsList = getDriver().findElements(By.cssSelector("ul#wishlist-table li"));
		for (WebElement product : productsList) {
			if (product.getText().contains(productName)) {
				return product.getText();
			}
		}
		return null;
	}

	public String getWishlistText() {
		return getDriver().findElement(By.cssSelector("div.my-wishlist")).getText();
	}

	public void addProductToCart(String productName) {
		List<WebElement> productsList = getDriver().findElements(By.cssSelector("ul#wishlist-table li"));
		for (WebElement product : productsList) {
			if (product.getText().contains(productName)) {
				product.findElement(By.cssSelector("button[onclick*='addWItemToCart']")).click();
				break;
			}
		}
	}

}

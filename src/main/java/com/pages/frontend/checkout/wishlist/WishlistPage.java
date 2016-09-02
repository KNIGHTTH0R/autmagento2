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

	public List<WebElement> getWishlistProducts() {
		return getDriver().findElements(By.cssSelector("ul#wishlist-table li"));
	}

	public void removeProductsFromWishlist() {

		for (WebElement product : getWishlistProducts()) {
			product.findElement(By.cssSelector(".remove-link")).click();
			getAlert().accept();
		}
	}

	public void verifyThatProductCannotBeAddedToCart(String productName) {
		for (WebElement product : getWishlistProducts()) {
			if (product.getText().contains(productName)) {
				Assert.assertTrue(!product.getText().contains(ContextConstants.ADD_TO_CART));
				break;
			}
		}
	}

	public String getWishlistProductText(String productName) {
		for (WebElement product : getWishlistProducts()) {
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
		for (WebElement product : getWishlistProducts()) {
			if (product.getText().contains(productName)) {
				product.findElement(By.cssSelector("button[onclick*='addWItemToCart']")).click();
				break;
			}
		}
	}

}

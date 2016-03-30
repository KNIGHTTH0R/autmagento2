package com.pages.frontend.checkout.wishlist;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.env.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class WishlistPage extends AbstractPage {

	@FindBy(css = "div.buttons-set.to-the-right button[type*='button']")
	private WebElement addAllToCArt;

	@FindBy(css = "div.my-wishlist")
	private WebElement wishlistContainer;

	public void addAllProductsToCArt() {
		element(addAllToCArt).waitUntilVisible();
		addAllToCArt.click();
	}

	public boolean isWishlistEmpty() {
		element(wishlistContainer).waitUntilVisible();
		return wishlistContainer.getText().contains(ContextConstants.EMPTY_WISHLIST_MESSAGE);
	}
}

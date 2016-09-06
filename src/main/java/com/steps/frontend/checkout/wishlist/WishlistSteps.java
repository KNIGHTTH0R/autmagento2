package com.steps.frontend.checkout.wishlist;

import org.junit.Assert;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class WishlistSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void addAllProductsToCArt() {
		wishlistPage().addAllProductsToCArt();
	}

	@Step
	public void removeProductsFromWishlist() {
		wishlistPage().removeProductsFromWishlist();
	}

	@Step
	public void verifyThatCannotBeAddedToCart(String productName) {
		Assert.assertTrue("The Add to cart button is present and it shouldn't",
				!wishlistPage().getWishlistProductText(productName).contains(ContextConstants.ADD_TO_CART));
	}

	@Step
	public void verifyPresenceOfAddAllToCartButton(boolean shouldBePresent) {
		if (shouldBePresent)
			Assert.assertTrue("The Add all to cart should be present and it's not !!!",
					wishlistPage().getWishlistText().contains(ContextConstants.ADD_ALL_TO_CART));

		else
			Assert.assertTrue("The Add all to cart button is present and it shouldn't !!!",
					!wishlistPage().getWishlistText().contains(ContextConstants.ADD_ALL_TO_CART));
	}

	@Step
	public void addProductToCart(String productName) {
		wishlistPage().addProductToCart(productName);
	}

}

package com.steps.frontend.checkout.wishlist;

import com.tools.requirements.AbstractSteps;

public class WishlistSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void addAllProductsToCart() {
		wishlistPage().addAllProductsToCArt();
	}

}

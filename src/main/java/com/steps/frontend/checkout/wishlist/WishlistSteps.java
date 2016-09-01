package com.steps.frontend.checkout.wishlist;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class WishlistSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

//	@Step
//	public void wipeWishlist() {
//		if (!wishlistPage().isWishlistEmpty()) {
//			wishlistPage().addAllProductsToCArt();
//		}
//	}
	
	@Step
	public void removeProductsFromWishlist(){
		wishlistPage().removeProductsFromWishlist();
	}

}

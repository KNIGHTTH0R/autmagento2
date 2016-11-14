package com.steps.frontend.checkout.cart;

import com.tools.requirements.AbstractSteps;

public class GeneralCartSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;

	public void clearCart() {

		if (!generalCartPage().isCartEmpty()) {
			generalCartPage().clickClearCart();
		}

	}
	public void clearBorrowCart() {
		
			generalCartPage().clickClearCart();
		
	}

}

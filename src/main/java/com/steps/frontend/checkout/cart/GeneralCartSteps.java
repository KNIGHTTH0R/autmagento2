package com.steps.frontend.checkout.cart;

import com.tools.requirements.AbstractSteps;

public class GeneralCartSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;

	public void clearCart() {
		generalCartPage().clickClearCart();
		generalCartPage().verifyIsCartEmpty();

	}
	public void clearBorrowCart() {
		
			generalCartPage().clickClearCart();
		
	}
	public void waitMethod() {
		waitABit(30000);
		
	}

}

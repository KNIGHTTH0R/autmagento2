package com.steps.frontend.checkout.cart;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class GeneralCartSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;

	@Step
	public void clearCart() {

		if (!generalCartPage().isCartEmpty()) {
			generalCartPage().clickClearCart();
		}

	}

}

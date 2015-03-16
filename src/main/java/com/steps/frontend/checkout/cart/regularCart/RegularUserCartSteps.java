package com.steps.frontend.checkout.cart.regularCart;

import com.tools.requirements.AbstractSteps;

public class RegularUserCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void selectProductDiscountType(String productCode, String discountType) {
		regularUserCartPage().selectProductDiscountType(productCode, discountType);
	}

}

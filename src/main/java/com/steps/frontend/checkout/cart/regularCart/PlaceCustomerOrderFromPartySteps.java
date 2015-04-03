package com.steps.frontend.checkout.cart.regularCart;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class PlaceCustomerOrderFromPartySteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void orderForCustomerFromPartyDetails(String name) {
		placeCustomerOrderFromPartyPage().typeContactName(name);
		placeCustomerOrderFromPartyPage().startOrderForCustomer();
	}

}

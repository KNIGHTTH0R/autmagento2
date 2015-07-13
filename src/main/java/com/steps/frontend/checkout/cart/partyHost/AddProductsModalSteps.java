package com.steps.frontend.checkout.cart.partyHost;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class AddProductsModalSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void verifyProductPropertiesInModalWindow(String productCode, String productName) {
		addProductsModalPage().verifyProductPropertiesInModalWindow(productCode, productName);
	}

	@Step
	public void searchForProduct(String productCode) {
		addProductsModalPage().typeProductCode(productCode);
		addProductsModalPage().submitSearch();
	}

}

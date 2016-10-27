package com.steps.backend.products;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class BackendProductListSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void findProduct(String sku) {
		backendProductListPage().inputSku(sku);
		backendProductListPage().clickOnSearch();
	}

	@Step
	public void openProductDetails(String sku) {
		backendProductListPage().openProductDetails(sku);

	}

}

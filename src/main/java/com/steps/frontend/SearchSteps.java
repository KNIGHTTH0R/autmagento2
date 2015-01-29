package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.AbstractSteps;

public class SearchSteps extends AbstractSteps {

	private static final long serialVersionUID = 3299276083448603139L;

	@StepGroup
	public ProductBasicModel searchAndSelectProduct(String searchTerm, String productName) {
		searchProduct(searchTerm);
		return selectProduct(productName);
	}

	@Step
	public void searchProduct(String searchKey) {
		headerPage().searchInput(searchKey);
		headerPage().clickOnSubmitButton();
	}

	@Step
	public ProductBasicModel selectProduct(String productName) {
		return productListPage().findProductAndClick(productName);
	}

}

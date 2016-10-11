package com.steps.frontend;

import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.StepGroup;

public class SearchSteps extends AbstractSteps {

	private static final long serialVersionUID = 3299276083448603139L;

	@StepGroup
	public void navigateToProductPage(String productName) {
		// searchProduct(searchTerm);
		// return selectProduct(productName);
		navigate(MongoReader.getBaseURL() + "/" + productName.toLowerCase() + ".html");
	}

	// @Step
	// public void searchProduct(String searchKey) {
	// headerPage().searchInput(searchKey);
	// headerPage().clickOnSubmitButton();
	// }
	//
	// @Step
	// public ProductBasicModel selectProduct(String productName) {
	// return productListPage().findProductAndClick(productName);
	// }

}

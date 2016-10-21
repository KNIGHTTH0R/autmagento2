package com.steps.frontend;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.StepGroup;

public class SearchSteps extends AbstractSteps {

	private static final long serialVersionUID = 3299276083448603139L;

	@StepGroup
	public void navigateToProductPage(String pathName) {
		// searchProduct(searchTerm);
		// return selectProduct(productName);
		// navigate(MongoReader.getBaseURL() + "/" + pathName.toLowerCase() + ".html");
		
		String[] currentUrlSplitted = getDriver().getCurrentUrl().split("/");

		navigate(currentUrlSplitted[0] + "//" + currentUrlSplitted[2] + "/" + currentUrlSplitted[3] + "/"
				+ pathName.toLowerCase() + ".html");
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

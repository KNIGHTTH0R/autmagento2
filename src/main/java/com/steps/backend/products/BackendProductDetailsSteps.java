package com.steps.backend.products;

import net.thucydides.core.annotations.Step;

import com.tools.constants.ConfigConstants;
import com.tools.requirements.AbstractSteps;

public class BackendProductDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectBundleItemMenu() {
		backendProductDetailsPage().selectOptionFromProductMenu(ConfigConstants.BUNDLEARTIKEL);

	}

	@Step
	public void addChildToBundleProduct(String childSku) {
		backendProductDetailsPage().clickAddNewProductButton();
		backendProductDetailsPage().setTitleForBundleChild(ConfigConstants.BUNDLEARTIKEL_NAME);
		backendProductDetailsPage().clickAddSelectionButton();
		backendProductDetailsPage().enterChildSku(childSku);
		backendProductDetailsPage().clickOnSearch();
		backendProductDetailsPage().selectFirstProductAsAChild();
		backendProductDetailsPage().clickAddSelectedProductToOption();
		backendProductDetailsPage().saveAndContinueEdit();

	}

	@Step
	public void addCatalogPage(String page) {
		backendProductDetailsPage().addCatalogPage(page);
		
	}

	@Step
	public void addCatalogRow(String row) {
		backendProductDetailsPage().addCatalogRow(row);
		
	}

	@Step
	public void saveAndContinue() {
		backendProductDetailsPage().saveAndContinueEdit();
		
	}

}

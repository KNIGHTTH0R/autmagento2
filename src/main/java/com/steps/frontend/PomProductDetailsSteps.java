package com.steps.frontend;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractSteps;

public class PomProductDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;
	@Step
	public void findProductAndClick(String productName){
		pomProductListPage().findProductAndClick(productName);
	}
	
	@StepGroup
	public void findStarterProductAndAddItToTheCart(String productName){
		pomProductListPage().findProductAndClick(productName);
		fancyBoxPage().submitProduct();
	}
	
	@Step
	public void verifyAddToCartButton(boolean shouldBeVisible) {
		if (shouldBeVisible) {
			Assert.assertTrue("The button should be present and it's not !!!",
					pomProductListPage().getProductDetailsText().contains(ContextConstants.ADD_TO_CART));
		} else {
			Assert.assertTrue("The button is present and it shouldn't !!!",
					!pomProductListPage().getProductDetailsText().contains(ContextConstants.ADD_TO_CART));
		}
	}

}

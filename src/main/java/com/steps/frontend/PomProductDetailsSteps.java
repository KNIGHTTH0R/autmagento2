package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

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

}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class PomProductDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;
	@Step
	public void findProductAndClick(String productName){
		pomProductListPage().findProductAndClick(productName);
	}

}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class FancyBoxSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void closeFancyBox() {
		fancyBoxPage().closeFancyBox();
	}

	@Step
	public void selectValueFromDropDown(String size) {
		fancyBoxPage().selectValueFromDropDown(size);
	}

	@Step
	public void submitProduct() {
		fancyBoxPage().submitProduct();
	}

	@Step
	public void goToShipping() {
		fancyBoxPage().goToShipping();
	}

}

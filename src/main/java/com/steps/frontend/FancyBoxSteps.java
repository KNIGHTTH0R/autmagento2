package com.steps.frontend;

import com.tools.requirements.AbstractSteps;

public class FancyBoxSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void closeFancyBox() {
		fancyBoxPage().closeFancyBox();
	}

	public void selectValueFromDropDown(String size) {
		fancyBoxPage().selectValueFromDropDown(size);
	}

}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class StylistContextSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;
	
	@Step
	public void inputStylistRef(String ref) {
		stylistContextPage().inputStylistRef(ref);
		waitABit(TimeConstants.TIME_CONSTANT);
	}
	
	@Step
	public void submitContextStep() {
		stylistContextPage().submitStep();
	}

}

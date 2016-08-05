package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.constants.TimeConstants;
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

	@StepGroup
	public void addStylistReference(String ref) {
		inputStylistRef(ref);
		submitContextStep();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

}

package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class StylecoachListBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void openPartyDetails(String searchTerm) {
		stylecoachListBackendPage().inputEmailFilter(searchTerm);
		stylecoachListBackendPage().clickOnSearch();
		stylecoachListBackendPage().openPartyDetails(searchTerm);
	}

}

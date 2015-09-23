package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class PartiesListSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public String goToFirstParty() {
		return partiesListPage().goToFirstParty();
		
	}

}

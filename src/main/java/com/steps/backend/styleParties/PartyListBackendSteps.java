package com.steps.backend.styleParties;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class PartyListBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void typeIdFrom(String id) {
		partyListBackendPage().typeIdFrom(id);
	}

	@Step
	public void clickOnSearch() {
		partyListBackendPage().clickOnSearch();
	}

	@Step
	public void openPartyDetails(String searchTerm) {
		partyListBackendPage().openPartyDetails(searchTerm);
	}

}

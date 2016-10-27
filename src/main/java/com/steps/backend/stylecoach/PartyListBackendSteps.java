package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;

import com.tools.data.backend.PartyBackendPerformanceModel;
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
		partyListBackendPage().typeIdFrom(searchTerm);
		partyListBackendPage().typeIdTo(searchTerm);
		partyListBackendPage().clickOnSearch();
		partyListBackendPage().openPartyDetails(searchTerm);
	}

	@Step
	public PartyBackendPerformanceModel getPartyBackendPerformance() {
		return partyDetailsBackendPage().getPartyBackendPerformance();
	}

}

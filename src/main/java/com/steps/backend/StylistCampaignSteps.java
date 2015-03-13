package com.steps.backend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class StylistCampaignSteps extends AbstractSteps {
	
	private static final long serialVersionUID = 1L;
	@Step
	public void clickJetztStartenButton() {
		stylistCampaignPage().clickJetztStartenButton();
	}
	@Step
	public void clickStarteJetztButton() {
		stylistCampaignPage().clickStarteJetztButton();
	}

}

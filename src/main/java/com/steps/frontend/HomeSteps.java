package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.Constants;
import com.tools.requirements.AbstractSteps;

public class HomeSteps extends AbstractSteps{

	private static final long serialVersionUID = 1L;
	@Step
	public void clickStyleCoachLink() {
		homePage().clickStyleCoachLink();
		
	}

	@StepGroup
	public void navigateToRegisterFormFromStyleCoachLinkAndJetzStarten() {
		getDriver().get(Constants.BASE_FE_URL);
		homePage().clickStyleCoachLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStartenFromStarterSet();
	}
	
	@StepGroup
	public void navigateToRegisterFormFromStyleCoachLinkAndStarteJetzt() {
		getDriver().get(Constants.BASE_FE_URL);
		homePage().clickStyleCoachLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}
	
}
package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class HomeSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickStyleCoachLink() {
		homePage().clickStyleCoachLink();
	}

	@StepGroup
	public void navigateToRegisterFormFromStyleCoachLinkAndJetzStarten() {
		getDriver().get(MongoReader.getBaseURL());
		homePage().clickStyleCoachLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStartenFromStarterSet();
	}

	@StepGroup
	public void navigateToRegisterFormFromStyleCoachLinkAndStarteJetzt() {
		getDriver().get(MongoReader.getBaseURL());
		homePage().clickStyleCoachLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@Step
	public void clickonGeneralView() {
		homePage().clickonGeneralView();
	}
	
	@Step
	public void goToNewItems(){
		homePage().goToNewItems();
	}
	
	@Step
	public void clickOnContactBoosterDetails(){
		homePage().clickOnContactBoosterDetails();
	}
	
	

}

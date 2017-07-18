package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.requirements.AbstractSteps;

public class KoboValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void enterKoboCode(String code) {
		koboValidationPage().enterKoboCode(code);
	}

	@Step
	public void verifyKoboCodeInModal(String code) {
		koboValidationPage().verifyKoboCodeInModal(code);
	}
	
	@Step
	public void submitFormBooster() {
		koboValidationPage().submitFormBooster();
	}
	
	@StepGroup
	public void enterKoboCodeAndGoToRegistrationProcess(String url,String code){
		navigate(url);
		navigate(url);
		homePage().clickOnContactBoosterDetails();
		koboValidationPage().enterKoboCode(code);
		koboValidationPage().submitFormBooster();
	}
	@StepGroup
	public void startKoboCampaignProcess(String url){
		navigate(url);
		koboCampaignPage().clickRegister();
	}
	

}

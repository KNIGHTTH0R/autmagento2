package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class KoboValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void enterKoboCode(String code) {
		koboValidationPage().enterKoboCode(code);
	}

	@Step
	public void submitFormBooster() {
		koboValidationPage().submitFormBooster();
	}
	
	@StepGroup
	public void enterKoboCodeAndGoToRegistrationProcess(String code){
		getDriver().get(MongoReader.getBaseURL());
		homePage().clickOnContactBoosterDetails();
		koboValidationPage().enterKoboCode(code);
		koboValidationPage().submitFormBooster();
	}
	

}

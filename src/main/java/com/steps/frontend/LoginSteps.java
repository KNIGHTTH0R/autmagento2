package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class LoginSteps extends AbstractSteps{

	private static final long serialVersionUID = 1L;
	
	@Step
	public void clickOnStylistRegistrationLink() {
		loginPage().clickOnStylistRegistrationLink();
	}
	@Step
	public void clickGoToCustomerRegistration(){
		loginPage().clickGoToCustomerRegistration();
	}

}

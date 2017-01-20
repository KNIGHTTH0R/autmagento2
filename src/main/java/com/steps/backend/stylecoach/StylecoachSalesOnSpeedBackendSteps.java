package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;

import com.pages.backend.stylecoach.SosDetailsBackendPage;
import com.tools.requirements.AbstractSteps;

public class StylecoachSalesOnSpeedBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectAllowSosSync(String syncSos) {
		sosDetailsBackendPage().selectAllowSosSync(syncSos);
		customerDetailsHomePage().saveAndContinueEdit();


		
	}
	@Step
	public String checkSosPassword() {
		return sosDetailsBackendPage().checkPasswordField();
	}
	
	@Step
	public void clickOnResetContactsButton() {
		sosDetailsBackendPage().clickOnResetContactsButton();
	}
	
	@Step
	public void clickOnResetAccountButton() {
		sosDetailsBackendPage().clickOnResetAccountButton();
	}
	
	@Step
	public boolean checkIsPresentResetAccountButton() {
		return sosDetailsBackendPage().checkIsPresentResetAccountButton();
	}
	@Step
	public boolean checkIsPresentResetContactButton() {
		return sosDetailsBackendPage().checkIsPresentResetContactButton();
	}
	
	@Step
	public String grabStyleCoachID() {
		return sosDetailsBackendPage().grabStyleCoachID();
	}
	
	@Step
	public void validateSuccessMessage() {
		// TODO Auto-generated method stub
		sosDetailsBackendPage().validateSuccessMessage();
		
		
	}
	
	
}

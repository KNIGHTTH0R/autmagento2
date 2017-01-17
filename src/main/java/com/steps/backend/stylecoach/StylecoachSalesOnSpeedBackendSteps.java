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
	public void checkSosPassword() {
		sosDetailsBackendPage().checkPasswordField();
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
	
}

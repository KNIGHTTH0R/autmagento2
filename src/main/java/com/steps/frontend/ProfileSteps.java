package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;

public class ProfileSteps extends AbstractSteps{

	private static final long serialVersionUID = -6439771925934414965L;

	
	@Step
	public void openProfileHistory(){
		dashboardMenuPage().clickOnProfileHistory();
	}
	
	@Step
	public void waitMore(){
		waitABit(9000);
	}

	public void grabOrderHistory() {
		profileHistoryPage().grabOrderHistory();
	}



}

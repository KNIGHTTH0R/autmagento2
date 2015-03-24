package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class PartyDetailsSteps extends AbstractSteps{

	private static final long serialVersionUID = 1L;
	@Step
	public void clickOrderForHostess(){
		partyDetailsPage().clickOrderForHostess();
	}
	@Step
	public void selectFirstAvailableDate(){
		partyDetailsPage().selectFirstAvailableDate();
	}
	@Step
	public void selectFirstAvailableHour(){
		partyDetailsPage().selectFirstAvailableHour();
	}
}

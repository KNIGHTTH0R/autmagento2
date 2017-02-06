package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.requirements.AbstractSteps;

public class ShopSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	
	@Step
	public void checkIfBorrowLinkIsdisplayed(boolean isDisplayed) {
		shopPage().checkIfBorrowLinkIsDisplayed(isDisplayed);
		
	}

	
}

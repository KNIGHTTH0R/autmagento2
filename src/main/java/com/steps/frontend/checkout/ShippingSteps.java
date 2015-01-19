package com.steps.frontend.checkout;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.data.CartTotalsModel;

public class ShippingSteps extends AbstractSteps{

	private static final long serialVersionUID = 8727875042758615102L;

	
	@Step
	public CartTotalsModel grabSurveyData(){
		return surveyPage().grabSurveyData();
	}
	
	@Step
	public void clickGoToPaymentMethod(){
		surveyPage().clickGoToPaymentMethod();
		
		waitABit(9000);
	}
	
}

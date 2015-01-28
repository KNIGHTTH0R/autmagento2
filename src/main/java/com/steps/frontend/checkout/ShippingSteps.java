package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.Constants;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;

public class ShippingSteps extends AbstractSteps{

	private static final long serialVersionUID = 8727875042758615102L;

	
	public CartTotalsModel grabSurveyData(){
		waitABit(Constants.TIME_CONSTANT);
		return surveyPage().grabSurveyData();
	}
	
	@Step
	public void clickGoToPaymentMethod(){
		surveyPage().clickGoToPaymentMethod();
		waitABit(9000);
	}

	public List<CartProductModel> grabProductsList() {
		waitABit(Constants.TIME_CONSTANT);
		return surveyPage().grabProductsList();
	}
	
}

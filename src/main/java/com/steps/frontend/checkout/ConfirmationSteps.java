package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.Constants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.requirements.AbstractSteps;

public class ConfirmationSteps extends AbstractSteps{

	private static final long serialVersionUID = 4739618000222382968L;
	
	public AddressModel grabSippingData(){
		return confirmationPage().grabShippingData();
	}
	
	public AddressModel grabBillingData(){
		return confirmationPage().grabBillingData();
	}
	
	public List<CartProductModel> grabProductsList() {
		return confirmationPage().grabProductsList();
	}
	
//	public CartTotalsModel grabSurveyData() {
//		return confirmationPage().grabSurveyData();
//	}
	public ShippingModel grabConfirmationTotals() {
		waitABit(Constants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());  
		waitABit(Constants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotals();
	}

	@Step
	public void agreeAndCheckout() {
		confirmationPage().clickIAgree();
		confirmationPage().clickOnSubmit();
	}


}

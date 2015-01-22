package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.data.AddressModel;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

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
	
	public CartTotalsModel grabSurveyData() {
		return confirmationPage().grabSurveyData();
	}

	@Step
	public void agreeAndCheckout() {
		confirmationPage().clickIAgree();
		confirmationPage().clickOnSubmit();
	}


}

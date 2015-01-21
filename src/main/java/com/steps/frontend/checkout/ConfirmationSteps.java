package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.data.AddressModel;
import com.tools.data.CartProductModel;

public class ConfirmationSteps extends AbstractSteps{

	private static final long serialVersionUID = 4739618000222382968L;
	
	@Step
	public AddressModel grabSippingData(){
		return confirmationPage().grabShippingData();
	}
	
	@Step
	public AddressModel grabBillingData(){
		return confirmationPage().grabBillingData();
	}

	@Step
	public void agreeAndCheckout() {
		confirmationPage().clickIAgree();
		confirmationPage().clickOnSubmit();
		
	}
//	@Step
	public List<CartProductModel> grabProductsList() {
		return confirmationPage().grabProductsList();
	}

}

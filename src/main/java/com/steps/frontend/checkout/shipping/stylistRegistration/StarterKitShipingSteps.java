package com.steps.frontend.checkout.shipping.stylistRegistration;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class StarterKitShipingSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Title("Fill new address for shipping")
	@Step
	public void fillNewAddressForShipping(AddressModel addressModel) {
		waitABit(TimeConstants.TIME_CONSTANT);
		inputShippingStreetNumber(addressModel.getStreetNumber());
		inputShippingPostCode(addressModel.getPostCode());
		inputShippingHomeTown(addressModel.getHomeTown());
	}

	@Step
	public void inputShippingStreetNumber(String streetNumber) {
		starterKitShippingPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputShippingPostCode(String postCode) {
		starterKitShippingPage().inputPostCode(postCode);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void inputShippingHomeTown(String homeTown) {
		starterKitShippingPage().inputHomeTown(homeTown);
	}

	@Step
	public void setSameAsBilling(boolean checked) {
		shippingFormPage().setSameAsBilling(checked);
	}

	@Step
	public void goToPaymentMethod() {
		surveyPage().clickGoToPaymentMethod();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

}

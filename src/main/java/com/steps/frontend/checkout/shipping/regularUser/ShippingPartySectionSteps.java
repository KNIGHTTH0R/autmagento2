package com.steps.frontend.checkout.shipping.regularUser;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class ShippingPartySectionSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickPartyYesOption() {
		regularUserShippingPage().clickPartyYesOption();
	}

	@Step
	public void clickPartyNoOption() {
		regularUserShippingPage().clickPartyNoOption();
	}

	@Step
	public void checkItemNotReceivedYet() {
		regularUserShippingPage().checkItemNotReceivedYet();
	}

	@Step
	public void selectCountry(String country) {
		regularUserShippingPage().selectCountry(country);
	}

	@Step
	public void clickShipToHostessButton() {
		regularUserShippingPage().clickShipToHostessButton();
	}

	@Step
	public void clickShipToStylecoach() {
		regularUserShippingPage().clickShipToStylecoach();
	}

	@Step
	public void selectShipToHostessAddress(String address) {
		regularUserShippingPage().selectShipToHostessAddress(address);
	}

	@Step
	public void selectShipToStylecoachAddress(String address) {
		regularUserShippingPage().selectShipToStylecoachAddress(address);
	}

}

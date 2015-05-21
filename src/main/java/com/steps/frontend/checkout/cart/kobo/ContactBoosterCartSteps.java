package com.steps.frontend.checkout.cart.kobo;

import net.thucydides.core.annotations.Step;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class ContactBoosterCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectContactBoosterVoucher() {
		contactBoosterCart().selectContactBoosterVoucher();
	}

	@Step
	public void clickToShipping() {
		contactBoosterCart().clickToShipping();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

}

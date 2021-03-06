package com.steps.frontend.checkout.cart.kobo;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class ContactBoosterCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectContactBooster100Voucher() {
		contactBoosterCart().selectContactBooster100Voucher();
	}
	@Step
	public void selectContactBooster200Voucher() {
		contactBoosterCart().selectContactBooster200Voucher();
	}
	
	@Step
	public void selectContactBooster50Voucher() {
		contactBoosterCart().selectContactBooster100Voucher();
	}
	@Step
	public void selectContactBooster250Voucher() {
		contactBoosterCart().selectContactBooster100Voucher();
	}

	@Step
	public void clickToShipping() {
		contactBoosterCart().clickToShipping();
	}

}

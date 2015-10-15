package com.workflows.backend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.CustomVerification;
import com.tools.data.backend.PartyBackendPerformanceModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;

public class BackendPartyPerformanceValidationWorkflows {
	
	@Title("Validate that closed party performance is correct")
	@Step
	public void validateClosedPartyPerformance(ClosedPartyPerformanceModel expectedModel, PartyBackendPerformanceModel grabbedModel) {

		verifyClosedPartyJewelryBonus(grabbedModel.getJewelryBonus(), expectedModel.getJewelryBonus());
		verifyClosedPartyJFourthyDiscount(grabbedModel.getFourthyDiscounts(), expectedModel.getFourthyDiscounts());
		verifyClosedPartyNoOfOrders(grabbedModel.getNoOfOrders(), expectedModel.getNoOfOrders());
//		verifyClosedPartyIp(grabbedModel.getIp(), expectedModel.getIp());
		verifyClosedPartyRetail(grabbedModel.getRetail(), expectedModel.getRetail());
	}

	@Step
	public void verifyClosedPartyJewelryBonus(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: JB doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyJFourthyDiscount(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: 40 discount doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyNoOfOrders(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: No of orders doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyIp(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Ip doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyInPaymentIp(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Ip in payment doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyRetail(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Retail doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

}

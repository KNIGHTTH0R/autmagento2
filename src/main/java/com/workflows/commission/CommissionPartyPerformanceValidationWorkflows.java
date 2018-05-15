package com.workflows.commission;

import com.tools.CustomVerification;
import com.tools.data.frontend.ClosedPartyPerformanceModel;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class CommissionPartyPerformanceValidationWorkflows {
	@Title("Validate that closes party performance is correct")
	@Step
	public void validateClosedPartyPerformance(ClosedPartyPerformanceModel grabbedModel, ClosedPartyPerformanceModel expectedModel) {

		verifyClosedPartyJewelryBonus(grabbedModel.getJewelryBonus(), expectedModel.getJewelryBonus());
		verifyClosedPartyJFourthyDiscount(grabbedModel.getFourthyDiscounts(), expectedModel.getFourthyDiscounts());
		verifyClosedPartyNoOfOrders(grabbedModel.getNoOfOrders(), expectedModel.getNoOfOrders());
		verifyClosedPartyIp(grabbedModel.getIp(), expectedModel.getIp());
		//verifyClosedPartyInPaymentIp(grabbedModel.getIpInPayment(), expectedModel.getIpInPayment());
	//	verifyClosedPartyRetail(grabbedModel.getRetail(), expectedModel.getRetailNoShipping());
	}
	
	@Title("Validate that closes party performance from modal is correct")
	@Step
	public void validateClosedPartyFromModalPerformance(ClosedPartyPerformanceModel grabbedModel, ClosedPartyPerformanceModel expectedModel) {
		System.out.println(grabbedModel);
		System.out.println(expectedModel);
		verifyClosedPartyJewelryBonus(grabbedModel.getJewelryBonus(), expectedModel.getJewelryBonus());
		verifyClosedPartyJFourthyDiscount(grabbedModel.getFourthyDiscounts(), expectedModel.getFourthyDiscounts());
		verifyClosedPartyNoOfOrders(grabbedModel.getNoOfOrders(), expectedModel.getNoOfOrders());
		verifyClosedPartyIp(grabbedModel.getIp(), expectedModel.getIp());
		verifyClosedPartyRetail(grabbedModel.getRetail(), expectedModel.getRetail());
		verifyClosedPartyGuestNo(grabbedModel.getGuestsNo(), "10");
	}

	
	@Step
	private void verifyClosedPartyGuestNo(String guestsNo, String guestNoExpected) {
		CustomVerification.verifyTrue("Failure: No of guests  doesn't match Expected: " + guestsNo + " Actual: " + guestNoExpected, guestsNo.contains(guestNoExpected));
		
	}

	@Step
	public void verifyClosedPartyJewelryBonus(String grabbedValue, String expectedValue) {
		String expectedValue1 = expectedValue.length()>7 ? expectedValue.replaceFirst("[.]", ""):expectedValue;
		CustomVerification.verifyTrue("Failure: JB doesn't match Expected: " + expectedValue1 + " Actual: " + grabbedValue, expectedValue1.contains(grabbedValue));
	}

	@Step
	public void verifyClosedPartyJFourthyDiscount(String grabbedValue, String expectedValue) {
		CustomVerification.verifyTrue("Failure: 40 discount doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyNoOfOrders(String grabbedValue, String expectedValue) {
		CustomVerification.verifyTrue("Failure: No of orders doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyIp(String grabbedValue, String expectedValue) {
		CustomVerification.verifyTrue("Failure: Ip doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyInPaymentIp(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Ip in payment doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyClosedPartyRetail(String grabbedValue, String expectedValue) {
		CustomVerification.verifyTrue("Failure: Retail doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	public void validatePartyPerformanceAfterCancelAnOrder(ClosedPartyPerformanceModel grabbedModel,String jewelryBonus , String fourthyDiscount){
		// TODO Auto-generated method stub
		verifyClosedPartyJewelryBonus(grabbedModel.getJewelryBonus(), jewelryBonus );
		verifyClosedPartyJFourthyDiscount(grabbedModel.getFourthyDiscounts(), fourthyDiscount );
//		verifyClosedPartyNoOfOrders(grabbedModel.getNoOfOrders(), expectedModel.getNoOfOrders());
//		verifyClosedPartyIp(grabbedModel.getIp(), expectedModel.getIp());
//		verifyClosedPartyInPaymentIp(grabbedModel.getIpInPayment(), expectedModel.getIpInPayment());
//		verifyClosedPartyRetail(grabbedModel.getRetail(), expectedModel.getRetail());
	}

}

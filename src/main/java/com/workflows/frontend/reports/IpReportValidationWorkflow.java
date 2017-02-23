package com.workflows.frontend.reports;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import com.steps.frontend.reports.ValidateIpsOverviewSteps;
import com.tools.CustomVerification;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.IpOverviewModel;
import com.tools.data.frontend.TermPurchaseIpModel;

public class IpReportValidationWorkflow {
	@Steps
	ValidateIpsOverviewSteps validateIpSteps;
	
	@Title("Validate current month and next month ips")
	@Step
	public void validateIps(TermPurchaseIpModel expectedModel, TermPurchaseIpModel grabbedModel) {

		verifyCurrentMonthIps(expectedModel.getCurrentMonthIp(), grabbedModel.getCurrentMonthIp());
		verifyNextMonthIps(expectedModel.getNextMonthIp(), grabbedModel.getNextMonthIp());
	}

	@Step
	public void verifyNextMonthIps(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue(
				"Failure: Next month ips don't match  - Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyCurrentMonthIps(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue(
				"Failure: Current month ips don't match  - Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}
	
	@Title("Validate Ips in Ip overview report")
	public void verifyIpOverviewReportDetails(IpOverViewSummaryModel grabbedModel,  IpOverviewModel expectedModel) {
//    System.out.println("model validari grab"+ grabbedModel.toString());
//    System.out.println("model validari expected"+ expectedModel.toString());
		validateIpSteps.verifyPaidOrdersPreviousMonths(grabbedModel.getPaidOrdersPreviosMonth(), expectedModel.getPaidOrdersPreviosMonth());
		System.out.println("model validari expecteds"+ grabbedModel.getPaidOrdersPreviosMonth());
		System.out.println("model validari expectedd"+ expectedModel.getPaidOrdersPreviosMonth());
		validateIpSteps.verifyPaidOrdersCurrentMonth(grabbedModel.getPaidOrdersThisMonth(), expectedModel.getPaidOrdersThisMonth());
		validateIpSteps.verifyReverseChargebacksThisMonth(grabbedModel.getReverseChargebackThisMonth(), expectedModel.getReverseChargebackThisMonth());
		validateIpSteps.verifyChargebacksCurrentMonth(grabbedModel.getChargebacksThisMonth(), expectedModel.getChargebacksThisMonth());
		validateIpSteps.verifyReturns(grabbedModel.getReturnsThisMonth(), expectedModel.getReturnsThisMonth());
//		verifyOpenIpsCurrentMonth(grabbedModel.getIpThisMonth(), expectedModel.getIpThisMonth());
//		verifyOpenIpsPreviousMonth(grabbedModel.getIpLastMonth(), expectedModel.getIpLastMonth());
//		verifyOpenChargebacks(grabbedModel.getOpenChargebacks(), expectedModel.getOpenChargebacks());
	//	verifyIpInTermPurchaseCurrentMonth(grabbedModel.getIpTPOrdersThisMonth(), expectedModel.getIpTPOrdersThisMonth());
	//	verifyIpTermPurchasePreviousMonth(grabbedModel.getIpTPOrdersLastMonth(), expectedModel.getIpTPOrdersLastMonth());
		
	}
	
	
	@Title("Validate Ips in Ip overview report")
	@StepGroup
	public void verifyOpenIpFromOverviewReportDetails(IpOverviewModel grabbedModel, IpOverViewOpenIpsModel expectedModel) {

//		verifyPaidOrdersPreviousMonths(grabbedModel.getPaidOrdersPreviosMonth(), expectedModel.getPaidOrdersPreviosMonth());
//		verifyPaidOrdersCurrentMonth(grabbedModel.getPaidOrdersThisMonth(), expectedModel.getPaidOrdersThisMonth());
//		verifyReverseChargebacksThisMonth(grabbedModel.getReverseChargebackThisMonth(), expectedModel.getReverseChargebackThisMonth());
//		verifyChargebacksCurrentMonth(grabbedModel.getChargebacksThisMonth(), expectedModel.getChargebacksThisMonth());
//		verifyReturns(grabbedModel.getReturnsThisMonth(), expectedModel.getReturnsThisMonth());
//		verifyOpenIpsCurrentMonth(grabbedModel.getIpThisMonth(), expectedModel.getIpThisMonth());
//		verifyOpenIpsPreviousMonth(grabbedModel.getIpLastMonth(), expectedModel.getIpLastMonth());
//		verifyOpenChargebacks(grabbedModel.getOpenChargebacks(), expectedModel.getOpenChargebacks());
	//	verifyIpInTermPurchaseCurrentMonth(grabbedModel.getIpTPOrdersThisMonth(), expectedModel.getIpTPOrdersThisMonth());
	//	verifyIpTermPurchasePreviousMonth(grabbedModel.getIpTPOrdersLastMonth(), expectedModel.getIpTPOrdersLastMonth());
		
	}

//	@Step
//	public void verifyPaidOrdersPreviousMonths(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for paid orders from previous months doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyPaidOrdersCurrentMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for paid orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyReverseChargebacksThisMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for reversed charbacks from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyChargebacksCurrentMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for charbacks from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyReturns(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for returns doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	@Step
//	public void verifyOpenIpsCurrentMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Open Ip value for current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	@Step
//	public void verifyOpenIpsPreviousMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Open Ip value for previous month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	@Step
//	public void verifyOpenChargebacks(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Open Ips value for charbacks from previous and current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	@Step
//	public void verifyIpInTermPurchaseCurrentMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for term purchase orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyIpTermPurchasePreviousMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for term purchase orders from previous month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}

}

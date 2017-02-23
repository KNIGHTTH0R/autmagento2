package com.steps.frontend.reports;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;

public class ValidateIpsOverviewSteps extends AbstractSteps{
	private static final long serialVersionUID = 1L;

	@Step
	public void verifyPaidOrdersPreviousMonths(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for paid orders from previous months doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
		Assert.assertTrue("Failure: Ip value for paid orders from previous months doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	
	@Step
	public void verifyPaidOrdersCurrentMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Ip value for paid orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	
	@Step
	public void verifyReverseChargebacksThisMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Ip value for reversed charbacks from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	
	@Step
	public void verifyChargebacksCurrentMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Ip value for charbacks from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	
	@Step
	public void verifyReturns(String grabbed, String expected) {
		Assert.assertTrue("Failure: Ip value for returns doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	@Step
	public void verifyOpenIpsCurrentMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Open Ip value for current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	@Step
	public void verifyOpenIpsPreviousMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Open Ip value for previous month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	@Step
	public void verifyOpenChargebacks(String grabbed, String expected) {
		Assert.assertTrue("Failure: Open Ips value for charbacks from previous and current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	@Step
	public void verifyIpInTermPurchaseCurrentMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Ip value for term purchase orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}
	
	@Step
	public void verifyIpTermPurchasePreviousMonth(String grabbed, String expected) {
		Assert.assertTrue("Failure: Ip value for term purchase orders from previous month doesn't match Expected: " + expected + " Actual: " + grabbed,
				expected.contains(grabbed));
	}

}

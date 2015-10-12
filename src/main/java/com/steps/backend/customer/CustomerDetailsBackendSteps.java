package com.steps.backend.customer;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.requirements.AbstractSteps;

public class CustomerDetailsBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public LoungeIpPerformanceModel grabSCPerformanceIpLogicAdmin() {
		customerDetailsHomePage().clickOnPerformanceTab();
		return customerDetailsHomePage().grabSCPerformanceIpLogicAdmin();
	}
}

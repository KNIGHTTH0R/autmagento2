package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class ReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void downloadProductsOrderedBySku() {
		reportsPage().downloadProductsOrderedBySku();
		waitABit(10000);
	}

}

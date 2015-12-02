package com.steps.frontend;

import com.tools.requirements.AbstractSteps;

public class ReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void downloadProductsOrderedBySku() {
		reportsPage().downloadProductsOrderedBySku();
		waitABit(10000);
	}

}

package com.steps.external.commission;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.requirements.AbstractSteps;

public class CommissionReportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void simulateMonth() {
		commissionReportPage().simulateMonth();
	}

	@Step
	public void selectMonthToBeClosed() {
		commissionReportPage().selectMonthToBeClosed();
	}

	@Step
	public void selectCloseMonthDate() {
		commissionReportPage().selectCloseMonthDate();
	}

	@StepGroup
	public void closeMonth() {
		commissionReportPage().selectMonthToBeClosed();
		commissionReportPage().selectCloseMonthDate();
		
//		commissionReportPage().closeMonth();
		
	}

}

package com.steps.external.commission;

import java.text.ParseException;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.connectors.http.ApacheHttpHelper;
import com.tools.calculation.ClosedMonthBonusCalculation;
import com.tools.env.constants.JenkinsConstants;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

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
	public void closeMonth() throws Exception {

		if (!DateUtils.isLastDayOfMonth(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")) {
			ApacheHttpHelper.sendGet(JenkinsConstants.REOPEN_MONTH_JOB);
		} else {
			// set commission run date on 15 of the month
		}

		commissionReportPage().selectMonthToBeClosed();
		commissionReportPage().selectCloseMonthDate();
		commissionReportPage().closeMonth();
		// get commission last run
		ClosedMonthBonusCalculation.calculateClosedMonthBonuses("1835", "2015-08-15 00:00:00", DateUtils.getCurrentDate("yyyy-MM-dd") + " 00:00:00");

	}

}

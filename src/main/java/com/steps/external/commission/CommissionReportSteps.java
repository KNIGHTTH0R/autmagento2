package com.steps.external.commission;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.connectors.http.ApacheHttpHelper;
import com.tools.calculation.ClosedMonthBonusCalculation;
import com.tools.data.backend.IpModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.constants.TimeConstants;
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
		commissionReportPage().enterCloseMonthDate();
	}

	@StepGroup
	public RewardPointsOfStylistModel closeMonth(String stylistId) throws Exception {

		if (!DateUtils.isLastDayOfMonth(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")) {
			ApacheHttpHelper.sendGet(JenkinsConstants.REOPEN_MONTH_JOB);
			waitABit(10000);
			getDriver().navigate().refresh();
			waitABit(TimeConstants.TIME_CONSTANT);
		} else {
			// set commission run date on 15 of the month
		}
		commissionReportPage().selectMonthToBeClosed();
		commissionReportPage().enterCloseMonthDate();
		commissionReportPage().saveCommDate();
		commissionReportPage().closeMonth();

		return ClosedMonthBonusCalculation.calculateClosedMonthBonuses(stylistId, "2015-09-15 00:00:00", DateUtils.getCurrentDate("yyyy-MM-dd") + " 00:00:00");

	}

	@StepGroup
	public IpModel closeLastMonthAndGetCurrentMonthIps(String stylistId) throws Exception {
		
		ApacheHttpHelper.sendGet(JenkinsConstants.IMPORT_ALL_JOB);
		ApacheHttpHelper.sendGet(JenkinsConstants.REOPEN_MONTH_JOB);
	
		waitABit(10000);
		getDriver().navigate().refresh();
		waitABit(TimeConstants.TIME_CONSTANT);
		commissionReportPage().selectMonthToBeClosed();
		commissionReportPage().enterCloseMonthDate();
		commissionReportPage().saveCommDate();
		commissionReportPage().closeMonth();

		return ClosedMonthBonusCalculation.calculateCurrentMonthBonuses(stylistId, DateUtils.getCurrentDate("yyyy-MM-dd") + " 00:00:00",
				DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));

	}

}

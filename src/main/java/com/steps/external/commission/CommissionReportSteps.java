package com.steps.external.commission;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import com.connectors.http.ApacheHttpHelper;
import com.tools.data.backend.IpModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.env.constants.DateConstants;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.generalCalculation.ClosedMonthBonusCalculation;
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
	public RewardPointsOfStylistModel closeMonthAndCalculateRewardPoints(String stylistId, String activationDate, String lastCommissionRunDate) throws Exception {

		// if
		// (!DateUtils.isLastDayOfMonth(DateUtils.getCurrentDate(DateConstants.FORMAT),
		// DateConstants.FORMAT)) {
		ApacheHttpHelper.sendGet(JenkinsConstants.REOPEN_MONTH_JOB);
		waitABit(10000);
		getDriver().navigate().refresh();
		waitABit(TimeConstants.TIME_CONSTANT);
		// } else {
		// // set commission run date on 15 of the month
		// }
		commissionReportPage().selectMonthToBeClosed();
		commissionReportPage().enterCloseMonthDate();
		commissionReportPage().saveCommDate();
		commissionReportPage().closeMonth();

		return ClosedMonthBonusCalculation.calculateClosedMonthBonuses(stylistId, activationDate, lastCommissionRunDate, DateUtils.getCurrentDateBegining(DateConstants.FORMAT));

	}

	@Title("Close last month and get current month ips")
	@StepGroup
	public IpModel closeLastMonthAndGetCurrentMonthIps(String stylistId) throws Exception {

		// ApacheHttpHelper.sendGet(JenkinsConstants.IMPORT_ALL_JOB);
		ApacheHttpHelper.sendGet(JenkinsConstants.REOPEN_MONTH_JOB);

		waitABit(10000);
		getDriver().navigate().refresh();
		waitABit(TimeConstants.TIME_CONSTANT);
		commissionReportPage().selectMonthToBeClosed();
		commissionReportPage().enterCloseMonthDate();
		commissionReportPage().saveCommDate();
		commissionReportPage().closeMonth();

		return ClosedMonthBonusCalculation.calculateCurrentMonthBonuses(stylistId, DateUtils.getCurrentDateBegining(DateConstants.FORMAT),
				DateUtils.getCurrentDate(DateConstants.FORMAT));

	}

}

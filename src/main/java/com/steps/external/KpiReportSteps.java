package com.steps.external;

import com.tools.data.KpiReportModelInfo;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Steps;

public class KpiReportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;
	
	@Steps
	KpiReportValidationSteps kpiReportValidation;

	public void validateKpi(KpiReportModelInfo kpiCalculated, KpiReportModelInfo kpiGrabbed) throws Exception {

		kpiReportValidation.validateSalesforceBeginning(kpiCalculated.getSalesforceBeginning(), kpiGrabbed.getSalesforceBeginning());
		kpiReportValidation.validateRecruites(kpiCalculated.getRecruits(), kpiGrabbed.getRecruits());
		kpiReportValidation.validateDeactivated(kpiCalculated.getDeactivated(), kpiGrabbed.getDeactivated());
		kpiReportValidation.validateSfEnding(kpiCalculated.getSfEnding(), kpiGrabbed.getSfEnding());
		kpiReportValidation.validatePerfomingSf(kpiCalculated.getPerformingSf(),kpiGrabbed.getPerformingSf());
		kpiReportValidation.validateTotalParties(kpiCalculated.getTotalParties(), kpiGrabbed.getTotalParties());
		kpiReportValidation.validateAveragePartiesPerActiveSf(kpiCalculated.getAvgPartiesPerActiveSf(), kpiGrabbed.getAvgPartiesPerActiveSf());
		kpiReportValidation.validateTotalFollowUpPaties(kpiCalculated.getTotalFollowUpParties(), kpiGrabbed.getTotalFollowUpParties());
		kpiReportValidation.validateAverageFollowUpPartiesPerActiveSf(kpiCalculated.getAvgFollowUpPartiesPerActiveSf(), kpiGrabbed.getAvgFollowUpPartiesPerActiveSf());
		kpiReportValidation.validateAverageGuest(kpiCalculated.getAvgGuest(), kpiGrabbed.getAvgGuest());
		kpiReportValidation.validateAverageBuyingGuest(kpiCalculated.getAvgBuyingguest(), kpiGrabbed.getAvgBuyingguest());
		kpiReportValidation.validateBuyingRate(kpiCalculated.getBuyingRate(), kpiGrabbed.getBuyingRate());
		kpiReportValidation.validatePartyAverageBeforeReturnInclVat(kpiCalculated.getPartyAvgBeforeReturnIncVat(), kpiGrabbed.getPartyAvgBeforeReturnIncVat());
		kpiReportValidation.validatePartyAverageBeforeReturnExclVat(kpiCalculated.getPartyAvgBeforeReturnExclVat(), kpiGrabbed.getPartyAvgBeforeReturnExclVat());
		kpiReportValidation.validateOnlineSalesInclVat(kpiCalculated.getOnlineSalesInclVat(), kpiGrabbed.getOnlineSalesInclVat());
		kpiReportValidation.validateOnlineSalesExclVat(kpiCalculated.getOnlineSalesExclVatl(), kpiGrabbed.getOnlineSalesExclVatl());
		kpiReportValidation.validateSFMInclVat(kpiCalculated.getSfmInclVat(), kpiGrabbed.getSfmInclVat());
		kpiReportValidation.validateSFMExclVat(kpiCalculated.getSfmExclVat(), kpiGrabbed.getSfmExclVat());
		kpiReportValidation.validateTurnoverBeforeReturnsInclVat(kpiCalculated.getTotalTurnoverBeforeReturnsInclVat(), kpiGrabbed.getTotalTurnoverBeforeReturnsInclVat());
		kpiReportValidation.validateTurnoverBeforeReturnsExclVat(kpiCalculated.getTotalTurnoverBeforeReturnsExclVat(), kpiGrabbed.getTotalTurnoverBeforeReturnsExclVat());
		kpiReportValidation.validateReturnsInclVat(kpiCalculated.getReturnsInclVat(), kpiGrabbed.getReturnsInclVat());
		kpiReportValidation.validateReturnsExclVat(kpiCalculated.getReturnsExclVat(), kpiGrabbed.getReturnsExclVat());
		kpiReportValidation.validateReturnRate(kpiCalculated.getReturnRate(), kpiGrabbed.getReturnRate());
		kpiReportValidation.validateTurnoverAfterReturnsInclVat(kpiCalculated.getTotalTurnoverAfterReturnsInclVat(), kpiGrabbed.getTotalTurnoverAfterReturnsInclVat());
		kpiReportValidation.validateTurnoverAfterReturnsExclVat(kpiCalculated.getTotalTurnoverAfterReturnsExclVat(), kpiGrabbed.getTotalTurnoverAfterReturnsExclVat());
		kpiReportValidation.validatePlannedPartyInMonth1(kpiCalculated.getPartiesPlannedInMonth1(), kpiGrabbed.getPartiesPlannedInMonth1());
		kpiReportValidation.validatePlannedPartyInMonth2(kpiCalculated.getPartiesPlannedInMonth2(), kpiGrabbed.getPartiesPlannedInMonth3());
		kpiReportValidation.validatePlannedPartyInMonth3(kpiCalculated.getPartiesPlannedInMonth3(), kpiGrabbed.getPartiesPlannedInMonth4());
		kpiReportValidation.validatePlannedPartyInMonth4(kpiCalculated.getPartiesPlannedInMonth4(), kpiGrabbed.getPartiesPlannedInMonth4());
		kpiReportValidation.validatePlannedPartyCurrentWeek(kpiCalculated.getPartiesPlannedCurrentWeek(), kpiGrabbed.getPartiesPlannedCurrentWeek());
		kpiReportValidation.validatePlannedPartyWeek1(kpiCalculated.getPartiesPlannedWeek1(), kpiGrabbed.getPartiesPlannedWeek1());
		kpiReportValidation.validatePlannedPartyWeek2(kpiCalculated.getPartiesPlannedWeek2(), kpiGrabbed.getPartiesPlannedWeek2());
		kpiReportValidation.validatePlannedPartyWeek3(kpiCalculated.getPartiesPlannedWeek3(), kpiGrabbed.getPartiesPlannedWeek3());
		kpiReportValidation.validatePlannedPartyWeek4(kpiCalculated.getPartiesPlannedWeek4(), kpiGrabbed.getPartiesPlannedWeek4());
		kpiReportValidation.validatePlannedPartyWeek5(kpiCalculated.getPartiesPlannedWeek5(), kpiGrabbed.getPartiesPlannedWeek5());
		kpiReportValidation.validatePlannedPartyWeek6(kpiCalculated.getPartiesPlannedWeek6(), kpiGrabbed.getPartiesPlannedWeek6());
		kpiReportValidation.validatePlannedPartyWeek7(kpiCalculated.getPartiesPlannedWeek7(), kpiGrabbed.getPartiesPlannedWeek7());
		kpiReportValidation.validatePlannedPartyWeek8(kpiCalculated.getPartiesPlannedWeek8(), kpiGrabbed.getPartiesPlannedWeek8());
		kpiReportValidation.validatePlannedPartyWeek9(kpiCalculated.getPartiesPlannedWeek9(), kpiGrabbed.getPartiesPlannedWeek9());
		kpiReportValidation.validatePlannedPartyWeek10(kpiCalculated.getPartiesPlannedWeek10(), kpiGrabbed.getPartiesPlannedWeek10());
			
	}

	
	
	
	
}

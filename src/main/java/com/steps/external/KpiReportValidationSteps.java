package com.steps.external;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class KpiReportValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;


	
	@Step
	public void validateSalesforceBeginning(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: alesforceBeginning does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateRecruites(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: Recruites does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	@Step
	public void validateDeactivated(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: Deactivated does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateSfEnding(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: SfEnding does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validatePerfomingSf(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PerfomingSf does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateTotalParties(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: TotalParties does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateAveragePartiesPerActiveSf(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: AveragePartiesPerActiveSf does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateTotalFollowUpPaties(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: TotalFollowUpPaties does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateAverageFollowUpPartiesPerActiveSf(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: AverageFollowUpPartiesPerActiveSf does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateAverageGuest(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: AverageGuest does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateAverageBuyingGuest(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: AverageBuyingGuest does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateBuyingRate(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: BuyingRate does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	@Step
	public void validatePartyAverageBeforeReturnInclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PartyAverageBeforeReturnInclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validatePartyAverageBeforeReturnExclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PartyAverageBeforeReturnExlVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	
	@Step
	public void validateOnlineSalesInclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: OnlineSalesInclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateOnlineSalesExclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: OnlineSalesExclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateSFMInclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: SFMInclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateSFMExclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: SFMExclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateTurnoverBeforeReturnsInclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: TurnoverBeforeReturnsInclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateTurnoverBeforeReturnsExclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: TurnoverBeforeReturnsExclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}

	@Step
	public void validateReturnsInclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: ReturnsInclVat( does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateReturnsExclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: ReturnsExclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateReturnRate(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: ReturnRate does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateTurnoverAfterReturnsInclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: TurnoverAfterReturnsInclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validateTurnoverAfterReturnsExclVat(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: TurnoverAfterReturnsExclVat does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyInMonth1(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedPartyInMonth1 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	
	@Step
	public void validatePlannedPartyInMonth2(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedPartyInMonth2 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	
	@Step
	public void validatePlannedPartyInMonth3(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedPartyInMonth3 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyInMonth4(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedPartyInMonth4 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	
	@Step
	public void validatePlannedPartyCurrentWeek(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty CurrentWeek does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek1(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week1 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek2(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week2 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek3(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week3 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek4(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week4 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek5(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week5 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek6(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week6 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek7(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week7 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek8(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week8 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek9(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week9 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	@Step
	public void validatePlannedPartyWeek10(String calculated, String compare) {

		CustomVerification.verifyTrue("Failure: PlannedParty Week10 does not match calculated: " + calculated
				+ " - grabbbed: " + compare, calculated.contentEquals(compare));
	}
	
	
}

package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.requirements.AbstractSteps;

public class ValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Steps
	public static CustomVerification customVerification;

	@StepGroup
	public void validateStylistData(StylistDataModel initialData, StylistDataModel finalData) {

		printStylistBackendValues("INITIAL", initialData.getCustomerLeads(), initialData.getHostessLeads(), initialData.getHostessLeadsWeek(), initialData.getStyleCoachLeads(),
				initialData.getStyleCoachLeadsWeek());
		printStylistBackendValues("FINAL", finalData.getCustomerLeads(), finalData.getHostessLeads(), finalData.getHostessLeadsWeek(), finalData.getStyleCoachLeads(),
				finalData.getStyleCoachLeadsWeek());

		verifyValues("Customer Leads ", initialData.getCustomerLeads(), finalData.getCustomerLeads());
		verifyValues("Hostess Leads ", initialData.getHostessLeads(), finalData.getHostessLeads());
		verifyValues("Hostess Leads Week ", initialData.getHostessLeadsWeek(), finalData.getHostessLeadsWeek());

	}

	@StepGroup
	public void validateCustomerLeadData(DBStylistModel initialData, StylistDataModel finalData) {

		verifyValues("Customer Leads ", initialData.getTotalCustomerReceived(), finalData.getCustomerLeads());

	}

	@StepGroup
	public void validateStyleCoachLeadData(DBStylistModel initialData, StylistDataModel finalData) {

		verifyValues("Customer Leads ", initialData.getTotalSCReceived(), finalData.getStyleCoachLeads());
		verifyValues("Customer Leads ", initialData.getTotalSCCurrentWeek(), finalData.getStyleCoachLeadsWeek());

	}

	@StepGroup
	public void validateHostLeadData(DBStylistModel initialData, StylistDataModel finalData) {

		verifyValues("Hostess Leads ", initialData.getTotalHostReceived(), finalData.getHostessLeads());
		verifyValues("Hostess Leads Week ", initialData.getTotalHostCurrentWeek(), finalData.getHostessLeadsWeek());

	}

	@Step
	public void verifyValues(String message, String initialStr, String finalStr) {
		int initialInt, finalInt;

		initialInt = Integer.valueOf(initialStr) + 1;
		finalInt = Integer.valueOf(finalStr);

		CustomVerification.verifyTrue(message + " values are incorrect:  Expected - " + initialInt + "  Actual - " + finalInt, initialInt == finalInt);
		System.out.println("Verified " + message + " ... ");
	}

}

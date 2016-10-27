package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

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

		verifyValues("Customer Leads ", initialData.getCustomerLeads(), finalData.getCustomerLeads());
		verifyValues("Hostess Leads ", initialData.getHostessLeads(), finalData.getHostessLeads());
		verifyValues("Hostess Leads Week ", initialData.getHostessLeadsWeek(), finalData.getHostessLeadsWeek());

	}

	@Title("Validate customer leads number")
	@StepGroup
	public void validateCustomerLeadData(DBStylistModel initialData, StylistDataModel finalData) {

		verifyValues("Customer Leads ", initialData.getTotalCustomerReceived(), finalData.getCustomerLeads());

	}

	@Title("Validate customer leads and style coach leads number")
	@StepGroup
	public void validateStyleCoachLeadData(DBStylistModel initialData, StylistDataModel finalData) {
		verifyValues("Customer Leads", initialData.getTotalCustomerReceived(), finalData.getCustomerLeads());
		verifyValues("Style Coach Leads", initialData.getTotalSCReceived(), finalData.getStyleCoachLeads());
		verifyValues("Style Coach Leads Week ", initialData.getTotalSCCurrentWeek(), finalData.getStyleCoachLeadsWeek());

	}

	@Title("Validate customer leads and host leads number")
	@StepGroup
	public void validateHostLeadData(DBStylistModel initialData, StylistDataModel finalData) {
		verifyValues("Customer Leads ", initialData.getTotalCustomerReceived(), finalData.getCustomerLeads());
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

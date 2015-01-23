package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.data.StylistDataModel;

public class ValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@StepGroup
	public void validateStylistData(StylistDataModel initialData, StylistDataModel finalData) {

		printStylistBackendValues("INITIAL", initialData.getCustomerLeads(), initialData.getHostessLeads(), initialData.getHostessLeadsWeek(), initialData.getStyleCoachLeads(), initialData.getStyleCoachLeadsWeek());
		printStylistBackendValues("FINAL", finalData.getCustomerLeads(), finalData.getHostessLeads(), finalData.getHostessLeadsWeek(), finalData.getStyleCoachLeads(), finalData.getStyleCoachLeadsWeek());

		verifyValues(initialData.getCustomerLeads(), finalData.getCustomerLeads(), "Customer Leads ");
		verifyValues(initialData.getHostessLeads(), finalData.getHostessLeads(), "Hostess Leads ");
		verifyValues(initialData.getHostessLeadsWeek(), finalData.getHostessLeadsWeek(), "Hostess Leads Week ");

	}

	@Step
	public void verifyValues(String initialStr, String finalStr, String message) {
		int initialInt, finalInt;

		initialInt = Integer.valueOf(initialStr) + 1;
		finalInt = Integer.valueOf(finalStr);

		Assert.assertTrue(message + " values are incorrect:  Expected - " + initialInt + "  Actual - " + finalInt, initialInt == finalInt);
		System.out.println("Verified " + message + " ... ");
	}
	
	

}

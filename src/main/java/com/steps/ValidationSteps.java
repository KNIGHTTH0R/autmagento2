package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.data.StylistDataModel;

public class ValidationSteps extends AbstractSteps {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@StepGroup
	public void validateStylistData(StylistDataModel initialData,
			StylistDataModel finalData) {

		System.out.println("Initial customerLeads: "
				+ initialData.getCustomerLeads());
		System.out.println("Initial hostessLeads: " + initialData.getHostessLeads());
		System.out.println("Initial hostessLeadsWeek: "
				+ initialData.getHostessLeadsWeek());
		System.out.println("Initial styleCoachLeads: "
				+ initialData.getStyleCoachLeads());
		System.out.println("Initial styleCoachLeadsWeek: "
				+ initialData.getStyleCoachLeadsWeek());

		System.out
				.println("--------------------------------------------------");

		System.out.println("Final customerLeads: " + finalData.getCustomerLeads());
		System.out.println("Final hostessLeads: " + finalData.getHostessLeads());
		System.out.println("Final hostessLeadsWeek: "
				+ finalData.getHostessLeadsWeek());
		System.out.println("Final styleCoachLeads: "
				+ finalData.getStyleCoachLeads());
		System.out.println("Final styleCoachLeadsWeek: "
				+ finalData.getStyleCoachLeadsWeek());


		verifyValues(initialData.getCustomerLeads(), finalData.getCustomerLeads(), "Customer Leads ");
		verifyValues(initialData.getHostessLeads(), finalData.getHostessLeads(), "Hostess Leads ");
		verifyValues(initialData.getHostessLeadsWeek(), finalData.getHostessLeadsWeek(), "Hostess Leads Week ");

		failThis();

	}

	@Step
	public void failThis() {
		Assert.assertTrue("We want it to fail! ", true == false);		
	}

	@Step
	public void verifyValues(String initialStr, String finalStr, String message) {
		int initialInt, finalInt;

		initialInt = Integer.valueOf(initialStr) + 1;
		finalInt = Integer.valueOf(finalStr);
		
		Assert.assertTrue(message + " values are incorrect:  Expected - "
				+ initialInt + "  Actual - " + finalInt, initialInt == finalInt);
		System.out.println("Verified " + message +  " ... ");
	}

}

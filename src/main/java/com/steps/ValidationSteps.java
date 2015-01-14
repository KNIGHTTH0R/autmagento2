package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.data.ValidationModel;

public class ValidationSteps extends AbstractSteps {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@StepGroup
	public void validateStylistData(ValidationModel initialData,
			ValidationModel finalData) {

		System.out.println("Initial customerLeads: "
				+ initialData.customerLeads);
		System.out.println("Initial hostessLeads: " + initialData.hostessLeads);
		System.out.println("Initial hostessLeadsWeek: "
				+ initialData.hostessLeadsWeek);
		System.out.println("Initial styleCoachLeads: "
				+ initialData.styleCoachLeads);
		System.out.println("Initial styleCoachLeadsWeek: "
				+ initialData.styleCoachLeadsWeek);

		System.out
				.println("--------------------------------------------------");

		System.out.println("Final customerLeads: " + finalData.customerLeads);
		System.out.println("Final hostessLeads: " + finalData.hostessLeads);
		System.out.println("Final hostessLeadsWeek: "
				+ finalData.hostessLeadsWeek);
		System.out.println("Final styleCoachLeads: "
				+ finalData.styleCoachLeads);
		System.out.println("Final styleCoachLeadsWeek: "
				+ finalData.styleCoachLeadsWeek);


		verifyValues(initialData.customerLeads, finalData.customerLeads, "Customer Leads ");
		verifyValues(initialData.hostessLeads, finalData.hostessLeads, "Hostess Leads ");
		verifyValues(initialData.hostessLeadsWeek, finalData.hostessLeadsWeek, "Hostess Leads Week ");

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

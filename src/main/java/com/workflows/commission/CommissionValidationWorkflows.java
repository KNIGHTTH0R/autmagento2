package com.workflows.commission;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBStylistModel;

public class CommissionValidationWorkflows {

	@Step
	public void validateCommssionStylistProperties(CommissionStylistModel grabbedModel, DBStylistModel expectedModel) {
		verifyStylistId(grabbedModel.getStylistId(), expectedModel.getStylistId());
		verifyCustomerId(grabbedModel.getCustomerId(), expectedModel.getCustomerId());
		verifyParentId(grabbedModel.getParentStylistId(), expectedModel.getParentId());
		verifyName(grabbedModel.getName(), expectedModel.getFirstName() + " " + expectedModel.getLastName());
		verifyStylistPostCode(grabbedModel.getPostcode(), expectedModel.getPostCode());
		verifyStreetAddress(grabbedModel.getStreet(), expectedModel.getStreet() + ", " + expectedModel.getHouseNumber());

	}

	@Step
	public void verifyStylistId(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Stylist id doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyCustomerId(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Customer id doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyParentId(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Parent id (sponsor) doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Name doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyStylistPostCode(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Postal code doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyStreetAddress(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Street address doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyCountry(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Street address doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

}

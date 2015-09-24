package com.workflows.commission;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBStylistModel;

public class CommissionStylistValidationWorkflows {

	@Step
	public void validateCommssionStylistProperties(CommissionStylistModel grabbedModel, DBStylistModel expectedModel) {

		verifyStylistId(grabbedModel.getStylistId(), expectedModel.getStylistId());
		verifyCustomerId(grabbedModel.getCustomerId(), expectedModel.getCustomerId());
		verifyParentId(grabbedModel.getParentStylistId(), expectedModel.getParentId());
		verifyName(grabbedModel.getName(), expectedModel.getFirstName() + " " + expectedModel.getLastName());
		verifyStylistPostCode(grabbedModel.getPostcode(), expectedModel.getPostCode());
		verifyStreetAddress(grabbedModel.getStreet(), expectedModel.getStreet() + ", " + expectedModel.getHouseNumber());
		verifyCountry(grabbedModel.getCountryId().toLowerCase(), expectedModel.getWebsite());
		verifyStatus(grabbedModel.getStatus(), "inactive");
		verifyJoinAt(grabbedModel.getJoinedAt(), expectedModel.getCreatedAt());
		verifyCreatedAt(grabbedModel.getCreatedAt(), expectedModel.getCreatedAt());
		verifyActivatedAt(grabbedModel.getActivatedAt(), expectedModel.getActivatedAt());
		verifyVatPayer(grabbedModel.getBankAccountVatPayer(), expectedModel.getVatPayer());
		verifyVatNumber(grabbedModel.getBankAccountVatNumber(), expectedModel.getVatNumber());
		verifyCareer(grabbedModel.getCareer(), "Style Coach");

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
		CustomVerification.verifyTrue("Failure: Contry doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyStatus(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Status doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contentEquals(grabbedValue));
	}

	@Step
	public void verifyJoinAt(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: join at date doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyCreatedAt(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: created at date doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyActivatedAt(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: activated at date doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyVatPayer(String expectedValue, String grabbedValue) {
		System.out.println(expectedValue+grabbedValue);
		CustomVerification.verifyTrue("Failure: vat payer status doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyVatNumber(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: vat number status doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue,
				(expectedValue == null ? grabbedValue == null : expectedValue.contains(grabbedValue)));
	}

	@Step
	public void verifyCareer(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: career doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

}

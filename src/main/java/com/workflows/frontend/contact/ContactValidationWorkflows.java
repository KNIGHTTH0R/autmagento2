package com.workflows.frontend.contact;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.ContactModel;

public class ContactValidationWorkflows {

	@Step
	public void validateContactDetails(ContactModel expectedModel, ContactModel grabbedModel) {

		verifyName(expectedModel.getName(), grabbedModel.getName());
		verifyStreet(expectedModel.getStreet(), grabbedModel.getStreet());
		verifyNumber(expectedModel.getNumber(), grabbedModel.getNumber());
		verifyZip(expectedModel.getZip(), grabbedModel.getZip());
		verifyTown(expectedModel.getTown(), grabbedModel.getTown());
		verifyCountry(expectedModel.getCountry(), grabbedModel.getCountry());
		verifyCreatedAt(expectedModel.getCreatedAt(), grabbedModel.getCreatedAt());
		verifyPartyHostStatus(expectedModel.getPartyHostStatus(), grabbedModel.getPartyHostStatus());
		verifyStylecoachFlagStatus(expectedModel.getStyleCoachStatus(), grabbedModel.getStyleCoachStatus());
		verifyNewsletterFlagStatus(expectedModel.getNewsletterStatus(), grabbedModel.getNewsletterStatus());
	}

	@Step
	public void verifyName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Name doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contains(expectedValue));
	}

	@Step
	public void verifyStreet(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Street doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contains(expectedValue));
	}

	@Step
	public void verifyNumber(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Number doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contains(expectedValue));
	}

	@Step
	public void verifyZip(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: ZIP doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyTown(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Town doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyCountry(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Country doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyCreatedAt(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Created at date doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contains(expectedValue));
	}

	@Step
	public void verifyPartyHostStatus(String expectedValue, String grabbedValue) {
		CustomVerification
				.verifyTrue("Failure: Party host status doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyStylecoachFlagStatus(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Stylecoach flag status doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyNewsletterFlagStatus(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Newsletter flag status doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}

}

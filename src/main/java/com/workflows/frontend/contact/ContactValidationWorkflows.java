package com.workflows.frontend.contact;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.ContactModel;

public class ContactValidationWorkflows {

	@Step
	public void validateContactDetails(ContactModel grabbedModel, ContactModel expectedModel) {

		verifyName(grabbedModel.getName(), expectedModel.getName());
		verifyStreet(grabbedModel.getStreet(), expectedModel.getStreet());
		verifyNumber(grabbedModel.getNumber(), expectedModel.getNumber());
		verifyZip(grabbedModel.getZip(), expectedModel.getZip());
		verifyTown(grabbedModel.getTown(), expectedModel.getTown());
		verifyCountry(grabbedModel.getCountry(), expectedModel.getCountry());
		verifyCreatedAt(grabbedModel.getCreatedAt(), expectedModel.getCreatedAt());
		verifyPartyHostStatus(grabbedModel.getPartyHostStatus(), expectedModel.getPartyHostStatus());
		verifyStylecoachFlagStatus(grabbedModel.getStyleCoachStatus(), expectedModel.getStyleCoachStatus());
		verifyNewsletterFlagStatus(grabbedModel.getNewsletterStatus(), expectedModel.getNewsletterStatus());
	}

	@Step
	public void verifyName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Name doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.contentEquals(expectedValue));
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

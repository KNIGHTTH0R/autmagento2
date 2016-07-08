package com.workflows.frontend.contact;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.Separators;

public class ContactValidationWorkflows {

	public ContactModel populateExpectedContactModel(CustomerFormModel oldStylecoachModel, CustomerFormModel customerModel, DateModel dateModel, AddressModel addressModel) {

		ContactModel result = new ContactModel();

		result.setName(customerModel.getFirstName() + Separators.SPACE + customerModel.getLastName());
		result.setCreatedAt(dateModel.getDate());
		result.setStreet(addressModel.getStreetAddress());
		result.setNumber(addressModel.getStreetNumber());
		result.setZip(addressModel.getPostCode());
		result.setTown(addressModel.getHomeTown());
		result.setCountry(addressModel.getCountryName());
		result.setLastHistoryRegistration(oldStylecoachModel.getFirstName() + Separators.SPACE + oldStylecoachModel.getLastName());
		
		return result;
	}
	@Title("Validate contact details")
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
		verifyLastHistoryRegistration(expectedModel.getLastHistoryRegistration(), grabbedModel.getLastHistoryRegistration());
	}

	@Step
	public void verifyLastHistoryRegistration(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Last history registration doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contains(expectedValue));
	}

	@Step
	public void verifyName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Name doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, grabbedValue.equalsIgnoreCase(expectedValue));
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

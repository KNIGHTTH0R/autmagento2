package com.workflows.backend.contact;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactDetailsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;

public class ContactBackendValidationWorkflows {

	public ContactDetailsModel populateContactDetailsModel(CustomerFormModel customerFormModel, AddressModel addressModel, DateModel dateModel, String parentId) {

		ContactDetailsModel result = new ContactDetailsModel();

		result.setEmail(customerFormModel.getEmailName());
		result.setCountryCode(addressModel.getPostCode());
		result.setHouseNumber(addressModel.getStreetNumber());
		result.setActivatedAt(dateModel.getDate());
		result.setPlz(addressModel.getPostCode());
		result.setStreetAddress(addressModel.getStreetAddress());
		result.setParentId(parentId);

		return result;
	}

	@StepGroup
	public void verifyContactDetails(ContactDetailsModel grabbedModel, ContactDetailsModel expectedModel) {

		verifyEmail(grabbedModel.getEmail(), expectedModel.getEmail());
		verifyCountryCode(grabbedModel.getCountryCode(), expectedModel.getCountryCode());
		verifyHouseNumber(grabbedModel.getHouseNumber(), expectedModel.getHouseNumber());
		verifyActivatedAt(grabbedModel.getActivatedAt(), expectedModel.getActivatedAt());
		verifyPlz(grabbedModel.getPlz(), expectedModel.getPlz());
		verifyStreetAddress(grabbedModel.getStreetAddress(), expectedModel.getStreetAddress());
		verifyParentId(grabbedModel.getParentId(), expectedModel.getParentId());
	}

	@Step
	public void verifyEmail(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Emails doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

	@Step
	public void verifyCountryCode(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Country code doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

	@Step
	public void verifyHouseNumber(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: House number doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

	@Step
	public void verifyActivatedAt(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Date doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

	@Step
	public void verifyPlz(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Plz doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

	@Step
	public void verifyStreetAddress(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Street address doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

	@Step
	public void verifyParentId(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Parent id doesn't match Expected: " + expected + " Actual: " + grabbed, grabbed.contentEquals(expected));
	}

}

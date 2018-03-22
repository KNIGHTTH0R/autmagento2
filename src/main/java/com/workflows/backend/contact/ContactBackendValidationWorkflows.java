package com.workflows.backend.contact;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import java.util.List;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactDetailsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;

public class ContactBackendValidationWorkflows {

	public ContactDetailsModel populateContactDetailsModel(CustomerFormModel customerFormModel,
			AddressModel addressModel, DateModel dateModel, String parentId) {

		ContactDetailsModel result = new ContactDetailsModel();

		result.setEmail(customerFormModel.getEmailName());
		result.setCountryCode(MongoReader.getContext().toUpperCase());
		result.setHouseNumber(addressModel.getStreetNumber());
		result.setActivatedAt(dateModel.getDate());
		result.setPlz(addressModel.getPostCode());
		result.setStreetAddress(addressModel.getStreetAddress());
		result.setParentId(parentId);

		return result;
	}
	
	public ContactDetailsModel populateSimpleContactDetailsModel(CustomerFormModel customerFormModel,
			AddressModel addressModel, String parentId) {

		ContactDetailsModel result = new ContactDetailsModel();

		result.setEmail(customerFormModel.getEmailName());
		result.setCountryCode(MongoReader.getContext().toUpperCase());
		result.setHouseNumber(addressModel.getStreetNumber());
		result.setPlz(addressModel.getPostCode());
		result.setStreetAddress(addressModel.getStreetAddress());
		result.setParentId(parentId);

		return result;
	}

	public ContactDetailsModel populateContactDetailsModel(CustomerFormModel customerFormModel,
			AddressModel addressModel, DateModel dateModel) {

		ContactDetailsModel result = new ContactDetailsModel();

		result.setEmail(customerFormModel.getEmailName());
		result.setCountryCode(MongoReader.getContext().toUpperCase());
		result.setHouseNumber(addressModel.getStreetNumber());
		result.setActivatedAt(dateModel.getDate());
		result.setPlz(addressModel.getPostCode());
		result.setStreetAddress(addressModel.getStreetAddress());

		return result;
	}

	@Title("Validate contact details")
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
	
	@Title("Validate contact details")
	@StepGroup
	public void verifyCreatedContactDetails(ContactDetailsModel grabbedModel, ContactDetailsModel expectedModel) {

		verifyEmail(grabbedModel.getEmail(), expectedModel.getEmail());
		verifyCountryCode(grabbedModel.getCountryCode(), expectedModel.getCountryCode());
		verifyHouseNumber(grabbedModel.getHouseNumber(), expectedModel.getHouseNumber());
		verifyPlz(grabbedModel.getPlz(), expectedModel.getPlz());
		verifyStreetAddress(grabbedModel.getStreetAddress(), expectedModel.getStreetAddress());
		verifyParentId(grabbedModel.getParentId(), expectedModel.getParentId());
	}
	
	@Title("Validate contact details")
	@StepGroup
	public void verifyCreatedContactDetailsByAcceptinInvitation(ContactDetailsModel grabbedModel, ContactDetailsModel expectedModel) {

		verifyEmail(grabbedModel.getEmail(), expectedModel.getEmail());
		verifyCountryCode(grabbedModel.getCountryCode(), expectedModel.getCountryCode());
		verifyParentId(grabbedModel.getParentId(), expectedModel.getParentId());
	}

	@Title("Validate contact details")
	@StepGroup
	public void verifyUnbounceContactDetails(ContactDetailsModel grabbedModel, ContactDetailsModel expectedModel) {

		verifyEmail(grabbedModel.getEmail(), expectedModel.getEmail());
		verifyActivatedAt(grabbedModel.getActivatedAt(), expectedModel.getActivatedAt());
		verifyPlz(grabbedModel.getPlz(), expectedModel.getPlz());
	}

	public void validateContactsParentStylecoach(List<DBStylistModel> stylistsList, String stylecoachName) {
		boolean match = false;

		for (DBStylistModel stylist : stylistsList) {
			if (stylecoachName.contains(stylist.getFirstName() + " " + stylist.getLastName())) {
				match = true;
				break;
			}
		}
		Assert.assertTrue("The contact is not distributed to the expected stylecoach", match);
	}

	@Step
	public void verifyEmail(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Emails doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyCountryCode(String grabbed, String expected) {
		CustomVerification.verifyTrue(
				"Failure: Country code doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyHouseNumber(String grabbed, String expected) {
		CustomVerification.verifyTrue(
				"Failure: House number doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyActivatedAt(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Date doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contains(expected));
	}

	@Step
	public void verifyPlz(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Plz doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyStreetAddress(String grabbed, String expected) {
		CustomVerification.verifyTrue(
				"Failure: Street address doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyParentId(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Parent id doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

}

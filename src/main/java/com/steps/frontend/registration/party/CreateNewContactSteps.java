package com.steps.frontend.registration.party;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class CreateNewContactSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void fillCreateNewContact(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetails(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().checkMember();
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}
	@Step
	public void fillCreateNewContactWithoutPlz(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetailsWithoutPlz(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().checkMember();
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void fillCreateNewContactWithoutAnyInterrest(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetails(addressData);
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void fillCreateNewContactWithoutAnyInterrestAndWithoutEmail(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		fillContactDetails(addressData);
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void fillCreateNewContactWithoutScInterrest(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetails(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void fillCreateNewContactWithoutScInterrestAmdWithoutEmail(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		fillContactDetails(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void fillCreateNewContactWithoutUnrequiredAddressDetailsAndWithoutInterrests(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillCountry(addressData);
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Title("Fill new contact details and address")
	@Step
	public void fillCreateNewContactDirectly(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetails(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().checkMember();
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
		loungePage().startOrderForCustomer();
	}

	@StepGroup
	public void fillContactDetails(AddressModel addressData) {
		createNewContactPage().streetInput(addressData.getStreetAddress());
		createNewContactPage().houseNumberInput(addressData.getStreetNumber());
		createNewContactPage().postcodeInput(addressData.getPostCode());
		createNewContactPage().cityInput(addressData.getHomeTown());
		createNewContactPage().selectCountryName(addressData.getCountryName());
		createNewContactPage().inputPhoneNumber(addressData.getPhoneNumber());
	}
	@StepGroup
	public void fillContactDetailsWithoutPlz(AddressModel addressData) {
		createNewContactPage().streetInput(addressData.getStreetAddress());
		createNewContactPage().houseNumberInput(addressData.getStreetNumber());
		createNewContactPage().postcodeInput(addressData.getPostCode());
		createNewContactPage().cityInput(addressData.getHomeTown());
		createNewContactPage().selectCountryName(addressData.getCountryName());
		createNewContactPage().inputPhoneNumber(addressData.getPhoneNumber());
	}

	@StepGroup
	public void fillCountry(AddressModel addressData) {
		createNewContactPage().selectCountryName(addressData.getCountryName());
	}
}

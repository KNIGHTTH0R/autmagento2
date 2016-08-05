package com.steps.frontend.registration.party;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import com.tools.constants.TimeConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class CreateNewContactSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Title("Fill new contact details")
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

	@Title("Fill new contact details without plz")
	@Step
	public void fillCreateNewContactWithoutPlz(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetailsWithoutPlz(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().checkMember();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Title("Fill new contact details without any interrests")
	@Step
	public void fillCreateNewContactWithoutAnyInterrest(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetails(addressData);
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Title("Fill new contact details without any interrests and without email")
	@Step
	public void fillCreateNewContactWithoutAnyInterrestAndWithoutEmail(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().firstnameInput(customerData.getFirstName());
		createNewContactPage().lastnameInput(customerData.getLastName());
		fillContactDetails(addressData);
		createNewContactPage().submitContact();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Title("Fill new contact details without stylecoach to become interrest")
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

	@Title("Fill new contact details without stylecoach to become interrest and without email")
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

	@Title("Fill new contact details without unrequired address details and without interrests")
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

	@Title("Fill new contact details")
	@StepGroup
	public void fillContactDetails(AddressModel addressData) {
		createNewContactPage().streetInput(addressData.getStreetAddress());
		createNewContactPage().houseNumberInput(addressData.getStreetNumber());
		createNewContactPage().postcodeInput(addressData.getPostCode());
		createNewContactPage().cityInput(addressData.getHomeTown());
		createNewContactPage().selectCountryName(addressData.getCountryName());
		createNewContactPage().inputPhoneNumber(addressData.getPhoneNumber());
	}

	@Title("Fill new contact details without plz")
	@StepGroup
	public void fillContactDetailsWithoutPlz(AddressModel addressData) {
		createNewContactPage().streetInput(addressData.getStreetAddress());
		createNewContactPage().houseNumberInput(addressData.getStreetNumber());
		createNewContactPage().postcodeInput(addressData.getPostCode());
		createNewContactPage().cityInput(addressData.getHomeTown());
		createNewContactPage().selectCountryName(addressData.getCountryName());
		createNewContactPage().inputPhoneNumber(addressData.getPhoneNumber());
	}

	@Title("Fill country")
	@StepGroup
	public void fillCountry(AddressModel addressData) {
		createNewContactPage().selectCountryName(addressData.getCountryName());
	}
}

package com.steps.frontend.registration.party;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
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
		waitABit(4000);
	}
	@Step
	public void fillCreateNewContactWithoutFirstname(CustomerFormModel customerData, AddressModel addressData) {
		createNewContactPage().changeClassNameForFirstnameInput();
		createNewContactPage().lastnameInput(customerData.getLastName());
		createNewContactPage().emailInput(customerData.getEmailName());
		fillContactDetails(addressData);
		createNewContactPage().checkNewsletter();
		createNewContactPage().checkParties();
		createNewContactPage().checkMember();
		createNewContactPage().submitContact();
		waitABit(4000);
	}
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
		waitABit(2000);
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
}

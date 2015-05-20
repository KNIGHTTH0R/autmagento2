package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class ContactBoosterRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void firstNameInput(String firstName) {
		contactBoosterRegistrationPage().firstNameInput(firstName);
	}

	@Step
	public void lastNameInput(String lastName) {
		contactBoosterRegistrationPage().lastNameInput(lastName);
	}

	@Step
	public void emailInput(String email) {
		contactBoosterRegistrationPage().emailInput(email);
	}

	@Step
	public void passwordInput(String password) {
		contactBoosterRegistrationPage().passwordInput(password);
	}

	@Step
	public void passwordConfirmInput(String password) {
		contactBoosterRegistrationPage().passwordConfirmInput(password);
	}

	@Step
	public void checkIAgree() {
		contactBoosterRegistrationPage().checkIAgree();
	}

	@Step
	public void submitAndContinue() {
		contactBoosterRegistrationPage().submitAndContinue();
	}

	@Step
	public void inputStreetAddress(String streetAddress) {
		contactBoosterRegistrationPage().inputStreetAddress(streetAddress);
	}

	@Step
	public void inputStreetNumber(String streetNumber) {
		contactBoosterRegistrationPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputPostCode(String postCode) {
		contactBoosterRegistrationPage().inputPostCode(postCode);
	}

	@Step
	public void inputHomeTown(String homeTown) {
		contactBoosterRegistrationPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectCountryName(String countryName) {
		contactBoosterRegistrationPage().selectCountryName(countryName);
	}

	@Step
	public void inputPhoneNumber(String phoneNumber) {
		contactBoosterRegistrationPage().inputPhoneNumber(phoneNumber);
	}

	@Step
	public void checkIsSubscribedCheckbox() {
		contactBoosterRegistrationPage().checkIsSubscribedCheckbox();
	}

	@Step
	public void checkStylePartiesCheckbox() {
		contactBoosterRegistrationPage().checkStylePartiesCheckbox();
	}

	@Step
	public void checkStyleMemberCheckbox() {
		contactBoosterRegistrationPage().checkStyleMemberCheckbox();
	}



	@Step
	@Title("Fill contact booster registration form")
	public void fillContactBoosterRegistrationForm(CustomerFormModel dataModel, AddressModel addressModel) {
		contactBoosterRegistrationPage().firstNameInput(dataModel.getFirstName());
		contactBoosterRegistrationPage().lastNameInput(dataModel.getLastName());
		contactBoosterRegistrationPage().inputStreetAddress(addressModel.getStreetAddress());
		contactBoosterRegistrationPage().inputStreetNumber(addressModel.getStreetNumber());
		contactBoosterRegistrationPage().inputPostCode(addressModel.getPostCode());
		contactBoosterRegistrationPage().inputHomeTown(addressModel.getHomeTown());
		contactBoosterRegistrationPage().selectCountryName(addressModel.getCountryName());
		contactBoosterRegistrationPage().inputPhoneNumber(addressModel.getPhoneNumber());
		contactBoosterRegistrationPage().emailInput(dataModel.getEmailName());
		contactBoosterRegistrationPage().passwordInput(dataModel.getPassword());
		contactBoosterRegistrationPage().passwordConfirmInput(dataModel.getPassword());
		contactBoosterRegistrationPage().checkStyleMemberCheckbox();
		contactBoosterRegistrationPage().checkStylePartiesCheckbox();
		contactBoosterRegistrationPage().checkIAgree();
		contactBoosterRegistrationPage().submitAndContinue();
	}

}

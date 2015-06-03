package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class KoboCampaignSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void clickRegister() {
		koboCampaignPage().clickRegister();
	}

	@Step
	public void firstNameInput(String firstName) {
		koboCampaignPage().firstNameInput(firstName);
	}

	@Step
	public void lastNameInput(String lastName) {
		koboCampaignPage().lastNameInput(lastName);
	}

	@Step
	public void emailInput(String email) {
		koboCampaignPage().emailInput(email);
	}

	@Step
	public void passwordInput(String password) {
		koboCampaignPage().passwordInput(password);
	}

	@Step
	public void passwordConfirmInput(String password) {
		koboCampaignPage().passwordConfirmInput(password);
	}

	@Step
	public void checkIAgree() {
		koboCampaignPage().checkIAgree();
	}

	@Step
	public void submitAndContinue() {
		koboCampaignPage().submitAndContinue();
	}

	@Step
	public void inputStreetAddress(String streetAddress) {
		koboCampaignPage().inputStreetAddress(streetAddress);
	}

	@Step
	public void inputStreetNumber(String streetNumber) {
		koboCampaignPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputPostCode(String postCode) {
		koboCampaignPage().inputPostCode(postCode);
	}

	@Step
	public void inputHomeTown(String homeTown) {
		koboCampaignPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectCountryName(String countryName) {
		koboCampaignPage().selectCountryName(countryName);
	}

	@Step
	public void inputPhoneNumber(String phoneNumber) {
		koboCampaignPage().inputPhoneNumber(phoneNumber);
	}

	@Step
	public void checkIsSubscribedCheckbox() {
		koboCampaignPage().checkIsSubscribedCheckbox();
	}

	@Step
	public void checkStylePartiesCheckbox() {
		koboCampaignPage().checkStylePartiesCheckbox();
	}

	@Step
	public void checkStyleMemberCheckbox() {
		koboCampaignPage().checkStyleMemberCheckbox();
	}

	@Step
	public void checkNoInvite() {
		koboCampaignPage().checkNoInvite();
	}

	@Step
	@Title("Fill kobo campaign registration form on master shop")
	public void fillKoboCampaignRegistrationFormOnMasterShop(CustomerFormModel dataModel, AddressModel addressModel) {
		koboCampaignPage().firstNameInput(dataModel.getFirstName());
		koboCampaignPage().lastNameInput(dataModel.getLastName());
		koboCampaignPage().inputStreetAddress(addressModel.getStreetAddress());
		koboCampaignPage().inputStreetNumber(addressModel.getStreetNumber());
		koboCampaignPage().inputPostCode(addressModel.getPostCode());
		koboCampaignPage().inputHomeTown(addressModel.getHomeTown());
		koboCampaignPage().selectCountryName(addressModel.getCountryName());
		koboCampaignPage().inputPhoneNumber(addressModel.getPhoneNumber());
		koboCampaignPage().passwordInput(dataModel.getPassword());
		koboCampaignPage().passwordConfirmInput(dataModel.getPassword());
		koboCampaignPage().emailInput(dataModel.getEmailName());
		koboCampaignPage().checkStyleMemberCheckbox();
		koboCampaignPage().checkStylePartiesCheckbox();
		koboCampaignPage().checkIAgree();
		koboCampaignPage().submitAndContinue();
	}
	@Step
	@Title("Fill kobo campaign registration form on SC context")
	public void fillKoboCampaignRegistrationFormOnStyleCoachContext(CustomerFormModel dataModel, AddressModel addressModel) {
		koboCampaignPage().firstNameInput(dataModel.getFirstName());
		koboCampaignPage().lastNameInput(dataModel.getLastName());
		koboCampaignPage().inputStreetAddress(addressModel.getStreetAddress());
		koboCampaignPage().inputStreetNumber(addressModel.getStreetNumber());
		koboCampaignPage().inputPostCode(addressModel.getPostCode());
		koboCampaignPage().inputHomeTown(addressModel.getHomeTown());
		koboCampaignPage().selectCountryName(addressModel.getCountryName());
		koboCampaignPage().inputPhoneNumber(addressModel.getPhoneNumber());
		koboCampaignPage().passwordInput(dataModel.getPassword());
		koboCampaignPage().passwordConfirmInput(dataModel.getPassword());
		koboCampaignPage().emailInput(dataModel.getEmailName());
		koboCampaignPage().checkStyleMemberCheckbox();
		koboCampaignPage().checkStylePartiesCheckbox();
		koboCampaignPage().checkIAgree();
		koboCampaignPage().submitAndContinue();
	}

}

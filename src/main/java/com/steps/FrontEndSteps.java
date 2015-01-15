package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.AbstractSteps;
import com.tools.data.AddressModel;
import com.tools.data.CustomerFormModel;
import com.tools.data.ProductBasicModel;

public class FrontEndSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	public void performLogin(String userName, String userPass) {
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	@StepGroup
	public void fillCreateCustomerForm(CustomerFormModel customerData,
			AddressModel addressData) {
		
//		getDriver().get(Constants.BASE_URL_FE);
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());

		// inputStylistEmail(stylistEmail);
		checkParties();

		fillContactDetails(addressData);
		// checkMember();
		checkNoInvite();
		checkIAgree();

		clickCompleteButton();
	}

	@StepGroup
	public void fillContactDetails(AddressModel addressData) {
		inputStreetAddress(addressData.getStreetAddress());
		inputStreetNumber(addressData.getStreetNumber());
		inputPostCode(addressData.getPostCode());
		inputHomeTown(addressData.getHomeTown());
		selectCountryName(addressData.getCountryName());
		createCustomerPage().inputPhoneNumber(addressData.getPhoneNumber());

	}
	

	@StepGroup
	public void verifyCustomerCreation() {
		verifyText();		
		verifyLink();		
	}

	// ------------------Secondary Form - create customer - Address fields
	@Step
	public void inputStreetAddress(String addressName) {
		createCustomerPage().inputStreetAddress(addressName);
	}

	@Step
	public void inputStreetNumber(String streetNumber) {
		createCustomerPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputPostCode(String postCode) {
		createCustomerPage().inputPostCode(postCode);
	}

	@Step
	public void inputHomeTown(String homeTown) {
		createCustomerPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectCountryName(String countryName) {
		createCustomerPage().selectCountryName(countryName);
	}

	@Step
	public void inputPhoneNumber(String phoneNumber) {
		createCustomerPage().inputPhoneNumber(phoneNumber);
	}

	// ----------------------------Main Form from create customer
	@Step
	public void inputStylistEmail(String stylistEmail) {
		createCustomerPage().inputStylistEmail(stylistEmail);
	}

	@Step
	public void inputFirstName(String firstName) {
		createCustomerPage().inputFirstName(firstName);
	}

	@Step
	public void inputLastName(String lastName) {
		createCustomerPage().inputLastName(lastName);
	}

	@Step
	public void inputEmail(String emailAddress) {
		createCustomerPage().inputEmail(emailAddress);
	}

	@Step
	public void inputPassword(String passText) {
		createCustomerPage().inputPassword(passText);
	}

	@Step
	public void inputConfirmation(String passText) {
		createCustomerPage().inputConfirmation(passText);
	}

	@Step
	public void checkParties() {
		createCustomerPage().checkParties();
	}

	@Step
	public void checkMember() {
		createCustomerPage().checkMember();
	}
	@Step
	public void checkNoInvite() {
		createCustomerPage().checkNoInvite();
	}

	@Step
	public void checkIAgree() {
		createCustomerPage().checkIAgree();
	}

	@Step
	public void clickCompleteButton() {
		createCustomerPage().clickCompleteButton();
	}

	
	@Step
	public void verifyLink(){
		registrationMessagePage().verifyLink();
	}
	
	
	@Step
	public void verifyText(){
		registrationMessagePage().verifyText();
	}

	@Step
	public void searchProduct(String searchKey) {
		loungePage().searchInput(searchKey);
		loungePage().clickOnSubmitButton();
	}
	
	
	@Step
	public ProductBasicModel findProduct(String productName){
		return productsPage().findProductAndClick(productName);
	}
}

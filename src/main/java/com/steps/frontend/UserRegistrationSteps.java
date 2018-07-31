package com.steps.frontend;

import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class UserRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	private void checkNewsletter() {
		createUserPage().checkNewsletter();

	}
	
	@Title("Verify User Information")
	@StepGroup
	public void verifyUserCreation(CustomerFormModel dataModel) {
		myAccountPage().verifyText();
		myAccountPage().verifyLink();
		verifyContactInformation(dataModel);
		verifyNewsletterText();
	}
	
	@Title("Verify Contact Information")
	@StepGroup
	public void verifyContactInformation(CustomerFormModel dataModel) {
		verifyFirstName(dataModel.getFirstName());
		verifyLastName(dataModel.getLastName());
		verifyEmailAddress(dataModel.getEmailName());
	}
	

	// -----------------Personal Information-------------------------

	@Step
	public void inputFirstName(String firstName) {
		createUserPage().inputFirstName(firstName);
	}

	@Step
	public void inputLastName(String lastName) {
		createUserPage().inputLastName(lastName);
	}

	// -----------------Sign-in Information-------------------------
	@Step
	public void inputEmail(String emailAddress) {
		createUserPage().inputEmail(emailAddress);
	}

	@Step
	public void inputPassword(String passText) {
		createUserPage().inputPassword(passText);
	}

	@Step
	public void inputPasswordConfirmation(String passText) {
		createUserPage().inputPasswordConfirmation(passText);
	}

	@Step
	public void clickOnCreateAnAccount() {
		createUserPage().clickOnCreateAnAccount();
	}

	//////////////////

	@Step
	public void verifyLink() {
		myAccountPage().verifyLink();
	}

	@Step
	public void verifyText() {
		myAccountPage().verifyText();
	}
	
	@Step
	public void verifyFirstName(String  firstnamw) {
		myAccountPage().verifyFirstName(firstnamw);
	}
	
	@Step
	public void verifyLastName(String lastname) {
		myAccountPage().verifyLastName(lastname);
	}
	
	@Step
	public void verifyEmailAddress(String emailAddress) {
		myAccountPage().verifyEmailAddress(emailAddress);
	}
	
	@Step
	public void verifyNewsletterText(){
		myAccountPage().checkNewsletterSubscriptionText();
	}

	public void fillUserRegistrationForm(CustomerFormModel customerData) {
		navigate(MongoReader.getBaseURL());
		homePage().clickOnCreateAccountLink();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		checkNewsletter();
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputPasswordConfirmation(customerData.getPassword());
		clickOnCreateAnAccount();
	}

}

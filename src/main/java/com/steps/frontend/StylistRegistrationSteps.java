package com.steps.frontend;

import java.util.Set;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import com.tools.Constants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

public class StylistRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	@StepGroup
	@Title("Fill create customer form")
	public String fillCreateCustomerForm(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		selectBirthDate(birthDate);
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		fillContactDetails(addressData);
		checkNoCoachCheckbox();
		checkIAgree();
		String date = FormatterUtils.getAndFormatCurrentDate();
		submitStep();
		inputStylistRef(customerData.getFirstName());
		submitStep();
		selectStarterKit();
		submitStep();
		clickOnNachahmePaymentMethod();
		submitPaymentMethod();

		return date;
	}

	@StepGroup
	public String fillStylistRegistrationPredefinedInfoForm(String name, AddressModel addressData, String birthDate) {

		selectBirthDate(birthDate);
		checkIAgree();
		String date = FormatterUtils.getAndFormatCurrentDate();
		submitStep();
		inputStylistRef(name);
		submitStep();
		selectStarterKit();
		submitStep();
		clickOnNachahmePaymentMethod();
		submitPaymentMethod();

		return date;
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
	public void selectBirthDate(String dateOfBirth) {
		String elems[] = dateOfBirth.split(Constants.DATE_SEPARATOR);
		if (elems.length != 3) {
			Assert.assertTrue("Error: birth date provided is not a valid format. Valid format - 'Feb,1970,12'", elems.length != 3);
		}
		clickDob();
		selectMonth(elems[0]);
		selectYear(elems[1]);
		selectDay(elems[2]);
	}

	@StepGroup
	public void verifyCustomerCreation() {
		verifyText();
		verifyLink();
	}

	// ------------------Secondary Form - create customer - Address fields
	@Step
	public void inputStreetAddress(String addressName) {
		stylistRegistrationPage().inputStreetAddress(addressName);
	}

	@Step
	public void inputStreetNumber(String streetNumber) {
		stylistRegistrationPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputPostCode(String postCode) {
		stylistRegistrationPage().inputPostCode(postCode);
	}

	@Step
	public void inputHomeTown(String homeTown) {
		stylistRegistrationPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectCountryName(String countryName) {
		stylistRegistrationPage().selectCountryName(countryName);
	}

	@Step
	public void clickOnNachahmePaymentMethod() {
		stylistRegistrationPage().clickOnNachahmePaymentMethod();
	}

	@Step
	public void submitPaymentMethod() {
		stylistRegistrationPage().submitPaymentMethod();
	}

	@Step
	public void inputPhoneNumber(String phoneNumber) {
		stylistRegistrationPage().inputPhoneNumber(phoneNumber);
	}

	@Step
	public void inputStylistRef(String ref) {
		stylistRegistrationPage().inputStylistRef(ref);
	}

	// ----------------------------Main Form from create customer
	@Step
	public void inputStylistEmail(String stylistEmail) {
		stylistRegistrationPage().inputStylistEmail(stylistEmail);
	}

	@Step
	public void inputFirstName(String firstName) {
		stylistRegistrationPage().inputFirstName(firstName);
	}

	@Step
	public void inputLastName(String lastName) {
		stylistRegistrationPage().inputLastName(lastName);
	}

	@Step
	public void inputEmail(String emailAddress) {
		stylistRegistrationPage().inputEmail(emailAddress);
	}

	@Step
	public void inputPassword(String passText) {
		stylistRegistrationPage().inputPassword(passText);
	}

	@Step
	public void inputConfirmation(String passText) {
		stylistRegistrationPage().inputConfirmation(passText);
	}

	@Step
	public void checkParties() {
		stylistRegistrationPage().checkParties();
	}

	@Step
	public void checkMember() {
		stylistRegistrationPage().checkMember();
	}

	@Step
	public void checkNoInvite() {
		stylistRegistrationPage().checkNoInvite();
	}

	@Step
	public void checkIAgree() {
		stylistRegistrationPage().checkIAgree();
	}

	@Step
	public void checkNoCoachCheckbox() {
		stylistRegistrationPage().checkNoCoachCheckbox();
	}

	@Step
	public void clickCompleteButton() {
		stylistRegistrationPage().clickCompleteButton();
	}

	@Step
	public void submitStep() {
		stylistRegistrationPage().submitStep();
	}

	@Step
	public void clickDob() {
		stylistRegistrationPage().clickDob();
	}

	@Step
	public void selectMonth(String month) {
		stylistRegistrationPage().selectMonth(month);
	}

	@Step
	public void selectYear(String year) {
		stylistRegistrationPage().selectYear(year);
	}

	@Step
	public void selectDay(String day) {
		stylistRegistrationPage().selectDay(day);
	}

	@Step
	public void selectStarterKit() {
		stylistRegistrationPage().selectStarterKit();
	}

	@Step
	public void verifyLink() {
		registrationMessagePage().verifyLink();
	}

	@Step
	public void verifyText() {
		registrationMessagePage().verifyText();
	}

	@StepGroup
	public void goToFacebookLogin(String user, String pass) {
		clickOnFacebookLogin();
		// waitABit(2000);
		Set<String> windowsList = getDriver().getWindowHandles();
		for (String string : windowsList) {
			getDriver().switchTo().window(string);
			// System.out.println("string: " + string);
			// System.out.println("HEre: " + getDriver().getTitle());
			if (getDriver().getTitle().trim().contains("Facebook")) {
				performFacebookLogin(user, pass);
			}
			// waitABit(2000);
		}

		getDriver().switchTo().defaultContent();
	}

	@Step
	public void clickLoginLinkFromMessage() {
		stylistRegistrationPage().clickLoginLinkFromMessage();
	}

	@Step
	public String getStylistRegisterPageTitle() {
		return stylistRegistrationPage().getStylistRegisterPageTitle();
	}

	@Step
	public void clickOnFacebookLogin() {
		loginPage().clickOnFacebookSignIn();
	}

	@Step
	public void performFacebookLogin(String user, String pass) {
		facebookEMBLoginPage().inputUser(user);
		facebookEMBLoginPage().inputPass(pass);
		facebookEMBLoginPage().clickLogin();
	}
}

package com.steps.frontend;

import java.util.Set;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import org.junit.Assert;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.variables.ContextConstants;
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
		submitStep();
		inputStylistRef(customerData.getFirstName() + customerData.getLastName());
		submitStep();
		selectStarterKit();
		submitStep();
		clickOnNachahmePaymentMethod();
		submitPaymentMethod();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	public String fillStylistRegistrationPredefinedInfoForm(String name, AddressModel addressData, String birthDate) {
		selectBirthDate(birthDate);
		checkIAgree();
		submitStep();
		inputStylistRef(name);
		submitStep();
		selectStarterKit();
		submitStep();
		clickOnNachahmePaymentMethod();
		submitPaymentMethod();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	@Title("Fill contact details ")
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
		String elems[] = FormatterUtils.splitDate(dateOfBirth);
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
		Set<String> windowsList = getDriver().getWindowHandles();
		for (String string : windowsList) {
			getDriver().switchTo().window(string);
			if (getDriver().getTitle().trim().contains("Facebook")) {
				performFacebookLogin(user, pass);
			}
		}
		getDriver().switchTo().defaultContent();
	}

	@Step
	public void clickLoginLinkFromMessage() {
		stylistRegistrationPage().clickLoginLinkFromMessage();
	}

	@Step
	public void validateStylistRegisterPageTitle() {
		Assert.assertTrue("Failure: You are not on the style coach register page", stylistRegistrationPage().getStylistRegisterPageTitle().contentEquals(ContextConstants.STYLE_COACH_REG_PAGE_TITLE));
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

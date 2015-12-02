package com.steps.frontend;

import java.io.IOException;
import java.util.Set;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import org.junit.Assert;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

public class StylistRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	@StepGroup
	@Title("Fill create stylecoach form")
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
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	@Title("Fill create stylecoach form using csv for plz")
	public void fillCreateCustomerFormWithoutPlz(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		selectBirthDate(birthDate);
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		fillContactDetailsWithoutPlz(addressData);
	}

	@StepGroup
	@Title("Fill create stylecoach form with known sponsor")
	public String fillCreateStylecoachFormWithKnownSponsor(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		selectBirthDate(birthDate);
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		fillContactDetails(addressData);
		checkIAgree();
		submitStep();
		inputStylistRef(customerData.getFirstName() + customerData.getLastName());
		submitStep();
		selectStarterKit();
		submitStep();
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	@Title("Fill create customer form and pay with visa")
	public String fillCreateCustomerFormPayWithVisa(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

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
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	@Title("Fill create customer form and pay with visa")
	public String fillCreateStylecoachFormWithKnownSponsorPayWithVisa(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		selectBirthDate(birthDate);
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		fillContactDetails(addressData);
		checkIAgree();
		submitStep();
		inputStylistRef(customerData.getFirstName() + customerData.getLastName());
		submitStep();
		selectStarterKit();
		submitStep();
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	@Title("Fill create customer form and return customer's lattitude and longitude")
	public CoordinatesModel fillCreateStylystFormAndReturnLatAndLong(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

		CoordinatesModel coordinatesModel = new CoordinatesModel();

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
		payWithCreditCard();
		try {
			coordinatesModel = AddressConverter.getLattitudeAndLongitudeFromAddress(addressData.getStreetAddress() + "," + addressData.getStreetNumber() + ","
					+ addressData.getHomeTown() + "," + addressData.getPostCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return coordinatesModel;
	}

	@StepGroup
	@Title("Fill create customer form")
	public String fillCreateCustomerFormFirstWithForbiddenCountry(CustomerFormModel customerData, AddressModel addressData, String birthDate) {

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		selectBirthDate(birthDate);
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		fillContactDetailsFirstWithForbidenCountry(addressData);
		checkNoCoachCheckbox();
		checkIAgree();
		submitStep();
		inputStylistRef(customerData.getFirstName() + customerData.getLastName());
		submitStep();
		selectStarterKit();
		submitStep();
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	public String fillStylistRegistrationPredefinedInfoForm(String name, String birthDate) {
		selectBirthDate(birthDate);
		checkIAgree();
		submitStep();
		inputStylistRef(name);
		submitStep();
		selectStarterKit();
		submitStep();
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	public String fillStylistRegistrationPredefinedInfoFormWithNotPreferedCountryFirst(String name, AddressModel addressData, String birthDate) {
		validateInfoBoxMessage();
		closeInfoBox();
		addressData.setCountryName(ContextConstants.COUNTRY_NAME);
		selectBirthDate(birthDate);
		checkIAgree();
		submitStep();
		inputStylistRef(name);
		submitStep();
		selectStarterKit();
		submitStep();
		payWithCreditCard();

		String date = FormatterUtils.getAndFormatCurrentDate();
		return date;
	}

	@StepGroup
	@Title("Fill contact details")
	public void fillContactDetails(AddressModel addressData) {
		inputStreetAddress(addressData.getStreetAddress());
		inputStreetNumber(addressData.getStreetNumber());
		inputPostCode(addressData.getPostCode());
		inputHomeTown(addressData.getHomeTown());
		selectCountryName(addressData.getCountryName());
		createCustomerPage().inputPhoneNumber(addressData.getPhoneNumber());

	}

	@StepGroup
	@Title("Fill contact details with csv")
	public void fillContactDetailsWithoutPlz(AddressModel addressData) {
		inputStreetAddress(addressData.getStreetAddress());
		inputStreetNumber(addressData.getStreetNumber());
		inputHomeTown(addressData.getHomeTown());
		selectCountryName(addressData.getCountryName());
		createCustomerPage().inputPhoneNumber(addressData.getPhoneNumber());

	}

	@StepGroup
	@Title("Fill contact details ")
	public void fillContactDetailsFirstWithForbidenCountry(AddressModel addressData) {
		inputStreetAddress(addressData.getStreetAddress());
		inputStreetNumber(addressData.getStreetNumber());
		inputPostCode(addressData.getPostCode());
		inputHomeTown(addressData.getHomeTown());
		selectCountryName(addressData.getCountryName());
		validateInfoBoxMessage();
		closeInfoBox();
		addressData.setCountryName(ContextConstants.COUNTRY_NAME);
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
	public void payWithCreditCard() {
		if (MongoReader.getContext().contentEquals("es")) {
			stylistRegistrationPage().expandCreditCardForm();
			stylistRegistrationPage().selectCardTypeEs("Visa/Electron");
		} else {
			stylistRegistrationPage().selectCardType("Visa/Electron");
		}
		stylistRegistrationPage().inputCardNumber("4548812049400004");
		stylistRegistrationPage().inputCardExpiryMonth("06");
		stylistRegistrationPage().inputCardExpiryYear("2016");
		stylistRegistrationPage().submitCreditCard();
		stylistRegistrationPage().inputCardCvv("285");
		stylistRegistrationPage().submitPaymentMethod();
		stylistRegistrationPage().inputCardPin("123456");
		stylistRegistrationPage().submitVisaFinalStep();
	}

	@Step
	public void submitPaymentMethod() {
		stylistRegistrationPage().submitPaymentMethod();
	}

	@Step
	public void finishPayment() {
		stylistRegistrationPage().finishPayment();
	}

	@Step
	public void inputPhoneNumber(String phoneNumber) {
		stylistRegistrationPage().inputPhoneNumber(phoneNumber);
	}

	@Step
	public void inputStylistRef(String ref) {
		stylistRegistrationPage().inputStylistRef(ref);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

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
		Assert.assertTrue("Failure: You are not on the style coach register page",
				stylistRegistrationPage().getStylistRegisterPageTitle().contentEquals(ContextConstants.STYLE_COACH_REG_PAGE_TITLE));
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

	@Step
	public void validateInfoBoxMessage() {
		stylistRegistrationPage().validateInfoBoxMessage();
	}

	@Step
	public void closeInfoBox() {
		stylistRegistrationPage().closeInfoBox();
	}
}

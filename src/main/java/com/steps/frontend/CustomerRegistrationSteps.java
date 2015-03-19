package com.steps.frontend;

import java.util.Set;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.pages.frontend.registration.landing.LandingCustomerAllocationPage.StyleMode;
import com.tools.Constants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class CustomerRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	@StepGroup
	public void fillCreateCustomerForm(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();		
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		checkMember();
		fillContactDetails(addressData);
		checkNoInvite();
		checkIAgree();
		clickCompleteButton();
	}

	@StepGroup
	public void fillCreateCustomerFormUnderContext(CustomerFormModel customerData, AddressModel addressData, String context) {

		getDriver().get(Constants.BASE_FE_URL + context);
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		checkMember();
		fillContactDetails(addressData);
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
	public void clickOnFacebookLogin() {
		loginPage().clickOnFacebookSignIn();
	}

	@Step
	public void performFacebookLogin(String user, String pass) {
		facebookEMBLoginPage().inputUser(user);
		facebookEMBLoginPage().inputPass(pass);
		facebookEMBLoginPage().clickLogin();
	}

	// Create customer from landing page
	/**
	 * This form is found on the http//[base.url]/contact-landing-page
	 * @param dataModel
	 * @param addressModel
	 */
	@Step
	public void fillLandingPageForm(CustomerFormModel dataModel, AddressModel addressModel) {
		getDriver().get(Constants.BASE_FE_URL + Constants.LANDING_PAGE);
		contactLandingPage().selectGender(true);
		contactLandingPage().inputFirstName(dataModel.getFirstName());
		contactLandingPage().inputLastName(dataModel.getLastName());
		contactLandingPage().inputEmail(dataModel.getEmailName());
		contactLandingPage().inputPostCode(addressModel.getPostCode());
		contactLandingPage().inputCity(addressModel.getHomeTown());
		contactLandingPage().selectCountry(addressModel.getCountryName());
		contactLandingPage().inputTelephoneAreaCode("49");
		contactLandingPage().inputTelephone(addressModel.getPhoneNumber());
		contactLandingPage().iAgreeCheckbox();
		contactLandingPage().clickOk();

	}

	/**
	 * This method will select a stylist allocation. Default requires no fields,
	 * name fields may be empty. If custom Stylist is selected first name and
	 * last name should be provided
	 *
	 * @param mode
	 * @param firstName
	 * @param lastName
	 */
	@Step
	public void selectStylistOption(StyleMode mode, String firstName, String lastName) {
		landingCustomerAllocationPage().selectStylistOption(mode, firstName, lastName);
	}

	/**
	 * This method is part of the landingPage flow
	 */
	@Step
	public void submitStylistSelection() {
		landingCustomerAllocationPage().submitAndContinue();
	}

	/**
	 * This method is part of the landingPage flow - ThankYou page
	 */
	@Step
	public String fillThankYouForm(String password) {
		
		thankYouPage().passwordInput(password);
		String email = thankYouPage().getEmailText();
		thankYouPage().checkIAgree();
		thankYouPage().submitButton();
		return email;
	}

	/**
	 * Assert for the ThankYouPage item - email
	 * @param expected
	 * @param actual
	 */
	@Step
	public void verifyCustomerEmail(String expected, String actual) {
		Assert.assertTrue("Failure: email address not as expected: "+ expected + ", actual: " + actual, expected.contentEquals(actual));
	}

	/**
	 * Assert on last step of landing page flow. success message display
	 */
	@Step
	public void verifySuccessLink() {
		String grabedTitle = getDriver().getCurrentUrl();
		Assert.assertTrue("Failure: Success link not found. Actual URL: " + grabedTitle, grabedTitle.contains("registersuccess"));
	}

	/**
	 * thankyou-register - is the id of the form on the thank you page.
	 * We verify that the form is not present with this method.
	 * Page title is also validated
	 */
	@Step
	public void verifySimpleThankYouPage() {
		waitABit(Constants.TIME_CONSTANT);
		String pageSource = thankYouPage().pageSource();
		String pageTitle = thankYouPage().pageTitle();
		Assert.assertTrue("Failure: Page title is not as expected. Might be a wrong page",pageTitle.contains("Thank you page"));
		Assert.assertTrue("Failure: Page may contain thankYou register form.",!pageSource.contains("thankyou-register"));
	}

	@Step
	public void fillWidgetRegistrationForm(String code, CustomerFormModel dataModel) {
		getDriver().get(Constants.BASE_FE_URL + Constants.REGISTER_LANDING_PAGE);
		registerLandingPage().memberCodeInput(code);
		registerLandingPage().firstNameInput(dataModel.getFirstName());
		registerLandingPage().lastNameInput(dataModel.getLastName());
		registerLandingPage().emailInput(dataModel.getEmailName());
		
		registerLandingPage().passwordInput(dataModel.getPassword());
		registerLandingPage().passwordConfirmInput(dataModel.getPassword());
		registerLandingPage().checkIAgree();
		registerLandingPage().submitAndContinue();
	}

}

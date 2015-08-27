package com.steps.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import org.junit.Assert;

import com.pages.frontend.registration.landing.LandingCustomerAllocationPage.StyleMode;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class CustomerRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	@StepGroup
	public void fillCreateCustomerForm(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		// checkMember();
		fillContactDetails(addressData);
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	// for distribution and DYSKS
	
	@Title("Fill create customer form with no member checked checked and select firts SC found by name - host retrieval")
	@StepGroup
	public List<DykscSeachModel> fillCreateCustomerFormAnReturnFoundByNameStylecoaches(CustomerFormModel customerData, AddressModel addressData,String firstName,String lastName) {

		List<DykscSeachModel> result = new ArrayList<DykscSeachModel>();

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		fillContactDetails(addressData);
		result = searchStylistByNameStylecoaches(firstName, lastName);
		checkIAgree();
		clickCompleteButton();

		return result;
	}

	@StepGroup
	public void fillCreateCustomerFormWithNoStylePartyAndStyleCoachChecked(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		inputPostCodeFromPersonalInfo(addressData.getPostCode());
		selectCountryNameFromPersonalInfo(addressData.getCountryName());
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@Title("Fill create customer form without party and stylecoach checkboxes checked and do not select any resulting SC - customer retrieval")
	@StepGroup
	public void fillCreateCustomerFormWithNoStylePartyAndStyleCoachCheckedNoStylistSelected(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		inputPostCodeFromPersonalInfo(addressData.getPostCode());
		selectCountryNameFromPersonalInfo(addressData.getCountryName());
		searchStylistByGeoipButDoNotSelectAnyStylecoach(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@Title("Fill create customer form without party and stylecoach checkboxes checked and select firts SC - customer retrieval")
	@StepGroup
	public List<DykscSeachModel> fillCreateCustomerFormWithNoStylePartyAndStyleCoachCheckedAndReturnFoundStylecoaches(CustomerFormModel customerData, AddressModel addressData) {

		List<DykscSeachModel> result = new ArrayList<DykscSeachModel>();

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		inputPostCodeFromPersonalInfo(addressData.getPostCode());
		selectCountryNameFromPersonalInfo(addressData.getCountryName());
		result = searchStylistByGeoipAndReturnFoundStylecoaches(addressData);
		checkIAgree();
		clickCompleteButton();

		return result;
	}

	@StepGroup
	public void fillCreateCustomerFormAndGetLatAndLong(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
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
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@Title("Fill create customer form with all checkboxes checked and do not select any resulting SC - SC retrieval")
	@StepGroup
	public void fillCreateCustomerFormNoStylistSelected(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
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
		searchStylistByGeoipButDoNotSelectAnyStylecoach(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@Title("Fill create customer form with all checkboxes checked and select firts SC - SC retrieval")
	@StepGroup
	public List<DykscSeachModel> fillCreateCustomerFormAndReturnFoundStylecoaches(CustomerFormModel customerData, AddressModel addressData) {

		List<DykscSeachModel> result = new ArrayList<DykscSeachModel>();

		getDriver().get(MongoReader.getBaseURL());
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
		result = searchStylistByGeoipAndReturnFoundStylecoaches(addressData);
		checkIAgree();
		clickCompleteButton();

		return result;
	}

	@StepGroup
	public void fillCreateCustomerFormNoMemberAndGetLatAndLong(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		fillContactDetails(addressData);
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@Title("Fill create customer form with no member checked and do not select any resulting SC - host retrieval")
	@StepGroup
	public void fillCreateCustomerFormNoMemberFlagAndNoStylistSelected(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		fillContactDetails(addressData);
		searchStylistByGeoipButDoNotSelectAnyStylecoach(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@Title("Fill create customer form with no member checked checked and select firts SC - host retrieval")
	@StepGroup
	public List<DykscSeachModel> fillCreateCustomerFormNoMemberAndReturnFoundStylecoaches(CustomerFormModel customerData, AddressModel addressData) {

		List<DykscSeachModel> result = new ArrayList<DykscSeachModel>();

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		fillContactDetails(addressData);
		result = searchStylistByGeoipAndReturnFoundStylecoaches(addressData);
		checkIAgree();
		clickCompleteButton();

		return result;
	}

	// end - distribution and DYSKS

	@StepGroup
	public void fillCreateCustomerFormNoPartiesAndGetLatAndLong(CustomerFormModel customerData, AddressModel addressData) {

		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickGoToCustomerRegistration();
		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		inputEmail(customerData.getEmailName());
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkMember();
		fillContactDetails(addressData);
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	@StepGroup
	@Title("Fill create customer form under context")
	public void fillCreateCustomerFormUnderContext(CustomerFormModel customerData, AddressModel addressData, String context) {

		getDriver().get(MongoReader.getBaseURL() + context);
		System.out.println(MongoReader.getBaseURL() + context);
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
	@Title("Fill contact details")
	public void fillContactDetails(AddressModel addressData) {
		inputStreetAddress(addressData.getStreetAddress());
		inputStreetNumber(addressData.getStreetNumber());
		createCustomerPage().inputPhoneNumber(addressData.getPhoneNumber());
		inputPostCode(addressData.getPostCode());
		inputHomeTown(addressData.getHomeTown());
		waitABit(1000);
		selectCountryName(addressData.getCountryName());

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
	public void inputPostCodeFromPersonalInfo(String postCode) {
		createCustomerPage().inputPostCodeFromPersonalInfo(postCode);
	}

	@Step
	public void inputHomeTown(String homeTown) {
		createCustomerPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectCountryName(String countryName) {
		createCustomerPage().selectCountryName(countryName);
		waitABit(3000);
	}

	@Step
	public void selectCountryNameFromPersonalInfo(String countryName) {
		createCustomerPage().selectCountryNameFromPersonalInfo(countryName);
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
	public void searchStylistByGeoip(AddressModel addressModel) {
		createCustomerPage().searchStylistByGeoip();
		createCustomerPage().inputPostcodeFilter(addressModel.getPostCode());
		createCustomerPage().selectCountryFilter(addressModel.getCountryName());
		createCustomerPage().searchByGeoipSubmit();
		if (createCustomerPage().isStylecoachFound()) {
			createCustomerPage().selectFirstStylistFromList();
		}
	}

	@Step
	public void searchStylistByGeoipButDoNotSelectAnyStylecoach(AddressModel addressModel) {
		createCustomerPage().searchStylistByGeoip();
		createCustomerPage().inputPostcodeFilter("11111");
		createCustomerPage().selectCountryFilter(addressModel.getCountryName());
		createCustomerPage().searchByGeoipSubmit();

	}

	@Step
	public List<DykscSeachModel> searchStylistByGeoipAndReturnFoundStylecoaches(AddressModel addressModel) {
		createCustomerPage().searchStylistByGeoip();
		createCustomerPage().inputPostcodeFilter(addressModel.getPostCode());
		createCustomerPage().selectCountryFilter(addressModel.getCountryName());
		createCustomerPage().searchByGeoipSubmit();
		if (createCustomerPage().isStylecoachFound()) {
			createCustomerPage().selectFirstStylistFromList();
		}

		return createCustomerPage().getFoundStylecoachesData();

	}
	@Step
	public List<DykscSeachModel> searchStylistByNameStylecoaches(String firstName, String lastName) {
		createCustomerPage().searchStylistByName();
		createCustomerPage().inputSearchFirstName(firstName);
		createCustomerPage().inputSearchLastName(lastName);
		createCustomerPage().searchByNameSubmit();
		if (createCustomerPage().isStylecoachFound()) {
			createCustomerPage().selectFirstStylistFromList();
		}
		
		return createCustomerPage().getFoundStylecoachesData();
		
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
	 * 
	 * @param dataModel
	 * @param addressModel
	 */
	@Step
	@Title("Fill landing page form")
	public void fillLandingPageForm(CustomerFormModel dataModel, AddressModel addressModel) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.LANDING_PAGE);
		contactLandingPage().selectGender(true);
		contactLandingPage().inputFirstName(dataModel.getFirstName());
		contactLandingPage().inputLastName(dataModel.getLastName());
		contactLandingPage().inputEmail(dataModel.getEmailName());
		contactLandingPage().inputTelephoneAreaCode("49");
		contactLandingPage().inputTelephone(addressModel.getPhoneNumber());
		contactLandingPage().inputPostCode(addressModel.getPostCode());
		contactLandingPage().inputCity(addressModel.getHomeTown());
		contactLandingPage().selectCountry(addressModel.getCountryName());
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
	public void selectStylistOption(StyleMode mode, String firstName, String lastName, AddressModel addressModel) {

		String pageTitle;
		int counter = 0;
		do {
			waitABit(2000);
			pageTitle = thankYouPage().pageTitle();
			counter++;
		} while (!pageTitle.contains("Thank you page") && counter < 45);
		System.out.println(pageTitle);
		Assert.assertTrue("Failure: Page title is not as expected. Might be a wrong page. Actual: " + pageTitle, pageTitle.contains("PIPPA&JEAN"));

		landingCustomerAllocationPage().selectStylistOption(mode, firstName, lastName, addressModel);
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

		String pageTitle;
		int counter = 0;
		do {
			waitABit(2000);
			pageTitle = thankYouPage().pageTitle();
			counter++;
		} while (!pageTitle.contains("Thank you page") && counter < 45);
		Assert.assertTrue("Failure: Page title is not as expected. Might be a wrong page. Actual: " + pageTitle, pageTitle.contains("Thank you page"));

		thankYouPage().passwordInput(password);
		String email = thankYouPage().getEmailText();
		thankYouPage().checkIAgree();
		thankYouPage().submitButton();
		return email;
	}

	/**
	 * Assert for the ThankYouPage item - email
	 * 
	 * @param expected
	 * @param actual
	 */
	@Step
	public void verifyCustomerEmail(String expected, String actual) {
		Assert.assertTrue("Failure: email address not as expected: " + expected + ", actual: " + actual, expected.contentEquals(actual));
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
	 * thankyou-register - is the id of the form on the thank you page. We
	 * verify that the form is not present with this method. Page title is also
	 * validated Note time wait is large due to slow website.
	 */
	@Step
	public void verifySimpleThankYouPage() {

		String pageTitle;
		int counter = 0;
		do {
			waitABit(2000);
			pageTitle = thankYouPage().pageTitle();
			counter++;
		} while (!pageTitle.contains("Thank you page") && counter < 45);
		Assert.assertTrue("Failure: Page title is not as expected. Might be a wrong page. Actual: " + pageTitle, pageTitle.contains("Thank you page"));

	}

	@Step
	@Title("Fill widget registration form")
	public void fillWidgetRegistrationForm(String code, CustomerFormModel dataModel) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.REGISTER_LANDING_PAGE);
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

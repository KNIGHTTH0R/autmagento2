package com.steps.external.faceboook;

import com.tools.constants.TimeConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class FacebookRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = -2649339632021723245L;

	// FacebookRegistrationSteps() {
	// driverInit();
	// }

	public static String currentWindowHandle;

	@StepGroup
	public void verifyFbUserInfo(String fbName, String fbEmail) {
		waitABit(2000);
		findFrame("Kundenkonto");
		waitABit(TimeConstants.TIME_CONSTANT);
		
		verifyFbUserName(fbName);
		verifyFbUserEmail(fbEmail);

	}
	
	@StepGroup
	public void fillCreateInvitationCustomerForm(CustomerFormModel customerData, AddressModel addressData,String fbEmail,String stylistEmail) {

		inputFirstName(customerData.getFirstName());
		inputLastName(customerData.getLastName());
		verifyFBUserEmailPrefield(fbEmail);
		inputPassword(customerData.getPassword());
		inputConfirmation(customerData.getPassword());
		checkParties();
		fillContactDetails(addressData);
		verifyStylistEmail(stylistEmail);
		checkIAgree();
		clickCompleteButton();
	}

	
	
	
	private void verifyStylistEmail(String stylistEmail) {
		
		createCustomerPage().verifyStylistEmail(stylistEmail);
	}

	private void verifyFBUserEmailPrefield(String fbEmail) {
		// TODO Auto-generated method stub
		createCustomerPage().verifyFBUserEmailPrefield(fbEmail);
	}

	@StepGroup
	public void fillCreateCustomerByFacebookForm(CustomerFormModel customerData, AddressModel addressData,
			String password) {
		
		inputPassword(password);
		inputConfirmation(password);
		checkParties();
		fillContactDetails(addressData);
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
	}

	public void fillCreateCustomerByFacebookFormUnderContext(CustomerFormModel dataModel, AddressModel addressData,
			String password) {
		
		inputPassword(password);
		inputConfirmation(password);
		checkParties();
		fillContactDetails(addressData);
		checkIAgree();
		clickCompleteButton();

	}

	// this is only a work around --should be fixed
	@StepGroup
	public void fillCreateCustomerByFacebookFormNotPrefCountry(CustomerFormModel customerData, AddressModel addressData,
			String password) {
		waitABit(2000);
		findFrame("Kundenkonto");
		waitABit(TimeConstants.TIME_CONSTANT);
		checkParties();
		fillContactDetails(addressData);

		inputPassword(password);
		inputConfirmation(password);
		
		checkParties();
		fillContactDetails(addressData);
		searchStylistByGeoip(addressData);
		checkIAgree();
		clickCompleteButton();
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
	public void checkIAgree() {
		createCustomerPage().checkIAgree();
	}

	@Step
	public void clickCompleteButton() {
		createCustomerPage().clickCompleteButton();
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
		waitABit(1000);

	}

	@StepGroup
	@Title("Fill contact details")
	public void fillContactDetailsWithoutPlz(AddressModel addressData) {
		inputStreetAddress(addressData.getStreetAddress());
		inputStreetNumber(addressData.getStreetNumber());
		createCustomerPage().inputPhoneNumber(addressData.getPhoneNumber());
		inputHomeTown(addressData.getHomeTown());
		waitABit(1000);
		selectCountryName(addressData.getCountryName());

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

	@StepGroup
	public void verifyCustomerCreation() {
		verifyText();
		verifyLink();
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
	public void verifyLink() {
		registrationMessagePage().verifyLink();
	}

	@Step
	public void verifyText() {
		registrationMessagePage().verifyText();
	}

	@Step
	public void inputPassword(String passText) {
		createCustomerPage().inputPassword(passText);
	}

	@StepGroup
	public void goToFacebookLogin() {
		clickOnFacebookLogin();
		findFrame("Log in With Facebook");

	}
	
	@StepGroup
	public void goToFacebookRegistration() {
		clickOnFacebookRegistration();
		findFrame("Log in With Facebook");

	}

	@StepGroup
	public void goToTeaserFacebookLogin() {
		clickOnTeaserFacebookLogin();
		findFrame("Log in With Facebook");

	}
	
	@StepGroup
	public void goToTeaserFacebookLoginEsStoreView() {
		clickOnTeaserFacebookLoginES();
		findFrame("Log in With Facebook");

	}

	private void clickOnTeaserFacebookLoginES() {
		getDriver().get("http://staging-aut.pippajean.com/de/welcome-shop-landing/?___store=de_lang_es");
		getDriver().get("http://staging-aut.pippajean.com/de/welcome-shop-landing/?___store=de_lang_es");
		currentWindowHandle = getDriver().getWindowHandle();
		getDriver().manage().window().maximize();
		loginPage().clickOnFacebookSignIn();
		
	}


	@StepGroup
	public void goToTeaserFacebookLoginUnderContext(String context) {
		clickOnTeaserFacebookLoginUnderContext(context);
		//findFrame("Log in With Facebook");

	}

	private void clickOnTeaserFacebookLoginUnderContext(String context) {
		getDriver().get(MongoReader.getBaseURL() + context+"/welcome-shop-landing");
		getDriver().get(MongoReader.getBaseURL() + context+"/welcome-shop-landing");
		currentWindowHandle = getDriver().getWindowHandle();
		getDriver().manage().window().maximize();
		loginPage().clickOnFacebookSignIn();

	}

	private void clickOnTeaserFacebookLogin() {
		getDriver().get(MongoReader.getBaseURL() + "/welcome-shop-landing");
		getDriver().get(MongoReader.getBaseURL() + "/welcome-shop-landing");
		currentWindowHandle = getDriver().getWindowHandle();
		getDriver().manage().window().maximize();
		loginPage().clickOnFacebookSignIn();

	}

	@StepGroup
	public void goToFacebookRegistration(String user, String pass) {
		clickOnFacebookRegistration();
		findFrame("Facebook");
		// performFacebookLogin(user, pass);
		// waitABit(3000);
	}

	private void clickOnFacebookRegistration() {
		getDriver().get(MongoReader.getBaseURL());
		getDriver().get(MongoReader.getBaseURL());
		currentWindowHandle = getDriver().getWindowHandle();
		getDriver().manage().window().maximize();
		headerPage().clickAnmeldenButton();
		loginPage().clickOnFacebookRegistrationButton();

	}

	@Step
	public void confirmAccessRequest() {
		facebookEMBLoginConfirmPage().clickOnSubmmit();
	}

	@Step
	public void clickOnFacebookLogin() {
		getDriver().get(MongoReader.getBaseURL());
		getDriver().get(MongoReader.getBaseURL());
		currentWindowHandle = getDriver().getWindowHandle();
		getDriver().manage().window().maximize();
		headerPage().clickAnmeldenButton();
		loginPage().clickOnFacebookSignIn();
	}

	@Step
	public void performFacebookLogin(String user, String pass) {
		facebookEMBLoginPage().inputUser(user);
		facebookEMBLoginPage().inputPass(pass);
		facebookEMBLoginPage().clickLogin();
	}

	@Step
	public void fillFacebookRegistration(String zipCode, String selectOption, String passsword) {
		waitABit(TimeConstants.TIME_CONSTANT);
		findFrame("Create new account");
		facebookRegistrationFormPage().zipInput(zipCode);
		facebookRegistrationFormPage().countrySelect(selectOption);
		facebookRegistrationFormPage().passwordInput(passsword);
		facebookRegistrationFormPage().noSearchStyleCoach();
		facebookRegistrationFormPage().agreeAndConfirm();
	}

	@Step
	public void loginToFacebookApp(String user, String pass) {

		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();

	}

	@Step
	public void loginToFacebookPippaAndSwitchPage(String user, String pass) {

		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();
		getDriver().switchTo().window(currentWindowHandle);
	}
	
	@Step
	public void loginToFacebookAndSwitchPage(String user, String pass) {

		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();
		waitABit(5000);
		findFrame("Meine Einladungen");
	}
	
	@Step
	public void loginToFacebookPippa(String user, String pass) {

		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();
	}

	@Step
	public void switchToMainPageES() {
	//	getDriver().switchTo().window(currentWindowHandle);
		waitABit(8000);
		findFrame("Facebook");
		
	}

	
	@Step
	public void switchToMainPage() {
		waitABit(8000);
		getDriver().switchTo().window(currentWindowHandle);
		
	}
	
	@Step
	public void loginToFacebook(String user, String pass) {

		//

		getDriver().get("http:\\www.facebook.com");
		getDriver().manage().window().maximize();
		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();
		onlineStylePartyManagerPage().closePopUp();
	}

	@Step
	public void accessSettingsOnFacebookDesktopApp() {

		getDriver().get("https://www.facebook.com/settings?tab=applications");
		onlineStylePartyManagerPage().closePopUp();
	}
	
	@Step
	public void accesMessagesPage() throws Exception{
		getDriver().get("https://www.facebook.com/messages/");
		onlineStylePartyManagerPage().closePopUp();
		waitABit(3000);
		facebookLoginPage().removePopUpFb();
	}

	@Step
	public void removeThePJDevApp(String appId) {
		waitABit(3000);
		facebookLoginPage().removeThePJDevApp(appId);

	}

	@Step
	public void removeTheFbApp(String appId) throws Exception {
		waitABit(3000);
		//facebookLoginPage().removePopUpFb();
		facebookLoginPage().removeTheFbApp(appId);

	}

	public void editpermissionsForPJDevApp(String appId) {
		// TODO Auto-generated method stub
		waitABit(3000);
		facebookLoginPage().editpermissionsForPJDevApp(appId);
	}

	public void verifySuccesfulyRegister() {
		// TODO Auto-generated method stub
		waitABit(TimeConstants.TIME_CONSTANT);
		findFrame("Create new account");
	}

	public void switchBackToPreviousPage() {
		// TODO Auto-generated method stub

		waitABit(TimeConstants.TIME_CONSTANT);
		findFrame("Schmuckstücke");

	}


	private void verifyFbUserName(String fbName) {
		facebookRegistrationFormPage().verifyFbUserName(fbName);
	}

	private void verifyFbUserEmail(String fbEmail) {
		facebookRegistrationFormPage().verifyFbUserEmail(fbEmail);
	}

}

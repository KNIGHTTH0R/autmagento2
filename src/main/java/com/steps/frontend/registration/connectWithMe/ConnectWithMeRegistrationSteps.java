package com.steps.frontend.registration.connectWithMe;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import org.junit.Assert;

import com.pages.frontend.registration.connectWithMe.ConnectWithMeAllocationPage.StyleMode;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class ConnectWithMeRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void navigateToConnectWithMeUnderMasterShop() {
		navigate(MongoReader.getBaseURL());
		homePage().clickStylePartyLink();
	}

	@Step
	public void navigateToConnectWithMeUnderContext(String context) {
		navigate(MongoReader.getBaseURL() + context);
		homePage().clickStylePartyLink();
	}

	@Step
	public void loggedInUsernavigatesToConnectWithMeUnderContext(String context) {
		homePage().clickStylePartyLink();
	}

	@Step
	@Title("Fill connect with me form")
	public void fillConnectWithMeForm(CustomerFormModel dataModel, AddressModel addressModel) {
		connectWithMeRegistrationPage().selectGender(true);
		connectWithMeRegistrationPage().inputFirstName(dataModel.getFirstName());
		connectWithMeRegistrationPage().inputLastName(dataModel.getLastName());
		connectWithMeRegistrationPage().inputEmail(dataModel.getEmailName());
		connectWithMeRegistrationPage().inputPostCode(addressModel.getPostCode());
		connectWithMeRegistrationPage().inputCity(addressModel.getHomeTown());
		connectWithMeRegistrationPage().inputTelephoneAreaCode("000");
		connectWithMeRegistrationPage().inputTelephone(addressModel.getPhoneNumber());
		connectWithMeRegistrationPage().selectCountry(addressModel.getCountryName());
		connectWithMeRegistrationPage().checkNewslletterCheckbox(true);
		connectWithMeRegistrationPage().checkPartiesCheckbox(true);
		connectWithMeRegistrationPage().checkMemberCheckbox(true);
		connectWithMeRegistrationPage().iAgreeCheckbox();
		connectWithMeRegistrationPage().clickOk();
	}

	@Step
	@Title("Fill connect with me predefined form (user logged in)")
	public void fillConnectWithMePredefinedForm() {
		connectWithMeRegistrationPage().checkNewslletterCheckbox(true);
		connectWithMeRegistrationPage().checkPartiesCheckbox(true);
		connectWithMeRegistrationPage().checkMemberCheckbox(true);
		connectWithMeRegistrationPage().clickOk();
	}

	@Step
	public void selectStylistOption(StyleMode mode, String firstName, String lastName, AddressModel addressModel) {

		String pageTitle;
		int counter = 0;
		do {
			waitABit(2000);
			pageTitle = thankYouPage().pageTitle();
			counter++;
		} while (!pageTitle.contains("Werde style coach") && counter < 30);
		System.out.println(pageTitle);
		Assert.assertTrue("Failure: Page title is not as expected. Might be a wrong page. Actual: " + pageTitle, pageTitle.contains("PIPPA&JEAN"));

		connectWithMeAllocationPage().selectStylistOpt(mode, firstName, lastName, addressModel);
	}

	@Step
	public void submitStylistSelection() {
		landingCustomerAllocationPage().submitAndContinue();
	}

	@Step
	public void verifyCustomerEmail(String expected, String actual) {
		Assert.assertTrue("Failure: email address not as expected: " + expected + ", actual: " + actual, expected.contentEquals(actual));
	}

	@Step
	public void verifySuccessLink() {
		String grabedTitle = getDriver().getCurrentUrl();
		Assert.assertTrue("Failure: Success link not found. Actual URL: " + grabedTitle, grabedTitle.contains("connectsuccess"));
	}

	@Step
	public void verifySuccessLinkUnderContext() {
		String grabedTitle = getDriver().getCurrentUrl();
		Assert.assertTrue("Failure: Success link not found. Actual URL: " + grabedTitle, grabedTitle.contains("connectDistribution"));
	}

	@Step
	public void verifyConnectWithMeRegistrationSuccesMessage() {
		connectSuccesPage().verifyConnectWithMeRegistrationSuccesMessage();
	}

	@Step
	public void verifyConnectWithMeSuccesRegistrationContainsScName(String stylecoachName) {
		connectSuccesPage().verifyConnectWithMeSuccesRegistrationContainsScName(stylecoachName);
	}

}

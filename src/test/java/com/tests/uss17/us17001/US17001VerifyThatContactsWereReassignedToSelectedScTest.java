package com.tests.uss17.us17001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.ContactDetailsSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyContactsListSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.FilePaths;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.contact.ContactValidationWorkflows;

@WithTag(name = "US17.1 Check reassigned duplicate contacts and customer associated contacts when new SC is selected", type = "Scenarios")
@Story(Application.MassAction.US17_1.class)
@RunWith(SerenityRunner.class)
public class US17001VerifyThatContactsWereReassignedToSelectedScTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public MyContactsListSteps myContactsListSteps;
	@Steps
	public ContactValidationWorkflows contactValidationWorkflows;
	@Steps
	public ContactDetailsSteps contactDetailsSteps;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel oldStylistModel;
	private CustomerFormModel contactModel;
	private AddressModel contactAddressModel;
	private DateModel contactDateModel;

	private CustomerFormModel customerModel;
	private AddressModel customerAddressModel;
	private DateModel customerDateModel;

	private ContactModel contactExpectedDetailsModel = new ContactModel();
	private ContactModel customerExpectedDetailsModel = new ContactModel();

	private ContactModel contactGrabbedDetailsModel = new ContactModel();
	private ContactModel customerGrabbedDetailsModel = new ContactModel();

	private String secondStyleCoachUsername;
	private String secondStyleCoachPassword;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17001.properties");
			prop.load(input);

			secondStyleCoachUsername = prop.getProperty("stylecoachUsername");
			secondStyleCoachPassword = prop.getProperty("stylecoachPassword");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		oldStylistModel = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").get(0);
		
		customerModel = MongoReader.grabCustomerFormModels("US17001SecondRegularCustomerRegistrationTest").get(0);
		customerDateModel = MongoReader.grabDateModels("US17001SecondRegularCustomerRegistrationTest").get(0);
		customerAddressModel = MongoReader.grabAddressModels("US17001SecondRegularCustomerRegistrationTest").get(0);

		contactModel = MongoReader.grabCustomerFormModels("US17001AddSecondNewContactToStyleCoachTest").get(0);
		contactDateModel = MongoReader.grabDateModels("US17001AddSecondNewContactToStyleCoachTest").get(0);
		contactAddressModel = MongoReader.grabAddressModels("US17001AddSecondNewContactToStyleCoachTest").get(0);

		contactExpectedDetailsModel = contactValidationWorkflows.populateExpectedContactModel(oldStylistModel, contactModel, contactDateModel, contactAddressModel);

		contactExpectedDetailsModel.setPartyHostStatus(ContextConstants.PARTY_FLAG_STATUS);
		contactExpectedDetailsModel.setStyleCoachStatus(ContextConstants.STYLE_COACH_FLAG_STATUS);
		contactExpectedDetailsModel.setNewsletterStatus(ContextConstants.NEWSLETTER_FLAG_STATUS);

		PrintUtils.printContactModel(contactExpectedDetailsModel);

		customerExpectedDetailsModel = contactValidationWorkflows.populateExpectedContactModel(oldStylistModel, customerModel, customerDateModel, customerAddressModel);

		customerExpectedDetailsModel.setPartyHostStatus(ContextConstants.PARTY_FLAG_STATUS);
		customerExpectedDetailsModel.setStyleCoachStatus(ContextConstants.STYLE_COACH_FLAG_STATUS);
		customerExpectedDetailsModel.setNewsletterStatus(ContextConstants.NEWSLETTER_FLAG_STATUS);

		PrintUtils.printContactModel(customerExpectedDetailsModel);

	}

	@Test
	public void us17001VerifyThatContactsWereReassignedToSelectedScTest() {

		customerRegistrationSteps.performLogin(secondStyleCoachUsername, secondStyleCoachPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());

		loungeSteps.goToContactsList();
		myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getEmailName(), contactDateModel.getDate());
		contactGrabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(contactExpectedDetailsModel, contactGrabbedDetailsModel);

		loungeSteps.goToContactsList();
		myContactsListSteps.verifyUnicAndOpenContactDetails(customerModel.getEmailName(), customerDateModel.getDate());
		customerGrabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(customerExpectedDetailsModel, customerGrabbedDetailsModel);

		customVerifications.printErrors();

	}

}

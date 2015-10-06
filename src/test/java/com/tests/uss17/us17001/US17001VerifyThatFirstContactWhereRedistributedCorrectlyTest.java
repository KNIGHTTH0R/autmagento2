package com.tests.uss17.us17001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

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
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.contact.ContactValidationWorkflows;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17001VerifyThatFirstContactWhereRedistributedCorrectlyTest extends BaseTest {

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

	public CustomerFormModel stylistRegistrationData;

	public CustomerFormModel contactModel;
	public AddressModel contactAddressModel;
	public DateModel contactDateModel;

	public CustomerFormModel customerModel;
	public AddressModel customerAddressModel;
	public DateModel customerDateModel;

	ContactModel contactExpectedDetailsModel = new ContactModel();
	ContactModel customerExpectedDetailsModel = new ContactModel();

	ContactModel contactGrabbedDetailsModel = new ContactModel();
	ContactModel customerGrabbedDetailsModel = new ContactModel();

	private String secondStyleCoachUsername;
	private String secondStyleCoachPassword;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17001.properties");
			prop.load(input);
			//this is the good one
//			secondStyleCoachUsername = prop.getProperty("secondStyleCoachUsername");
//			secondStyleCoachPassword = prop.getProperty("secondStyleCoachPassword");
			// this is just for testing purpose,work-around due to a bug 
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
		customerModel = MongoReader.grabCustomerFormModels("US17001SecondRegularCustomerRegistrationTest").get(0);
		customerDateModel = MongoReader.grabStylistDateModels("US17001SecondRegularCustomerRegistrationTest").get(0);
		customerAddressModel = MongoReader.grabAddressModels("US17001SecondRegularCustomerRegistrationTest").get(0);

		contactModel = MongoReader.grabCustomerFormModels("US17001AddNewContactToStyleCoachTest").get(0);
		contactDateModel = MongoReader.grabStylistDateModels("US17001AddSecondNewContactToStyleCoachTest").get(0);
		contactAddressModel = MongoReader.grabAddressModels("US17001AddSecondNewContactToStyleCoachTest").get(0);

		contactExpectedDetailsModel.setName(contactModel.getFirstName() + " " + contactModel.getLastName());
		contactExpectedDetailsModel.setCreatedAt(contactDateModel.getDate());
		contactExpectedDetailsModel.setStreet(contactAddressModel.getStreetAddress());
		contactExpectedDetailsModel.setNumber(contactAddressModel.getStreetNumber());
		contactExpectedDetailsModel.setZip(contactAddressModel.getPostCode());
		contactExpectedDetailsModel.setTown(contactAddressModel.getHomeTown());
		contactExpectedDetailsModel.setCountry(contactAddressModel.getCountryName());

		contactExpectedDetailsModel.setPartyHostStatus(ContextConstants.PARTY_FLAG_STATUS);
		contactExpectedDetailsModel.setStyleCoachStatus(ContextConstants.STYLE_COACH_FLAG_STATUS);
		contactExpectedDetailsModel.setNewsletterStatus(ContextConstants.NEWSLETTER_FLAG_STATUS);
		
		PrintUtils.printContactModel(contactExpectedDetailsModel);

		customerExpectedDetailsModel.setName(customerModel.getFirstName() + " " + customerModel.getLastName());
		customerExpectedDetailsModel.setCreatedAt(customerDateModel.getDate());
		customerExpectedDetailsModel.setStreet(customerAddressModel.getStreetAddress());
		customerExpectedDetailsModel.setNumber(customerAddressModel.getStreetNumber());
		customerExpectedDetailsModel.setZip(customerAddressModel.getPostCode());
		customerExpectedDetailsModel.setTown(customerAddressModel.getHomeTown());
		customerExpectedDetailsModel.setCountry(customerAddressModel.getCountryName());

		customerExpectedDetailsModel.setPartyHostStatus(ContextConstants.PARTY_FLAG_STATUS);
		customerExpectedDetailsModel.setStyleCoachStatus(ContextConstants.STYLE_COACH_FLAG_STATUS);
		customerExpectedDetailsModel.setNewsletterStatus(ContextConstants.NEWSLETTER_FLAG_STATUS);
		
		PrintUtils.printContactModel(customerExpectedDetailsModel);

	}

	@Test
	public void us17001VerifyThatFirstContactWhereRedistributedCorrectlyTest() {

		customerRegistrationSteps.performLogin(secondStyleCoachUsername, secondStyleCoachPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());

		loungeSteps.goToContactsList();
		myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getEmailName(), contactDateModel.getDate());
		contactGrabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(contactExpectedDetailsModel, contactGrabbedDetailsModel);
		customVerifications.printErrors();

		loungeSteps.goToContactsList();
		myContactsListSteps.verifyUnicAndOpenContactDetails(customerModel.getEmailName(), customerDateModel.getDate());
		customerGrabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(customerExpectedDetailsModel, customerGrabbedDetailsModel);
		customVerifications.printErrors();

	}

}

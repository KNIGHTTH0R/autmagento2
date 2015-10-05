package com.tests.uss17.us17002;

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

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.ContactDetailsSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyContactsListSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.contact.ContactValidationWorkflows;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17002VerifyThatFirstContactWhereRedistributedCorrectlyTest extends BaseTest {

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
	public ContactDetailsSteps contactDetailsSteps;
	@Steps
	public ContactValidationWorkflows contactValidationWorkflows;

	public CustomerFormModel stylistRegistrationData;

	public CustomerFormModel contactModel;
	public DateModel dateModel;
	public AddressModel addressModel;
	public ContactModel grabbedDetailsModel;
	public ContactModel expectedDetailsModel;

	private String masterSCUsername;
	private String masterSCPassword;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17002.properties");
			prop.load(input);

			masterSCUsername = prop.getProperty("masterSCUsername");
			masterSCPassword = prop.getProperty("masterSCPassword");

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
		contactModel = MongoReader.grabCustomerFormModels("US17002AddNewContactToStyleCoachTest").get(0);
		dateModel = MongoReader.grabStylistDateModels("US17002AddNewContactToStyleCoachTest").get(0);
		addressModel = MongoReader.grabAddressModels("US17002AddNewContactToStyleCoachTest").get(0);

		// TODO make this pretty, put it in a method
		expectedDetailsModel.setName(contactModel.getFirstName() + " " + contactModel.getLastName());
		expectedDetailsModel.setCreatedAt(dateModel.getDate());
		expectedDetailsModel.setStreet(addressModel.getStreetAddress());
		expectedDetailsModel.setNumber(addressModel.getStreetNumber());
		expectedDetailsModel.setZip(addressModel.getPostCode());
		expectedDetailsModel.setTown(addressModel.getHomeTown());
		expectedDetailsModel.setCountry(addressModel.getCountryName());
		expectedDetailsModel.setPartyHostStatus(ContextConstants.PARTY_FLAG_STATUS);
		expectedDetailsModel.setStyleCoachStatus(ContextConstants.STYLE_COACH_FLAG_STATUS);
		expectedDetailsModel.setNewsletterStatus(ContextConstants.NEWSLETTER_FLAG_STATUS);

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17002VerifyThatFirstContactWhereRedistributedCorrectlyTest() {

		customerRegistrationSteps.performLogin(masterSCUsername, masterSCPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToContactsList();
		myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getEmailName(), dateModel.getDate());
		grabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(grabbedDetailsModel, expectedDetailsModel);
	}

}

package com.tests.uss17.us17002;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.contact.ContactValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US17.2 Check reassigned contacts on customer's preffered SC hierarchy if customer's preffered is quit", type = "Scenarios")
@Story(Application.MassAction.US17_2.class)
@RunWith(SerenityRunner.class)
public class US17002VerifyThatContactWasReassignedCorrectlyTest extends BaseTest {

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
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel oldStylistModel;
	private CustomerFormModel contactModel;
	private DateModel dateModel;
	private AddressModel addressModel;
	private ContactModel grabbedDetailsModel;
	private ContactModel expectedDetailsModel = new ContactModel();
	private String username;
	private String password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17002.properties");
			prop.load(input);

			username = prop.getProperty("stylecoachUsername");
			password = prop.getProperty("stylecoachPassword");

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
		oldStylistModel = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationTest").get(0);
		contactModel = MongoReader.grabCustomerFormModels("US17002RegularCustomerRegistrationTest").get(0);
		dateModel = MongoReader.grabDateModels("US17002RegularCustomerRegistrationTest").get(0);
		addressModel = MongoReader.grabAddressModels("US17002RegularCustomerRegistrationTest").get(0);

		expectedDetailsModel = contactValidationWorkflows.populateExpectedContactModel(oldStylistModel, contactModel, dateModel, addressModel);

		expectedDetailsModel.setHasPartyHostInterrest(true);
		expectedDetailsModel.setHasStyleCoachInterrest(false);
		expectedDetailsModel.setIsNewsletterSubscribed(true);

		PrintUtils.printContactModel(expectedDetailsModel);
	}

	@Test
	public void us17002VerifyThatContactWasReassignedCorrectlyTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToContactsList();
		
		myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getFirstName(),contactModel.getLastName());

		
	//	myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getEmailName(), dateModel.getDate());
		grabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(expectedDetailsModel, grabbedDetailsModel);
		customVerifications.printErrors();
	}

}

package com.tests.uss17.us17003;

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
import com.tools.constants.ContextConstants;
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

@WithTag(name = "US17.3 Check reassigned contacts up on canceled SC hierarchy when no new Sc is selected", type = "Scenarios")
@Story(Application.MassAction.US17_3.class)
@RunWith(SerenityRunner.class)
public class US17003VerifyContactIsReassignedUpOnCanceledScHierarchyTest extends BaseTest {

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

	private ContactModel contactExpectedDetailsModel = new ContactModel();
	private ContactModel contactGrabbedDetailsModel = new ContactModel();

	private String username;
	private String password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17003.properties");
			prop.load(input);
			
			username = prop.getProperty("masterSCUsername");
			password = prop.getProperty("masterSCPassword");

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
		oldStylistModel = MongoReader.grabCustomerFormModels("US17003StyleCoachRegistrationTest").get(0);
		contactModel = MongoReader.grabCustomerFormModels("US17003AddNewContactToStyleCoachTest").get(0);
		contactDateModel = MongoReader.grabDateModels("US17003AddNewContactToStyleCoachTest").get(0);
		contactAddressModel = MongoReader.grabAddressModels("US17003AddNewContactToStyleCoachTest").get(0);

		contactExpectedDetailsModel = contactValidationWorkflows.populateExpectedContactModel(oldStylistModel,contactModel, contactDateModel, contactAddressModel);

		contactExpectedDetailsModel.setHasPartyHostInterrest(true);
		contactExpectedDetailsModel.setHasStyleCoachInterrest(true);
		contactExpectedDetailsModel.setIsNewsletterSubscribed(true);

		PrintUtils.printContactModel(contactExpectedDetailsModel);
	}

	@Test
	public void us17003VerifyContactIsReassignedUpOnCanceledScHierarchyTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());

		loungeSteps.goToContactsList();
		
		myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getFirstName(),contactModel.getLastName());
	//	myContactsListSteps.verifyUnicAndOpenContactDetails(contactModel.getEmailName(), contactDateModel.getDate());
		contactGrabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateContactDetails(contactExpectedDetailsModel, contactGrabbedDetailsModel);

		customVerifications.printErrors();

	}

}

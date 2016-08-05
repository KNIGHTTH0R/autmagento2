package com.tests.uss29.us29001;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactDetailsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.contact.ContactBackendValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithDriver;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US28.1 Unbounce registration with regular distribution", type = "Scenarios")
@Story(Application.UnbounceRegistration.US28_1.class)
@RunWith(SerenityRunner.class)
public class US29001CheckContactInBackendTest extends BaseTest {

	public MongoConnector mongoConnector;
	public GmailConnector gmailConnector;

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public ContactBackendValidationWorkflows contactBackendValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private ContactDetailsModel expectedContactDetailsModel;
	private CustomerFormModel customerFormModel;
	private AddressModel addressModel;
	private DateModel dateModel;
	private List<DBStylistModel> stylistsList;

	@Before
	public void setUp() throws Exception {
		
		customerFormModel = MongoReader.grabCustomerFormModels("US29001UnbounceDykscRegistrationTest").get(0);
		addressModel = MongoReader.grabAddressModels("US29001UnbounceDykscRegistrationTest").get(0);
		dateModel = MongoReader.grabDateModels("US29001UnbounceDykscRegistrationTest").get(0);

	//	stylistsList = MongoReader.grabDbStylistModels("US29001UnbounceDykscRegistrationTest");

		expectedContactDetailsModel = contactBackendValidationWorkflows.populateContactDetailsModel(customerFormModel,
				addressModel, dateModel);
	}

	@Test
	@WithDriver("firefox")
	public void us29001CheckContactInBackendTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnContactList();
		stylecoachListBackendSteps.openContactDetails(customerFormModel.getEmailName());
		ContactDetailsModel grabbedContactDetailsModel = stylecoachListBackendSteps.grabContactDetails();
		contactBackendValidationWorkflows.verifyUnbounceContactDetails(grabbedContactDetailsModel,
				expectedContactDetailsModel);
		contactBackendValidationWorkflows.validateContactsParentStylecoach(stylistsList,
				grabbedContactDetailsModel.getParentId());

		customVerifications.printErrors();
	}

}

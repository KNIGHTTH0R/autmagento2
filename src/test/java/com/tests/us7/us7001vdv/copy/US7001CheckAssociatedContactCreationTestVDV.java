package com.tests.us7.us7001vdv.copy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactDetailsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.contact.ContactBackendValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US6.1 Sc Registration New Customer Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(SerenityRunner.class)
public class US7001CheckAssociatedContactCreationTestVDV extends BaseTest {

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
	private String prefferedStylist = "Pippa Jean";

	@Before
	public void setUp() throws Exception {

		customerFormModel = MongoReader.grabCustomerFormModels("US7001RegularCustRegOnMasterTestVDV").get(0);
		addressModel = MongoReader.grabAddressModels("US7001RegularCustRegOnMasterTestVDV").get(0);
		expectedContactDetailsModel = contactBackendValidationWorkflows.populateSimpleContactDetailsModel(customerFormModel, addressModel, prefferedStylist);
	}

	@Test
	public void us7001CheckAssociatedContactCreationTestVDV() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnContactList();
		stylecoachListBackendSteps.openContactDetails(customerFormModel.getEmailName());
		ContactDetailsModel grabbedContactDetailsModel = stylecoachListBackendSteps.grabContactDetails();
		contactBackendValidationWorkflows.verifyCreatedContactDetails(grabbedContactDetailsModel, expectedContactDetailsModel);
		
		customVerifications.printErrors();
	}

}

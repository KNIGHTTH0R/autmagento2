package com.tests.uss17.us17001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
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
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.contact.ContactValidationWorkflows;

@WithTag(name = "US17.1 Check reassigned duplicate contacts and customer associated contacts when new SC is selected", type = "Scenarios")
@Story(Application.MassAction.US17_1.class)
@RunWith(SerenityRunner.class)
public class US17001AddNewContactToStyleCoachTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;
	@Steps
	public MyContactsListSteps myContactsListSteps;
	
	@Steps
	public ContactValidationWorkflows contactValidationWorkflows;
	@Steps
	public ContactDetailsSteps contactDetailsSteps;
	
	private ContactModel expectedDetailsModel = new ContactModel();

	private ContactModel grabbedDetailsModel;
	
	private DateModel dateModel;
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CustomerFormModel stylistRegistrationData;
	

	@Before
	public void setUp() throws Exception {
		dateModel=new DateModel();
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		stylistRegistrationData=new CustomerFormModel();
		
		stylistRegistrationData.setEmailName("vdv.automation@gmail.com");
		stylistRegistrationData.setPassword("emilian1");
		MongoConnector.cleanCollection(getClass().getSimpleName());
		
		dateModel.setDate(DateUtils.getCurrentDate("dd.MM.YYYY"));

		expectedDetailsModel = contactValidationWorkflows.populateExpectedContactModel(stylistRegistrationData,dataModel,dateModel,addressModel);

		expectedDetailsModel.setHasPartyHostInterrest(true);
		expectedDetailsModel.setHasStyleCoachInterrest(true);
		expectedDetailsModel.setIsNewsletterSubscribed(true);
	}

	@Test
	public void us17001AddNewContactToStyleCoachTest() {

		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		loungeSteps.goToToAddNewContact();
		createNewContactSteps.fillCreateNewContact(dataModel, addressModel);
		myContactsListSteps.verifyUnicAndOpenContactDetails(dataModel.getFirstName(),dataModel.getFirstName());
	
		grabbedDetailsModel = contactDetailsSteps.grabContactDetails();
		contactValidationWorkflows.validateCreatedContactDetails(expectedDetailsModel, grabbedDetailsModel);
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}
}

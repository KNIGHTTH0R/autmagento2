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
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US17.1 Check reassigned duplicate contacts and customer associated contacts when new SC is selected", type = "Scenarios")
@Story(Application.MassAction.US17_1.class)
@RunWith(SerenityRunner.class)
public class US17001AddSecondNewContactToStyleCoachTest extends BaseTest {

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

	private CustomerFormModel stylistRegistrationData;
	private CustomerFormModel contactModel;
	private DateModel dateModel = new DateModel();
	private AddressModel addressModel;


	@Before
	public void setUp() throws Exception {

		contactModel = MongoReader.grabCustomerFormModels("US17001AddNewContactToStyleCoachTest").get(0);
		contactModel.setFirstName();
		addressModel = new AddressModel();

		int size = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17001AddSecondNewContactToStyleCoachTest() {

		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToToAddNewContact();
		createNewContactSteps.fillCreateNewContactWithoutUnrequiredAddressDetailsAndWithoutInterrests(contactModel, addressModel);
		dateModel.setDate(DateUtils.getCurrentDate("dd.MM.YYYY"));

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(contactModel, getClass().getSimpleName());
		MongoWriter.saveAddressModel(addressModel,  getClass().getSimpleName());
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
	}
}

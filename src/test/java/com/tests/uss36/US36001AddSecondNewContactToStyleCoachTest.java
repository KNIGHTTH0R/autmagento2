package com.tests.uss36;

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
import com.tools.utils.DateUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class US36001AddSecondNewContactToStyleCoachTest extends BaseTest{
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

		contactModel = MongoReader.grabCustomerFormModels("US36001AddNewContactToStyleCoachTest").get(0);
		contactModel.setFirstName();
		addressModel = new AddressModel();

		int size = MongoReader.grabCustomerFormModels("US36001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US36001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us36001AddSecondNewContactToStyleCoachTest() {

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

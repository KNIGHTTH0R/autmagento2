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
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.Separators;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US17.1 Check reassigned duplicate contacts and customer associated contacts when new SC is selected", type = "Scenarios")
@Story(Application.MassAction.US17_1.class)
@RunWith(SerenityRunner.class)
public class US17001RegularCustomerRegistrationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private DateModel dateModel;
	private CustomerFormModel stylistAddressModel;

	@Before
	public void setUp() throws Exception {
		dateModel = new DateModel();
		stylistAddressModel = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").get(0);
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17001RegularCustomerRegistrationTest() {
		
		customerRegistrationSteps.fillCreateCustomerFormUnderContext(dataModel, addressModel,
				Separators.SLASH + stylistAddressModel.getFirstName() + stylistAddressModel.getLastName());
		customerRegistrationSteps.verifyCustomerCreation();
		dateModel.setDate(DateUtils.getCurrentDate("dd.MM.YYYY"));
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveAddressModel(addressModel, getClass().getSimpleName());
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
	}

}
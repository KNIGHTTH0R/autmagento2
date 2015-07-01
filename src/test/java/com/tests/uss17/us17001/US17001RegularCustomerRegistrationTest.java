package com.tests.uss17.us17001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US17001RegularCustomerRegistrationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public CustomerFormModel stylistAddressModel;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		stylistAddressModel = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").get(0);
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17001RegularCustomerRegistrationTest() {

		customerRegistrationSteps.fillCreateCustomerFormUnderContext(dataModel, addressModel, stylistAddressModel.getFirstName() + stylistAddressModel.getLastName());
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}
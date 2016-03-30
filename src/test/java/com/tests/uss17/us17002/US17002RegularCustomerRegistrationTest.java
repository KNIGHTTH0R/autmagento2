package com.tests.uss17.us17002;

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

@WithTag(name = "US17.2 Check reassigned contacts on customer's preffered SC hierarchy if customer's preffered is quit", type = "Scenarios")
@Story(Application.MassAction.US17_2.class)
@RunWith(SerenityRunner.class)
public class US17002RegularCustomerRegistrationTest extends BaseTest {

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
		stylistAddressModel = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationTest").get(0);
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17002RegularCustomerRegistrationTest() {

		customerRegistrationSteps.fillCreateCustomerFormNoMemberUnderContext(dataModel, addressModel,
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
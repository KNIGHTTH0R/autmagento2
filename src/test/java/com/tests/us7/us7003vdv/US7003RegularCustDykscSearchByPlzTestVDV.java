package com.tests.us7.us7003vdv;

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
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.1b Regular Customer Registration on Master Not Preffered Country Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US7003RegularCustDykscSearchByPlzTestVDV extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;

	@Before
	public void setUp() throws Exception {
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		addressModel.setPostCode("10179");
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us7003RegularCustDykscSearchByPlzTestVDV() {

		customerRegistrationSteps.fillCreateCustomerForm(dataModel, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}
package com.tests.us7.us7002;

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
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7002", type = "frontend")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US7002RegularCustomerRegistrationTest extends BaseTest{

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;
	public String context;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		context = "simona";
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7002RegularCustomerRegistrationTest() {

		customerRegistrationSteps.fillCreateCustomerFormUnderContext(dataModel, addressModel,"simona");
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
		
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}
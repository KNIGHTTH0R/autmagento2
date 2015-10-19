package com.tests.uss14.us14003;

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
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;

@WithTag(name = "US14.3 Distribution during checkout to customer lead qualified SC", type = "Scenarios")
@Story(Application.DistributionDuringCheckout.US14_3.class)
@RunWith(ThucydidesRunner.class)
public class US14003CustomerLeadDistributionTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		addressModel.setStreetAddress(FieldGenerators.generateRandomString(12, Mode.ALPHA));
		addressModel.setHomeTown(FieldGenerators.generateRandomString(12, Mode.ALPHA));
		addressModel.setPostCode("123");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us14003CustomerLeadDistributionTest() {

		customerRegistrationSteps.fillCreateCustomerFormWithNoStylePartyAndStyleCoachChecked(dataModel, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());

	}

}
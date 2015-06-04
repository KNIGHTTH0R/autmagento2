package com.tests.uss14.us14001;

import java.util.ArrayList;
import java.util.List;

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
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;
import com.tools.utils.RandomAddress;

@WithTag(name = "US13", type = "frontend")
@Story(Application.Distribution.StyleCoachLead.class)
@RunWith(ThucydidesRunner.class)
public class US14001StyleCoachLeadDistributionTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;

	@Before
	public void setUp() throws Exception {

		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		addressModel.setStreetAddress(FieldGenerators.generateRandomString(12, Mode.ALPHA));
		addressModel.setHomeTown(FieldGenerators.generateRandomString(12, Mode.ALPHA));
		addressModel.setPostCode("123");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us14001StyleCoachLeadDistributionTest() {

		customerRegistrationSteps.fillCreateCustomerFormAndGetLatAndLong(dataModel, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());

	}

}
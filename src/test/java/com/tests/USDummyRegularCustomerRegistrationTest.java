package com.tests;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.CreateProduct;
import com.connectors.mongo.MongoConnector;
import com.poc.geolocationAPI.AddressConverter;
import com.poc.readFromFile.RandomAddress;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;

@WithTag(name = "US7", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class USDummyRegularCustomerRegistrationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;
	CoordinatesModel coordinatesModel = new CoordinatesModel();
	RandomAddress randomAddress;
	List<DBStylistModel> stylistList = new ArrayList<DBStylistModel>();
	List<DBStylistModel> activeStylistList = new ArrayList<DBStylistModel>();
	List<DBStylistModel> activeStylistList2 = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		stylistList = CreateProduct.getStylistList();
		activeStylistList = CreateProduct.getActiveStylistsFromList(stylistList);

		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		randomAddress = new RandomAddress();

		addressModel = randomAddress.getRandomAddressFromFile();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void usDummyRegularCustomerRegistrationTest() {

		coordinatesModel = customerRegistrationSteps.fillCreateCustomerFormAndGetLatAndLong(dataModel, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
		customVerifications.printErrors();
	}

	// @After
	// public void saveData() {
	// MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	// MongoWriter.saveCoordinatesModel(coordinatesModel,
	// getClass().getSimpleName());
	// }

}
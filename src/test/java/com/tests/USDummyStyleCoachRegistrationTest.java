package com.tests;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.RandomAddress;

@WithTag(name = "US6", type = "frontend")
@Story(Application.Registration.Stylist.class)
@RunWith(ThucydidesRunner.class)
public class USDummyStyleCoachRegistrationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomVerification customVerification;

	public CustomerFormModel customerFormData;
	public DateModel customerFormDate = new DateModel();
	public DateModel birthDate = new DateModel();
	public CoordinatesModel coordinatesModel = new CoordinatesModel();
	public AddressModel customerFormAddress;
	public StylistDataModel validationModel;
	RandomAddress randomAddress;

	@Before
	public void setUp() throws Exception {
		
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		randomAddress = new RandomAddress();
		customerFormAddress = randomAddress.getRandomAddressFromFile();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void usDummyStyleCoachRegistrationTest() {
		headerSteps.navigateToRegisterForm();
		coordinatesModel = stylistRegistrationSteps.fillCreateStylystFormAndReturnLatAndLong(customerFormData, customerFormAddress, birthDate.getDate());

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());

	}
}

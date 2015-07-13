package com.tests.uss15.us15003;

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
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US15", type = "frontend")
@Story(Application.Newsletter.class)
@RunWith(ThucydidesRunner.class)
public class US15003StyleCoachRegistrationTest extends BaseTest {

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
	public AddressModel customerFormAddress;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15003StyleCoachRegistrationTest() {
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerFormPayWithVisa(customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);
		customVerification.printErrors();

	}

	@After
	public void saveData() {

		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
	}
}

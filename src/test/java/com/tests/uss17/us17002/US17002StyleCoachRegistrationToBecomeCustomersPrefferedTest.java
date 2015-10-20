package com.tests.uss17.us17002;

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
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "frontend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomVerification customVerification;

	private CustomerFormModel stylecoachFormData;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;

	@Before
	public void setUp() throws Exception {

		stylecoachFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17002StyleCoachRegistrationToBecomeCustomersPrefferedTest() {
		headerSteps.navigateToStylecoachRegisterFormUnderContext("thomas");
		stylistRegistrationSteps.fillCreateStylecoachFormWithKnownSponsorPayWithVisa(stylecoachFormData, customerFormAddress, birthDate.getDate());
		customVerification.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(stylecoachFormData, getClass().getSimpleName());
	}
}

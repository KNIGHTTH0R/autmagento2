package com.tests.uss17.us17003;

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
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "frontend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17003StyleCoachRegistrationWithKnownSponsorTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomVerification customVerification;

	public CustomerFormModel stylecoachFormData;
	public DateModel birthDate = new DateModel();
	public AddressModel customerFormAddress;
	public StylistDataModel validationModel;
	private CustomerFormModel sponsorStylecoachData;

	@Before
	public void setUp() throws Exception {

		sponsorStylecoachData = MongoReader.grabCustomerFormModels("US17003StyleCoachRegistrationTest").get(0);

		stylecoachFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17003StyleCoachRegistrationWithKnownSponsorTest() {
		headerSteps.navigateToStylecoachRegisterFormUnderContext(sponsorStylecoachData.getFirstName() + sponsorStylecoachData.getLastName());
		stylistRegistrationSteps.fillCreateStylecoachFormWithKnownSponsorPayWithVisa(stylecoachFormData, customerFormAddress, birthDate.getDate());
		customVerification.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(stylecoachFormData, getClass().getSimpleName());
	}
}

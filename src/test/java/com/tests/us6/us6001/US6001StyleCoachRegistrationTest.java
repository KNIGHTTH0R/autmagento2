package com.tests.us6.us6001;

import net.thucydides.core.annotations.Narrative;
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

//@WithTag(name = "US6001", type = "frontend")
@Narrative(title="6001 Story" , text={"In order to choose the best flight for my travels",                      
        "As a traveller",
        "I want to be able to search for flights between specific destinations"})
//@Story(Application.StyleCoach.Registration.class)
@RunWith(ThucydidesRunner.class)
public class US6001StyleCoachRegistrationTest extends BaseTest {

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
	public void us6001StyleCoachRegistrationTest(){ 
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerForm(customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);
		customVerification.printErrors();
		
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());

	}
}

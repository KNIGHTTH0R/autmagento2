package com.tests.us6;

import java.io.IOException;

import javax.xml.soap.SOAPException;

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
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;


@WithTag(name = "US6001", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US6001StyleCoachRegistrationTest extends BaseTest{
	
	@Steps
	public HeaderSteps headerSteps;
	
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}
	
	@Test
	public void us6001StyleCoachRegistrationTest() throws SOAPException, IOException{
		
		headerSteps.navigateToRegisterform();
		stylistRegistrationSteps.fillCreateCustomerForm(dataModel, addressModel);
		customVerifications.printErrors();		
	
	}
	
	@After
	public void saveData() {
		MongoWriter.saveStylistFormModel(dataModel, getClass().getSimpleName());
	}
}

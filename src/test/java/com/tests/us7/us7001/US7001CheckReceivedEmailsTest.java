package com.tests.us7.us7001;


import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.backend.StylistRegistrationAndActivationDateModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7001", type = "external")
@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US7001CheckReceivedEmailsTest extends BaseTest {

	
	@Steps
	public EmailClientSteps emailClientSteps;


	public CustomerConfigurationModel customerConfigurationModel = new CustomerConfigurationModel();
	
	public StylistPropertiesModel beforeLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public StylistPropertiesModel afterLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public StylistPropertiesModel afterOrderPaidStylistExpectedProperties = new StylistPropertiesModel();
	public StylistRegistrationAndActivationDateModel datesModel = new StylistRegistrationAndActivationDateModel();
	public String stylistEmail;

	@Before
	public void setUp() throws Exception {
		

		int size = MongoReader.grabStylistFormModels("US7001RegularCustomerRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabStylistFormModels("US7001RegularCustomerRegistrationTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");
		
	}
	@Test
	public void us7001CheckReceivedEmailsTest() {
		
		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + Constants.WEB_MAIL, ""),"Newsletter");
		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + Constants.WEB_MAIL, ""),"Willkommen");
	
	}


}


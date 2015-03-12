package com.tests.us6002;

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
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;


@WithTag(name = "US6001", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US6002StyleCoachRegistrationTest extends BaseTest{
	
	@Steps
	public HeaderSteps headerSteps;	
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public EmailClientSteps emailClientSteps;	
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerRegistrationSteps customerRegistrationSteps;

	public CustomerFormModel dataModel;
	public DateModel dateModel = new DateModel();
	public AddressModel addressModel;
	public StylistDataModel validationModel;
	
	public String stylistEmail;
	public String password;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
		int size = MongoReader.grabStylistFormModels("US6002CreateCustomerTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabStylistFormModels("US6002CreateCustomerTest").get(0).getEmailName();
			password = MongoReader.grabStylistFormModels("US6002CreateCustomerTest").get(0).getPassword();
			System.out.println(stylistEmail);
			System.out.println(password);
		} else
			System.out.println("The database has no entries");
	}
	
	@Test
	public void us6001StyleCoachRegistrationTest() throws SOAPException, IOException{
		
		//confirmation link
		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(stylistEmail.replace("@" + Constants.WEB_MAIL, ""),"Benutzerkonto");
		
		headerSteps.navigateToRegisterform();
		stylistRegistrationSteps.clickLoginLinkFromMessage();
		customerRegistrationSteps.performLogin(stylistEmail, password);
		String date = stylistRegistrationSteps.fillStylistRegistrationPredefinedInfoForm(dataModel, addressModel);
		dateModel.setDate(date);
		customVerifications.printErrors();		
	
	}
	
	@After
	public void saveData() {
		MongoWriter.saveStylistFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
		
	}
}

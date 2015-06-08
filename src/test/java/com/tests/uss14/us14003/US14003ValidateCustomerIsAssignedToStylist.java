package com.tests.uss14.us14003;

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
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US13", type = "frontend")
@Story(Application.Distribution.StyleCoachLead.class)
@RunWith(ThucydidesRunner.class)
public class US14003ValidateCustomerIsAssignedToStylist extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;

	public String stylistEmail;
	public String stylistPassword;
	public static List<DBStylistModel> stylistsList = new ArrayList<DBStylistModel>();
	DBStylistModel distStylist = new DBStylistModel();

	@Before
	public void setUp() throws Exception {
		int size = MongoReader.grabCustomerFormModels("US14003CustomerLeadDistributionTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US14003CustomerLeadDistributionTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US14003CustomerLeadDistributionTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");

		stylistsList = MongoReader.grabDbStylistModels("US14003AddAddressToCustomerTest");
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us14003ValidateCustomerIsAssignedToStylist() {

		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		headerSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), headerSteps.getStyleCoachFirstNameFromProfile());
		distStylist = headerSteps.validateCustomerIsAssignedToOneOfTheStyleCoachesAndGetConfig(stylistsList, headerSteps.getStyleCoachEmailFromProfile());
		System.out.println("@@@@@@@@@@" + distStylist.getTotalSCReceived());
		System.out.println("@@@@@@@@@@" + distStylist.getTotalSCCurrentWeek());
	}

	@After
	public void tearDown() {

		MongoWriter.saveDbStylistModel(distStylist, getClass().getSimpleName());
	}

}

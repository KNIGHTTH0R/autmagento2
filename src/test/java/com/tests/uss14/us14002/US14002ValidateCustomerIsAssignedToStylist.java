package com.tests.uss14.us14002;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US14.2 Distribution during checkout to party host lead qualified SC", type = "Scenarios")
@Story(Application.DistributionDuringCheckout.US14_2.class)
@RunWith(SerenityRunner.class)
public class US14002ValidateCustomerIsAssignedToStylist extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	public String stylistEmail;
	public String stylistPassword;
	public static List<DBStylistModel> stylistsList = new ArrayList<DBStylistModel>();
	DBStylistModel distStylist = new DBStylistModel();

	@Before
	public void setUp() throws Exception {
		int size = MongoReader.grabCustomerFormModels("US14002HostLeadDistributionTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US14002HostLeadDistributionTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US14002HostLeadDistributionTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");

		stylistsList = MongoReader.grabDbStylistModels("US14002ChangeCustomerAddressTest");
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us14002ValidateCustomerIsAssignedToStylist() {

		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), dashboardSteps.getStyleCoachFirstNameFromProfile());
		distStylist = dashboardSteps.validateCustomerIsAssignedToOneOfTheStyleCoachesAndGetConfig(stylistsList, dashboardSteps.getStyleCoachEmailFromProfile());
		System.out.println("@@@@@@@@@@" + distStylist.getTotalSCReceived());
		System.out.println("@@@@@@@@@@" + distStylist.getTotalSCCurrentWeek());
	}

	@After
	public void tearDown() {

		MongoWriter.saveDbStylistModel(distStylist, getClass().getSimpleName());
	}

}
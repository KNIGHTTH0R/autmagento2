package com.tests.uss13.us13003;

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
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US13.3 Distribution to party host lead qualified SC", type = "Scenarios")
@Story(Application.Distribution.US13_3.class)
@RunWith(ThucydidesRunner.class)
public class US13003ValidateCustomerIsAssignedToStylist extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String stylistEmail;
	private String stylistPassword;
	private static List<DBStylistModel> stylistsList = new ArrayList<DBStylistModel>();
	private DBStylistModel distStylist = new DBStylistModel();

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US13003HostLeadDistributionTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US13003HostLeadDistributionTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US13003HostLeadDistributionTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");

		stylistsList = MongoReader.grabDbStylistModels("US13003HostLeadDistributionTest");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us13003ValidateCustomerIsAssignedToStylist() {
		// if the stylistList is empty,it means that no suitable style coach was
		// found for distribution and the customer remains under master.The test
		// will be skipped
		if (stylistsList.size() > 0) {
			customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
			headerSteps.goToProfile();
			dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), dashboardSteps.getStyleCoachFirstNameFromProfile());
			distStylist = dashboardSteps.validateCustomerIsAssignedToOneOfTheStyleCoachesAndGetConfig(stylistsList, dashboardSteps.getStyleCoachEmailFromProfile());
		}
	}

	@After
	public void tearDown() {
		if (stylistsList.size() > 0) {
			MongoWriter.saveDbStylistModel(distStylist, getClass().getSimpleName());
		}
	}
}

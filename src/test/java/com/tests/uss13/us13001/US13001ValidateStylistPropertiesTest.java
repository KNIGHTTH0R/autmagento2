package com.tests.uss13.us13001;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.ValidationSteps;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US13", type = "backend")
@Story(Application.Distribution.CustomerLead.class)
@RunWith(ThucydidesRunner.class)
public class US13001ValidateStylistPropertiesTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ValidationSteps validationSteps;

	public StylistDataModel validationModel = new StylistDataModel();

	DBStylistModel stylist = new DBStylistModel();
	public static List<DBStylistModel> stylistsList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		stylistsList = MongoReader.grabDbStylistModels("US13001CustomerLeadDistributionTest");
		if (stylistsList.size() > 0) {
			stylist = MongoReader.grabDbStylistModels("US13001ValidateCustomerIsAssignedToStylist").get(0);
		}
	}

	@Test
	public void us13001ValidateStylistPropertiesTest() {
		// if the stylistList is empty,it means that no suitable style coach was
		// found for distribution and the customer remains under master.The test
		// will be skipped
		if (stylistsList.size() > 0) {
			backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
			backEndSteps.clickOnCustomers();
			backEndSteps.searchForEmail(stylist.getEmail());
			backEndSteps.openCustomerDetails(stylist.getEmail());
			backEndSteps.clickOnLeadSettings();
			validationModel = backEndSteps.grabLeadSettingsData();
			validationSteps.validateCustomerLeadData(stylist, validationModel);

			customVerifications.printErrors();
		}
	}

}
package com.tests.uss13.us13007;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.ValidationSteps;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.StylistDataModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US13.7 DYKSC By Name Assignation", type = "Scenarios")
@Story(Application.Distribution.US13_7.class)
@RunWith(SerenityRunner.class)
public class US13007ValidateStylistPropertiesTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ValidationSteps validationSteps;

	private StylistDataModel validationModel = new StylistDataModel();
	private DBStylistModel stylist = new DBStylistModel();
	private static List<DBStylistModel> stylistsList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {
		stylistsList = MongoReader.grabDbStylistModels("US13007DykscSearchByNameTest");
		if (stylistsList.size() > 0) {
			stylist = MongoReader.grabDbStylistModels("US13007ValidateCustomerIsAssignedToStylist").get(0);
		}
	}

	@Test
	public void us13007ValidateStylistPropertiesTest() {
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
			validationSteps.validateHostLeadData(stylist, validationModel);

			customVerifications.printErrors();
		}

	}

}
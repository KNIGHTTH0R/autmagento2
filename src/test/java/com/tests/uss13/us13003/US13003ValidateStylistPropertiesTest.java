package com.tests.uss13.us13003;

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
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US13", type = "backend")
@Story(Application.Distribution.HostLead.class)
@RunWith(ThucydidesRunner.class)
public class US13003ValidateStylistPropertiesTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ValidationSteps validationSteps;

	public StylistDataModel validationModel = new StylistDataModel();

	DBStylistModel stylist = new DBStylistModel();

	@Before
	public void setUp() throws Exception {

		stylist = MongoReader.grabDbStylistModels("US13003ValidateCustomerIsAssignedToStylist").get(0);

	}

	@Test
	public void us000GrabStylistPropertiesTest() {
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
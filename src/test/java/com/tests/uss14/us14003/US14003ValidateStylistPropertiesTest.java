package com.tests.uss14.us14003;

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

@WithTag(name = "US14.3 Distribution during checkout to customer lead qualified SC", type = "Scenarios")
@Story(Application.DistributionDuringCheckout.US14_3.class)
@RunWith(SerenityRunner.class)
public class US14003ValidateStylistPropertiesTest extends BaseTest {

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

		stylist = MongoReader.grabDbStylistModels("US14003ValidateCustomerIsAssignedToStylist").get(0);
	}

	@Test
	public void us14003ValidateStylistPropertiesTest() {
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
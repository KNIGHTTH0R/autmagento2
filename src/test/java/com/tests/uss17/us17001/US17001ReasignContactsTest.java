package com.tests.uss17.us17001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US17", type = "backend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US17001ReasignContactsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public StylistPropertiesModel afterLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");


	}

	@Test
	public void us7001CheckCustomerActivation() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStylecoachList();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);

		StylistPropertiesModel afterLinkConfirmationStylistProperties = backEndSteps.grabCustomerConfiguration();

		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(afterLinkConfirmationStylistProperties, afterLinkConfirmationStylistExpectedProperties);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("AFTER CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(afterLinkConfirmationStylistProperties);
		PrintUtils.printStylistPropertiesModel(afterLinkConfirmationStylistExpectedProperties);

		customVerifications.printErrors();
	}

}

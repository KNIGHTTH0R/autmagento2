package com.tests.uss17.us17003;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.tests.BaseTest;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17003VerifyThatOldStylistWasDeactivatedTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	public StylistPropertiesModel afterLinkConfirmationStylistExpectedProperties = new StylistPropertiesModel();
	public String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US17003StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US17003StyleCoachRegistrationTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us17003ReasignContactsTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStylecoachList();
		stylecoachListBackendSteps.verifyStylecoachEmailAndStatus(stylistEmail);
	}

}

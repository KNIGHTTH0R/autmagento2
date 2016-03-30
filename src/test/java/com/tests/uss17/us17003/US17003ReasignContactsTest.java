package com.tests.uss17.us17003;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.tests.BaseTest;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US17.3 Check reassigned contacts up on canceled SC hierarchy when no new Sc is selected", type = "Scenarios")
@Story(Application.MassAction.US17_3.class)
@RunWith(SerenityRunner.class)
public class US17003ReasignContactsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private String stylistEmail;

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
		stylecoachListBackendSteps.reassignCustomers(stylistEmail);
		stylecoachListBackendSteps.verifyStylecoachEmailAndStatus(stylistEmail);
	}

}

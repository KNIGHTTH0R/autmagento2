package com.tests.uss17.us17002;

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
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US17002ReasignContactsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private String stylistEmail;
	private CustomerFormModel newStylistModel;

	@Before
	public void setUp() throws Exception {

		newStylistModel = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest").get(0);
		stylistEmail = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationTest").get(0).getEmailName();

	}

	@Test
	public void us17002ReasignContactsTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStylecoachList();
		stylecoachListBackendSteps.reassignCustomersToAnotherStylecoach(stylistEmail, newStylistModel.getEmailName());
		stylecoachListBackendSteps.verifyStylecoachEmailAndStatus(stylistEmail);
	}

}

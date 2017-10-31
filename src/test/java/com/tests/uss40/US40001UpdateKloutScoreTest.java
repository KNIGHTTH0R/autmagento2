package com.tests.uss40;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.ValidationSteps;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US 40 Update online SC and klout score", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US40001UpdateKloutScoreTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ValidationSteps validationSteps;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void us40001UpdateKloutScoreTest() {
	

		backEndSteps.performStagingAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("em99@evozon.com");
		backEndSteps.openCustomerDetails("em99@evozon.com");
	//	backEndSteps.updateKloutScore("10");
		backEndSteps.updateOnlineStyleCoachStatus("Nein");

		customVerifications.printErrors();

	}

}
package com.tests.uss10.uss10008;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.PartyListBackendSteps;
import com.tests.BaseTest;
import com.tools.data.backend.PartyBackendPerformanceModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.BackendPartyPerformanceValidationWorkflows;

@WithTag(name = "US10", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US10008ValidatePartyBackendPerformanceTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BackendPartyPerformanceValidationWorkflows backendPartyPerformanceValidationWorkflows;

	@Steps
	public PartyListBackendSteps partyListBackendSteps;
	ClosedPartyPerformanceModel expectedModel;

	@Before
	public void setUp() {
		expectedModel = MongoReader.grabClosedPartyPerformanceModel("US10008ClosePartyAnfVerifyCommissionBonusesTest").get(0);
	}

	@Test
	public void us10008ValidatePartyBackendPerformanceTest() {
		backEndSteps.performAdminLogin("", "");
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails("");
		PartyBackendPerformanceModel grabbedModel = partyListBackendSteps.getPartyBackendPerformance();
		backendPartyPerformanceValidationWorkflows.validateClosedPartyPerformance(expectedModel, grabbedModel);
	}

}

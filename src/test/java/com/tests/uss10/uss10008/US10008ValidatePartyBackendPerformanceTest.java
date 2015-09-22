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
import com.tools.data.UrlModel;
import com.tools.data.backend.PartyBackendPerformanceModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.env.variables.Credentials;
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
	public static UrlModel urlModel = new UrlModel();
	String partyId;

	@Before
	public void setUp() {
		urlModel = MongoReader.grabUrlModels("US10008CreatePartyWithNewContactHostTest").get(0);
		expectedModel = MongoReader.grabClosedPartyPerformanceModel("US10008ClosePartyAnfVerifyCommissionBonusesTest").get(0);
		String[] parts = urlModel.getUrl().split("/");
		partyId = parts[parts.length - 1];
	}

	@Test
	public void us10008ValidatePartyBackendPerformanceTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails(partyId);
		PartyBackendPerformanceModel grabbedModel = partyListBackendSteps.getPartyBackendPerformance();
		backendPartyPerformanceValidationWorkflows.validateClosedPartyPerformance(expectedModel, grabbedModel);
	}

}

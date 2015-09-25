package com.tests.uss10.uss10007;

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
import com.tools.CustomVerification;
import com.tools.data.UrlModel;
import com.tools.data.backend.PartyBackendPerformanceModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.BackendPartyPerformanceValidationWorkflows;

@WithTag(name = "US10", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US10007ValidateFollowUpPartyBackendPerformanceTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BackendPartyPerformanceValidationWorkflows backendPartyPerformanceValidationWorkflows;
	@Steps 
	public CustomVerification customVerifications;
	@Steps
	public PartyListBackendSteps partyListBackendSteps;
	ClosedPartyPerformanceModel expectedModel;
	public static UrlModel urlModel = new UrlModel();
	String partyId;

	@Before
	public void setUp() {
		urlModel = MongoReader.grabUrlModels("US10007CloseFollowUpPartyAnfVerifyCommissionBonusesTest").get(0);
		expectedModel = MongoReader.grabClosedPartyPerformanceModel("US10007CloseFollowUpPartyAnfVerifyCommissionBonusesTest").get(0);
		PrintUtils.printClosedPartyModel(expectedModel);
		//TODO make a method or smth for this
		String[] parts = urlModel.getUrl().split("/");
		partyId = parts[parts.length - 1];
		System.out.println("party Id:" + partyId);
	}

	@Test
	public void us10007ValidatePartyBackendPerformanceTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails(partyId);
		PartyBackendPerformanceModel grabbedModel = partyListBackendSteps.getPartyBackendPerformance();
		backendPartyPerformanceValidationWorkflows.validateClosedPartyPerformance(expectedModel, grabbedModel);
		
		customVerifications.printErrors();
	}

}

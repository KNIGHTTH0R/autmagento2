package com.tests.uss10.uss10008;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.PartyDetailsBackendSteps;
import com.steps.backend.stylecoach.PartyListBackendSteps;
import com.tests.BaseTest;
import com.tools.data.UrlModel;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;

@WithTag(name = "US10.8 Check virgin party performance and bonuses", type = "Scenarios")
@Story(Application.PartyPerformance.US10_8.class)
@RunWith(ThucydidesRunner.class)
public class US10008AddManuallyBonusOnPartyTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public PartyListBackendSteps partyListBackendSteps;
	@Steps
	public PartyDetailsBackendSteps partyDetailsBackendSteps;

	private ClosedPartyPerformanceModel expectedModel;
	private static UrlModel urlModel = new UrlModel();
	private String partyId;

	@Before
	public void setUp() {
		urlModel = MongoReader.grabUrlModels("US10008CreatePartyWithNewContactHostTest").get(0);
		expectedModel = MongoReader.grabClosedPartyPerformanceModel("US10008CloseVirginPartyAnfVerifyCommissionBonusesTest").get(0);
		PrintUtils.printClosedPartyModel(expectedModel);
		String[] parts = urlModel.getUrl().split("/");
		partyId = parts[parts.length - 1];
		System.out.println("party Id:" + partyId);
	}

	@Test
	public void us10008AddManuallyBonusOnPartyTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails(partyId);
		partyDetailsBackendSteps.goToBonusTab();
		partyDetailsBackendSteps.addJewelryBonusToParty("100");
	}
}

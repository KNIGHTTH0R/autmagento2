package com.tests.uss10.uss10008;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.PartyDetailsBackendSteps;
import com.steps.backend.stylecoach.PartyListBackendSteps;
import com.tests.BaseTest;
import com.tools.data.UrlModel;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US10.8 Check virgin party performance and bonuses", type = "Scenarios")
@Story(Application.PartyPerformance.US10_8.class)
@RunWith(SerenityRunner.class)
public class US10008AddManuallyBonusOnPartyTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public PartyListBackendSteps partyListBackendSteps;
	@Steps
	public PartyDetailsBackendSteps partyDetailsBackendSteps;

	private static UrlModel urlModel = new UrlModel();
	private String partyId;

	@Before
	public void setUp() {
		urlModel = MongoReader.grabUrlModels("US10008CreatePartyWithExistingContactHostTest").get(0);
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
		partyDetailsBackendSteps.addJewelryAndFourthyDiscountBonusToParty("1000","1");
	}
}

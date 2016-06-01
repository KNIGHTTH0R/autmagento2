package com.tests.us9.us9005;

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

@WithTag(name = "US9.4 Place Host Order With Term Purchase Test", type = "Scenarios")
@Story(Application.HostCart.US9_4.class)
@RunWith(SerenityRunner.class)
public class US10009UpdatePartyBonusesTest extends BaseTest {
	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public PartyDetailsBackendSteps partyDetailsBackendSteps;	
	@Steps
	public PartyListBackendSteps partyListBackendSteps;

	public static UrlModel urlModel = new UrlModel();
	private String partyId;

	@Before
	public void setUp() throws Exception {

		urlModel = MongoReader.grabUrlModels("US10009CreatePartyWithStylistHostTest").get(0);
		String[] bits = urlModel.getUrl().split("/");
		partyId = bits[bits.length - 1];
		System.out.println(partyId);
	}

	@Test
	public void us10009UpdatePartyBonusesTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails(partyId);
		partyDetailsBackendSteps.addJewelryAndFourthyDiscountBonusToParty();

	}

}

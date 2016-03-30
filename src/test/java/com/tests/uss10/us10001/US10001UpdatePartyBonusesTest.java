package com.tests.uss10.us10001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.PartyDetailsBackendSteps;
import com.steps.backend.stylecoach.PartyListBackendSteps;
import com.tests.BaseTest;
import com.tools.data.UrlModel;
import com.tools.env.constants.Credentials;
import com.tools.env.constants.SoapKeys;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US9.1 Place Host Order With 40% Discount and JB Test", type = "Scenarios")
@Story(Application.HostCart.US9_1.class)
@RunWith(SerenityRunner.class)
public class US10001UpdatePartyBonusesTest extends BaseTest {
	
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

		urlModel = MongoReader.grabUrlModels("US10001CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
		String[] bits = urlModel.getUrl().split("/");
		partyId = bits[bits.length - 1];
		System.out.println(partyId);
	}

	@Test
	public void us10001UpdatePartyBonusesTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails(partyId);
		partyDetailsBackendSteps.addJewelryAndFourthyDiscountBonusToParty();

	}

}

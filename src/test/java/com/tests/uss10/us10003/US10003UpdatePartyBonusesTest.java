package com.tests.uss10.us10003;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.styleParties.PartyDetailsBackendSteps;
import com.steps.backend.styleParties.PartyListBackendSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.UrlModel;
import com.tools.persistance.MongoReader;

@WithTag(name = "US10003", type = "backend")
//@Story(Application.StyleParty.CreateParty.class)
@RunWith(ThucydidesRunner.class)
public class US10003UpdatePartyBonusesTest extends BaseTest {
	
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

		urlModel = MongoReader.grabUrlModels("US10003CreatePartyWithNewContactHostTest" + Constants.GRAB).get(0);
		String[] bits = urlModel.getUrl().split("/");
		partyId = bits[bits.length - 1];
		System.out.println(partyId);
	}

	@Test
	public void us10003ClosePartyTest() {

		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.clickOnStyleParties();
		partyListBackendSteps.openPartyDetails(partyId);
		partyDetailsBackendSteps.addJewelryAndFourthyDiscountBonusToParty();

	}

}

package com.tests.uss10.uss10009;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.EmailSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.PartyHostGuestSteps;
import com.tests.BaseTest;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US10.5 Create Follow Up Party", type = "Scenarios")
@Story(Application.StyleParty.US10_5.class)
@RunWith(SerenityRunner.class)
public class US10009AddManuallyGuestsToPartyFromHostPartyPageTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public PartyHostGuestSteps partyHostGuestSteps;

	private String email, password;
	private UrlModel urlModel = new UrlModel();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);

			email = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		urlModel = MongoReader.grabUrlModels("US10009CreatePartyWithCustomerHostTestVDV"+SoapKeys.GRAB).get(0);
		
		MongoConnector.cleanCollection(getClass().getSimpleName()+SoapKeys.GRAB);


	}

	@Test
	public void us10009AddManuallyGuestsToPartyFromHostPartyPageTest() {

		frontEndSteps.performLogin(email, password);
		customerRegistrationSteps.navigate(urlModel.getUrl());
		String passord=partyDetailsSteps.grabHostPassword();
		partyDetailsSteps.clickOnPartyLink();
		partyDetailsSteps.switchToNewestOpenedTab();
		partyHostGuestSteps.loginInHostPage(passord);
		partyDetailsSteps.switchToNewestOpenedTab();
		partyHostGuestSteps.sendGuestInvitationByEmail();
		partyHostGuestSteps.expendInviteGuestForm();
		partyHostGuestSteps.insertGuest("emilian","kindy");
		partyHostGuestSteps.verifyGuestIsDisplayedOnHostPage("emilian","kindy",true);
		partyHostGuestSteps.insertGuest("emilian","worker");
		partyHostGuestSteps.verifyGuestIsDisplayedOnHostPage("emilian","worker",true);
		partyHostGuestSteps.hostDeclineGuestInvitation("emilian","kindy");
		partyHostGuestSteps.verifyGuestIsDisplayedOnHostPage("emilian","kindy",false);
		
	}

	@After
	public void saveData() {
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

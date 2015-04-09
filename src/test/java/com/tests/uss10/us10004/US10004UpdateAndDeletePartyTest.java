package com.tests.uss10.us10004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.UpdatePartySteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;

@WithTag(name = "US10004", type = "frontend")
// @Story(Application.StyleParty.CreateParty.class)
@RunWith(ThucydidesRunner.class)
public class US10004UpdateAndDeletePartyTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public UpdatePartySteps updatePartySteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;

	public static UrlModel urlModel = new UrlModel();
	public static DateModel dateModel = new DateModel();
	private String username, password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			username = prop.getProperty("username");
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

		MongoConnector.cleanCollection(getClass().getSimpleName());

		urlModel = MongoReader.grabUrlModels("US10004CreatePartyWithStylistHostTest" + Constants.GRAB).get(0);
		dateModel = MongoReader.grabStylistDateModels("US10004CreatePartyWithStylistHostTest" + Constants.GRAB).get(0);

	}

	@Test
	public void us10004InviteRegisteredGuestUpdateAndDeletePartyTest() {

		customerRegistrationSteps.performLogin(username, password);
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.editParty();
		updatePartySteps.updatePartyDateAndHour();
		partyDetailsSteps.deleteParty();

	}

}

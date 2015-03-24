package com.tests.uss10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.requirements.Application;

@WithTag(name = "US10001", type = "frontend")
@Story(Application.StyleParty.CreateParty.class)
@RunWith(ThucydidesRunner.class)
public class US10001CreatePartyTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
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

	}

	@Test
	public void us10001CreatePartyTest() {
		customerRegistrationSteps.performLogin(username, password);
		headerSteps.clickLounge();
		loungeSteps.clickCreateParty();
		partyDetailsSteps.selectFirstAvailableDate();
		partyDetailsSteps.selectFirstAvailableHour();
		
	}
}

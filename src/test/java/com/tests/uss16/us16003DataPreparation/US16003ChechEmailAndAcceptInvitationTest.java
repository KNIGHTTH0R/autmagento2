package com.tests.uss16.us16003DataPreparation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US10.6 Order for Customer as Party host and Validate Party Wishlist", type = "Scenarios")
@Story(Application.StyleParty.US10_6.class)
@RunWith(SerenityRunner.class)
public class US16003ChechEmailAndAcceptInvitationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailClientSteps emailSteps;
	private String email;

	@Before
	public void setUp() throws Exception {
		// customer credentials
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss16" + File.separator + "us16003.properties");
			prop.load(input);
			email = prop.getProperty("customerUsername");

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
	public void us16003ChechEmailAndAcceptInvitationTest() {
		// frontEndSteps.performLogin(email, password);

		// Dich ein zur PIPPA&JEAN Style Party
		emailSteps.confirmPartyInvitation(email.replace("@" + ConfigConstants.WEB_MAIL, ""),
				"Dich ein zur PIPPA&JEAN Style Party");

	}

}

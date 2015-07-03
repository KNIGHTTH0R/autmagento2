package com.tests.uss17.us17002;

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
import com.tests.BaseTest;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7002CheckReassignedStylecoachSponsorTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;

	private String stylistEmail;
	private String stylistPassword;
	private String newStylecoachUsername;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17001.properties");
			prop.load(input);
			newStylecoachUsername = prop.getProperty("stylecoachUsername");

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

		int size = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationWithKnownSponsorTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationWithKnownSponsorTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationWithKnownSponsorTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us7002CheckReassignedStylecoachSponsorTest() {

		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		System.out.println(headerSteps.getStyleCoachEmailFromProfile());
		headerSteps.validateCustomerIsAssignedToStyleCoach(newStylecoachUsername, headerSteps.getStyleCoachEmailFromProfile());

	}

}

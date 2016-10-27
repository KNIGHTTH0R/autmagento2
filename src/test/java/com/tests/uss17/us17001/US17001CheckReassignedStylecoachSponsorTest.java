package com.tests.uss17.us17001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17.1 Check reassigned duplicate contacts and customer associated contacts when new SC is selected", type = "Scenarios")
@Story(Application.MassAction.US17_1.class)
@RunWith(SerenityRunner.class)
public class US17001CheckReassignedStylecoachSponsorTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

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

		int size = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationWithKnownSponsorTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationWithKnownSponsorTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US17001StyleCoachRegistrationWithKnownSponsorTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us7001CheckReassignedStylecoachSponsorTest() {

		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		System.out.println(dashboardSteps.getStyleCoachEmailFromProfile());
		dashboardSteps.validateCustomerIsAssignedToStyleCoach(newStylecoachUsername, dashboardSteps.getStyleCoachEmailFromProfile());

	}

}

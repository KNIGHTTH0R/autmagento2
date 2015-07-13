package com.tests.us7.us7009;

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
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.KoboRegistration.class)
@RunWith(ThucydidesRunner.class)
public class US7009ValidateCustomerIsAssignedToStylist extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;

	public String stylistEmail;
	public String stylistPassword;
	public String expectedStyleCoach;

	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us7009.properties");
			prop.load(input);
			expectedStyleCoach = prop.getProperty("expectedStyleCoach");	

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

		int size = MongoReader.grabCustomerFormModels("US7009KoboRegistrationTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7009KoboRegistrationTest").get(0).getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7009KoboRegistrationTest").get(0).getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us7009ValidateCustomerIsAssignedToStylist() {

		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		headerSteps.getBoutiqueName();
		headerSteps.getStyleCoachFirstNameFromProfile();
		headerSteps.getStyleCoachFullNameFromProfile();

		headerSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(), headerSteps.getStyleCoachFirstNameFromProfile());
		headerSteps.validateCustomerIsAssignedToStyleCoach(expectedStyleCoach, headerSteps.getStyleCoachFullNameFromProfile());

	}

}

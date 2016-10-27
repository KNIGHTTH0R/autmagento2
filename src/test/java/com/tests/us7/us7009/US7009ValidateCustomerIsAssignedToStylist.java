package com.tests.us7.us7009;

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
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.9 Kobo Registration Not On Voucher Owner Context Test ", type = "Scenarios")
@Story(Application.KoboRegistration.US7_9.class)
@RunWith(SerenityRunner.class)
public class US7009ValidateCustomerIsAssignedToStylist extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String stylistEmail;
	private String stylistPassword;
	private String expectedStyleCoach;

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

		int size = MongoReader.grabCustomerFormModels("US7009KoboRegOnNotVoucherOwnerContextTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US7009KoboRegOnNotVoucherOwnerContextTest").get(0)
					.getEmailName();
			stylistPassword = MongoReader.grabCustomerFormModels("US7009KoboRegOnNotVoucherOwnerContextTest").get(0)
					.getPassword();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us7009ValidateCustomerIsAssignedToStylist() {

		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		headerSteps.goToProfile();
		headerSteps.getBoutiqueName();

		dashboardSteps.validateCustomeStyleCoachName(headerSteps.getBoutiqueName(),
				dashboardSteps.getStyleCoachFirstNameFromProfile());
		dashboardSteps.validateCustomerIsAssignedToStyleCoach(expectedStyleCoach,
				dashboardSteps.getStyleCoachFullNameFromProfile());

	}

}

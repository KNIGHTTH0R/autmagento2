package com.tests.uss19.us19004;

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

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.registration.connectWithMe.ConnectWithMeRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.UrlConstants;
import com.tools.data.StylistDataModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.KoboCampaign.class)
@RunWith(SerenityRunner.class)
public class US19004RegisteredConnectWithMeOnContextTest extends BaseTest {

	@Steps
	public ConnectWithMeRegistrationSteps connectWithMeRegistrationSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;

	public StylistDataModel validationModel;
	private String context;
	private String expectedStyleCoachName;
	private String username, password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss19" + File.separator + "us19004.properties");
			prop.load(input);

			username = prop.getProperty("username");
			password = prop.getProperty("password");
			context = prop.getProperty("context");
			expectedStyleCoachName = prop.getProperty("expectedStyleCoachName");

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
	}

	@Test
	public void us19004RegisteredConnectWithMeOnContextTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		connectWithMeRegistrationSteps.loggedInUsernavigatesToConnectWithMeUnderContext(context);
		connectWithMeRegistrationSteps.fillConnectWithMePredefinedForm();
		connectWithMeRegistrationSteps.verifySuccessLinkUnderContext();
		connectWithMeRegistrationSteps.verifyConnectWithMeRegistrationSuccesMessage();
		connectWithMeRegistrationSteps.verifyConnectWithMeSuccesRegistrationContainsScName(expectedStyleCoachName);

	}

}
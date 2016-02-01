package com.tests.uss19.us19004;

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

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyContactsListSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US19004VerifyThatContactsWhereAddedToStylecoachTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public MyContactsListSteps myContactsListSteps;

	public CustomerFormModel stylistRegistrationData;

	public CustomerFormModel contactModel;
	public DateModel dateModel;

	private String stylecoachUsername;
	private String stylecoachPassword;
	private String username;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss19" + File.separator + "us19004.properties");
			prop.load(input);
			stylecoachUsername = prop.getProperty("stylecoachUsername");
			stylecoachPassword = prop.getProperty("stylecoachPassword");
			username = prop.getProperty("username");

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
	public void us19004VerifyThatContactsWhereAddedToStylecoachTest() {

		customerRegistrationSteps.performLogin(stylecoachUsername, stylecoachPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToContactsList();
		myContactsListSteps.verifyThatContactIsInTheList(username);

	}

}


package com.tests.uss10.us10001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.tests.BaseTest;
import com.tools.data.UrlModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.stagingaut.Constants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US10", type = "frontend")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10001CreatePartyWithStylistHostTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;

	@Steps
	public PartyCreationSteps partyCreationSteps;
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);

	}

	@Test
	public void us10001CreatePartyWithStylistHostTest() {
		customerRegistrationSteps.performLogin(username, password);
		headerSteps.goToCreatePartyPage();;
		urlModel.setUrl(partyCreationSteps.fillPartyDetailsForStylistHost());
		dateModel.setDate(String.valueOf(System.currentTimeMillis()));
	}

	@After
	public void saveData() {
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName() + Constants.GRAB);
	}
}


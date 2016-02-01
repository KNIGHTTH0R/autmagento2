package com.tests.uss10.us10003;

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
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.UpdatePartySteps;
import com.tests.BaseTest;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US10.3 Edit Party and Verify Not Allowed Countries Test", type = "Scenarios")
@Story(Application.StyleParty.US10_3.class)
@RunWith(ThucydidesRunner.class)
public class US10003EditPartyAndVerifyNotAllowedCountriesTest extends BaseTest {
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public UpdatePartySteps updatePartySteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;

	private static UrlModel urlModel = new UrlModel();
	private String username, password;
	boolean runTest = true;
	private CustomerFormModel customerData;

	@Before
	public void setUp() throws Exception {

		customerData = new CustomerFormModel();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
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

		urlModel = MongoReader.grabUrlModels("US10003CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).get(0);

	}

	@Test
	public void us10003EditPartyAndVerifyNotAllowedCountriesTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());

		footerSteps.selectWebsiteFromFooter(ContextConstants.NOT_PREFERED_WEBSITE);
		if (!headerSteps.succesfullLogin()) {
			System.out.println(headerSteps.succesfullLogin());
			customerRegistrationSteps.performLoginAfterChangingWebsite(username, password);
		}
		String newUrl = urlModel.getUrl().replace("/" + MongoReader.getContext() + "/", "/" + ContextConstants.NOT_PREFERED_WEBSITE + "/");
		customerRegistrationSteps.navigate(newUrl);
		updatePartySteps.selectNotAllowedCountryName(ContextConstants.NOT_PREFERED_LANGUAGE);
		updatePartySteps.verifyCountryRestrictionMessage();

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerData, getClass().getSimpleName());
	}

}

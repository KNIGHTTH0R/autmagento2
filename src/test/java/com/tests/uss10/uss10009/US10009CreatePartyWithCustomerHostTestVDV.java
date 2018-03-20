package com.tests.uss10.uss10009;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.sun.jna.Platform;
import com.tests.BaseTest;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.customDrivers.CustomZaleniumDriver;
import com.tools.data.UrlModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "Create party with Customer Host", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(SerenityRunner.class)
public class US10009CreatePartyWithCustomerHostTestVDV extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;

	private static UrlModel urlModel = new UrlModel();
	private static DateModel dateModel = new DateModel();
	private String username, password;
	private String customerName;

	@Before
	public void setUp() throws Exception {
		
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			customerName = prop.getProperty("customerName");

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);

	}

	@Test
	public void us10009CreatePartyWithCustomerHostTestVDV() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
	    desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
	    desiredCapabilities.setCapability("name", "myTestName");
		
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
		//	footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
	//	headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToCreatePartyPage();
		urlModel.setUrl(partyCreationSteps.fillPartyDetailsForCustomerHost(customerName));
		System.out.println("url Saved "+urlModel.getUrl());
		
		dateModel.setDate(String.valueOf(System.currentTimeMillis()));
		
	}

	@After
	public void saveData() {

		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}

}

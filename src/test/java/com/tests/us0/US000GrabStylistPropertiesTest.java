package com.tests.us0;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.env.stagingaut.UrlConstants;
import com.tools.env.stagingaut.Credentials;
import com.tools.persistance.MongoWriter;

@WithTag(name = "US000", type = "backend")
//@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US000GrabStylistPropertiesTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps 
	public CustomVerification customVerifications;

	public StylistDataModel validationModel = new StylistDataModel();

	private String stylistName;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us0" + File.separator + "Stylist.properties");
			prop.load(input);
			stylistName = prop.getProperty("stylistName");
			System.out.println(stylistName);
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
	
	

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us000GrabStylistPropertiesTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistName);
		backEndSteps.openCustomerDetails(stylistName);
		backEndSteps.clickOnLeadSettings();
		validationModel = backEndSteps.grabLeadSettingsData();
		System.out.println(validationModel.getCustomerLeads());
		System.out.println(validationModel.getHostessLeads());
		System.out.println(validationModel.getHostessLeadsWeek());
		System.out.println(validationModel.getStyleCoachLeads());
		System.out.println(validationModel.getStyleCoachLeadsWeek());

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveStylistDataModel(validationModel, getClass().getSimpleName());
	}
}
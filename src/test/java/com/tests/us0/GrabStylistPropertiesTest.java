package com.tests.us0;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.StylistDataModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class GrabStylistPropertiesTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	public StylistDataModel validationModel;

	private String stylistName;

	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH
					+ "Stylist.properties");
			prop.load(input);
			stylistName = prop.getProperty("stylistName");

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

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void grabStylistPropertiesTest() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.redirectToManageCustomers();
		backEndSteps.searchForEmail(stylistName);
		backEndSteps.openCustomerDetails(stylistName);
		backEndSteps.clickOnLeadSettings();
		validationModel = backEndSteps.grabLeadSettingsData();

	}

	@After
	public void saveData() {
		MongoWriter.saveStylistDataModel(validationModel, getClass().getSimpleName());
//		Properties prop = new Properties();
//		OutputStream output = null;
//
//		try {
//			output = new FileOutputStream(Constants.RESOURCES_PATH
//					+ "StylistData.properties");
//
//			prop.setProperty("styleCoachLeads", validationModel.styleCoachLeads);
//			prop.setProperty("hostessLeads", validationModel.hostessLeads);
//			prop.setProperty("customerLeads", validationModel.customerLeads);
//			prop.setProperty("styleCoachLeadsWeek",
//					validationModel.styleCoachLeadsWeek);
//			prop.setProperty("hostessLeadsWeek",
//					validationModel.hostessLeadsWeek);
//
//			prop.store(output, null);
//
//		} catch (IOException io) {
//			io.printStackTrace();
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
	}
}
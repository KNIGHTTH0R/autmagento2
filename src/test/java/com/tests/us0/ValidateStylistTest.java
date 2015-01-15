package com.tests.us0;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.BackEndSteps;
import com.steps.ValidationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.StylistDataModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class ValidateStylistTest extends BaseTest{

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public ValidationSteps validationSteps;

	public StylistDataModel initialValidation = new StylistDataModel();
	public StylistDataModel finalValidation;

	private String stylistName;
	private String accountActive;
	private String emailActive;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "Stylist.properties");
			prop.load(input);
			stylistName = prop.getProperty("stylistName");
			prop = new Properties();
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
		try {
			input = null;
			input = new FileInputStream(Constants.RESOURCES_PATH + "CustomerConfirmation.properties");
			prop.load(input);
			accountActive = prop.getProperty("accountActive");
			emailActive = prop.getProperty("emailActive");
			prop = new Properties();
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
		
		List<StylistDataModel> validationList = MongoReader.grabStylistDataModels("GrabStylistPropertiesTest");
		initialValidation = validationList.get(0);
//		try {
//			input = null;
//			input = new FileInputStream(Constants.RESOURCES_PATH + "StylistData.properties");
//			prop.load(input);
//			initialValidation.customerLeads = prop.getProperty("customerLeads");
//			initialValidation.hostessLeads = prop.getProperty("hostessLeads");
//			initialValidation.hostessLeadsWeek = prop.getProperty("hostessLeadsWeek");
//			initialValidation.styleCoachLeads = prop.getProperty("styleCoachLeads");
//			initialValidation.styleCoachLeadsWeek = prop.getProperty("styleCoachLeadsWeek");
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void validateStylistData() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.redirectToManageCustomers();
		backEndSteps.searchForEmail(stylistName);
		backEndSteps.openCustomerDetails(stylistName);
		backEndSteps.clickOnLeadSettings();
		finalValidation = backEndSteps.grabLeadSettingsData();
		
		//this line is just to use emailActive var.
		emailActive.contentEquals("true");
		
		
		if(accountActive.contentEquals("true")){
//			if(accountActive.contentEquals("true") && emailActive.contentEquals("true")){
			validationSteps.validateStylistData(initialValidation, finalValidation);
		}
	}
}
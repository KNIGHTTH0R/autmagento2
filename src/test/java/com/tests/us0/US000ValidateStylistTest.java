package com.tests.us0;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.ValidationSteps;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.StylistDataModel;
import com.tools.data.backend.CustomerConfigurationModel;
import com.tools.persistance.MongoReader;

@WithTag(name = "US000", type = "backend")
//@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US000ValidateStylistTest extends BaseTest{

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public ValidationSteps validationSteps;
	@Steps 
	public CustomVerification customVerifications;

	public StylistDataModel initialValidation = new StylistDataModel();
	public CustomerConfigurationModel customerConfigurationModel = new CustomerConfigurationModel();
	public StylistDataModel finalValidation;

	private String stylistName;
	private String accountActive;
	private String emailActive;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us0" + File.separator + "Stylist.properties");
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
		
		List<CustomerConfigurationModel> customerConfigurationModels= MongoReader.grabCustomerConfigurationModels("US000CheckCustomerActivationTest");
		if(customerConfigurationModels.size() > 0){
			customerConfigurationModel = customerConfigurationModels.get(0);
			
			emailActive = customerConfigurationModel.getEmailActive();
			accountActive = customerConfigurationModel.getAccountActive();			
			
		}else{
			System.out.println("The database table has no entries");
		}
		
		List<StylistDataModel> validationList = MongoReader.grabStylistDataModels("US000GrabStylistPropertiesTest");
		if(validationList.size() > 0){
			initialValidation = validationList.get(0);
		}else
			System.out.println("The database table has no entries");	
	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us000ValidateStylistData() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistName);
		backEndSteps.openCustomerDetails(stylistName);
		backEndSteps.clickOnLeadSettings();
		finalValidation = backEndSteps.grabLeadSettingsData();
	
		
		//this line is just to use emailActive var.
		emailActive.contentEquals("true");
		
		
		if(accountActive.contentEquals("true")){
			validationSteps.validateStylistData(initialValidation, finalValidation);
		}
		
		
		customVerifications.printErrors();
	}
}

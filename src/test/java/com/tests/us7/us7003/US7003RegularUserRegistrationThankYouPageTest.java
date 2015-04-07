package com.tests.us7.us7003;

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
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;


@WithTag(name = "US7", type = "fontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7003RegularUserRegistrationThankYouPageTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private String username;
	private String password;
	

	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us7" + File.separator + "us7003.properties");
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
		
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		dataModel.setEmailName(username);
		dataModel.setPassword(password);
		addressModel = new AddressModel();
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7003RegularUserRegistrationThankYouPageTest() {
		customerRegistrationSteps.fillLandingPageForm(dataModel, addressModel);
		String email = customerRegistrationSteps.fillThankYouForm(dataModel.getPassword());
		
		customerRegistrationSteps.verifyCustomerEmail(dataModel.getEmailName(), email);
		customerRegistrationSteps.verifySuccessLink();
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}
	
	
}

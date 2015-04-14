package com.tests.us5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.soap.SOAPException;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.external.FacebookRegistrationSteps;
import com.tests.BaseTest;
import com.tools.env.stagingaut.Constants;
import com.tools.requirements.Application;


@WithTag(name = "US5", type = "frontend,external")
@Story(Application.External.class)
@RunWith(ThucydidesRunner.class)
public class US5001FacebookCustomerDeleteTest extends BaseTest{
	
	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;
	
	private String fbUser;
	private String beUser,bePass;
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + Constants.US_05_FOLDER + File.separator + "us0005.properties");
			prop.load(input);
			fbUser = prop.getProperty("fbUser");
			beUser = prop.getProperty("beUser");
			bePass = prop.getProperty("bePass");

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
	
	@Test
	public void usS5001FacebookDeleteCustomerTest() throws SOAPException, IOException{
		backEndSteps.performAdminLogin(beUser, bePass);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(fbUser);
		backEndSteps.openCustomerDetails(fbUser);
		backEndSteps.deleteCustomer();
	}
}

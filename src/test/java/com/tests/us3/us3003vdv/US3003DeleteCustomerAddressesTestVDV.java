package com.tests.us3.us3003vdv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.3 Shop for myself VAT valid and no SMB billing and shipping DE", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_3.class)
@RunWith(SerenityRunner.class)
public class US3003DeleteCustomerAddressesTestVDV extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	public String stylistEmail;

	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3003.properties");
			prop.load(input);
			
			stylistEmail = prop.getProperty("username");

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
	public void us3003DeleteCustomerAddressesTestVDV() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.openCustomersGrid();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		backEndSteps.deleteAllAdresses();
	}

}

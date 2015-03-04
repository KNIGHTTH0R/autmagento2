package com.tests.us5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.requirements.Application;


@WithTag(name = "US0005", type = "frontend,external")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US0005FacebookLoginTest extends BaseTest{
	
	
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	
	private String fbUser,fbPass;
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + Constants.US_05_FOLDER + File.separator + "us0005.properties");
			prop.load(input);
			fbUser = prop.getProperty("fbUser");
			fbPass = prop.getProperty("fbPass");

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
	public void us3002CartSegmentationWithVatBillingTest() {
		customerRegistrationSteps.goToFacebookLogin(fbUser, fbPass);
	}
	
	
	
	
}

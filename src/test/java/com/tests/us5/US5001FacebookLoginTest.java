package com.tests.us5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.external.FacebookRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.requirements.Application;


@WithTag(name = "US5", type = "frontend,external")
@Story(Application.External.class)
@RunWith(SerenityRunner.class)
public class US5001FacebookLoginTest extends BaseTest{
	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;
	
	private String fbUser, fbPass;
	private String zipCode,countryCode, pippaPass;
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_05_FOLDER + File.separator + "us0005.properties");
			prop.load(input);
			fbUser = prop.getProperty("fbUser");
			fbPass = prop.getProperty("fbPass");
			zipCode = prop.getProperty("zipCode");
			countryCode = prop.getProperty("countryCode");
			pippaPass = prop.getProperty("pippaPass");

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
	public void us5001FacebookLoginTest() {
		facebookRegistrationSteps.goToFacebookLogin(fbUser, fbPass);
		facebookRegistrationSteps.fillFacebookRegistration(zipCode, countryCode, pippaPass);
		
	}
}

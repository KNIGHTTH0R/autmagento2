package com.tests.us5.us5003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.soap.SOAPException;

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
import com.tools.constants.Credentials;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.requirements.Application;


@WithTag(name = "US5", type = "frontend,external")
@Story(Application.External.class)
@RunWith(SerenityRunner.class)
public class US5003FacebookAsociatedContactDeleteTest extends BaseTest{
	
	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;
	
	private String fbUser;
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_05_FOLDER + File.separator + "us5001.properties");
			prop.load(input);
			fbUser = prop.getProperty("fbUser");

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
	public void us5003FacebookAsociatedContactDeleteTest() throws SOAPException, IOException{
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnContacts();
		
		backEndSteps.searchForContactEmail(fbUser);
	//	backEndSteps.selectContact(fbUser);
		backEndSteps.selectContact(fbUser);
		backEndSteps.deleteContact();
		

		
		
		
	}
}

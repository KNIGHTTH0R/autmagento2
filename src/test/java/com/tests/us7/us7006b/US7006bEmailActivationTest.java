package com.tests.us7.us7006b;

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

import com.steps.EmailSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US7", type = "external")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7006bEmailActivationTest extends BaseTest{

	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;
	
	public String clientName;
	public String validateURL;
	private String styleCoachFN;
//	private String styleCoachLN;
	
	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us7006.properties");
			prop.load(input);
			styleCoachFN = prop.getProperty("styleCoachFN");
//			styleCoachLN = prop.getProperty("styleCoachLN");

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

		int size = MongoReader.grabCustomerFormModels("US7006bUserRegistrationSpecificStylistLandingPageTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7006bUserRegistrationSpecificStylistLandingPageTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");
	}
	
	@Test
	public void us7006bEmailActivationTest() {

//		emailClientSteps.openMailinator();
//		validateURL = emailClientSteps.grabEmail(clientName.replace("@" + ConfigConstants.WEB_MAIL, ""));
//		System.out.println(validateURL);
//		emailSteps.validateURL(validateURL, "customer/account/confirm");
//		System.out.println("!!!!!!  " + validateURL);
//		System.out.println("!!!!!!  " + styleCoachFN.toLowerCase());
//		emailSteps.validateContextFromURL(validateURL, styleCoachFN.toLowerCase());
		
		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(clientName.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
		
		
		customVerifications.printErrors();
	}
	
	
}

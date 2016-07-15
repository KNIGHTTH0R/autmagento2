package com.tests.us7.us7006b;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.EmailSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.6b Landing Page Registration Selected SC Not Pref Country Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_6.class)
@RunWith(SerenityRunner.class)
public class US7006bEmailActivationTest extends BaseTest{

	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;
	
	private String clientName;
	
	@Before
	public void setUp() throws Exception {
		

		int size = MongoReader.grabCustomerFormModels("US7006bLandingPageRegSelectedScNotPrefCountryTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7006bLandingPageRegSelectedScNotPrefCountryTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");
	}
	
	@Test
	public void us7006bEmailActivationTest() {
		emailClientSteps.grabEmail(clientName.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
		customVerifications.printErrors();
	}
	
	
}

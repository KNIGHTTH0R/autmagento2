package com.tests.uss37;


import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.FacebookRegistrationSteps;
import com.steps.external.ospm.OnlineStylePartyManagerSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.UrlModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "USS37", type = "facebook login- frontend")
@Story(Application.OnlineStylePartyManager.class)
@RunWith(SerenityRunner.class)
public class US37001OSPMRemovePjAppFromFacebookTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;
	@Steps
	public FacebookRegistrationSteps facebookLoginSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	OnlineStylePartyManagerSteps onlineStylePartyManagerSteps;
	@Steps
	CustomVerification customVerification;

	
	
	@Test
	public void us37001OSPMRemovePjAppFromFacebookTest() {
		
		
		facebookLoginSteps.loginToFacebook("emilianmihai25@gmail.com", "emilian1");

		facebookLoginSteps.accessSettingsOnFacebookDesktopApp();
		facebookLoginSteps.removeThePJDevApp("app-id-390416157958904");

		customVerification.printErrors();
		
		
	}
}

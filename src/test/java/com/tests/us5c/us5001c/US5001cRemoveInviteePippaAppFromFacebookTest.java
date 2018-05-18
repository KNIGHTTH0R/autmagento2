package com.tests.us5c.us5001c;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.faceboook.FacebookRegistrationSteps;
import com.steps.external.ospm.OnlineStylePartyManagerSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US5", type = "Guest page- Accept invitation; FBuser:logout, FBapp:notInstalled")
@Story(Application.OnlineStylePartyManager.class)
@RunWith(SerenityRunner.class)
public class US5001cRemoveInviteePippaAppFromFacebookTest extends BaseTest{
	
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
	OnlineStylePartyManagerSteps fBpermissionSteps;
	@Steps
	CustomVerification customVerification;

	private String fbUser, fbPass, appID;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;

	


	@Test
	public void us5001cRemoveInviteePippaAppFromFacebookTest() throws Exception {

		facebookLoginSteps.loginToFacebook(fbUser, fbPass);
		facebookLoginSteps.accessSettingsOnFacebookDesktopApp();
		facebookLoginSteps.removeTheFbApp(appID);
		

	}
}

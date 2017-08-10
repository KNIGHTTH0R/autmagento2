package com.tests.uss37;

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
import com.tools.constants.ContextConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@Story(Application.OnlineStylePartyManager.class)
@RunWith(SerenityRunner.class)
public class US37001OSPMInsufficientPrivilegesLoginTest extends BaseTest{
	
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
	
	private static UrlModel urlModel = new UrlModel();
	private static DateModel dateModel = new DateModel();
	
	
	@Steps
	CustomVerification customVerification;
	
	@Test
	public void us37001OSPMInsufficientPrivilegesLoginTest() {
		customerRegistrationSteps.performLogin("emilianmihai25@gmail.com", "emilian1"); 
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToCreatePartyPage();
		partyCreationSteps.checkOnlineStyleParty();
		urlModel.setUrl(partyCreationSteps.fillPartyDetailsForCustomerHost(ContextConstants.PARTY_USER));
		dateModel.setDate(String.valueOf(System.currentTimeMillis()));
		partyDetailsSteps.clickOnlineStylePartyManagerButton();
		headerSteps.switchToNewestOpenedTab();
		onlineStylePartyManagerSteps.clickOnLoginWithFacebookButton();
		//headerSteps.openNewTab();
		facebookLoginSteps.loginToFacebookApp("emilianmihai25@gmail.com", "emilian1");
		
	//	onlineStylePartyManagerSteps.acceptAllThePermissions();
		onlineStylePartyManagerSteps.uncheckTheRemoveManageGroupsPermissions();
		onlineStylePartyManagerSteps.verifyMessage(ContextConstants.ALLERT_MESSAGE);
		customVerification.printErrors();
	}
}


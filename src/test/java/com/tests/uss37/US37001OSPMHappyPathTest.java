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
public class US37001OSPMHappyPathTest extends BaseTest{
	
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
	private String comment="Hi! How are you :)?";
	private String product="Tory Necklace";
	private String productUrlkey="tory-necklace";
	
	@Steps
	CustomVerification customVerification;
	
	@Test
	public void us37001OSPMHappyPathTest() {
		customerRegistrationSteps.performLogin("emilianmihai25@gmail.com", "emilian1");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToCreatePartyPage();
		urlModel.setUrl(partyCreationSteps.fillPartyDetailsForStylistHost());
		dateModel.setDate(String.valueOf(System.currentTimeMillis()));
		
		//this is the remove app action 
/*//		headerSteps.openNewTab();
//		headerSteps.switchToNewestOpenedTab();
//		facebookLoginSteps.loginToFacebook("emilianmihai25@gmail.com", "emilian1");
//		facebookLoginSteps.accessSettingsOnFacebookDesktopApp();
//		facebookLoginSteps.removeThePJDevApp("app-id-390416157958904");
//		headerSteps.switchBackToPreviousTab();
*/		partyDetailsSteps.clickOnlineStylePartyManagerButton();
		headerSteps.switchToNewestOpenedTab();
		onlineStylePartyManagerSteps.clickOnLoginWithFacebookButton();
		//headerSteps.openNewTab();
		facebookLoginSteps.loginToFacebookApp("emilianmihai25@gmail.com", "emilian1");
		
		onlineStylePartyManagerSteps.acceptAllThePermissions();
		onlineStylePartyManagerSteps.selectFirstGroup();
		onlineStylePartyManagerSteps.selectFirstLiveStreamVideo();
		onlineStylePartyManagerSteps.addSomeTextAsComment(comment);
		onlineStylePartyManagerSteps.verifyIfCommentIsPosted(comment);
		
		
		//should be clarified
		onlineStylePartyManagerSteps.verifyShopButtonRedirect();
		onlineStylePartyManagerSteps.verifyContactBoosterButtonRedirect();
		onlineStylePartyManagerSteps.verifyOverviewButtonRedirect();
		
		onlineStylePartyManagerSteps.searchForAProduct(product);
		onlineStylePartyManagerSteps.selectAndLinkSearchedProduct(product);
		onlineStylePartyManagerSteps.verifyIfProductIsPosted(productUrlkey);
		
		customVerification.printErrors();
	}
}

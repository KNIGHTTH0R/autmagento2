package com.steps.frontend;

import com.tools.constants.Separators;
import com.tools.constants.TimeConstants;
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class HeaderSteps extends AbstractSteps {

	private static final long serialVersionUID = 1221784607709066875L;

	@Step
	public void openCartPreview() {
		headerPage().clickShoppingBag();
		
	}
	public boolean isEmptyCart() {
		return headerPage().isEmptyCart();
	}

	@Step
	public void goToCart() {
		headerPage().clickGoToCart();
	}
	
	@Step
	public void goToCheckout() {
		headerPage().clickGoToCheckout();
	}

	@Step
	public void goToProfile() {
		headerPage().clickOnProfileButton();
	}

	@Step
	public void goToLounge() {
		headerPage().clickLounge();
	}

	@Step
	public void clickOnWishlistButton() {
		headerPage().clickOnWishlistButton();
	}

	@Step
	@Title("Go to create party page")
	public void goToCreatePartyPage() {
		loungePage().clickCreateParty();

	}

	@Step
	@Title("Go to parties list")
	public void goToPartieList() {
		loungePage().clickGoToPartyList();
		waitABit(1000);

	}

	@Step
	@Title("Go to Order for customer page")
	public void goToOrderForCustomerPage() {
		headerPage().clickLounge();
		loungePage().clickOrderForCustomer();
		waitABit(1000);

	}

	@Step
	@Title("Go to my business page")
	public void goToMyBusinessPage() {
		headerPage().clickLounge();
		loungePage().goToMyBusiness();
		waitABit(1000);

	}

	@Step
	@Title("Go to create party for new contact")
	public void goToCreatePartyWithNewContactPage() {
		headerPage().clickLounge();
		loungePage().clickCreateParty();
		partyCreationPage().checkHostedByCustomer();
		partyCreationPage().clickAddContact();
		waitABit(1000);

	}

	public void redirectToProfileHistory() {
		navigate(MongoReader.getBaseURL() + UrlConstants.PROFILE_HISTORY_URL);

	}
	
	@Step
	public void redirectToPartyCreate() {
		waitABit(TimeConstants.TIME_CONSTANT);
		navigate(MongoReader.getBaseURL() + "/stylist/party/create/");
		waitABit(TimeConstants.TIME_CONSTANT);
		partyCreationPage().checkHostedByCustomer();
		partyCreationPage().clickAddContact();
		waitABit(1000);
	}

	@Step
	public void redirectToWishlist() {
		navigate(MongoReader.getBaseURL() + UrlConstants.WISHLIST_URL);
	}

	@Step
	public void redirectToStylistsCustomerOrderReport() {
		waitABit(TimeConstants.TIME_CONSTANT);
		navigate(MongoReader.getBaseURL() + UrlConstants.STYLISTS_CUSTOMER_ORDER_REPORT);
	}

	@Step
	public void redirectToStylistReports() {
		waitABit(TimeConstants.TIME_CONSTANT);
		navigate(MongoReader.getBaseURL() + UrlConstants.STYLISTS_REPORTS);
	}
	
	
	@Step
	public void redirectToTeamReport() {
		waitABit(TimeConstants.TIME_CONSTANT);
		navigate(MongoReader.getBaseURL() + UrlConstants.TEAM_REPORT);
	}

	@Step
	public void redirectToCartPage() {
		navigate(MongoReader.getBaseURL() + UrlConstants.CART_PAGE_URL);
	}

	@StepGroup
	public void navigateToRegisterForm() {
		navigate(MongoReader.getBaseURL());
		navigate(MongoReader.getBaseURL());
		headerPage().clickSignInLink();
		footerPage().clickRegistrierungLink();

	}
	@StepGroup
	public void navigateToSCRegisterForm() {
		navigate(MongoReader.getBaseURL()+"/stylist/register/personalinfo/");
		navigate(MongoReader.getBaseURL()+"/stylist/register/personalinfo/");
		

	}

	@StepGroup
	public void navigateToStylecoachRegisterFormUnderContext(String context) {
		navigate(MongoReader.getBaseURL() + Separators.SLASH + context.toLowerCase());
		waitABit(2000);
		footerPage().clickRegistrierungLink();

	}
	
	@StepGroup
	public void navigateToStylecoachRegisterForm() {
		waitABit(2000);
		footerPage().clickRegistrierungLink();

	}

	@StepGroup
	public void navigateToRegisterFormFromStylistRegistrationLinkAndStarteJetzButton() {
		navigate(MongoReader.getBaseURL());
		headerPage().clickSignInLink();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormAndLogout() {
		// navigate(MongoReader.getBaseURL());
		headerPage().clickAbmeldenButton();
		headerPage().clickSignInLink();
		footerPage().clickRegistrierungLink();

	}

	@Step
	public void selectLanguage(String language) {
		headerPage().selectLanguage(language);
	}

	@Step
	public void clickAnmeldenButton() {
		headerPage().clickSignInLink();
	}

	@Step
	public void clickAbmeldenButton() {
		headerPage().clickAbmeldenButton();
	}

	@Step
	public String getBoutiqueName() {
		return headerPage().getBoutiqueName();
	}

	@Step
	public void navigateToPartyPageAndStartOrder(String url) {
		navigate(url);
		partyCreationPage().clickOrderForHostess();
	}

	public boolean succesfullLogin() {
		return headerPage().succesfullLogin();
	}
	
	
	public void checkSucesfullLogin() {
		 headerPage().checkSucesfullLogin();
	}
	
	public void checkSucesfullLoginDE() {
		// TODO Auto-generated method stub
		headerPage().checkSucesfullLoginDE();
	}
	
	@Step
	public void checkSucesfullLoginES() {
		headerPage().checkSucesfullLoginES();
		
	}
	@Step
	public void goToShop() {
		headerPage().clickShop();
		//waitABit(30000);
	}

	public void navigateToNotPrefWebsite(String context) {
		navigate(MongoReader.getBaseURL()+"context");
		
	}

	public void navigateToESWebsite(String context) {
		navigate("http://staging-aut.pippajean.com/es/");
	}

	@StepGroup
	public void verifyWebsiteAndStoreView(String website,String storeView) {
		headerPage().verifyWebsite(website);
		headerPage().verifyStoreView(storeView);
		
	}
	
	@StepGroup
	public void verifyWebsiteAndStylistContext(String website,String context) {
		headerPage().verifyWebsite(website);
		headerPage().verifyStylistContext(context);
		
	}

	public void switchToEsStoreView() {
		getDriver().get(MongoReader.getBaseURL()+"/?___store=de_lang_es");
		
	}
	public void switchToDeStoreView() {
		getDriver().get(MongoReader.getBaseURL()+"/?___store=de_lang_de");
		
	}

	public void clickOnKoboShare() {
		headerPage().clickOnKoboShare();
		findFrame("Log in With Facebook");
		
	}

	public void clickOnShareOnlineBootique() {
		headerPage().clickOnShareOnlineBootique();
		findFrame("Log in With Facebook");
	}

	public void clickInviteFacebookFriends() {
		headerPage().clickInviteFacebookFriends();
		findFrame("Log in With Facebook");
		
	}

	@Step
	public void checkSucesfullLoginInPippa() {
		// TODO Auto-generated method stub
		headerPage().openNewTab();
		switchToNewestOpenedTab();
		headerPage().navigate("https://staging.pippajean.com/de/");
		headerPage().checkSucesfullLoginInPippa();
	}

	public void performLogOut() {
		headerPage().performLogOut();	
		waitABit(3000);
	}
	
	
}

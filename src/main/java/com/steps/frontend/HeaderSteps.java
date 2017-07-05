package com.steps.frontend;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.Separators;
import com.tools.constants.TimeConstants;
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class HeaderSteps extends AbstractSteps {

	private static final long serialVersionUID = 1221784607709066875L;

	@Step
	public void openCartPreview() {
		headerPage().clickShoppingBag();
		// return headerPage().getShoppingBagTotalSum();
	}

	@Step
	public void goToCart() {
		headerPage().clickGoToCart();
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
	public void redirectToCartPage() {
		navigate(MongoReader.getBaseURL() + UrlConstants.CART_PAGE_URL);
	}

	@StepGroup
	public void navigateToRegisterForm() {
		navigate(MongoReader.getBaseURL());
		navigate(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		footerPage().clickRegistrierungLink();

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
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormAndLogout() {
		// navigate(MongoReader.getBaseURL());
		headerPage().clickAbmeldenButton();
		headerPage().clickAnmeldenButton();
		footerPage().clickRegistrierungLink();

	}

	@Step
	public void selectLanguage(String language) {
		headerPage().selectLanguage(language);
	}

	@Step
	public void clickAnmeldenButton() {
		headerPage().clickAnmeldenButton();
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
	
	
	@Step
	public void goToShop() {
		headerPage().clickShop();
		//waitABit(30000);
	}

	public void navigateToNotPrefWebsite(String context) {
		navigate(MongoReader.getBaseURL()+"context");
		
	}

}

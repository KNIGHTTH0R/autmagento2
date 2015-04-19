package com.steps.frontend;

import java.io.File;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import org.junit.Assert;

import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class HeaderSteps extends AbstractSteps {

	private static final long serialVersionUID = 1221784607709066875L;

	@Step
	public String openCartPreview() {
		headerPage().clickShoppingBag();
		return headerPage().getShoppingBagTotalSum();
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
	@Title("Go to create party page")
	public void goToCreatePartyPage() {
		headerPage().clickLounge();
		loungePage().clickCreateParty();
		
	}
	@Step
	@Title("Go to create party for new contact")
	public void goToCreatePartyWithNewContactPage() {
		headerPage().clickLounge();
		loungePage().clickCreateParty();
		partyCreationPage().checkHostedByCustomer();
		partyCreationPage().clickAddContact();
		
	}

	public void redirectToProfileHistory() {
		getDriver().get(MongoReader.getBaseURL() +  MongoReader.getContext() + UrlConstants.PROFILE_HISTORY_URL);
	}
	@Step
	public void redirectToWishlist() {
		getDriver().get(MongoReader.getBaseURL() +  MongoReader.getContext() +  UrlConstants.WISHLIST_URL);
		wishlistPage().addAllProductsToCArt();
	}

	public void redirectToStylistsCustomerOrderReport() {
		getDriver().get(MongoReader.getBaseURL() +  MongoReader.getContext() + UrlConstants.STYLISTS_CUSTOMER_ORDER_REPORT);
	}

	public void redirectToCartPage() {
		getDriver().get(MongoReader.getBaseURL() +  MongoReader.getContext() + UrlConstants.CART_PAGE_URL);
//		getDriver().get(UrlConstants.CART_PAGE_URL);
	}

	@StepGroup
	public void navigateToRegisterForm() {
		getDriver().get(MongoReader.getBaseURL() + MongoReader.getContext());
//		getDriver().get(UrlConstants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormFromStylistRegistrationLinkAndStarteJetzButton() {
		getDriver().get(MongoReader.getBaseURL() + MongoReader.getContext());
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormAndLogout() {
		getDriver().get(MongoReader.getBaseURL() + MongoReader.getContext());
		headerPage().clickAbmeldenButton();
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

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
	public String getStyleCoachFirstNameFromProfile() {
		return headerPage().getStyleCoachFirstNameFromProfile();
	}

	@Step
	public void validateCustomeStyleCoachName(String boutiqueName, String styleCoachName) {
		Assert.assertTrue("Failure: The stylecoach name and boutique name don't match !", boutiqueName.contentEquals(styleCoachName));
		Assert.assertFalse("Failure: The stylecoach name and boutique is empty !", boutiqueName.contentEquals(""));
	}

	@Step
	public void navigateToPartyPageAndStartOrder(String url) {
		headerPage().navigateToPartyPage(url);
		partyCreationPage().clickOrderForHostess();
	}

}

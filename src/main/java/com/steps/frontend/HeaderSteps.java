package com.steps.frontend;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import org.junit.Assert;

import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.Separators;
import com.tools.env.constants.TimeConstants;
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
		headerPage().clickLounge();
		loungePage().clickCreateParty();
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
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.PROFILE_HISTORY_URL);

	}

	@Step
	public void redirectToWishlist() {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.WISHLIST_URL);

	}

	@Step
	public void wipeWishlist() {
		if (!wishlistPage().isWishlistEmpty()) {
			wishlistPage().addAllProductsToCArt();
		}
	}

	public void redirectToStylistsCustomerOrderReport() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.STYLISTS_CUSTOMER_ORDER_REPORT);
	}

	public void redirectToCartPage() {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.CART_PAGE_URL);
	}

	@StepGroup
	public void navigateToRegisterForm() {
		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		footerPage().clickRegistrierungLink();

	}

	@StepGroup
	public void navigateToStylecoachRegisterFormUnderContext(String context) {
		getDriver().get(MongoReader.getBaseURL() + Separators.SLASH + context.toLowerCase());
		waitABit(2000);
		System.out.println(MongoReader.getBaseURL() + Separators.SLASH + context.toLowerCase());
		footerPage().clickRegistrierungLink();

	}

	@StepGroup
	public void navigateToRegisterFormFromStylistRegistrationLinkAndStarteJetzButton() {
		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormAndLogout() {
		getDriver().get(MongoReader.getBaseURL());
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
	public String getStyleCoachFirstNameFromProfile() {
		return headerPage().getStyleCoachFirstNameFromProfile();
	}

	@Step
	public String getStyleCoachFullNameFromProfile() {
		return headerPage().getStyleCoachFullNameFromProfile();
	}

	@Step
	public String getStyleCoachEmailFromProfile() {
		return headerPage().getStyleCoachEmailFromProfile();
	}

	@Step
	public void validateCustomeStyleCoachName(String boutiqueName, String styleCoachName) {
		Assert.assertTrue("Failure: The stylecoach name and boutique name don't match !", boutiqueName.contentEquals(styleCoachName));
		Assert.assertFalse("Failure: The stylecoach name and boutique is empty !", boutiqueName.contentEquals("") || boutiqueName == null);
	}

	@Step
	public void validateCustomerIsAssignedToStyleCoach(String expectedSCName, String grabbedSCName) {
		Assert.assertTrue("Failure: The customer is not assigned to the expected SC !", expectedSCName.contentEquals(grabbedSCName));

	}

	@Step
	public DBStylistModel validateCustomerIsAssignedToOneOfTheStyleCoachesAndGetConfig(List<DBStylistModel> stylistsList, String grabbedEmail) {
		boolean match = false;
		DBStylistModel result = new DBStylistModel();
		for (DBStylistModel dbStylistModel : stylistsList) {
			if (dbStylistModel.getEmail().contentEquals(grabbedEmail)) {
				match = true;
				result.setEmail(dbStylistModel.getEmail());
				result.setStatus(dbStylistModel.getStatus());
				result.setFirstName(dbStylistModel.getFirstName());
				result.setLattitude(dbStylistModel.getLattitude());
				result.setLongitude(dbStylistModel.getLongitude());
				result.setQualifiedSC(dbStylistModel.getQualifiedSC());
				result.setQualifiedHost(dbStylistModel.getQualifiedHost());
				result.setQualifiedCustomer(dbStylistModel.getQualifiedCustomer());
				result.setTotalSCReceived(dbStylistModel.getTotalSCReceived());
				result.setTotalHostReceived(dbStylistModel.getTotalHostReceived());
				result.setTotalCustomerReceived(dbStylistModel.getTotalCustomerReceived());
				result.setTotalSCCurrentWeek(dbStylistModel.getTotalSCCurrentWeek());
				result.setTotalHostCurrentWeek(dbStylistModel.getTotalHostCurrentWeek());
				result.setMaxSCPerWeek(dbStylistModel.getMaxSCPerWeek());
				result.setLeadRetrievalPaused(dbStylistModel.getLeadRetrievalPaused());
				result.setStyleCoachLeadRange(dbStylistModel.getStyleCoachLeadRange());
				result.setHostLeadRange(dbStylistModel.getHostLeadRange());

				break;
			}
		}
		Assert.assertTrue("Failure: The customer is not assigned to the expected SC !", match);

		return result;
	}

	@Step
	public void navigateToPartyPageAndStartOrder(String url) {
		headerPage().navigateToPartyPage(url);
		partyCreationPage().clickOrderForHostess();
	}

	@Step
	public boolean succesfullLogin() {
		return headerPage().succesfullLogin();
	}

}

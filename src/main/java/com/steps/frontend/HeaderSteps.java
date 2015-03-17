package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.Constants;
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

	public void redirectToProfileHistory() {
		getDriver().get(Constants.PROFILE_HISTORY_URL);
	}

	public void redirectToCartPage() {
		getDriver().get(Constants.CART_PAGE_URL);
	}

	@StepGroup
	public void navigateToRegisterForm() {
		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormFromStylistRegistrationLinkAndStarteJetzButton() {
		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

	}

	@StepGroup
	public void navigateToRegisterFormAndLogout() {
		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAbmeldenButton();
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();

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
	public String getStyleCoachFirstNameFromProfile(){ 
		return headerPage().getStyleCoachFirstNameFromProfile();
	}
	@Step
	public void validateCustomeStyleCoachName(String boutiqueName, String styleCoachName){		
		Assert.assertTrue("The stylecoach name and boutique name don't match !", boutiqueName.contentEquals(styleCoachName));
		Assert.assertFalse(boutiqueName.contentEquals(""));
	}

}

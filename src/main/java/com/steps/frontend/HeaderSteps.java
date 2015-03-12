package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.Constants;
import com.tools.requirements.AbstractSteps;

public class HeaderSteps extends AbstractSteps {

	private static final long serialVersionUID = 1221784607709066875L;

	/**
	 * Return the total sum on the cart preview.
	 * 
	 * @return - Preview Price
	 */
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
	public void navigateToRegisterForm(){
		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();
		
	}
	@StepGroup
	public void navigateToRegisterForm2(){
		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();
		
	}
	@StepGroup
	public void navigateToRegisterForm3(){
		getDriver().get(Constants.BASE_FE_URL);
		homePage().clickStyleCoachLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStartenFromStarterSet();
//		starterSetPage().clickOnJetztStyleCoachWerdenButton();
		
	}
	@StepGroup
	public void navigateToRegisterForm4(){
		getDriver().get(Constants.BASE_FE_URL);
		homePage().clickStyleCoachLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();
		
	}
	@StepGroup
	public void navigateToRegisterForm5(){
		footerPage().clickRegistrierungLink();
		
	}
	@StepGroup
	public void navigateToRegisterForm6(){
		footerPage().clickStarterSetLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();		
	}
	@StepGroup
	public void navigateToRegisterForm7(){
		footerPage().clickStarterSetLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();		
	}
	
	@StepGroup
	public void navigateToRegisterForm8(){
		footerPage().clickIncentivereisenLink();
		stylistCampaignPage().clickStarteJetztButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();		
	}	
	
	@StepGroup
	public void navigateToRegisterFormAndLogout(){
		getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAbmeldenButton();
		headerPage().clickAnmeldenButton();
		loginPage().clickOnStylistRegistrationLink();
		stylistCampaignPage().clickJetztStartenButton();
		starterSetPage().clickOnJetztStyleCoachWerdenButton();
		
	}

	@Step
	public String getUrl() {
		return headerPage().getUrl();
	}

}

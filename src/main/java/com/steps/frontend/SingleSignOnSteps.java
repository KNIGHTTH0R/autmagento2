package com.steps.frontend;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class SingleSignOnSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	

	@Step
	public  void validateShopLoginStoreView(String storeView) {
		singleSignOnPage().vaslidateShopLoginStoreView(storeView);
		
	}
	
	@Step
	public  void validateCustomerName(String customerName) {
		singleSignOnPage().validateCustomerName(customerName);
		
	}
	
	
	@Step
	public  void validateShopLogoutStoreView(String storeView) {
		singleSignOnPage().validateShopLogoutStoreView(storeView);
		
	}
	
	@Step
	public  void validateShopLoginWebsite(String website) {
		singleSignOnPage().validateShopLoginWebsite(website);
		
	}

	
	@Step
	public  void validateContext(String context) {
		singleSignOnPage().validateContext(context);
		
	}


	@Step
	public void validateShopLoginWebsiteAndStoreView(String website, String storeView, String context) {
		validateShopLoginStoreView(storeView);
		validateShopLoginWebsite(website);
		validateContext(context);
		
	}
	@Step
	public void validateShopLoginOtherWebsiteAndStoreView(String website, String storeView) {
	//	navigate("https://staging.pippajean.com/");
		headerPage().switchWebsite(website);
		validateShopLoginStoreView(storeView);
		validateShopLoginWebsite(website);
		
	}
	
	@Step
	public void validateShopLoginOtherWebsiteAndStoreView(String website, String storeView,String customerName) {
	//	navigate("https://staging.pippajean.com/");
		headerPage().switchWebsite(website);
		validateCustomerName(customerName);
		validateShopLoginStoreView(storeView);
		validateShopLoginWebsite(website);
		
	}
	
	@Step
	public void validateShopLogoutOtherWebsiteAndStoreView(String website, String storeView) {
	//	navigate("https://staging.pippajean.com/");
		headerPage().switchWebsite(website);
		validateShopLogoutStoreView(storeView);
		validateShopLoginWebsite(website);
	
	}
	
	@Step
	public void validateShopLogoutWebsiteAndStoreView(String website, String storeView, String context) {
		validateShopLogoutStoreView(storeView);
		validateShopLoginWebsite(website);
		validateContext(context);
		
	}

	@Step
	public void validateLoggedInAlreadyInAcademy() {
		openNewTab();
		switchToNewestOpenedTab();
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedInAcademy();
		switchBackToFirstTab();
	}
	
	@Step
	public void validateLoggedInAcademy() {
	//	openNewTab();
		switchToNewestOpenedTab();
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedInAcademy();
		switchBackToFirstTab();
	}
	
	@Step
	public void validateLoggedInAcademyWithRightUser(String userEmail) {
	//	openNewTab();
		switchToNewestOpenedTab();
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedInAcademy();
		academyPage().clickEnrollButton();
		academyPage().startTraining();
		academyPage().openCourse("3");
		singleSignOnPage().validateLoggedWithRightUser(userEmail);
		switchBackToFirstTab();
	}
	
	@Step
	public void validateLoggedInAcademySameTab() {
		openNewTab();
		switchToNewestOpenedTab();
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedInAcademy();
	}


	public void validateLoggedOutAlreadyFromAcademy() {
//		openNewTab();
		switchToNewestOpenedTab();
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedOutFromAcademy();
		switchBackToFirstTab();
		
	}
	
	public void validateLoggedOutFromAcademy() {
//		openNewTab();
		
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedOutFromAcademy();

		
	}

	public void navigateToShop() {
		// TODO Auto-generated method stub
		navigate("https://staging.pippajean.com/");
	}

	public void clickOnShopLogo() {
		// TODO Auto-generated method stub
		singleSignOnPage().clickOnShopLogo();
	}

	public void keepStylistContext() {
		// TODO Auto-generated method stub
		singleSignOnPage().keepStylistContext();
	}
}

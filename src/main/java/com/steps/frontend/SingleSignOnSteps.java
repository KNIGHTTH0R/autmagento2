package com.steps.frontend;

import java.util.Set;

import org.junit.Assert;
import org.mockito.internal.invocation.finder.VerifiableInvocationsFinder;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class SingleSignOnSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;

	

	@Step
	public  void validateShopLoginStoreView(String storeView) {
		singleSignOnPage().vaslidateShopLoginStoreView(storeView);
		
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
	public void validateShopLogoutOtherWebsiteAndStoreView(String website, String storeView) {
	//	navigate("https://staging.pippajean.com/");
		headerPage().switchWebsite(website);
		validateShopLogoutStoreView(storeView);
		validateShopLoginWebsite(website);
	
	}
	
	@Step
	public void validateShopLogoutWebsiteAndStoreView(String website, String storeView, String context) {
		validateShopLogoutStoreView(storeView);
	//	validateShopLoginWebsite(website);
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


	public void validateLoggedOutAlreadyFromAcademy() {
//		openNewTab();
		switchToNewestOpenedTab();
		navigate("https://staging-academy.pippajean.com");
		singleSignOnPage().validateLoggedOutFromAcademy();
		switchBackToFirstTab();
		
	}

	public void navigateToShop() {
		// TODO Auto-generated method stub
		navigate("https://staging.pippajean.com/");
	}
}

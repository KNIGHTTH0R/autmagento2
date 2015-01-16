package com.tools;

import net.thucydides.core.steps.ScenarioSteps;

import com.pages.backend.CustomerDetailsHomePage;
import com.pages.backend.CustomerListPage;
import com.pages.backend.LeadSettingsPage;
import com.pages.backend.MagentoLoginPage;
import com.pages.backend.NavigationPage;
import com.pages.external.MailinatorHomePage;
import com.pages.external.MailinatorPage;
import com.pages.frontend.CartPage;
import com.pages.frontend.CreateCustomerPage;
import com.pages.frontend.HeaderPage;
import com.pages.frontend.LoginPage;
import com.pages.frontend.ProductDetailsPage;
import com.pages.frontend.ProductListPage;
import com.pages.frontend.RegistrationMessagePage;

public class AbstractSteps extends ScenarioSteps {

	private static final long serialVersionUID = 7370145458268780962L;

	// ----------------------BE------------------------------------
	public MagentoLoginPage magentoLoginPage() {
		return getPages().currentPageAt(MagentoLoginPage.class);
	}

	public NavigationPage navigationPage() {
		return getPages().currentPageAt(NavigationPage.class);
	}

	public CustomerListPage customerListPage() {
		return getPages().currentPageAt(CustomerListPage.class);
	}

	public CustomerDetailsHomePage customerDetailsHomePage() {
		return getPages().currentPageAt(CustomerDetailsHomePage.class);
	}

	public LeadSettingsPage leadSettingsPage() {
		return getPages().currentPageAt(LeadSettingsPage.class);
	}

	// -----------------------FE-----------------------------------
	public LoginPage loginPage() {
		return getPages().currentPageAt(LoginPage.class);
	}

	public CreateCustomerPage createCustomerPage() {
		return getPages().currentPageAt(CreateCustomerPage.class);
	}

	public RegistrationMessagePage registrationMessagePage() {
		return getPages().currentPageAt(RegistrationMessagePage.class);
	}
	
	public HeaderPage headerPage(){
		return getPages().currentPageAt(HeaderPage.class);
	}
	
	public ProductListPage productListPage(){
		return getPages().currentPageAt(ProductListPage.class);
	}
	public ProductDetailsPage productDetailsPage(){
		return getPages().currentPageAt(ProductDetailsPage.class);
	}
	
	public CartPage cartPage(){
		return getPages().currentPageAt(CartPage.class);
	}

	// -----------------------Email-----------------------------------

	public MailinatorPage mailinatorPage() {
		return getPages().currentPageAt(MailinatorPage.class);
	}

	public MailinatorHomePage mailinatorHomePage() {
		return getPages().currentPageAt(MailinatorHomePage.class);
	}
}

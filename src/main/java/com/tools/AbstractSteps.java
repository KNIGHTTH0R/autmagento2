package com.tools;

import org.junit.Assert;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.backend.MagentoLoginPage;
import com.pages.backend.NavigationPage;
import com.pages.backend.customer.CustomerListPage;
import com.pages.backend.customer.details.CustomerDetailsPage;
import com.pages.backend.customer.details.LeadSettingsPage;
import com.pages.backend.orders.OrderListPage;
import com.pages.backend.orders.details.OrderInfoPage;
import com.pages.backend.orders.details.OrderItemsPage;
import com.pages.backend.orders.details.OrderTotalsPage;
import com.pages.external.MailinatorHomePage;
import com.pages.external.MailinatorPage;
import com.pages.frontend.CreateCustomerPage;
import com.pages.frontend.HeaderPage;
import com.pages.frontend.LoginPage;
import com.pages.frontend.ProductDetailsPage;
import com.pages.frontend.ProductListPage;
import com.pages.frontend.RegistrationMessagePage;
import com.pages.frontend.checkout.CartPage;
import com.pages.frontend.checkout.ConfirmationPage;
import com.pages.frontend.checkout.SuccessPage;
import com.pages.frontend.checkout.payment.CreditCardFormPage;
import com.pages.frontend.checkout.payment.PaymentPage;
import com.pages.frontend.checkout.shipping.BillingFormPage;
import com.pages.frontend.checkout.shipping.ShippingFormPage;
import com.pages.frontend.checkout.shipping.SurveyPage;
import com.pages.frontend.profile.DashboardMenuPage;
import com.pages.frontend.profile.ProfileHistoryPage;

public class AbstractSteps extends ScenarioSteps {

	private static final long serialVersionUID = 7370145458268780962L;

	/**
	 * General FrontEnd Login method.
	 * 
	 * @param userName
	 * @param userPass
	 */
	@Step
	public void performLogin(String userName, String userPass) {
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	
	public AbstractPage abstractPage(){
		return getPages().currentPageAt(AbstractPage.class);
	}

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

	public CustomerDetailsPage customerDetailsHomePage() {
		return getPages().currentPageAt(CustomerDetailsPage.class);
	}

	public LeadSettingsPage leadSettingsPage() {
		return getPages().currentPageAt(LeadSettingsPage.class);
	}
	
	public OrderListPage orderListPage(){
		return getPages().currentPageAt(OrderListPage.class);
	}
	
	public OrderItemsPage orderItemsPage(){
		return getPages().currentPageAt(OrderItemsPage.class);
	}
	public OrderTotalsPage orderTotalsPage(){
		return getPages().currentPageAt(OrderTotalsPage.class);
	}
	public OrderInfoPage orderInfoPage(){
		return getPages().currentPageAt(OrderInfoPage.class);
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

	public HeaderPage headerPage() {
		return getPages().currentPageAt(HeaderPage.class);
	}

	public ProductListPage productListPage() {
		return getPages().currentPageAt(ProductListPage.class);
	}

	public ProductDetailsPage productDetailsPage() {
		return getPages().currentPageAt(ProductDetailsPage.class);
	}

	//cart
	public CartPage cartPage() {
		return getPages().currentPageAt(CartPage.class);
	}

	// shipping pages
	public BillingFormPage billingFormPage() {
		return getPages().currentPageAt(BillingFormPage.class);
	}

	public ShippingFormPage shippingFormPage() {
		return getPages().currentPageAt(ShippingFormPage.class);
	}

	public SurveyPage surveyPage() {
		return getPages().currentPageAt(SurveyPage.class);
	}
	
	// payment pages
	public PaymentPage paymentPage(){
		return getPages().currentPageAt(PaymentPage.class);
	}
	
	public CreditCardFormPage creditCardFormPage(){
		return getPages().currentPageAt(CreditCardFormPage.class);
	}
	
	//confirmation page
	public ConfirmationPage confirmationPage(){
		return getPages().currentPageAt(ConfirmationPage.class);
	}
	
	public SuccessPage successPage(){
		return getPages().currentPageAt(SuccessPage.class);
	}
	
	//Profile
	public DashboardMenuPage dashboardMenuPage(){
		return getPages().currentPageAt(DashboardMenuPage.class);
	}
	
	public ProfileHistoryPage profileHistoryPage(){
		return getPages().currentPageAt(ProfileHistoryPage.class);
	}
	

	// -----------------------Email-----------------------------------

	public MailinatorPage mailinatorPage() {
		return getPages().currentPageAt(MailinatorPage.class);
	}

	public MailinatorHomePage mailinatorHomePage() {
		return getPages().currentPageAt(MailinatorHomePage.class);
	}
	
	
	//------------------------- Common methods 
	
	@Step
	@Screenshots(onlyOnFailures = true)
	public void printStylistBackendValues(String message, String customerLeads, String hostessLeads, String hostessLeadWeek, String styleCoachLeads, String styleCoachLeadsWeek) {
		System.out.println(" -- Print Totals - " + message);
		System.out.println("CUSTOMERLEADS: " + customerLeads);
		System.out.println("HOSTESSLEADS: " + hostessLeads);
		System.out.println("HOSTESSLEADSWEEK: " + hostessLeadWeek);
		System.out.println("STYLECOACHLEADS: " + styleCoachLeads);
		System.out.println("STYLECOACHLEADSWEEK: " + styleCoachLeadsWeek);
		getDriver().getCurrentUrl();
	}


}

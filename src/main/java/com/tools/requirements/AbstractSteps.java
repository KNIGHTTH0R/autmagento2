package com.tools.requirements;

import java.util.Set;

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
import com.pages.backend.orders.details.OrdersActionsPage;
import com.pages.backend.promotion.ShoppingCartPriceRulesPage;
import com.pages.external.MailinatorPage;
import com.pages.external.facebook.FacebookEMBLoginConfirmPage;
import com.pages.external.facebook.FacebookEMBLoginPage;
import com.pages.frontend.CreateCustomerPage;
import com.pages.frontend.FooterPage;
import com.pages.frontend.HeaderPage;
import com.pages.frontend.HomePage;
import com.pages.frontend.LoginPage;
import com.pages.frontend.LoungePage;
import com.pages.frontend.PartyCreationPage;
import com.pages.frontend.PartyDetailsPage;
import com.pages.frontend.ProductDetailsPage;
import com.pages.frontend.ProductListPage;
import com.pages.frontend.RegistrationMessagePage;
import com.pages.frontend.StarterSetPage;
import com.pages.frontend.StylistCampaignPage;
import com.pages.frontend.StylistRegistrationPage;
import com.pages.frontend.checkout.ConfirmationPage;
import com.pages.frontend.checkout.SuccessPage;
import com.pages.frontend.checkout.cart.partyHost.HostCartPage;
import com.pages.frontend.checkout.cart.regularCart.RegularUserCartPage;
import com.pages.frontend.checkout.cart.styleCoachCart.CartPage;
import com.pages.frontend.checkout.payment.CreditCardFormPage;
import com.pages.frontend.checkout.payment.PaymentPage;
import com.pages.frontend.checkout.shipping.BillingFormPage;
import com.pages.frontend.checkout.shipping.ShippingFormPage;
import com.pages.frontend.checkout.shipping.SurveyPage;
import com.pages.frontend.checkout.shipping.regularUser.ShippingPartySectionPage;
import com.pages.frontend.profile.DashboardMenuPage;
import com.pages.frontend.profile.ProfileHistoryPage;
import com.pages.frontend.registration.FacebookRegistrationFormPage;
import com.pages.frontend.registration.landing.ContactLandingPage;
import com.pages.frontend.registration.landing.LandingCustomerAllocationPage;
import com.pages.frontend.registration.landing.RegistrationSuccessPage;
import com.pages.frontend.registration.landing.ThankYouPage;
import com.pages.frontend.registration.widget.RegisterLandingPage;
import com.pages.frontend.reports.StylistsCustomerOrderReportPage;
import com.tools.Constants;

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

	
	public void refresh() {
		getDriver().navigate().refresh();
		waitABit(Constants.TIME_CONSTANT);
	}

	@Step
	public void performLoginUnderContext(String userName, String userPass, String context) {
		getDriver().get(Constants.BASE_FE_URL + context);
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	@Step
	public void navigateToLoginPageAndPerformLogin(String userName, String userPass) {
		// getDriver().get(Constants.BASE_FE_URL);
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	/**
	 * Clean cart content. On current user. This method should be used
	 * imediattly after login. URL path might not be rewritten correctly
	 * otherwise.
	 * 
	 */
	public void wipeCart() {
		String initURL = getDriver().getCurrentUrl();
		String modiURL = getDriver().getCurrentUrl().replace("stylist/lounge/", "checkout/cart/clearAllItems/");
		getDriver().get(modiURL);
		waitABit(Constants.TIME_CONSTANT);
		getDriver().get(initURL);
	}
	
	//TODO fix this
	public void wipeRegularCart() {
		String initURL = getDriver().getCurrentUrl();
		String modiURL = getDriver().getCurrentUrl().replace("schmuckstucke/neu.html", "checkout/cart/clearAllItems/");
		getDriver().get(modiURL);
		waitABit(Constants.TIME_CONSTANT);
		getDriver().get(initURL);
	}
	public void wipeHostCart() {
		String initURL = getDriver().getCurrentUrl();
		String modiURL = getDriver().getCurrentUrl().replace("checkout/cart/", "checkout/cart/clearAllItems/");
		getDriver().get(modiURL);
		waitABit(Constants.TIME_CONSTANT);
		getDriver().get(initURL);
	}

	public void findFrame(String frameName) {
		Set<String> windowsList = getDriver().getWindowHandles();
		for (String string : windowsList) {
			getDriver().switchTo().window(string);
			if (getDriver().getTitle().trim().contains(frameName)) {
				break;
			}
		}
	}

	public void navigate(String URL) {
		getDriver().get(URL);
	}

	public AbstractPage abstractPage() {
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

	public OrderListPage orderListPage() {
		return getPages().currentPageAt(OrderListPage.class);
	}

	public OrdersActionsPage ordersActionsPage() {
		return getPages().currentPageAt(OrdersActionsPage.class);
	}

	public OrderItemsPage orderItemsPage() {
		return getPages().currentPageAt(OrderItemsPage.class);
	}

	public OrderTotalsPage orderTotalsPage() {
		return getPages().currentPageAt(OrderTotalsPage.class);
	}

	public OrderInfoPage orderInfoPage() {
		return getPages().currentPageAt(OrderInfoPage.class);
	}

	public ShoppingCartPriceRulesPage shoppingCartPriceRulesPage() {
		return getPages().currentPageAt(ShoppingCartPriceRulesPage.class);
	}

	// -----------------------FE-----------------------------------
	public LoginPage loginPage() {
		return getPages().currentPageAt(LoginPage.class);
	}
	public LoungePage loungePage() {
		return getPages().currentPageAt(LoungePage.class);
	}

	public HomePage homePage() {
		return getPages().currentPageAt(HomePage.class);
	}

	public StarterSetPage starterSetPage() {
		return getPages().currentPageAt(StarterSetPage.class);
	}

	public StylistCampaignPage stylistCampaignPage() {
		return getPages().currentPageAt(StylistCampaignPage.class);
	}

	public StylistRegistrationPage stylistRegistrationPage() {
		return getPages().currentPageAt(StylistRegistrationPage.class);
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

	public FooterPage footerPage() {
		return getPages().currentPageAt(FooterPage.class);
	}

	public ProductListPage productListPage() {
		return getPages().currentPageAt(ProductListPage.class);
	}

	public ProductDetailsPage productDetailsPage() {
		return getPages().currentPageAt(ProductDetailsPage.class);
	}
	public PartyCreationPage partyCreationPage() {
		return getPages().currentPageAt(PartyCreationPage.class);
	}
	public PartyDetailsPage partyDetailsPage() {
		return getPages().currentPageAt(PartyDetailsPage.class);
	}

	// cart
	public CartPage cartPage() {
		return getPages().currentPageAt(CartPage.class);
	}

	public RegularUserCartPage regularUserCartPage() {
		return getPages().currentPageAt(RegularUserCartPage.class);
	}
	public HostCartPage hostCartPage() {
		return getPages().currentPageAt(HostCartPage.class);
	}

	// shipping pages
	public BillingFormPage billingFormPage() {
		return getPages().currentPageAt(BillingFormPage.class);
	}
	public ShippingPartySectionPage regularUserShippingPage() {
		return getPages().currentPageAt(ShippingPartySectionPage.class);
	}

	public ShippingFormPage shippingFormPage() {
		return getPages().currentPageAt(ShippingFormPage.class);
	}

	public SurveyPage surveyPage() {
		return getPages().currentPageAt(SurveyPage.class);
	}

	// payment pages
	public PaymentPage paymentPage() {
		return getPages().currentPageAt(PaymentPage.class);
	}

	public CreditCardFormPage creditCardFormPage() {
		return getPages().currentPageAt(CreditCardFormPage.class);
	}

	// confirmation page
	public ConfirmationPage confirmationPage() {
		return getPages().currentPageAt(ConfirmationPage.class);
	}

	public SuccessPage successPage() {
		return getPages().currentPageAt(SuccessPage.class);
	}
	//Lounge-Reports 
	public StylistsCustomerOrderReportPage stylistsCustomerOrderReportPage() {
		return getPages().currentPageAt(StylistsCustomerOrderReportPage.class);
	}

	// Profile
	public DashboardMenuPage dashboardMenuPage() {
		return getPages().currentPageAt(DashboardMenuPage.class);
	}

	public ProfileHistoryPage profileHistoryPage() {
		return getPages().currentPageAt(ProfileHistoryPage.class);
	}

	public FacebookRegistrationFormPage facebookRegistrationFormPage() {
		return getPages().currentPageAt(FacebookRegistrationFormPage.class);
	}
	
	public ContactLandingPage contactLandingPage() {
		return getPages().currentPageAt(ContactLandingPage.class);
	}
	
	public LandingCustomerAllocationPage landingCustomerAllocationPage() {
		return getPages().currentPageAt(LandingCustomerAllocationPage.class);
	}
	
	public ThankYouPage thankYouPage() {
		return getPages().currentPageAt(ThankYouPage.class);
	}
	
	public RegistrationSuccessPage registrationSuccessPage() {
		return getPages().currentPageAt(RegistrationSuccessPage.class);
	}
	
	public RegisterLandingPage registerLandingPage() {
		return getPages().currentPageAt(RegisterLandingPage.class);
	}

	// -----------------------Email-----------------------------------

	public MailinatorPage mailinatorPage() {
		return getPages().currentPageAt(MailinatorPage.class);
	}

	// External facebook
	public FacebookEMBLoginPage facebookEMBLoginPage() {
		return getPages().currentPageAt(FacebookEMBLoginPage.class);
	}

	public FacebookEMBLoginConfirmPage facebookEMBLoginConfirmPage() {
		return getPages().currentPageAt(FacebookEMBLoginConfirmPage.class);
	}

	// ------------------------- Common methods

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

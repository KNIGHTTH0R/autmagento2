package com.tools.requirements;

import java.util.Set;

import org.junit.Assert;

import com.pages.backend.MagentoLoginPage;
import com.pages.backend.NavigationPage;
import com.pages.backend.SystemConfigurationPage;
import com.pages.backend.creditMemo.CreditMemoDetailsPage;
import com.pages.backend.creditMemo.CreditMemoListPage;
import com.pages.backend.customer.CustomerListPage;
import com.pages.backend.customer.details.CustomerDetailsPage;
import com.pages.backend.customer.details.LeadSettingsPage;
import com.pages.backend.newsletter.NewsletterSubscribersListPage;
import com.pages.backend.orders.OrderListPage;
import com.pages.backend.orders.details.OrderInfoPage;
import com.pages.backend.orders.details.OrderItemsPage;
import com.pages.backend.orders.details.OrderTotalsPage;
import com.pages.backend.orders.details.OrdersActionsPage;
import com.pages.backend.products.BackendProductDetailsPage;
import com.pages.backend.products.BackendProductListPage;
import com.pages.backend.promotion.ShoppingCartPriceRulesPage;
import com.pages.backend.stylecoach.ContactDetailsBackendPage;
import com.pages.backend.stylecoach.ContactListBackendPage;
import com.pages.backend.stylecoach.PartyDetailsBackendPage;
import com.pages.backend.stylecoach.PartyListBackendPage;
import com.pages.backend.stylecoach.StylecoachDetailsBackendPage;
import com.pages.backend.stylecoach.StylecoachListBackendPage;
import com.pages.backend.termPurchase.TermPurchaseGridPage;
import com.pages.external.MailinatorPage;
import com.pages.external.commission.CommissionReportPage;
import com.pages.external.facebook.FacebookEMBLoginConfirmPage;
import com.pages.external.facebook.FacebookEMBLoginPage;
import com.pages.external.mailchimp.MailchimpHeaderPage;
import com.pages.external.mailchimp.MailchimpListDetailsPage;
import com.pages.external.mailchimp.MailchimpListsPage;
import com.pages.external.mailchimp.MailchimpLoginPage;
import com.pages.external.mailchimp.MailchimpSearchPage;
import com.pages.external.mailchimp.MailchimpSubscriberProfilePage;
import com.pages.external.unbounce.UnbounceDykscPage;
import com.pages.external.unbounce.UnbouncePage;
import com.pages.external.unbounce.UnbounceRegSuccesPage;
import com.pages.frontend.ContactDetailsPage;
import com.pages.frontend.CreateCustomerPage;
import com.pages.frontend.FancyBoxPage;
import com.pages.frontend.FooterPage;
import com.pages.frontend.HeaderPage;
import com.pages.frontend.HomePage;
import com.pages.frontend.KoboSuccesFormPage;
import com.pages.frontend.KoboValidationPage;
import com.pages.frontend.LoginPage;
import com.pages.frontend.LoungePage;
import com.pages.frontend.MyBusinessPage;
import com.pages.frontend.MyContactsListPage;
import com.pages.frontend.PartiesListPage;
import com.pages.frontend.PartyCreationPage;
import com.pages.frontend.PartyDetailsPage;
import com.pages.frontend.PomProductListPage;
import com.pages.frontend.ProductDetailsPage;
import com.pages.frontend.ProductListPage;
import com.pages.frontend.RegistrationMessagePage;
import com.pages.frontend.ReportsPage;
import com.pages.frontend.StarterSetPage;
import com.pages.frontend.StylistCampaignPage;
import com.pages.frontend.StylistContextPage;
import com.pages.frontend.StylistRegistrationPage;
import com.pages.frontend.UpdatePartyPage;
import com.pages.frontend.checkout.ConfirmationPage;
import com.pages.frontend.checkout.SuccessPage;
import com.pages.frontend.checkout.cart.GeneralCartPage;
import com.pages.frontend.checkout.cart.borrowCart.BorrowCartPage;
import com.pages.frontend.checkout.cart.kobo.ContactBoosterCartPage;
import com.pages.frontend.checkout.cart.partyHost.AddProductsModalPage;
import com.pages.frontend.checkout.cart.partyHost.HostCartPage;
import com.pages.frontend.checkout.cart.partyHost.OrderForCustomerCartPage;
import com.pages.frontend.checkout.cart.regularCart.PlaceCustomerOrderFromPartyPage;
import com.pages.frontend.checkout.cart.regularCart.RegularUserCartPage;
import com.pages.frontend.checkout.cart.styleCoachCart.CartPage;
import com.pages.frontend.checkout.payment.CreditCardFormPage;
import com.pages.frontend.checkout.payment.PaymentPage;
import com.pages.frontend.checkout.shipping.BillingFormPage;
import com.pages.frontend.checkout.shipping.ShippingFormPage;
import com.pages.frontend.checkout.shipping.SurveyPage;
import com.pages.frontend.checkout.shipping.host.ContactHostShippingPage;
import com.pages.frontend.checkout.shipping.kobo.KoboShippingPage;
import com.pages.frontend.checkout.shipping.regularUser.ShippingPartySectionPage;
import com.pages.frontend.checkout.wishlist.WishlistPage;
import com.pages.frontend.profile.DashboardMenuPage;
import com.pages.frontend.profile.DashboardPage;
import com.pages.frontend.profile.ProfileHistoryPage;
import com.pages.frontend.registration.FacebookRegistrationFormPage;
import com.pages.frontend.registration.connectWithMe.ConnectSuccesPage;
import com.pages.frontend.registration.connectWithMe.ConnectWithMeAllocationPage;
import com.pages.frontend.registration.connectWithMe.ConnectWithMeRegistrationPage;
import com.pages.frontend.registration.contactBooster.ContactBoosterRegistrationPage;
import com.pages.frontend.registration.contactBooster.KoboCampaignPage;
import com.pages.frontend.registration.landing.ContactLandingPage;
import com.pages.frontend.registration.landing.LandingCustomerAllocationPage;
import com.pages.frontend.registration.landing.ThankYouPage;
import com.pages.frontend.registration.party.CreateNewContactPage;
import com.pages.frontend.registration.widget.RegisterLandingPage;
import com.pages.frontend.reports.IpReportsPage;
import com.pages.frontend.reports.JewelryBonusHistoryPage;
import com.pages.frontend.reports.StylistsCustomerOrderReportPage;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.persistance.MongoReader;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

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
		navigate(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();

	}

	@Step
	public void performLoginAfterChangingWebsite(String userName, String userPass) {
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();

	}

	@Step
	public void performLoginAndVerifyWebsiteAndLanguage(String userName, String userPass, String language,
			String website) {
		navigate(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
		headerPage().verifyThatLanguageFromHeaderIsCorrectySelected(language);
		footerPage().verifyThatFooterWebsiteIsCorrect(website);

	}

	@Step
	public void performLoginOnPreferedWebsite(String userName, String userPass) {

		navigate(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
		Assert.assertTrue(
				getDriver().getCurrentUrl().contains(MongoReader.getSoapURL() + ContextConstants.NOT_PREFERED_WEBSITE));
		footerPage().verifyThatFooterWebsiteIsCorrect(ContextConstants.NOT_PREFERED_WEBSITE);
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	public void refresh() {
		getDriver().navigate().refresh();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void performLoginUnderContext(String userName, String userPass, String context) {
		navigate(MongoReader.getBaseURL() + context);
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	@Step
	public void navigateToLoginPageAndPerformLogin(String userName, String userPass) {
		navigate(MongoReader.getBaseURL());
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
		navigate(modiURL);
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().get(initURL);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public void wipeRegularCart() {
		String initURL = getDriver().getCurrentUrl();
		String modiURL = getDriver().getCurrentUrl().replace("schmuckstucke/neu.html", "checkout/cart/clearAllItems/");
		navigate(modiURL);
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().get(initURL);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public void wipeHostCart() {
		String initURL = getDriver().getCurrentUrl();
		String modiURL = getDriver().getCurrentUrl().replace("checkout/cart/", "checkout/cart/clearAllItems/");
		navigate(modiURL);
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().get(initURL);
		waitABit(TimeConstants.TIME_CONSTANT);
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

	@Step
	public void navigate(String URL) {
		// failsafe in case of redirects to live
		if (!URL.contains("www.pippajean.com")) {
//			getDriver().manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
			getDriver().get(URL);
			getDriver().manage().window().maximize();
		}

	}

	public AbstractPage abstractPage() {
		return getPages().currentPageAt(AbstractPage.class);
	}

	// ----------------------BE------------------------------------
	public MagentoLoginPage magentoLoginPage() {
		return getPages().currentPageAt(MagentoLoginPage.class);
	}

	public NewsletterSubscribersListPage newsletterSubscribersListPage() {
		return getPages().currentPageAt(NewsletterSubscribersListPage.class);
	}

	public NavigationPage navigationPage() {
		return getPages().currentPageAt(NavigationPage.class);
	}

	public MyContactsListPage myContactsListPage() {
		return getPages().currentPageAt(MyContactsListPage.class);
	}

	public CustomerListPage customerListPage() {
		return getPages().currentPageAt(CustomerListPage.class);
	}

	public PartyListBackendPage partyListBackendPage() {
		return getPages().currentPageAt(PartyListBackendPage.class);
	}

	public AddProductsModalPage addProductsModalPage() {
		return getPages().currentPageAt(AddProductsModalPage.class);
	}

	public StylecoachListBackendPage stylecoachListBackendPage() {
		return getPages().currentPageAt(StylecoachListBackendPage.class);
	}

	public ContactListBackendPage contactListBackendPage() {
		return getPages().currentPageAt(ContactListBackendPage.class);
	}

	public ContactDetailsBackendPage contactDetailsBackendPage() {
		return getPages().currentPageAt(ContactDetailsBackendPage.class);
	}

	public PartyDetailsBackendPage partyDetailsBackendPage() {
		return getPages().currentPageAt(PartyDetailsBackendPage.class);
	}

	public CustomerDetailsPage customerDetailsHomePage() {
		return getPages().currentPageAt(CustomerDetailsPage.class);
	}

	public BackendProductListPage backendProductListPage() {
		return getPages().currentPageAt(BackendProductListPage.class);
	}

	public BackendProductDetailsPage backendProductDetailsPage() {
		return getPages().currentPageAt(BackendProductDetailsPage.class);
	}

	public LeadSettingsPage leadSettingsPage() {
		return getPages().currentPageAt(LeadSettingsPage.class);
	}

	public OrderListPage orderListPage() {
		return getPages().currentPageAt(OrderListPage.class);
	}

	public CreditMemoListPage creditMemoListPage() {
		return getPages().currentPageAt(CreditMemoListPage.class);
	}

	public CreditMemoDetailsPage creditMemoDetailsPage() {
		return getPages().currentPageAt(CreditMemoDetailsPage.class);
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

	public StylecoachDetailsBackendPage stylecoachDetailsBackendPage() {
		return getPages().currentPageAt(StylecoachDetailsBackendPage.class);
	}
	
	public TermPurchaseGridPage termPurchaseGridPage() {
		return getPages().currentPageAt(TermPurchaseGridPage.class);
	}
	
	public SystemConfigurationPage systemConfigurationPage() {
		return getPages().currentPageAt(SystemConfigurationPage.class);
	}

	// -----------------------FE-----------------------------------
	public LoginPage loginPage() {
		return getPages().currentPageAt(LoginPage.class);
	}

	public DashboardPage dashboardPage() {
		return getPages().currentPageAt(DashboardPage.class);
	}

	public FancyBoxPage fancyBoxPage() {
		return getPages().currentPageAt(FancyBoxPage.class);
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

	public StylistContextPage stylistContextPage() {
		return getPages().currentPageAt(StylistContextPage.class);
	}

	public ConnectWithMeRegistrationPage connectWithMeRegistrationPage() {
		return getPages().currentPageAt(ConnectWithMeRegistrationPage.class);
	}

	public ConnectWithMeAllocationPage connectWithMeAllocationPage() {
		return getPages().currentPageAt(ConnectWithMeAllocationPage.class);
	}

	public ConnectSuccesPage connectSuccesPage() {
		return getPages().currentPageAt(ConnectSuccesPage.class);
	}

	public CreateCustomerPage createCustomerPage() {
		return getPages().currentPageAt(CreateCustomerPage.class);
	}

	public ReportsPage reportsPage() {
		return getPages().currentPageAt(ReportsPage.class);
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

	public PartiesListPage partiesListPage() {
		return getPages().currentPageAt(PartiesListPage.class);
	}

	public PlaceCustomerOrderFromPartyPage placeCustomerOrderFromPartyPage() {
		return getPages().currentPageAt(PlaceCustomerOrderFromPartyPage.class);
	}

	public UpdatePartyPage updatePartyPage() {
		return getPages().currentPageAt(UpdatePartyPage.class);
	}

	public PartyDetailsPage partyDetailsPage() {
		return getPages().currentPageAt(PartyDetailsPage.class);
	}

	public ContactDetailsPage contactDetailsPage() {
		return getPages().currentPageAt(ContactDetailsPage.class);
	}

	public CreateNewContactPage createNewContactPage() {
		return getPages().currentPageAt(CreateNewContactPage.class);
	}

	public KoboValidationPage koboValidationPage() {
		return getPages().currentPageAt(KoboValidationPage.class);
	}

	public ContactBoosterRegistrationPage contactBoosterRegistrationPage() {
		return getPages().currentPageAt(ContactBoosterRegistrationPage.class);
	}

	public KoboSuccesFormPage koboSuccesFormPage() {
		return getPages().currentPageAt(KoboSuccesFormPage.class);
	}

	public KoboCampaignPage koboCampaignPage() {
		return getPages().currentPageAt(KoboCampaignPage.class);
	}

	public PomProductListPage pomProductListPage() {
		return getPages().currentPageAt(PomProductListPage.class);
	}
	public IpReportsPage ipReportsPage() {
		return getPages().currentPageAt(IpReportsPage.class);
	}
	public UnbounceDykscPage unbounceDykscPage() {
		return getPages().currentPageAt(UnbounceDykscPage.class);
	}

	// cart
	public CartPage cartPage() {
		return getPages().currentPageAt(CartPage.class);
	}

	public BorrowCartPage borrowCartPage() {
		return getPages().currentPageAt(BorrowCartPage.class);
	}

	public WishlistPage wishlistPage() {
		return getPages().currentPageAt(WishlistPage.class);
	}

	public RegularUserCartPage regularUserCartPage() {
		return getPages().currentPageAt(RegularUserCartPage.class);
	}

	public HostCartPage hostCartPage() {
		return getPages().currentPageAt(HostCartPage.class);
	}

	public OrderForCustomerCartPage orderForCustomerCartPage() {
		return getPages().currentPageAt(OrderForCustomerCartPage.class);
	}

	// shipping pages
	public BillingFormPage billingFormPage() {
		return getPages().currentPageAt(BillingFormPage.class);
	}

	public ShippingPartySectionPage regularUserShippingPage() {
		return getPages().currentPageAt(ShippingPartySectionPage.class);
	}

	public KoboShippingPage koboShippingPage() {
		return getPages().currentPageAt(KoboShippingPage.class);
	}

	public ShippingFormPage shippingFormPage() {
		return getPages().currentPageAt(ShippingFormPage.class);
	}

	public ContactHostShippingPage contactHostShippingPage() {
		return getPages().currentPageAt(ContactHostShippingPage.class);
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

	// Lounge-Reports
	public StylistsCustomerOrderReportPage stylistsCustomerOrderReportPage() {
		return getPages().currentPageAt(StylistsCustomerOrderReportPage.class);
	}

	public JewelryBonusHistoryPage jewelryBonusHistoryPage() {
		return getPages().currentPageAt(JewelryBonusHistoryPage.class);
	}
	
	public GeneralCartPage generalCartPage() {
		return getPages().currentPageAt(GeneralCartPage.class);
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

	public RegisterLandingPage registerLandingPage() {
		return getPages().currentPageAt(RegisterLandingPage.class);
	}

	public MyBusinessPage myBusinessPage() {
		return getPages().currentPageAt(MyBusinessPage.class);
	}

	public ContactBoosterCartPage contactBoosterCart() {
		return getPages().currentPageAt(ContactBoosterCartPage.class);
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

	// external - Mailchimp
	public MailchimpLoginPage mailchimpLoginPage() {
		return getPages().currentPageAt(MailchimpLoginPage.class);
	}

	public MailchimpHeaderPage mailchimpHeaderPage() {
		return getPages().currentPageAt(MailchimpHeaderPage.class);
	}

	public MailchimpListDetailsPage mailchimpListDetailsPage() {
		return getPages().currentPageAt(MailchimpListDetailsPage.class);
	}

	public MailchimpListsPage mailchimpListsPage() {
		return getPages().currentPageAt(MailchimpListsPage.class);
	}

	public MailchimpSearchPage mailchimpSearchPage() {
		return getPages().currentPageAt(MailchimpSearchPage.class);
	}

	public MailchimpSubscriberProfilePage mailchimpSubscriberProfilePage() {
		return getPages().currentPageAt(MailchimpSubscriberProfilePage.class);
	}

	// unbounce
	public UnbouncePage unbouncePage() {
		return getPages().currentPageAt(UnbouncePage.class);
	}
	
	public UnbounceRegSuccesPage unbounceRegSuccesPage() {
		return getPages().currentPageAt(UnbounceRegSuccesPage.class);
	}

	// commission
	public CommissionReportPage commissionReportPage() {
		return getPages().currentPageAt(CommissionReportPage.class);
	}

	// ------------------------- Common methods

	@Step
	@Screenshots(onlyOnFailures = true)
	public void printStylistBackendValues(String message, String customerLeads, String hostessLeads,
			String hostessLeadWeek, String styleCoachLeads, String styleCoachLeadsWeek) {
		System.out.println(" -- Print Totals - " + message);
		System.out.println("CUSTOMERLEADS: " + customerLeads);
		System.out.println("HOSTESSLEADS: " + hostessLeads);
		System.out.println("HOSTESSLEADSWEEK: " + hostessLeadWeek);
		System.out.println("STYLECOACHLEADS: " + styleCoachLeads);
		System.out.println("STYLECOACHLEADSWEEK: " + styleCoachLeadsWeek);
		getDriver().getCurrentUrl();
	}

}

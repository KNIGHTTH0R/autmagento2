package com.tools.requirements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.pages.backend.MagentoLoginPage;
import com.pages.backend.NavigationPage;
import com.pages.backend.SystemConfigurationPage;
import com.pages.backend.borrow.BorrowPage;
import com.pages.backend.contact.ContactListPage;
import com.pages.backend.creditMemo.CreditMemoDetailsPage;
import com.pages.backend.creditMemo.CreditMemoListPage;
import com.pages.backend.customer.CustomerListPage;
import com.pages.backend.customer.details.CustomerDetailsPage;
import com.pages.backend.customer.details.LeadSettingsPage;
import com.pages.backend.importExport.ImportExportPage;
import com.pages.backend.newsletter.NewsletterSubscribersListPage;
import com.pages.backend.orders.OrderListPage;
import com.pages.backend.orders.details.OrderDetailsNavPage;
import com.pages.backend.orders.details.OrderInfoPage;
import com.pages.backend.orders.details.OrderItemsPage;
import com.pages.backend.orders.details.OrderNotificationPage;
import com.pages.backend.orders.details.OrderTotalsPage;
import com.pages.backend.orders.details.OrdersActionsPage;
import com.pages.backend.products.BackendProductDetailsPage;
import com.pages.backend.products.BackendProductListPage;
import com.pages.backend.promotion.ShoppingCartPriceRulesPage;
import com.pages.backend.stylecoach.ContactDetailsBackendPage;
import com.pages.backend.stylecoach.ContactListBackendPage;
import com.pages.backend.stylecoach.PartyDetailsBackendPage;
import com.pages.backend.stylecoach.PartyListBackendPage;
import com.pages.backend.stylecoach.SosDetailsBackendPage;
import com.pages.backend.stylecoach.StylecoachDetailsBackendPage;
import com.pages.backend.stylecoach.StylecoachListBackendPage;
import com.pages.backend.termPurchase.TermPurchaseGridPage;
import com.pages.external.MailinatorPage;
import com.pages.external.academy.AcademyPage;
import com.pages.external.academy.AcademyQuizPage;
import com.pages.external.academy.LoginAcademyPage;
import com.pages.external.commission.CommissionReportPage;
import com.pages.external.facebook.FacebookEMBLoginConfirmPage;
import com.pages.external.facebook.FacebookEMBLoginPage;
import com.pages.external.mailchimp.MailchimpHeaderPage;
import com.pages.external.mailchimp.MailchimpListDetailsPage;
import com.pages.external.mailchimp.MailchimpListsPage;
import com.pages.external.mailchimp.MailchimpLoginPage;
import com.pages.external.mailchimp.MailchimpSearchPage;
import com.pages.external.mailchimp.MailchimpSubscriberProfilePage;
import com.pages.external.navision.NavisionHomePage;
import com.pages.external.navision.NavisionSearchPage;
import com.pages.external.navision.NavisionSyncDashboardPage;
import com.pages.external.ospm.FacebookLoginPage;
import com.pages.external.ospm.OnlineStylePartyGuestPage;
import com.pages.external.ospm.OnlineStylePartyManagerPage;
import com.pages.external.ospm.OnlineStylePartyStylistPage;
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
import com.pages.frontend.ShopPage;
import com.pages.frontend.SingleSignOnPage;
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
import com.pages.frontend.checkout.payment.ElvPaymentMethodPage;
import com.pages.frontend.checkout.payment.KlarnaPage;
import com.pages.frontend.checkout.payment.PaymentPage;
import com.pages.frontend.checkout.payment.SepaPaymentPage;
import com.pages.frontend.checkout.shipping.BillingFormPage;
import com.pages.frontend.checkout.shipping.ShippingFormPage;
import com.pages.frontend.checkout.shipping.SurveyPage;
import com.pages.frontend.checkout.shipping.host.ContactHostShippingPage;
import com.pages.frontend.checkout.shipping.kobo.KoboShippingPage;
import com.pages.frontend.checkout.shipping.regularUser.ShippingPartySectionPage;
import com.pages.frontend.checkout.wishlist.WishlistPage;
import com.pages.frontend.invitation.FacebookInvitationPage;
import com.pages.frontend.invitation.FacebookMessagePage;
import com.pages.frontend.profile.DashboardPage;
import com.pages.frontend.profile.ProfileHistoryPage;
import com.pages.frontend.profile.ProfileNavPage;
import com.pages.frontend.profile.SosPage;
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
import com.pages.frontend.reports.AvailabilityReportPage;
import com.pages.frontend.reports.IpReportsPage;
import com.pages.frontend.reports.JewelryBonusHistoryPage;
import com.pages.frontend.reports.StylistInventoryPage;
import com.pages.frontend.reports.StylistsCustomerOrderReportPage;
import com.pages.frontend.reports.TeamReportPage;
import com.tools.constants.ContextConstants;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.TimeConstants;
import com.tools.persistance.MongoReader;

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
		navigate(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();

	}
	
	@Step
	public void performLoginOnWebsite(String userName, String userPass,String website) {
//		navigate(MongoReader.getBaseURL()+"/"+website);
//		navigate(MongoReader.getBaseURL()+"/"+website);
		navigate("https://staging.pippajean.com/"+website);
		navigate("https://staging.pippajean.com/"+website);
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
		waitABit(5000);

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
		navigate(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
		Assert.assertTrue(
				getDriver().getCurrentUrl().contains(EnvironmentConstants.SOAP_URL + ContextConstants.NOT_PREFERED_WEBSITE));
		footerPage().verifyThatFooterWebsiteIsCorrect(ContextConstants.NOT_PREFERED_WEBSITE);
		headerPage().clickAnmeldenButton();
		loginPage().inputUserName(userName);
		loginPage().inputUserPass(userPass);
		loginPage().clickOnLoginButton();
	}

	public void switchToNewestOpenedTab() {
		Set<String> winSet = getDriver().getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		Assert.assertTrue("There is only one tab!", winList.size() > 1);
		String newTab = winList.get(winList.size() - 1);
		getDriver().switchTo().window(newTab);
		getDriver().manage().window().maximize();
	}

	
	public void closeOthersTab(){
		String originalHandle = getDriver().getWindowHandle();

	   
	    for(String handle : getDriver().getWindowHandles()) {
	        if (!handle.equals(originalHandle)) {
	        	getDriver().switchTo().window(handle);
	        	getDriver().close();
	        }
	    }

	    getDriver().switchTo().window(originalHandle);
	}
	@Step
	public void switchBackToPreviousTab() {
		Set<String> winSet = getDriver().getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		Assert.assertTrue("There is only one tab!", winList.size() > 1);
		String previousTab = winList.get(winList.size() - 2);
		getDriver().switchTo().window(previousTab);
		getDriver().manage().window().maximize();
	}
	
	@Step
	public void switchBackToFirstTab() {
		Set<String> winSet = getDriver().getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		Assert.assertTrue("There is only one tab!", winList.size() > 1);
		String firstTab = winList.get(0);
		getDriver().switchTo().window(firstTab);
		getDriver().manage().window().maximize();
	}

	@Step
	public void refresh() {
		waitABit(2000);
		getDriver().navigate().refresh();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void openNewTab() {
		abstractPage().openNewTab();
	}

	public void waitCertainTime(long time) {
		waitABit(time);
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
			System.out.println(getDriver().getTitle());
			if (getDriver().getTitle().trim().contains(frameName)) {
				break;
			}
		}
	}


	@Step
	public void navigate(String URL) {
		// failsafe in case of redirects to live
		if (!URL.contains("www.pippajean.com")) {
			getDriver().manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
			getDriver().get(URL);
			getDriver().manage().window().maximize();
//			JavascriptExecutor executor = (JavascriptExecutor)getDriver();
//			executor.executeScript("document.body.style.zoom = '0.6'");
		}

	}

	@Step
	public void navigateWithoutMaximize(String URL) {
		// failsafe in case of redirects to live
		if (!URL.contains("www.pippajean.com")) {
			// getDriver().manage().timeouts().pageLoadTimeout(3600,
			// TimeUnit.SECONDS);
			getDriver().get(URL);
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

	public ContactListPage contactListPage() {
		return getPages().currentPageAt(ContactListPage.class);
	}

	
	public PartyListBackendPage partyListBackendPage() {
		return getPages().currentPageAt(PartyListBackendPage.class);
	}

	public AddProductsModalPage addProductsModalPage() {
		return getPages().currentPageAt(AddProductsModalPage.class);
	}

	public ProfileNavPage profileNavPage() {
		return getPages().currentPageAt(ProfileNavPage.class);
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

	public ImportExportPage importExportPage(){
		return getPages().currentPageAt(ImportExportPage.class);
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

	public OrderDetailsNavPage orderDetailsNavPage() {
		return getPages().currentPageAt(OrderDetailsNavPage.class);
	}

	public OrderNotificationPage orderNotificationPage() {
		return getPages().currentPageAt(OrderNotificationPage.class);
	}
	
	public BorrowPage borrowPage() {
		return getPages().currentPageAt(BorrowPage.class);
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
	
	public ShopPage shopPage() {
		return getPages().currentPageAt(ShopPage.class);
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

	public SingleSignOnPage singleSignOnPage() {
		return getPages().currentPageAt(SingleSignOnPage.class);
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

	public AvailabilityReportPage availabilityReportPage() {
		return getPages().currentPageAt(AvailabilityReportPage.class);
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
	
	public AcademyPage academyPage() {
		return getPages().currentPageAt(AcademyPage.class);
	}
	
	public AcademyQuizPage academyQuizPage() {
		return getPages().currentPageAt(AcademyQuizPage.class);
	}
	
	public LoginAcademyPage loginAcademyPage() {
		return getPages().currentPageAt(LoginAcademyPage.class);
	}

	public TeamReportPage teamReportPage() {
		return getPages().currentPageAt(TeamReportPage.class);
	}

	// cart
	public CartPage cartPage() {
		return getPages().currentPageAt(CartPage.class);
	}

	public BorrowCartPage borrowCartPage() {
		return getPages().currentPageAt(BorrowCartPage.class);
	}
	
	public StylistInventoryPage stylistInventoryPage() {
		return getPages().currentPageAt(StylistInventoryPage.class);
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

	public ElvPaymentMethodPage elvPaymentMethodPage() {
		return getPages().currentPageAt(ElvPaymentMethodPage.class);
	}

	public SepaPaymentPage sepaPaymentPage() {
		return getPages().currentPageAt(SepaPaymentPage.class);
	}

	public KlarnaPage klarnaPage() {
		return getPages().currentPageAt(KlarnaPage.class);
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

	public ProfileHistoryPage profileHistoryPage() {
		return getPages().currentPageAt(ProfileHistoryPage.class);
	}

	public FacebookRegistrationFormPage facebookRegistrationFormPage() {
		return getPages().currentPageAt(FacebookRegistrationFormPage.class);
	}
	
	public FacebookInvitationPage facebookInvitationPage() {
		return getPages().currentPageAt(FacebookInvitationPage.class);
	}
	
	public FacebookMessagePage facebookMessagePage() {
		return getPages().currentPageAt(FacebookMessagePage.class);
	}
	
	public FacebookLoginPage facebookLoginPage() {
		return getPages().currentPageAt(FacebookLoginPage.class);
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
	public NavisionHomePage navisionHomePage() {
		return getPages().currentPageAt(NavisionHomePage.class);
	}
	
	public NavisionSearchPage navisionSearchPage() {
		return getPages().currentPageAt(NavisionSearchPage.class);
	}
	
	public NavisionSyncDashboardPage navisionSyncDashboardPage() {
		return getPages().currentPageAt(NavisionSyncDashboardPage.class);
	}
	//sos app
	public SosDetailsBackendPage sosDetailsBackendPage() {
		return getPages().currentPageAt(SosDetailsBackendPage.class);
	}
	
	public SosPage sosPage() {
		return getPages().currentPageAt(SosPage.class);
	}
	
	public OnlineStylePartyManagerPage onlineStylePartyManagerPage() {
		return getPages().currentPageAt(OnlineStylePartyManagerPage.class);
	}
	
	public OnlineStylePartyGuestPage onlineStylePartyGuestPage() {
		return getPages().currentPageAt(OnlineStylePartyGuestPage.class);
	}
	
	public OnlineStylePartyStylistPage onlineStylePartyStylistPage() {
		return getPages().currentPageAt(OnlineStylePartyStylistPage.class);
	}
	
	
	
}

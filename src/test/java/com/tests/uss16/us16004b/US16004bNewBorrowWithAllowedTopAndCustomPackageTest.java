package com.tests.uss16.us16004b;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.borrow.BorrowSystemConfigurationSteps;
import com.steps.frontend.ContactDetailsSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyBusinessSteps;
import com.steps.frontend.MyContactsListSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class US16004bNewBorrowWithAllowedTopAndCustomPackageTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public BorrowCartSteps borrowCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public BorrowCartValidationWorkflows borrowCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BorrowSystemConfigurationSteps borrowSystemConfigurationSteps;
	@Steps
	public MyBusinessSteps myBusinessSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public WishlistSteps wishlistSteps;
	@Steps
	ContactDetailsSteps contactDetailsSteps;
	@Steps
	MyContactsListSteps myContactsListSteps;
	
	
	
	public String stylistEmail, stylistPassword;
	public CustomerFormModel customerData;
	public CustomerFormModel stylistData;
	public CustomerFormModel contactData;
	private ProductDetailedModel genProduct1;
//	private ProductDetailedModel genProduct1;
//	private ProductDetailedModel genProduct1;
//	private ProductDetailedModel genProduct1;
	
	private static List<RegularBasicProductModel> productsCartList = new ArrayList<RegularBasicProductModel>();
	private static List<RegularBasicProductModel> productsWishList = new ArrayList<RegularBasicProductModel>();
	private static List<RegularBasicProductModel> grabbedProductsWishList = new ArrayList<RegularBasicProductModel>();
	private static UrlModel urlModel = new UrlModel();
	

	
	@Before
	public void setUp() throws Exception {
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);
		
		productsCartList = MongoReader.grabRegularBasicProductModel("US16003RegularCustomerSetProductsInCartAndWishlist" + "CART");
		productsWishList = MongoReader.grabRegularBasicProductModel("US16003RegularCustomerSetProductsInCartAndWishlist" + "WISH");
		
		urlModel = MongoReader.grabUrlModels("US16003CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).get(0);
		
		
		for (RegularBasicProductModel regularBasicProductModel : productsCartList) {
			System.out.println("Prod From cart Code "+regularBasicProductModel.getProdCode());
		}
		
		for (RegularBasicProductModel regularBasicProductModel : productsWishList) {
			System.out.println("Prod From wish Code "+regularBasicProductModel.getProdCode());
		}
		
		stylistData = MongoReader.grabCustomerFormModels("US16003StyleCoachRegistrationTest").get(0);
		System.out.println("stylist data "+stylistData.getEmailName());
		System.out.println("stylist pass "+stylistData.getPassword());
		
		stylistEmail=stylistData.getEmailName();
		stylistPassword=stylistData.getPassword();
		
		
		customerData = MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").get(0);
		System.out.println("customer data "+customerData.getEmailName());
		System.out.println("customer pass "+customerData.getPassword());
		
		
		contactData= MongoReader.grabCustomerFormModels("US16003AddNewContactToStyleCoachTest").get(0);
		System.out.println("contact data "+contactData.getEmailName());
		System.out.println("contact pass "+contactData.getPassword());
		

		
	}
	

	@Test
	public void us16004bNewBorrowWithAllowedTopAndCustomPackageTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		borrowSystemConfigurationSteps.goToBorrowTab();
		borrowSystemConfigurationSteps.selectDisabledBorrowOption("Nein");
		borrowSystemConfigurationSteps.selectBorrowProcessType("(New process) Allow defined products to be borrowed");
		borrowSystemConfigurationSteps.selectCountries();
		borrowSystemConfigurationSteps.selectProductsForStylistwithExtendedOption();
		borrowSystemConfigurationSteps.saveConfiguration();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		backEndSteps.selectTopStatus("Allow");
		backEndSteps.selectAllowedToBorrow("Custom package");
//	
		
		
		customerRegistrationSteps.performLogin(stylistEmail,stylistPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		
		
		//!!!! must be changed with Ioana version- verify if borrow appears in my business menu - done 
		loungeSteps.checkIfBorrowLinkIsDisplayed(true);
		//activate the cart
		loungeSteps.clickGoToBorrowCart();
		
		//go to mybusiness page and check the link --> done
		loungeSteps.goToMyBusinessFromCart();
		myBusinessSteps.checkIfBorrowCartLinkIsDisplayed(true);
		
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
	
	
//		//go to product details  page and verify if add to cart is not displayed - >done
	
		searchSteps.navigateToProductPage(genProduct1.getSku());

		productSteps.verifyAddToCartButton(false);

//		//add product in wish list  done
		addRegularProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");

	//	wishlistSteps.verifyPresenceOfAddAllToCartButton(false);

		loungeSteps.goToLoungePage();
		loungeSteps.verifyBorrowBlockStatus(ContextConstants.ALLOWED_STATUS);
		//should be added on frontend and also in our  constants file
		//verify top block messages 
		//	loungeSteps.verifyBorrowBlockMessage(ContextConstants.ALLOWED_MESSAGE);
		
		loungeSteps.goToContactsList();
		myContactsListSteps.openContactDetailsPage(customerData.getEmailName());
	 
		//contactDetailsSteps.checkBlockLinesForRegisterContact();
		contactDetailsSteps.checkBlockLinesForContacts();// maybe is not aplicable here and should be deleted 
		
		grabbedProductsWishList=contactDetailsSteps.grabWishlistItems();
		contactDetailsSteps.validateWishlistCounter(productsWishList.size());
		contactDetailsSteps.validateProductWishListBlock(productsWishList, grabbedProductsWishList);
		
		contactDetailsSteps.clickBackToContactsbutton();
		myContactsListSteps.openContactDetailsPage(contactData.getEmailName());
		contactDetailsSteps.checkBlockLinesForContacts();
		//contactDetailsSteps.checkBlockLinesForNotRegisterContact();
		
		//party
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.checkIfAddToBorrowCartButtonIsDisplayed(false);
		
		//check if product are dispalyed 
		
		partyDetailsSteps.checkWishlistSection(productsWishList);
		
		
		
		
	
		
		
		
		
		
		// verify minicart 
		//go to borrow cart 
		
		//verify that default xxx is displayed 
		
		// place borrow order 
		
		//verify block on lounge page 
		
		///todo party wishlist validation 
		
	}
	
}

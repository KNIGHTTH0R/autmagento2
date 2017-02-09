package com.tests.uss16.us16004c;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import com.steps.frontend.ShopSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
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
import com.tools.requirements.Application;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US16.1 SC borrow products Test", type = "Scenarios")
@Story(Application.BorrowCart.US16_1.class)
@RunWith(SerenityRunner.class)
public class US16004cNewBorrowWithDeniedTopAndNotEligibleTest extends BaseTest {

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
	public ShopSteps shopSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	MyContactsListSteps myContactsListSteps;
	@Steps
	ContactDetailsSteps contactDetailsSteps;
	
	
	public String stylistEmail, stylistPassword;
	public CustomerFormModel customerData;
	public CustomerFormModel stylistData;
	public CustomerFormModel contactData;
	

	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	private static UrlModel urlModel = new UrlModel();

	
//	private static List<RegularBasicProductModel> productsCartList = new ArrayList<RegularBasicProductModel>();
	private static List<RegularBasicProductModel> productsWishList = new ArrayList<RegularBasicProductModel>();
	private static List<RegularBasicProductModel> grabbedProductsWishList = new ArrayList<RegularBasicProductModel>();
	
	
	@Before
	public void setUp() throws Exception {
	//	productsCartList = MongoReader.grabRegularBasicProductModel("US16003RegularCustomerSetProductsInCartAndWishlist" + "CART");
		productsWishList = MongoReader.grabRegularBasicProductModel("US16003RegularCustomerSetProductsInCartAndWishlist" + "WISH");
		urlModel = MongoReader.grabUrlModels("US16003CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).get(0);
		
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
	public void us16004bNewBorrowWithDeniedTopAndNotEligibleTest() {
		
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
		backEndSteps.selectTopStatus("Deny");
		backEndSteps.selectAllowedToBorrow("Nicht berechtigt");
		
		
		
		customerRegistrationSteps.performLogin(stylistEmail, stylistPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		
		loungeSteps.checkIfBorrowLinkIsDisplayed(true);
		//loungeSteps.checkIfBorrowBoxIsdisplayed(true);
		System.out.println("status " +ContextConstants.NOT_ELIGIBLE );
		loungeSteps.verifyBorrowBlockStatus(ContextConstants.NOT_ELIGIBLE);
		
		//	loungeSteps.verifyBorrowBlockMessage(ContextConstants.ALLOWED_MESSAGE);
		loungeSteps.goToMyBusiness();
		myBusinessSteps.checkIfBorrowCartLinkIsDisplayed(true);
		
		//go to cart check the message 
		
		loungeSteps.clickGoToBorrowCart();
		borrowCartSteps.verifyErrorMessageInCart(ContextConstants.NOT_ELIGIBLE_MESSAGE);
		borrowCartSteps.checkBorrowCartForNewFunctionality(false,false);
	
		//go to party and check the products 
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.checkIfAddToBorrowCartButtonIsDisplayed(false);
		
		//go to contact details and check 
		
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
		
		headerSteps.goToShop();
		shopSteps.checkIfBorrowLinkIsdisplayed(true);
	}
	

}
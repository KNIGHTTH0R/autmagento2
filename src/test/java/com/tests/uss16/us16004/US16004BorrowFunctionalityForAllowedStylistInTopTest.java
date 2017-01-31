package com.tests.uss16.us16004;

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
import com.tools.constants.SoapKeys;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;


@RunWith(SerenityRunner.class)
public class US16004BorrowFunctionalityForAllowedStylistInTopTest extends BaseTest {

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
	
	public CustomerFormModel customerData;
	public CustomerFormModel contactData;
	private ProductDetailedModel genProduct1;
	
	private static List<RegularBasicProductModel> productsList = new ArrayList<RegularBasicProductModel>();
	@Before
	public void setUp() throws Exception {
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);
		
		productsList = MongoReader.grabRegularBasicProductModel("US16004RegularCustomerOrderTest" + SoapKeys.CALC);
		
		for (RegularBasicProductModel regularBasicProductModel : productsList) {
			System.out.println("Prod Code "+regularBasicProductModel.getProdCode());
		}
		
		customerData = MongoReader.grabCustomerFormModels("US16004StyleCoachRegistrationTest").get(0);
		System.out.println("customer data "+customerData.getEmailName());
		System.out.println("customer pass "+customerData.getPassword());
		contactData= MongoReader.grabCustomerFormModels("US16004AddNewContactToStyleCoachTest").get(0);
		System.out.println("contact data "+contactData.getEmailName());
		System.out.println("contact pass "+contactData.getPassword());
		

		
	}
	

	@Test
	public void us16004BorrowFunctionalityForAllowedStylistInTopTest() {
		
		
		
		
		customerRegistrationSteps.performLogin("emborrow@yopmail.com","emilian1");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		
		// verify if borrow appears in my business menu - done
		loungeSteps.checkIfBorrowLinkIsDisplayed(true);
		loungeSteps.clickGoToBorrowCart();
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
//		
//		
//		//go to product details  page and verify if add to cart is not displayed - >done
		searchSteps.navigateToProductPage(genProduct1.getSku());
		productSteps.verifyAddToCartButton(false);
//		
//		
//		//must create a product -> done
//		//add product in wish list  done
		addRegularProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");
//		//verify add all  to cart buttons are not displayed -> done but is a bug !!!!!!
//		//should verify add to cart on product side ???? 
	//	wishlistSteps.verifyPresenceOfAddAllToCartButton(false);
//		go to lounge
		loungeSteps.goToLoungePage();
		
		
		
		loungeSteps.verifyBorrowBlockStatus(ContextConstants.ALLOWED_STATUS);
	//	loungeSteps.verifyBorrowBlockMessage(ContextConstants.ALLOWED_MESSAGE);
		
		//OTHER TEST: create a customer ....with 2 products in cart
		// add 2 products in wishlist 
		
		//OTHER TESRT: Create a simple contact without customer id
		
		//go to stylist contacts page and search for first contact
		
		
		// block should be visible on contact details page 
		//contactDetailsSteps.checkPresenceOfCustomerInfosBlock(true);
		
		// check each section from block :
		//1.cart items
		//2.wishlist items
		//history
		
		//go to stylist contacts page and search for second contact
		//check that Customer Block is not displayed on this page;
		
		
		
		
		
		
		
		
	
		
		
		
		
		//verify top block messages 
		// verify minicart 
		//go to borrow cart 
		
		//verify that default xxx is displayed 
		
		// place borrow order 
		
		//verify block on lounge page 
		
		///todo party wishlist validation 
		
	}
	
}

package com.tests.uss16.us16003;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.borrow.BorrowSystemConfigurationSteps;
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

@WithTag(name = "US16.3 Allowed SC in Top borrow products using new functionality ", type = "Scenarios")
@Story(Application.BorrowCart.US16_3.class)
@RunWith(SerenityRunner.class)
public class US16003BorrowFunctionalityForAllowedStylistInTopTest extends BaseTest {

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
	
	
	@Test
	public void us16003BorrowFunctionalityForAllowedStylistInTopTest() {
		customerRegistrationSteps.performLogin("emborrow@evozon.com","emilian1");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.clickGoToBorrowCart();
		// verify if borrow appears in my business menu - done
		loungeSteps.checkIfBorrowLinkIsDisplayed();
		
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
		
		
		//go to product details  page and verify if add to cart is not displayed - >in  progress
		searchSteps.navigateToProductPage("skuProdus");
		productSteps.verifyAddToCartButton(false);
		
		
		//must create a product
		//add product in wish list 
		//	addRegularProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");
		//verify add all  to cart buttons are not displayed 
		//should verify add to cart on product side ???? 
		wishlistSteps.verifyPresenceOfAddAllToCartButton(false);
		
		loungeSteps.goToLoungePage();
		
		//go to lounge
		
		
		
	
		
		
		
		
		//verify top block messages 
		
		//go to borrow cart 
		
		//verify that default xxx is displayed 
		
		// place borrow order 
		
		
		
	}
	
}

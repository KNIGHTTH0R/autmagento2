package com.tests.uss16.us16004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import com.steps.frontend.MyContactsListSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.borrowCart.BorrowCartCalculator;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class US16004PlaceBarrowOrderAllowedStylistInTopTest extends BaseTest {

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
	public CheckoutValidationSteps checkoutValidationSteps;
	
	private String username, password;
	private static String billingAddress;
	private static String shippingValue;
	private static String taxClass;
	private CreditCardModel creditCardData = new CreditCardModel();

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	
	
	
	public CustomerFormModel stylistData;
	public String stylistEmail, stylistPassword;
	
	
	@Before
	public void setUp() throws Exception {
		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();
		
		Properties prop = new Properties();
	//	InputStream input = null;
		
		stylistData = MongoReader.grabCustomerFormModels("US16004StyleCoachRegistrationTest").get(0);

		username = stylistData.getEmailName();
		password = stylistData.getPassword();
		
		//should be extracted from stylist 
		billingAddress = "dKRRywdq rDOPSlhx, Filandastraße, 22, 94469 Deggendorf, Deutschland";
		shippingValue = "3.90";
		taxClass = "19";
		
		
	}
	
	@Test
	public void us16004PlaceBarrowOrderAllowedStylistInTopTest() {
		
		customerRegistrationSteps.performLogin("emilian@yopmail.com","emilian1");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		
		loungeSteps.clickGoToBorrowCart();
		
		BorrowProductModel productData;
		
		productData = addBorrowedProductsWorkflow.setBorrowedDefaultProductToCart();
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
//		productData = addBorrowedProductsWorkflow.setXXXBorrowedProductToCart();
//		BorrowCartCalculator.allBorrowedProductsList.add(productData);
		
		productData = addBorrowedProductsWorkflow.setYYYBorrowedProductToCart();
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
		
		// check if the elements of new borrow cart are correct displayed 
		borrowCartSteps.checkBorrowCartForNewFunctionality();
		
		BorrowCartCalculator.calculateCartAndShippingTotals(taxClass, shippingValue);

		headerSteps.openCartPreview();
		//   check the no of items in mini cart ->done
		borrowCartSteps.checkNoOfProductsDisplayedInMiniCart(BorrowCartCalculator.allBorrowedProductsList.size());

		headerSteps.goToCart();

		BorrowDataGrabber.grabbedBorrowCartProductsList = borrowCartSteps.grabProductsDataNewFunctionality();
	
		System.out.println("grabbed list sizwe=" +BorrowDataGrabber.grabbedBorrowCartProductsList.size());
		System.out.println("grabbed list sizwe=" +	BorrowCartCalculator.allBorrowedProductsList.size());
		borrowCartSteps.grabTotals();
		borrowCartSteps.clickGoToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.checkTermsCheckbox();

		shippingSteps.grabBorrowedProductsList();
		shippingSteps.grabSurveyData();
		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.urlModel.setName("Payment URL");
		DataGrabber.urlModel.setUrl(url);
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabProductsList();
		confirmationSteps.grabConfirmationTotals();
		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		
		borrowCartValidationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		
		//should delete what i have created for the new functionality !!! <- discussion with Mihai
		//borrowCartValidationWorkflows.performBorrowNewCartValidations();
		borrowCartValidationWorkflows.performBorrowCartValidations();
		confirmationSteps.agreeAndCheckout();

		checkoutValidationSteps.verifySuccessMessage();

		customVerifications.printErrors();
		
		
		///go to lounge page 
		//check borrow status block/ message 
		

		loungeSteps.clickGoToBorrowCartFromLounge();
		borrowCartSteps.verifyErrorMessageInCart("You cannot borrow a new package until you return the already borrowed one.");
		
		
		
	}
	
	
	
}

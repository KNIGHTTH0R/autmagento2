package com.tests.uss16.us16004b;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.ShopSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.borrowCart.BorrowCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class US16004bPlaceBarrowOrderAllowedTopAndCustomPackageTest extends BaseTest {

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
	@Steps
	public ShopSteps shopSteps;
	
	
	private String username, password;
	private static String billingAddress;
	private static String shippingValue;
	private static String taxClass;

	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	
	
	
	public CustomerFormModel stylistData;
	private AddressModel addressModel;
	public String stylistEmail, stylistPassword;
	
	
	@Before
	public void setUp() throws Exception {
		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();
		
	//	InputStream input = null;
		
		stylistData = MongoReader.grabCustomerFormModels("US16003StyleCoachRegistrationTest").get(0);
		addressModel=MongoReader.grabAddressModels("US16003StyleCoachRegistrationTest").get(0);
		
		username = stylistData.getEmailName();
		password = stylistData.getPassword();
		billingAddress=ShopSteps.retriveAddressAsString(stylistData,addressModel);
		//should be extracted from stylist 
		
		shippingValue = "3.90";
		taxClass = "19";
		
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}
	
	@Test
	public void us16004bPlaceBarrowOrderAllowedTopAndCustomPackageTest() {
		
		customerRegistrationSteps.performLogin(username,password);
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
		borrowCartSteps.checkBorrowCartForNewFunctionality(true,true);
		
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

//		paymentSteps.expandCreditCardForm();
//		paymentSteps.fillCreditCardForm(creditCardData);
		paymentSteps.payWithBankTransfer();
		
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
		
		headerSteps.goToLounge();
		loungeSteps.verifyBorrowBlockStatus(ContextConstants.WAITING_IN_RETURN);
		
		//should be added on frontend and also in our  constants file
		//verify top block messages 
		//	loungeSteps.verifyBorrowBlockMessage(ContextConstants.ALLOWED_MESSAGE);
		
		loungeSteps.clickGoToBorrowCart();
		borrowCartSteps.verifyErrorMessageInCart(ContextConstants.WAITING_MESSAGE);
		
		
	}
	@After
	public void saveData() {
	MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
	
	}
	
	
}

package com.tests.uss16.us16005;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

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
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.borrowCart.BorrowCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;


@RunWith(SerenityRunner.class)
public class US16005PlaceBorrowOrderAllowTopAndCustomPackageTest extends BaseTest {

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

	@Steps
	public ProfileSteps profileSteps;
	
	
	private String username, password;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	
	
	
	public CustomerFormModel stylistData;
	public String stylistEmail, stylistPassword, billingAddress, shippingValue,taxClass;
	
	
	
	
	@Before
	public void setUp() throws Exception {
		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();
		

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_16_FOLDER + File.separator + "us16006.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			billingAddress=prop.getProperty("billingAddress");
			shippingValue = prop.getProperty("shippingPrice");
			taxClass =prop.getProperty("taxClass");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
		
	}
	
	@Test
	public void us16005PlaceBorrowOrderAllowTopAndCustomPackageTest() {
		
		customerRegistrationSteps.performLogin(username,password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		
		loungeSteps.clickGoToBorrowCart();
		
		BorrowProductModel productData;
		
		productData = addBorrowedProductsWorkflow.setBorrowedDefaultProductToCart();
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
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

		paymentSteps.payWithBankTransfer();
		confirmationSteps.grabProductsList();
		confirmationSteps.grabConfirmationTotals();
		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		
		borrowCartValidationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		
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
		
		loungeSteps.clickGoToBorrowCart();
		borrowCartSteps.verifyErrorMessageInCart(ContextConstants.WAITING_MESSAGE);
		
		//verify my order from profile
		headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		String orderPrice = orderHistory.get(0).getTotalPrice();
		profileSteps.verifyOrderId(orderId, DataGrabber.orderModel.getOrderId());
		profileSteps.verifyOrderPrice(orderPrice, DataGrabber.orderModel.getTotalPrice());
		DataGrabber.orderModel = orderHistory.get(0);
		
		
	}
	@After
	public void saveData() {

		MongoWriter.saveBorrowCartCalcDetailsModel(BorrowCartCalculator.borrowCartCalcDetailsModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(BorrowCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(BorrowDataGrabber.borrowCartConfirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (BorrowProductModel product : BorrowCartCalculator.allBorrowedProductsList) {
			MongoWriter.saveBorrowProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}
	
	
}

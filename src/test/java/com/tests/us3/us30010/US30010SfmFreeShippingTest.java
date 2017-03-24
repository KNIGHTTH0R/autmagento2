package com.tests.us3.us30010;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.constants.ConfigConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US3.10 Shop For Myself With Free Shipping ", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_10.class)
@RunWith(SerenityRunner.class)
public class US30010SfmFreeShippingTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ValidationWorkflows validationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private static String billingAddress;
	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String taxClass;
	private CreditCardModel creditCardData = new CreditCardModel();

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();
		
		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		/*genProduct1 = createdProductsList.get(0);
		genProduct2 = createdProductsList.get(1);
		*/
		if(!createdProductsList.isEmpty() && createdProductsList.size()>=2){
			genProduct1 = createdProductsList.get(0);
			genProduct2 = createdProductsList.get(1);
		}else{
			genProduct1 = MagentoProductCalls.createProductModel();
			 genProduct1.setIp("84");
			 genProduct1.setPrice("49.90");
			 MagentoProductCalls.createApiProduct(genProduct1);
			
			 genProduct2 = MagentoProductCalls.createProductModel();
			 genProduct2.setIp("25");
			 genProduct2.setPrice("89.00");
			 MagentoProductCalls.createApiProduct(genProduct2);
			
			
		}

//		genProduct1 = MagentoProductCalls.createProductModel();
//		genProduct1.setPrice("49.00");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createProductModel();
//		genProduct2.setPrice("89.00");
//		MagentoProductCalls.createApiProduct(genProduct2);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us30010.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			billingAddress = prop.getProperty("billingAddress");
			jewelryDiscount = prop.getProperty("jewelryDiscount");
			marketingDiscount = prop.getProperty("marketingDiscount");
			shippingValue = prop.getProperty("shippingPrice");
			taxClass = prop.getProperty("taxClass");

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

	}

	@Test
	public void us30010SfmFreeShippingTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();
		BasicProductModel productData;

		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0",ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		CartCalculator.calculateJMDiscounts(jewelryDiscount, marketingDiscount, taxClass, shippingValue);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.grabTotals();
		cartSteps.goToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.grabSurveyData();
		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabConfirmationTotals();

		confirmationSteps.agreeAndCheckout();

		validationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		validationWorkflows.performCartValidationsTotals();

		customVerifications.printErrors();
	}

}
package com.tests.us3.us3001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.CreateProduct;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US3", type = "frontend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US3001Test extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public ValidationWorkflows validationWorkflows;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CustomVerification customVerifications;


	private CreditCardModel creditCardData = new CreditCardModel();
	
	// Test data - from property file
	private String username, password;
	// Test data fields
	private static String jewelryDisount;
	private static String marketingDisount;
	private static String shippingValue;
	private static String taxClass;
	private static String addressString;
	// Test data Credit card details
	private static String cardNumber;
	private static String cardName;
	private static String cardMonth;
	private static String cardYear;
	private static String cardCVC;
	
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		genProduct1 = CreateProduct.createProductModel();	
		genProduct1.setIp("84");
		genProduct1.setPrice("49.90");
		CreateProduct.createApiProduct(genProduct1);
		
		genProduct2 = CreateProduct.createProductModel();		
		genProduct2.setIp("25");
		genProduct2.setPrice("89.00");
		CreateProduct.createApiProduct(genProduct2);
		
		genProduct3 = CreateProduct.createMarketingProductModel();
		genProduct3.setIp("0");
		genProduct3.setPrice("229.00");
		CreateProduct.createApiProduct(genProduct3);
		
//		genProduct1.setName("CORALIE SET (ROSÉ)");
//		genProduct1.setSku("K024RO");
//		genProduct1.setIp("84");
//		genProduct1.setPrice("99.00");
//		genProduct2.setName("DAMARIS RING");
//		genProduct2.setSku("R025WT");
//		genProduct2.setIp("25");
//		genProduct2.setPrice("29.90");
//		genProduct3.setName("STYLE BOOK HERBST / WINTER 2014 (270 STK)");
//		genProduct3.setSku("M101");
//		genProduct3.setIp("0");
//		genProduct3.setPrice("229.00");

		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			jewelryDisount = prop.getProperty("jewelryDisount");
			marketingDisount = prop.getProperty("marketingDisount");
			addressString = prop.getProperty("addressString");
			shippingValue = prop.getProperty("shippingPrice");
			taxClass = prop.getProperty("taxClass");

			cardNumber = prop.getProperty("cardNumber");
			cardName = prop.getProperty("cardName");
			cardMonth = prop.getProperty("cardMonth");
			cardYear = prop.getProperty("cardYear");
			cardCVC = prop.getProperty("cardCVC");

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

		creditCardData.setCardNumber(cardNumber);
		creditCardData.setCardName(cardName);
		creditCardData.setMonthExpiration(cardMonth);
		creditCardData.setYearExpiration(cardYear);
		creditCardData.setCvcNumber(cardCVC);
		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
	}

	@Test
	public void us3001CartSegmentationWithVatTest() {
		frontEndSteps.performLogin(username, password);
		frontEndSteps.wipeCart();
		BasicProductModel productData;		
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "2", "0",ConfigConstants.DISCOUNT_25);
		CartCalculator.productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0",ConfigConstants.DISCOUNT_25);
		CartCalculator.productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0",ConfigConstants.DISCOUNT_0);
		CartCalculator.productsListMarketing.add(productData);
//		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "2", "17",ConfigConstants.DISCOUNT_25);
//		CartCalculator.productsList25.add(productData);
//		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "16",ConfigConstants.DISCOUNT_25);
//		CartCalculator.productsList25.add(productData);
//		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0",ConfigConstants.DISCOUNT_0);
//		CartCalculator.productsListMarketing.add(productData);
		CartCalculator.calculateJMDiscounts(jewelryDisount, marketingDisount, taxClass, shippingValue);	

		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
		DataGrabber.cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		cartSteps.typeJewerlyBonus(jewelryDisount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDisount);
		cartSteps.updateMarketingBonus();
		
		DataGrabber.cartProductsWith50DiscountDiscounted = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25DiscountDiscounted = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProductsDiscounted = cartSteps.grabMarketingMaterialProductsData();			
		
		cartSteps.grabTotals();
		cartSteps.clickGoToShipping();
		
		CartCalculator.calculateShippingWith19PercentRemoved(shippingValue);

		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);	
		shippingSteps.refresh();
		shippingSteps.grabProductsList();
		shippingSteps.grabSurveyData();
		shippingSteps.clickGoToPaymentMethod();
		
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

		confirmationSteps.agreeAndCheckout();
		validationWorkflows.setBillingShippingAddress(addressString, addressString);
		validationWorkflows.performCartValidations119Vat();
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCalcDetailsModel(CartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(DataGrabber.confirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (BasicProductModel product : CartCalculator.allProductsList) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

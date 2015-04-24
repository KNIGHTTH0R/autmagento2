package com.tests.us2copy;

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
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
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
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US2", type = "frontend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US002CopyCartSegmentationLogicTest extends BaseTest {

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
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CustomVerification customVerifications;

	private CreditCardModel creditCardData = new CreditCardModel();

	private String username, password;


	// Test data Credit card details
	private static String cardNumber;
	private static String cardName;
	private static String cardMonth;
	private static String cardYear;
	private static String cardCVC;

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private ProductDetailedModel genProduct4;
	private ProductDetailedModel genProduct5;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();
		Properties prop = new Properties();
		InputStream input = null;

		genProduct1 = CreateProduct.createProductModel();
		genProduct1.setPrice("129.00");
		CreateProduct.createApiProduct(genProduct1);

		genProduct2 = CreateProduct.createProductModel();
		genProduct2.setPrice("79.00");
		CreateProduct.createApiProduct(genProduct2);

		genProduct3 = CreateProduct.createProductModel();
		genProduct3.setPrice("84.00");
		CreateProduct.createApiProduct(genProduct3);
		
		genProduct4 = CreateProduct.createMarketingProductModel();
		genProduct4.setPrice("5.00");
		CreateProduct.createApiProduct(genProduct4);
		
		genProduct5 = CreateProduct.createMarketingProductModel();
		genProduct5.setPrice("229.00");
		CreateProduct.createApiProduct(genProduct5);

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us2" + File.separator + "us002.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
	}

	@Test
	public void us002CartSegmentationLogicTest() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		frontEndSteps.wipeCart();
		BasicProductModel productData;
		BasicProductModel productData2;
		
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0",ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		productData2 = addProductsWorkflow.updateBasicProductToCart(genProduct1, "1", "0",ConfigConstants.DISCOUNT_25);
		CartCalculator.productsList25.add(productData2);
	
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "2", "0",ConfigConstants.DISCOUNT_50);		
		productData2 = addProductsWorkflow.updateBasicProductToCart(genProduct2, "1", "0",ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData2);
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0",ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct4, "2", "0",ConfigConstants.DISCOUNT_0);
		CartCalculator.productsListMarketing.add(productData);
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct5, "1", "0",ConfigConstants.DISCOUNT_0);
		CartCalculator.productsListMarketing.add(productData);
		CartCalculator.calculateJMDiscounts("0", "0", "19", "0");


		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
		cartSteps.updateProductQuantityIn50DiscountArea("2", genProduct1.getSku());
		cartSteps.updateProductQuantityIn50DiscountArea("0", genProduct2.getSku());
		cartSteps.updateCart();
		
		DataGrabber.cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();
	
		
		cartSteps.grabTotals();
		cartSteps.clickGoToShipping();
		
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

		validationWorkflows.performSimpleCartValidations();
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {

		MongoWriter.saveCalcDetailsModel(CartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(DataGrabber.confirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (BasicProductModel product : CartCalculator.allProductsListRecalculated) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}

	}

}

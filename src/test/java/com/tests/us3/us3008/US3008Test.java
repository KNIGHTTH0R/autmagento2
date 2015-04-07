package com.tests.us3.us3008;

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
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US3008", type = "frontend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US3 extends BaseTest {
	
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
	public ValidationWorkflows validationWorkflows;
	@Steps 
	public CustomVerification customVerifications;
	
	private String username, password;
	private static String billingAddress;
	private static String shippingAddress;
	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String taxClass;
	private CreditCardModel creditCardData = new CreditCardModel();
	
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	
	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();
		
		genProduct1 = CreateProduct.createProductModel();		
		genProduct1.setPrice("49.90");
		CreateProduct.createApiProduct(genProduct1);
		
		genProduct2 = CreateProduct.createProductModel();		
		genProduct2.setPrice("89.00");
		CreateProduct.createApiProduct(genProduct2);
		
		genProduct3 = CreateProduct.createMarketingProductModel();
		genProduct3.setPrice("229.00");
		CreateProduct.createApiProduct(genProduct3);
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us3" + File.separator + "us3008.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			billingAddress = prop.getProperty("billingAddress");
			shippingAddress = prop.getProperty("shippingAddress");
			jewelryDiscount = prop.getProperty("jewelryDiscount");
			marketingDiscount = prop.getProperty("marketingDiscount");
			shippingValue = prop.getProperty("shippingPrice");
			taxClass = prop.getProperty("taxClass");
			
			creditCardData.setCardNumber(prop.getProperty("cardNumber"));
			creditCardData.setCardName(prop.getProperty("cardName"));
			creditCardData.setMonthExpiration(prop.getProperty("cardMonth"));
			creditCardData.setYearExpiration(prop.getProperty("cardYear"));
			creditCardData.setCvcNumber(prop.getProperty("cardCVC"));

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);
	}

	@Test
	public void us3008CartSegmentationWithVatAndSmbBillingDeShippingAtTest() {
		customerRegistrationSteps.performLogin(username, password);
		customerRegistrationSteps.wipeCart();
		BasicProductModel productData;
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0",Constants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0",Constants.DISCOUNT_25);
		CartCalculator.productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0",Constants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "2", "0",Constants.DISCOUNT_0);
		CartCalculator.productsListMarketing.add(productData);
		

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		DataGrabber.cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		cartSteps.typeJewerlyBonus(jewelryDiscount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDiscount);
		cartSteps.updateMarketingBonus();
		
		CartCalculator.calculateJMDiscounts(jewelryDiscount, marketingDiscount, taxClass, shippingValue);
		
		DataGrabber.cartProductsWith50DiscountDiscounted = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25DiscountDiscounted = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProductsDiscounted = cartSteps.grabMarketingMaterialProductsData();			
		cartSteps.grabTotals();
		cartSteps.clickGoToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(false);
		shippingSteps.selectShippingAddress(shippingAddress);
		
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
		
		validationWorkflows.setBillingShippingAddress(billingAddress, shippingAddress);
		validationWorkflows.performCartValidations();
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCalcDetailsModel(CartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + Constants.CALC);
		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel, getClass().getSimpleName() + Constants.CALC);
		MongoWriter.saveShippingModel(DataGrabber.confirmationTotals, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + Constants.GRAB);
		for (BasicProductModel product : CartCalculator.allProductsListRecalculated) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + Constants.GRAB);
		}
	}	

}

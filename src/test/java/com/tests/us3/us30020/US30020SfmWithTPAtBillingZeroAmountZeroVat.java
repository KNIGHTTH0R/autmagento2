package com.tests.us3.us30020;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
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
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class US30020SfmWithTPAtBillingZeroAmountZeroVat extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public ValidationWorkflows validationWorkflows;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
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

	private String username, password;
	// Test data fields
	private static String jewelryDisount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String taxClass;
	private static String addressString;

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();

	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		productsList = MongoReader.grabBasicProductModel("US30019BuyProductsWithTPForTheFirstTimeTest" + SoapKeys.GRAB);
		
		System.out.println("list size "+productsList.size());
		

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setIp("25");
		genProduct1.setPrice("49.00");
		genProduct1.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getFirstDayOfNextMonth("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2= MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct2.setName(productsList.get(0).getName());
		genProduct2.setSku(productsList.get(0).getProdCode());
		genProduct2.setPrice(productsList.get(0).getUnitPrice());
		genProduct2.setIp("25");
	//	MagentoProductCalls.updateApiProduct(genProduct2, incrementId);
	
		
		
		genProduct3 = MagentoProductCalls.createMarketingProductModel();
		genProduct3.setPrice("229.00");
		genProduct3.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
			prop.load(input);

			username = prop.getProperty("username");
			password = prop.getProperty("password");
			jewelryDisount = "138";
			marketingDiscount = "229";
			addressString = prop.getProperty("addressString");
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
	}

	@Test
	public void us30020SfmWithTPAtBillingZeroAmountZeroVat() throws ParseException {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();
		BasicProductModel productData;

		
		String deliveryTP2 = DateUtils.getFirstFridayAfterDate(
				DateUtils.addDaysToAAGivenDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 7),
				"yyyy-MM-dd");
		System.out.println("ssss434343ss");
		System.out.println("ce masa "+genProduct2.getStockData().getEarliestAvailability());
		String deliveryTP1 = DateUtils.getFirstFridayAfterDate(
				DateUtils.addDaysToAAGivenDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 14),
				"yyyy-MM-dd");

		System.out.println("ssssss");
		String deliveryTP3 = DateUtils.getFirstFridayAfterDate(
				DateUtils.addDaysToAAGivenDate(genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 7),
				"yyyy-MM-dd");

		
		System.out.println("sunt aici");
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(deliveryTP2);
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp1.add(productData);

		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0", ConfigConstants.DISCOUNT_25);
		if (!genProduct2.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(deliveryTP1);
		CartCalculator.productsList25.add(productData);
		CartCalculator.productsListTp0.add(productData);

		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0", ConfigConstants.DISCOUNT_0);
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(deliveryTP3);

		CartCalculator.productsListMarketing.add(productData);
		CartCalculator.productsListTp2.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		DataGrabber.cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		cartSteps.goToShipping();
		
		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);
		
		shippingSteps.goToPaymentMethod();
		
		String orderId = FormatterUtils.getOrderId(shippingSteps.grabUrl());
		DataGrabber.orderModel.setOrderId(FormatterUtils.incrementSingleTpOrderId(orderId, 1));
		DataGrabber.orderModelTp1.setOrderId(FormatterUtils.incrementSingleTpOrderId(orderId, 2));
		DataGrabber.orderModelTp2.setOrderId(FormatterUtils.incrementSingleTpOrderId(orderId, 3));
		
		
		
		paymentSteps.goBack();
		shippingSteps.goBack();
		
		cartSteps.typeJewerlyBonus(jewelryDisount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDiscount);
		cartSteps.updateMarketingBonus();
		
		CartCalculator.calculateJMDiscountsTP(jewelryDisount, marketingDiscount, taxClass, shippingValue);

		DataGrabber.cartProductsWith50DiscountDiscounted = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25DiscountDiscounted = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProductsDiscounted = cartSteps.grabMarketingMaterialProductsData();
	

		System.out.println("am ajuns aici");
		
		
		cartSteps.grabTotals();
		cartSteps.goToShipping();
		
		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);

		CartCalculator.shippingCalculatedModeTP0 = CartCalculator
				.calculateShippingWith19PercentRemovedForTp(CartCalculator.calculatedTotalsDiscountsTp0, "0");
		CartCalculator.shippingCalculatedModeTP1 = CartCalculator
				.calculateShippingWith19PercentRemovedForTp(CartCalculator.calculatedTotalsDiscountsTp1, "0");
		CartCalculator.shippingCalculatedModeTP2 =CartCalculator
				.calculateShippingWith19PercentRemovedForTp(CartCalculator.calculatedTotalsDiscountsTp2, "0");
		
		CartCalculator.productsListTp0=CartCalculator.calculatedShippingForAllProductsTP(CartCalculator.productsListTp0);
		CartCalculator.productsListTp1=CartCalculator.calculatedShippingForAllProductsTP(CartCalculator.productsListTp1);
		CartCalculator.productsListTp2=CartCalculator.calculatedShippingForAllProductsTP(CartCalculator.productsListTp2);
		
		shippingSteps.grabSFMProductsListTp0();
		shippingSteps.grabSFMProductsListTp1();
		shippingSteps.grabSFMProductsListTp2();

		// DataGrabber
		DataGrabber.sfmShippingTotalsTp0 = shippingSteps.grabSurveyDataTp0();
		DataGrabber.sfmShippingTotalsTp1 = shippingSteps.grabSurveyDataTp1();
		DataGrabber.sfmShippingTotalsTp2 = shippingSteps.grabSurveyDataTp2();

		shippingSteps.goToPaymentMethod();


		confirmationSteps.grabSFMProductsListTp0();
		confirmationSteps.grabSFMProductsListTp1();
		confirmationSteps.grabSFMProductsListTp2();

		DataGrabber.sfmConfirmationTotalsTp0 = confirmationSteps.grabConfirmationTotalsTp0();
		DataGrabber.sfmConfirmationTotalsTp1 = confirmationSteps.grabConfirmationTotalsTp1();
		DataGrabber.sfmConfirmationTotalsTp2 = confirmationSteps.grabConfirmationTotalsTp2();

		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		validationWorkflows.setBillingShippingAddress(addressString, addressString);
		validationWorkflows.performCartValidationsZeroAmountTP();

		customVerifications.printErrors();


	}

	@After
	public void saveData() {
//		MongoWriter.saveCalcDetailsModel(CartCalculator.calculatedTotalsDiscounts,
//				getClass().getSimpleName() + SoapKeys.CALC);
//		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel,
//				getClass().getSimpleName() + SoapKeys.CALC);
//		MongoWriter.saveShippingModel(DataGrabber.confirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
//		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
//		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
//		for (BasicProductModel product : CartCalculator.allProductsList) {
//			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
//		}
	}

}

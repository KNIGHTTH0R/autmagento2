package com.tests.uss31.uss31008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.constants.ConfigConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.HostDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US31.1 TP execution cron - Automated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_2.class)
@RunWith(SerenityRunner.class)
public class US31008SfmWithTpTestSemiAutomatedCronTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
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
	public GeneralCartSteps generalCartSteps;
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
	@Steps
	public RegularUserCartSteps regularUserCartSteps;

	private String username, password;
	

	private CreditCardModel creditCardData = new CreditCardModel();
	private String prod1IncrementId;
	private String prod2IncrementId;
	private String prod3IncrementId;
	private String prod4IncrementId;
	private String prod5IncrementId;
	private String prod6IncrementId;
	
	private static String billingAddress;
	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String taxClass;
	
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private ProductDetailedModel genProduct4;
	private ProductDetailedModel genProduct5;
	private ProductDetailedModel genProduct6;
	
	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	OrderModel orderModelListTp1 = new OrderModel();

	@Before
	public void setUp() throws ParseException {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();
		
		productsList = MongoReader.grabBasicProductModel("US31005BuyProductsWithTPForTheFirstTimeTest" + SoapKeys.GRAB);
		

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.setPrice("10.00");
		genProduct1.setIp("8");
		prod1IncrementId = MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("29.90");
		genProduct2.setIp("25");
		genProduct2.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		prod2IncrementId = MagentoProductCalls.createApiProduct(genProduct2);
		
		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("30.00");
		genProduct3.setIp("25");
		genProduct3.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"),"yyyy-MM-dd",7)));
		prod3IncrementId = MagentoProductCalls.createApiProduct(genProduct3);
		
		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("40.00");
		genProduct4.setIp("25");
		genProduct4.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"),"yyyy-MM-dd",14)));
		prod4IncrementId = MagentoProductCalls.createApiProduct(genProduct4);
		
		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("50.00");
		genProduct5.setIp("25");
		genProduct5.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"),"yyyy-MM-dd",21)));
		prod5IncrementId = MagentoProductCalls.createApiProduct(genProduct5);
		
		
		
		genProduct6 = MagentoProductCalls.createMarketingProductModel();
		genProduct6.setPrice("60.00");
		genProduct6.setIp("25");
		genProduct6.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"),"yyyy-MM-dd",28)));
		prod6IncrementId = MagentoProductCalls.createApiProduct(genProduct6);
		
	
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3007.properties");
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


		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP1");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP2");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP3");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP4");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP5");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP6");

	}
	@Test
	public void us31008SfmWithTpTestSemiAutomatedCronTest() throws ParseException {
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
	
		String deliveryTP1 = DateUtils.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd");
		
		System.out.println("deliveryTP1"+ deliveryTP1);
		String deliveryTP2 = DateUtils.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd");
		System.out.println("deliveryTP2"+ deliveryTP2);
		String deliveryTP3 = DateUtils.getFirstFridayAfterDate(genProduct3.getStockData().getEarliestAvailability(),"yyyy-MM-dd");
		String deliveryTP4 = DateUtils.getFirstFridayAfterDate(genProduct4.getStockData().getEarliestAvailability(),"yyyy-MM-dd");
		String deliveryTP5 = DateUtils.getFirstFridayAfterDate(genProduct5.getStockData().getEarliestAvailability(),"yyyy-MM-dd");
		String deliveryTP6 = DateUtils.getFirstFridayAfterDate(genProduct6.getStockData().getEarliestAvailability(),"yyyy-MM-dd");
		
		System.out.println("deliveryTP3"+ deliveryTP3);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp0.add(productData);

		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct2.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp1.add(productData);


		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp2.add(productData);
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct4, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct4.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct4.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp3.add(productData);
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct5, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct5.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct5.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp4.add(productData);
		
		productData = addProductsWorkflow.setBasicProductToCart(genProduct6, "1", "0", ConfigConstants.DISCOUNT_0);
		if (!genProduct6.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct6.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsListMarketing.add(productData);
		CartCalculator.productsListTp5.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
		
		cartSteps.typeJewerlyBonus(jewelryDiscount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDiscount);
		cartSteps.updateMarketingBonus();
		
		String deliveryTP1Locale = DateUtils.parseDate(deliveryTP1, "yyyy-MM-dd", "dd. MMM. yy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		String deliveryTP2Locale = DateUtils.parseDate(deliveryTP2, "yyyy-MM-dd", "dd. MMM. yy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		
		String deliveryTP3Locale = DateUtils.parseDate(deliveryTP3, "yyyy-MM-dd", "dd. MMM. yy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		
		String deliveryTP4Locale = DateUtils.parseDate(deliveryTP4, "yyyy-MM-dd", "dd. MMM. yy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		
		String deliveryTP5Locale = DateUtils.parseDate(deliveryTP5, "yyyy-MM-dd", "dd. MMM. yy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		
		String deliveryTP6Locale = DateUtils.parseDate(deliveryTP6, "yyyy-MM-dd", "dd. MMM. yy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		
		
		
		
		cartSteps.goToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);
		
		shippingSteps.goToPaymentMethod();
		
		String orderId = FormatterUtils.getOrderId(shippingSteps.grabUrl());
		DataGrabber.orderModelTp1.setOrderId(FormatterUtils.incrementOrderId(orderId, 1));
		DataGrabber.orderModelTp1.setDeliveryDate(deliveryTP1Locale);
		
		DataGrabber.orderModelTp2.setOrderId(FormatterUtils.incrementOrderId(orderId, 2));
		DataGrabber.orderModelTp2.setDeliveryDate(deliveryTP2Locale);
		
		DataGrabber.orderModelTp3.setOrderId(FormatterUtils.incrementOrderId(orderId, 3));
		DataGrabber.orderModelTp3.setDeliveryDate(deliveryTP3Locale);
		
		DataGrabber.orderModelTp4.setOrderId(FormatterUtils.incrementOrderId(orderId, 4));
		DataGrabber.orderModelTp4.setDeliveryDate(deliveryTP4Locale);
		
		DataGrabber.orderModelTp5.setOrderId(FormatterUtils.incrementOrderId(orderId, 5));
		DataGrabber.orderModelTp5.setDeliveryDate(deliveryTP5Locale);
		
		DataGrabber.orderModelTp6.setOrderId(FormatterUtils.incrementOrderId(orderId, 6));
		DataGrabber.orderModelTp6.setDeliveryDate(deliveryTP6Locale);
		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();

		customVerifications.printErrors();
	}
	
	@After
	public void saveData() throws Exception {

		// ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL
		// + HostDataGrabber.orderModelTp1.getOrderId() +
		// EnvironmentConstants.JOB_TOKEN);
		// backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		// ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
		System.out.println("F 0");
		MongoWriter.saveOrderModel(DataGrabber.orderModelTp1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveOrderModel(DataGrabber.orderModelTp2, getClass().getSimpleName() + "TP2");
		MongoWriter.saveOrderModel(DataGrabber.orderModelTp3, getClass().getSimpleName() + "TP3");
		MongoWriter.saveOrderModel(DataGrabber.orderModelTp4, getClass().getSimpleName() + "TP4");
		MongoWriter.saveOrderModel(DataGrabber.orderModelTp5, getClass().getSimpleName() + "TP5");
		MongoWriter.saveOrderModel(DataGrabber.orderModelTp6, getClass().getSimpleName() + "TP6");

		System.out.println("F 1");
		MongoWriter.saveProductDetailedModel(genProduct1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveProductDetailedModel(genProduct2, getClass().getSimpleName() + "TP2");
		MongoWriter.saveProductDetailedModel(genProduct3, getClass().getSimpleName() + "TP3");
		MongoWriter.saveProductDetailedModel(genProduct4, getClass().getSimpleName() + "TP4");
		MongoWriter.saveProductDetailedModel(genProduct5, getClass().getSimpleName() + "TP5");
		MongoWriter.saveProductDetailedModel(genProduct6, getClass().getSimpleName() + "TP6");
		
		
		System.out.println("F 2");
		
		System.out.println("1 " +CartCalculator.productsListTp0);
		System.out.println("2 " +CartCalculator.productsListTp1);
		System.out.println("3 " +CartCalculator.productsListTp2);
		System.out.println("4 " +CartCalculator.productsListTp3);
		System.out.println("5 " +CartCalculator.productsListTp4);
		System.out.println("6 " +CartCalculator.productsListTp5);
		
		for (BasicProductModel product : CartCalculator.productsListTp0) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP1");
		}
		
		for (BasicProductModel product : CartCalculator.productsListTp1) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP2");
		}
		
		for (BasicProductModel product : CartCalculator.productsListTp2) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP3");
		}
		
		for (BasicProductModel product : CartCalculator.productsListTp3) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP4");
		}
		
		for (BasicProductModel product : CartCalculator.productsListTp4) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP5");
		}
		
		for (BasicProductModel product : CartCalculator.productsListTp5) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP6");
		}
		
		System.out.println("F 3");
		MongoWriter.saveIncrementId(prod1IncrementId, getClass().getSimpleName() + "TP1");
		MongoWriter.saveIncrementId(prod2IncrementId, getClass().getSimpleName() + "TP2");
		MongoWriter.saveIncrementId(prod3IncrementId, getClass().getSimpleName() + "TP3");
		MongoWriter.saveIncrementId(prod4IncrementId, getClass().getSimpleName() + "TP4");
		MongoWriter.saveIncrementId(prod5IncrementId, getClass().getSimpleName() + "TP5");
		MongoWriter.saveIncrementId(prod6IncrementId, getClass().getSimpleName() + "TP6");

		// orderModelListTp1 =
		// MongoReader.getOrderModel("US31003PartyHostBuysForCustomerTpTest" +
		// "TP1").get(0);
		// ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL
		// + orderModelListTp1.getOrderId()
		// + EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME,
		// EnvironmentConstants.PASSWORD);
		// backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		// ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT,
		// EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);

	}
}
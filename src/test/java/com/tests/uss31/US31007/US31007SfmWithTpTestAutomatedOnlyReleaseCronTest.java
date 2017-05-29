package com.tests.uss31.US31007;

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
public class US31007SfmWithTpTestAutomatedOnlyReleaseCronTest extends BaseTest {

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
	
	
	private static String billingAddress;
	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String taxClass;
	
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	
	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	OrderModel orderModelListTp1 = new OrderModel();

	@Before
	public void setUp() throws ParseException {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();
		
		productsList = MongoReader.grabBasicProductModel("US31007BuyProductsWithTPForTheFirstTimeTest" + SoapKeys.GRAB);
		

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.setPrice("10.00");
		genProduct1.setIp("8");
		prod1IncrementId = MagentoProductCalls.createApiProduct(genProduct1);
		//orderModelListTp1 = MongoReader.getOrderModel("US31002PartyHostBuysForCustomerTpTest" + "TP1").get(0);

		genProduct2= MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct2.setName(productsList.get(0).getName());
		genProduct2.setSku(productsList.get(0).getProdCode());
		genProduct2.setPrice(productsList.get(0).getUnitPrice());
		genProduct1.setIp(productsList.get(0).getPriceIP());
		prod2IncrementId = MongoReader.grabIncrementId("US31007BuyProductsWithTPForTheFirstTimeTest");
		
		genProduct3 = MagentoProductCalls.createMarketingProductModel();
		genProduct3.setPrice("74.00");
		genProduct3.setIp("25");
		genProduct3.setStockData(MagentoProductCalls.createNotAvailableYetStockData(
				DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"), "yyyy-MM-dd", 14)));
		prod3IncrementId = MagentoProductCalls.createApiProduct(genProduct3);
		
	
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

	}
	@Test
	public void us31007SfmWithTpTestAutomatedOnlyReleaseCronTest() throws ParseException {
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
		String deliveryTP1 = DateUtils.getFirstFridayAfterDate(
				DateUtils.addDaysToAAGivenDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 7),
				"yyyy-MM-dd");
		
		System.out.println("deliveryTP1"+ deliveryTP1);
		String deliveryTP2 = DateUtils.getFirstFridayAfterDate(
				DateUtils.addDaysToAAGivenDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 14),
				"yyyy-MM-dd");
		System.out.println("deliveryTP2"+ deliveryTP2);
		String deliveryTP3 = DateUtils.getFirstFridayAfterDate(
				DateUtils.addDaysToAAGivenDate(genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 7),
				"yyyy-MM-dd");

		
		
		
		System.out.println("deliveryTP3"+ deliveryTP3);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList50.add(productData);
		CartCalculator.productsListTp0.add(productData);

		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "4", "0", ConfigConstants.DISCOUNT_25);
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsList25.add(productData);
		CartCalculator.productsListTp1.add(productData);


		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "2", "0", ConfigConstants.DISCOUNT_0);
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		CartCalculator.productsListMarketing.add(productData);
		CartCalculator.productsListTp2.add(productData);

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
		
		cartSteps.selectDeliveryDate(genProduct1.getSku(), deliveryTP1Locale, "50-table");
		cartSteps.selectDeliveryDate(genProduct2.getSku(), deliveryTP2Locale, "25-table");
		cartSteps.selectDeliveryDate(genProduct3.getSku(), deliveryTP3Locale, "table-marketing-material");
		
		
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

		System.out.println("F 1");
		MongoWriter.saveProductDetailedModel(genProduct1, getClass().getSimpleName() + "TP1");
		System.out.println("F 1.1");
		
		System.out.println("s1 simpl " +genProduct1);
		MongoWriter.saveProductDetailedModel(genProduct2, getClass().getSimpleName() + "TP2");
		System.out.println("F 1.2");
		System.out.println("m1 mark " +genProduct3);
		MongoWriter.saveProductDetailedModel(genProduct3, getClass().getSimpleName() + "TP3");
		
		System.out.println("F 2");
		
		System.out.println("1 " +CartCalculator.productsListTp0);
		System.out.println("2 " +CartCalculator.productsListTp1);
		System.out.println("3 " +CartCalculator.productsListTp2);
		
		for (BasicProductModel product : CartCalculator.productsListTp0) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP1");
		}
		
		System.out.println("simplu "+CartCalculator.productsListTp2);
		for (BasicProductModel product : CartCalculator.productsListTp1) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP2");
		}
		
		System.out.println("marketing "+CartCalculator.productsListTp2);
		for (BasicProductModel product : CartCalculator.productsListTp2) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + "TP3");
		}
		
		
		System.out.println("F 3");
		MongoWriter.saveIncrementId(prod1IncrementId, getClass().getSimpleName() + "TP1");
		MongoWriter.saveIncrementId(prod2IncrementId, getClass().getSimpleName() + "TP2");
		MongoWriter.saveIncrementId(prod3IncrementId, getClass().getSimpleName() + "TP3");

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
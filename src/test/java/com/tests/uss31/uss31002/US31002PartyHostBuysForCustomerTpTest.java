package com.tests.uss31.uss31002;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Locale;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

@WithTag(name = "US11.7 Party Host Buys For Customer With Term Purchase Test", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_7.class)
@RunWith(SerenityRunner.class)
public class US31002PartyHostBuysForCustomerTpTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public HostCartValidationWorkflows hostCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private String username, password, customerName;
	private String country;
	private String plz;
	private String voucherCode;
	private CreditCardModel creditCardData = new CreditCardModel();
	private static UrlModel urlModel = new UrlModel();
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private ProductDetailedModel genProduct4;
	private ProductDetailedModel genProduct5;
	private String prod1IncrementId;
	private String prod2IncrementId;
	private String prod3IncrementId;
	private String prod4IncrementId;

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

		genProduct2 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct2.setPrice("10.00");
		genProduct2.setIp("8");
		prod1IncrementId = MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("29.90");
		genProduct3.setIp("25");
		genProduct3.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		prod2IncrementId = MagentoProductCalls.createApiProduct(genProduct3);

		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("30.00");
		genProduct4.setIp("25");
		genProduct4.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"), "yyyy-MM-dd", 7)));
		prod3IncrementId = MagentoProductCalls.createApiProduct(genProduct4);

		genProduct5 = MagentoProductCalls.createProductModel();
		genProduct5.setPrice("40.00");
		genProduct5.setIp("25");
		genProduct5.setStockData(MagentoProductCalls.createNotAvailableYetStockData(DateUtils.addDaysToAAGivenDate(DateUtils.getNextMonthMiddle("yyyy-MM-dd"), "yyyy-MM-dd", 14)));
		prod4IncrementId = MagentoProductCalls.createApiProduct(genProduct5);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss11" + File.separator + "us11007.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			customerName = prop.getProperty("customerName");

			country = prop.getProperty("country");
			plz = prop.getProperty("plz");
			voucherCode = prop.getProperty("voucherCode");

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

		urlModel = MongoReader.grabUrlModels("US10007CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP1");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP2");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP3");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP4");

	}

	@Test
	public void us31002PartyHostBuysForCustomerTpTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		do {
			customerRegistrationSteps.navigate(urlModel.getUrl());
			partyDetailsSteps.orderForCustomer();
			partyDetailsSteps.orderForCustomerFromParty(customerName);
		} while (!orderForCustomerCartSteps.getCartOwnerInfo().contains(customerName.toUpperCase()));
		customerRegistrationSteps.wipeHostCart();
		HostBasicProductModel productData;

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct2, "2", "0");
		if (!genProduct2.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp1.add(productData);

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct3, "4", "0");
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils.getFirstFridayAfterDate(genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp2.add(productData);

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct4, "2", "0");
		if (!genProduct4.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils.getFirstFridayAfterDate(genProduct4.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp3.add(productData);

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct5, "4", "0");
		if (!genProduct5.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils.getFirstFridayAfterDate(genProduct5.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp4.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		String deliveryTp1 = regularUserCartSteps.getDeliveryDate(genProduct2.getSku(), new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		String deliveryTp2 = regularUserCartSteps.getDeliveryDate(genProduct3.getSku(), new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		String deliveryTp3 = regularUserCartSteps.getDeliveryDate(genProduct4.getSku(), new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		String deliveryTp4 = regularUserCartSteps.getDeliveryDate(genProduct5.getSku(), new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		orderForCustomerCartSteps.typeCouponCode(voucherCode);

		orderForCustomerCartSteps.clickGoToShipping();
		shippingPartySectionSteps.checkItemNotReceivedYet();

		shippingPartySectionSteps.enterPLZ(plz);
		shippingPartySectionSteps.selectCountry(country);

		shippingSteps.goToPaymentMethod();

		String orderId = FormatterUtils.getOrderId(shippingSteps.grabUrl());
		HostDataGrabber.orderModelTp1.setOrderId(FormatterUtils.incrementOrderId(orderId, 1));
		HostDataGrabber.orderModelTp1.setDeliveryDate(deliveryTp1);
		HostDataGrabber.orderModelTp2.setOrderId(FormatterUtils.incrementOrderId(orderId, 2));
		HostDataGrabber.orderModelTp2.setDeliveryDate(deliveryTp2);
		HostDataGrabber.orderModelTp3.setOrderId(FormatterUtils.incrementOrderId(orderId, 3));
		HostDataGrabber.orderModelTp3.setDeliveryDate(deliveryTp3);
		HostDataGrabber.orderModelTp4.setOrderId(FormatterUtils.incrementOrderId(orderId, 4));
		HostDataGrabber.orderModelTp4.setDeliveryDate(deliveryTp4);

		if (!paymentSteps.isCreditCardFormExpended()) {
			paymentSteps.expandCreditCardForm();
		}
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();

		customVerifications.printErrors();

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp2, getClass().getSimpleName() + "TP2");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp3, getClass().getSimpleName() + "TP3");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp4, getClass().getSimpleName() + "TP4");

		MongoWriter.saveProductDetailedModel(genProduct2, getClass().getSimpleName() + "TP1");
		MongoWriter.saveProductDetailedModel(genProduct3, getClass().getSimpleName() + "TP2");
		MongoWriter.saveProductDetailedModel(genProduct4, getClass().getSimpleName() + "TP3");
		MongoWriter.saveProductDetailedModel(genProduct5, getClass().getSimpleName() + "TP4");

		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp1) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP1");
		}
		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp2) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP2");
		}
		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp3) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP3");
		}
		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp4) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP4");
		}
		MongoWriter.saveIncrementId(prod1IncrementId, getClass().getSimpleName() + "TP1");
		MongoWriter.saveIncrementId(prod2IncrementId, getClass().getSimpleName() + "TP2");
		MongoWriter.saveIncrementId(prod3IncrementId, getClass().getSimpleName() + "TP3");
		MongoWriter.saveIncrementId(prod4IncrementId, getClass().getSimpleName() + "TP4");

		// script for updating deliveryDates
		// ApacheHttpHelper.sendGet(JenkinsConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
	}

}

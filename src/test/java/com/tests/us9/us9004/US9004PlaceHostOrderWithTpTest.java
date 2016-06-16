package com.tests.us9.us9004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Locale;
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
import com.steps.frontend.checkout.cart.partyHost.HostCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.cartcalculations.partyHost.HostCartTotalsCalculation;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddHostProductsWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US9.4 Place Host Order With Term Purchase Test", type = "Scenarios")
@Story(Application.HostCart.US9_4.class)
@RunWith(SerenityRunner.class)
public class US9004PlaceHostOrderWithTpTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public HostCartSteps hostCartSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddHostProductsWorkflow addHostProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public HostCartValidationWorkflows hostCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private String discountClass;
	private String billingAddress;
	private String shippingValue;
	private String voucherValue;
	private CreditCardModel creditCardData = new CreditCardModel();
	private static UrlModel partyUrlModel = new UrlModel();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private TermPurchaseIpModel ipModel = new TermPurchaseIpModel();

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct2.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("100.00");
		genProduct3.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us9" + File.separator + "us9001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			billingAddress = prop.getProperty("billingAddress");
			shippingValue = prop.getProperty("shippingValue");
			voucherValue = prop.getProperty("voucherValue");

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP0");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP1");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP2");
		MongoConnector.cleanCollection(getClass().getSimpleName());

		partyUrlModel = MongoReader.grabUrlModels("US10001bCreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
	}

	@Test
	public void us9004PlaceHostOrderWithTpTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		customerRegistrationSteps.wipeHostCart();
		HostBasicProductModel productData;

		productData = addHostProductsWorkflow.setHostProductToCart(genProduct1, "1", "0");
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp0.add(productData);

		productData = addHostProductsWorkflow.setHostProductToCart(genProduct2, "1", "0");
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp1.add(productData);

		productData = addHostProductsWorkflow.setHostProductToCart(genProduct3, "4", "0");
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils.getFirstFridayAfterDate(DateUtils.addDaysToAAGivenDate(
					genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 7), "yyyy-MM-dd"));
		HostCartCalculator.allProductsListTp2.add(productData);

		HostCartCalculator.allProductsList.addAll(HostCartCalculator.allProductsListTp0);
		HostCartCalculator.allProductsList.addAll(HostCartCalculator.allProductsListTp1);
		HostCartCalculator.allProductsList.addAll(HostCartCalculator.allProductsListTp2);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		hostCartSteps.selectProductDiscountType(genProduct1.getSku(), ContextConstants.JEWELRY_BONUS);
		hostCartSteps.updateProductList(HostCartCalculator.allProductsList, genProduct1.getSku(),
				ContextConstants.JEWELRY_BONUS);
		hostCartSteps.selectProductDiscountType(genProduct2.getSku(), ContextConstants.DISCOUNT_40_BONUS);
		hostCartSteps.updateProductList(HostCartCalculator.allProductsList, genProduct2.getSku(),
				ContextConstants.DISCOUNT_40_BONUS);

		String deliveryTp1 = regularUserCartSteps.getDeliveryDate(genProduct2.getSku(),
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		String deliveryTp2 = regularUserCartSteps.selectDeliveryDate(genProduct3.getSku(),
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		hostCartSteps.grabProductsData();
		hostCartSteps.grabTotals();
		HostCartCalculator.calculateHostCartTotals(discountClass, shippingValue);
		HostCartCalculator.calculateShippingTotals(shippingValue, voucherValue, discountClass);
		ipModel = HostCartTotalsCalculation.calculateTermPurchaseIpPoints(HostCartCalculator.allProductsList);

		hostCartSteps.clickGoToShipping();
		hostCartSteps.acceptInfoPopupForNotConsumedBonus();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);

		shippingSteps.grabHostProductsListTp0();
		shippingSteps.grabHostProductsListTp1();
		shippingSteps.grabHostProductsListTp2();

		HostDataGrabber.hostShippingTotalsTp0 = shippingSteps.grabSurveyDataTp0();
		HostDataGrabber.hostShippingTotalsTp1 = shippingSteps.grabSurveyDataTp1();
		HostDataGrabber.hostShippingTotalsTp2 = shippingSteps.grabSurveyDataTp2();

		shippingSteps.goToPaymentMethod();

		String orderId = FormatterUtils.getOrderId(shippingSteps.grabUrl());
		HostDataGrabber.orderModel.setOrderId(FormatterUtils.incrementOrderId(orderId, 1));
		HostDataGrabber.orderModelTp1.setOrderId(FormatterUtils.incrementOrderId(orderId, 2));
		HostDataGrabber.orderModelTp1.setDeliveryDate(deliveryTp1);
		HostDataGrabber.orderModelTp2.setOrderId(FormatterUtils.incrementOrderId(orderId, 3));
		HostDataGrabber.orderModelTp2.setDeliveryDate(deliveryTp2);


		if (!paymentSteps.isCreditCardFormExpended())
			paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabHostProductsListTp0();
		confirmationSteps.grabHostProductsListTp1();
		confirmationSteps.grabHostProductsListTp2();

		HostDataGrabber.hostConfirmationTotalsTp0 = confirmationSteps.grabConfirmationTotalsTp0();
		HostDataGrabber.hostConfirmationTotalsTp1 = confirmationSteps.grabConfirmationTotalsTp1();
		HostDataGrabber.hostConfirmationTotalsTp2 = confirmationSteps.grabConfirmationTotalsTp2();

		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		hostCartValidationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		hostCartValidationWorkflows.performTpCartValidationsWithJbAndForthyDiscount();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(HostDataGrabber.orderModel, getClass().getSimpleName() + "TP0");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp2, getClass().getSimpleName() + "TP2");
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + "TP0");
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModelTp1,
				getClass().getSimpleName() + "TP1");
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModelTp2,
				getClass().getSimpleName() + "TP2");
		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp0) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP0");
		}
		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp1) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP1");
		}
		for (HostBasicProductModel product : HostCartCalculator.allProductsListTp2) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + "TP2");
		}
		MongoWriter.saveIpModel(ipModel, getClass().getSimpleName());
	}
}

package com.tests.us8.us8007;

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
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.regularUser.RegularCartTotalsCalculation;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US8.7 Customer Buy With Term Purchase Test", type = "Scenarios")
@Story(Application.RegularCart.US8_7.class)
@RunWith(SerenityRunner.class)
public class US8007CustomerBuyWithTpTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public RegularCartValidationWorkflows regularCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public FooterSteps footerSteps;

	private String username, password;
	private String discountClass;
	private String billingAddress, shippingAddress;
	private String shippingValue;
	private String voucherCode;
	private String voucherValue;
	private CreditCardModel creditCardData = new CreditCardModel();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private TermPurchaseIpModel ipModel = new TermPurchaseIpModel();

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();
		DataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct2.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("5.00");
		genProduct3.setStockData(
				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8007.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			billingAddress = prop.getProperty("billingAddress");
			shippingAddress = prop.getProperty("shippingAddress");
			shippingValue = prop.getProperty("shippingValue");

			voucherCode = prop.getProperty("voucherCode");
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
	}

	@Test
	public void us8007CustomerBuyWithTpTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		customerRegistrationSteps.wipeRegularCart();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		RegularUserCartCalculator.allProductsListTp0.add(productData);
		
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		if (!genProduct2.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils
					.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(), "yyyy-MM-dd"));
		RegularUserCartCalculator.allProductsListTp1.add(productData);
		
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0");
		if (!genProduct3.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(DateUtils.getFirstFridayAfterDate(DateUtils.addDaysToAAGivenDate(
					genProduct3.getStockData().getEarliestAvailability(), "yyyy-MM-dd", 7), "yyyy-MM-dd"));
		RegularUserCartCalculator.allProductsListTp2.add(productData);

		RegularUserCartCalculator.allProductsList.addAll(RegularUserCartCalculator.allProductsListTp0);
		RegularUserCartCalculator.allProductsList.addAll(RegularUserCartCalculator.allProductsListTp1);
		RegularUserCartCalculator.allProductsList.addAll(RegularUserCartCalculator.allProductsListTp2);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.selectProductDiscountType(genProduct1.getSku(), ContextConstants.JEWELRY_BONUS);
		regularUserCartSteps.updateProductList(RegularUserCartCalculator.allProductsListTp0, genProduct1.getSku(),
				ContextConstants.JEWELRY_BONUS);
		regularUserCartSteps.selectProductDiscountType(genProduct2.getSku(), ContextConstants.DISCOUNT_40_BONUS);
		regularUserCartSteps.updateProductList(RegularUserCartCalculator.allProductsListTp1, genProduct2.getSku(),
				ContextConstants.DISCOUNT_40_BONUS);

		String deliveryTp1 = regularUserCartSteps.getDeliveryDate(genProduct2.getSku(),
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		String deliveryTp2 = regularUserCartSteps.selectDeliveryDate(genProduct3.getSku(),
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		regularUserCartSteps.verifyMultipleDeliveryOption();

		RegularUserDataGrabber.grabbedRegularCartProductsList = regularUserCartSteps.grabProductsData();
		RegularUserDataGrabber.regularUserGrabbedCartTotals = regularUserCartSteps.grabTotals(voucherCode);

		RegularUserCartCalculator.calculateCartTotals(RegularUserCartCalculator.allProductsList, discountClass,
				shippingValue, voucherValue);
		RegularUserCartCalculator.calculateShippingTotals(shippingValue, voucherValue, discountClass);
		ipModel = RegularCartTotalsCalculation
				.calculateTermPurchaseIpPoints(RegularUserCartCalculator.allProductsListwithVoucher);

		regularUserCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(false);
		shippingSteps.selectShippingAddress(shippingAddress);

		shippingSteps.grabRegularProductsListTp0();
		shippingSteps.grabRegularProductsListTp1();
		shippingSteps.grabRegularProductsListTp2();

		RegularUserDataGrabber.regularUserShippingTotalsTp0 = shippingSteps.grabSurveyDataTp0();
		RegularUserDataGrabber.regularUserShippingTotalsTp1 = shippingSteps.grabSurveyDataTp1();
		RegularUserDataGrabber.regularUserShippingTotalsTp2 = shippingSteps.grabSurveyDataTp2();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.getOrderId(url, 1));
		RegularUserDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		RegularUserDataGrabber.orderModelTp1.setOrderId(FormatterUtils.getOrderId(url, 2));
		RegularUserDataGrabber.orderModelTp1.setDeliveryDate(deliveryTp1);
		RegularUserDataGrabber.orderModelTp2.setOrderId(FormatterUtils.getOrderId(url, 3));
		RegularUserDataGrabber.orderModelTp2.setDeliveryDate(deliveryTp2);

		if (!paymentSteps.isCreditCardFormExpended())
			paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabRegularProductsListTp0();
		confirmationSteps.grabRegularProductsListTp1();
		confirmationSteps.grabRegularProductsListTp2();

		RegularUserDataGrabber.regularUserConfirmationTotalsTp0 = confirmationSteps.grabConfirmationTotalsTp0();
		RegularUserDataGrabber.regularUserConfirmationTotalsTp1 = confirmationSteps.grabConfirmationTotalsTp1();
		RegularUserDataGrabber.regularUserConfirmationTotalsTp2 = confirmationSteps.grabConfirmationTotalsTp2();

		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		regularCartValidationWorkflows.setBillingShippingAddress(billingAddress, shippingAddress);
		regularCartValidationWorkflows.performCartValidationsSplittedQuote();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName() + "TP0");
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModelTp1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModelTp2, getClass().getSimpleName() + "TP2");
		MongoWriter.saveShippingModel(RegularUserCartCalculator.shippingCalculatedModel,
				getClass().getSimpleName() + "TP0");
		MongoWriter.saveShippingModel(RegularUserCartCalculator.shippingCalculatedModelTp1,
				getClass().getSimpleName() + "TP1");
		MongoWriter.saveShippingModel(RegularUserCartCalculator.shippingCalculatedModelTp2,
				getClass().getSimpleName() + "TP2");
		for (RegularBasicProductModel product : RegularUserCartCalculator.allProductsListTp0) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + "TP0");
		}
		for (RegularBasicProductModel product : RegularUserCartCalculator.allProductsListTp1) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + "TP1");
		}
		for (RegularBasicProductModel product : RegularUserCartCalculator.allProductsListTp2) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + "TP2");
		}
		MongoWriter.saveIpModel(ipModel, getClass().getSimpleName());
	}
}

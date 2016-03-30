package com.tests.us8.us8004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.steps.frontend.DashboardSteps;
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
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

@WithTag(name = "US8.4 Customer Buy With Kobo", type = "Scenarios")
@Story(Application.RegularCart.US8_4.class)
@RunWith(SerenityRunner.class)
public class US8004CustomerBuyWithContactBoosterTest extends BaseTest {

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
	@Steps
	public DashboardSteps dashboardSteps;

	private String username, password;
	private String discountClass;
	private String billingAddress, shippingAddress;
	private String shippingValue;
	private String initialStylistName;
	private String voucherCode;
	private String voucherValue;
	private String firstVoucherCode;
	private String secondVoucherCode;
	private CreditCardModel creditCardData = new CreditCardModel();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createPomProductModel();
		genProduct2.setPrice("49.90");
		voucherValue = genProduct2.getPrice();
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("10.00");
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8004.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			billingAddress = prop.getProperty("billingAddress");
			shippingAddress = prop.getProperty("shippingAddress");
			shippingValue = prop.getProperty("shippingValue");

			firstVoucherCode = prop.getProperty("firstVoucherCode");
			secondVoucherCode = prop.getProperty("secondVoucherCode");

			initialStylistName = prop.getProperty("initialStylistName");

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
	public void us8004CustomerBuyWithContactBoosterTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();
		voucherCode = dashboardSteps.getStyleCoachFullNameFromProfile().contentEquals(initialStylistName) ? secondVoucherCode : firstVoucherCode;
		homeSteps.goToNewItems();
		customerRegistrationSteps.wipeRegularCart();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "4", "0");
		RegularUserCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.typeCouponCode(voucherCode);
		regularUserCartSteps.submitVoucherCode();

		regularUserCartSteps.validateNotPrefferedShopAndGoToPreferredOne();

		regularUserCartSteps.typeCouponCode(voucherCode);
		regularUserCartSteps.submitVoucherCode();

		RegularUserDataGrabber.grabbedRegularCartProductsList = regularUserCartSteps.grabProductsData();
		RegularUserDataGrabber.regularUserGrabbedCartTotals = regularUserCartSteps.grabTotals(voucherCode);

		RegularUserCartCalculator.calculateCartAndShippingTotals(RegularUserCartCalculator.allProductsList, discountClass, shippingValue, voucherValue);

		regularUserCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(false);
		shippingSteps.selectShippingAddress(shippingAddress);

		RegularUserDataGrabber.grabbedRegularShippingProductsList = shippingSteps.grabRegularProductsList();

		RegularUserDataGrabber.regularUserShippingTotals = shippingSteps.grabSurveyData();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.urlModel.setName("Payment URL");
		DataGrabber.urlModel.setUrl(url);
		RegularUserDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabRegularProductsList();

		RegularUserDataGrabber.regularUserConfirmationTotals = confirmationSteps.grabConfirmationTotals();

		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		regularCartValidationWorkflows.setBillingShippingAddress(billingAddress, shippingAddress);
		regularCartValidationWorkflows.performCartValidationsWithVoucherApplied(true);

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveRegularCartCalcDetailsModel(RegularUserCartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveShippingModel(RegularUserCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveUrlModel(RegularUserDataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (RegularBasicProductModel product : RegularUserCartCalculator.allProductsList) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}
}

package com.tests.us8.us8001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

@WithTag(name = "US8.1 Customer Buy With Forthy Discounts And Jb Test", type = "Scenarios")
@Story(Application.RegularCart.US8_1.class)
@RunWith(SerenityRunner.class)
public class US8001CustomerBuyWithForthyDiscountsAndJbTest extends BaseTest {

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
	public GeneralCartSteps generalCartSteps;
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
	private String billingAddress;
	private String shippingValue;
	private String voucherCode;
	private String voucherValue;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	private CreditCardModel creditCardData = new CreditCardModel();

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("10.00");
		MagentoProductCalls.createApiProduct(genProduct3);
		
   //     createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		
//		genProduct1 = createdProductsList.get(1);
//		System.out.println(genProduct1.getSku());
//		genProduct2 = createdProductsList.get(0);
//		System.out.println(genProduct2.getSku());
//		genProduct3 = createdProductsList.get(6);
//		System.out.println(genProduct3.getSku());

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			billingAddress = prop.getProperty("billingAddress");
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
	}

	@Test
	public void us8001CustomerBuyWithForthyDiscountsAndJbTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "4", "0");
		RegularUserCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();
		regularUserCartSteps.selectProductDiscountType(genProduct1.getSku(), ContextConstants.JEWELRY_BONUS);
		regularUserCartSteps.updateProductList(RegularUserCartCalculator.allProductsList, genProduct1.getSku(),
				ContextConstants.JEWELRY_BONUS);
		regularUserCartSteps.selectProductDiscountType(genProduct2.getSku(), ContextConstants.DISCOUNT_40_BONUS);
		regularUserCartSteps.updateProductList(RegularUserCartCalculator.allProductsList, genProduct2.getSku(),
				ContextConstants.DISCOUNT_40_BONUS);

		regularUserCartSteps.typeCouponCode(voucherCode);
		regularUserCartSteps.validateThatVoucherCannotBeAppliedMessage();

		RegularUserDataGrabber.grabbedRegularCartProductsList = regularUserCartSteps.grabProductsData();
		RegularUserDataGrabber.regularUserGrabbedCartTotals = regularUserCartSteps.grabTotals(voucherCode);

		RegularUserCartCalculator.calculateCartAndShippingTotals(RegularUserCartCalculator.allProductsList,
				discountClass, shippingValue, voucherValue);

		regularUserCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);

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
		
		
		//paymentSteps.payWithBankTransfer();

		confirmationSteps.grabRegularProductsList();

		RegularUserDataGrabber.regularUserConfirmationTotals = confirmationSteps.grabConfirmationTotals();

		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		regularCartValidationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		regularCartValidationWorkflows.performCartValidationsWith40DiscountAndJb();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveRegularCartCalcDetailsModel(RegularUserCartCalculator.calculatedTotalsDiscounts,
				getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveShippingModel(RegularUserCartCalculator.shippingCalculatedModel,
				getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveUrlModel(RegularUserDataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (RegularBasicProductModel product : RegularUserCartCalculator.allProductsList) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}
}

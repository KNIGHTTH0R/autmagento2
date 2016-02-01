package com.tests.us4.us4002;

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

import com.connectors.http.MagentoProductCalls;
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
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US4.2 Shop for myself with Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_2.class)
@RunWith(ThucydidesRunner.class)
public class US4002ShopForMyselfWithBuy3GetOneTest extends BaseTest {

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
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private String billingAddress;
	private CreditCardModel creditCardData = new CreditCardModel();
	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String shippingValueForLessThan150;
	private static String taxClass;

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("100");
		MagentoProductCalls.createApiProduct(genProduct1);
		genProduct2.setName("QPIWDODRU");
		genProduct2.setSku("DFCDVEUBK");
		genProduct2.setIp("42");
		genProduct2.setPrice("49.90");
		genProduct3 = MagentoProductCalls.createMarketingProductModel();
		genProduct3.setPrice("5.00");
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us4" + File.separator + "us4002.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			billingAddress = prop.getProperty("billingAddress");
			jewelryDiscount = prop.getProperty("jewelryDiscount");
			marketingDiscount = prop.getProperty("marketingDiscount");
			shippingValue = prop.getProperty("shippingPrice");
			shippingValueForLessThan150 = prop.getProperty("shippingPriceForLessThan150");
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
	public void us4002ShopForMyselfWithBuy3GetOneTest() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		frontEndSteps.wipeCart();
		BasicProductModel productData;

		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_25);
		CartCalculator.productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "2", "0", ConfigConstants.DISCOUNT_25);
		CartCalculator.productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "3", "0", ConfigConstants.DISCOUNT_0);
		CartCalculator.productsListMarketing.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		DataGrabber.cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		DataGrabber.cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		DataGrabber.cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		cartSteps.grabTotals();
		cartSteps.goToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);

		shippingSteps.grabProductsList();
		shippingSteps.grabSurveyData();

		shippingSteps.goToPaymentMethod();

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

		CartCalculator.calculate3P1Rule(jewelryDiscount, marketingDiscount, taxClass, shippingValue, shippingValueForLessThan150);
		DataGrabber.addAll25AndMmProducts();
		validationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		validationWorkflows.performCartValidationsBu3Get1Rule();

		customVerifications.printErrors();

	}

	@After
	public void saveData() {

		MongoWriter.saveCalcDetailsModel(CartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(DataGrabber.confirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (BasicProductModel product : CartCalculator.allProductsList) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}

	}

}

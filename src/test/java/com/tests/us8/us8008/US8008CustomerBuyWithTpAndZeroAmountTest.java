package com.tests.us8.us8008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

@WithTag(name = "US8.8 Customer Buy With 0 Amount Immediate and Tp products", type = "Scenarios")
@Story(Application.RegularCart.US8_8.class)
@RunWith(SerenityRunner.class)
public class US8008CustomerBuyWithTpAndZeroAmountTest extends BaseTest {

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
	public RegularCartValidationWorkflows regularCartValidationWorkflows;
	@Steps
	public FooterSteps footerSteps;

	private String username, password;
	private String voucherCode;
	private String billingAddress, shippingAddress;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();


	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();
		DataGrabber.wipe();
		
		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		genProduct1 = createdProductsList.get(6);
		genProduct2 = createdProductsList.get(8);
		genProduct3 = createdProductsList.get(10);
		

//		genProduct1 = MagentoProductCalls.createProductModel();
//		genProduct1.setPrice("50.00");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createNotAvailableYetProductModel();
//		genProduct2.setPrice("29.00");
//		genProduct1.setIp("0");
//		MagentoProductCalls.createApiProduct(genProduct2);
//
//		genProduct3 = MagentoProductCalls.createNotAvailableYetProductModel();
//		genProduct3.setPrice("19.90");
//		genProduct1.setIp("0");
//		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8008.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			voucherCode = prop.getProperty("voucherCode");

			billingAddress = prop.getProperty("billingAddress");
			shippingAddress = prop.getProperty("shippingAddress");

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
	}

	@Test
	public void us8008CustomerBuyWithTpAndZeroAmountTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0");

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(false);
		shippingSteps.selectShippingAddress(shippingAddress);

		// go to payment without bonuses first in order to get the order id
		// (which is dysplayed only on that page);if you apply bonuses in order
		// to place 0 amount order that page will be skipped
		shippingSteps.goToPaymentMethod();
		
		String orderId = FormatterUtils.getOrderId(shippingSteps.grabUrl());
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.incrementOrderId(orderId, 1));
		RegularUserDataGrabber.orderModelTp1.setOrderId(FormatterUtils.incrementOrderId(orderId, 2));
		RegularUserDataGrabber.orderModelTp2.setOrderId(FormatterUtils.incrementOrderId(orderId, 3));

		paymentSteps.goBack();
		shippingSteps.goBack();

		regularUserCartSteps.selectDeliveryDate(genProduct3.getSku(),
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		regularUserCartSteps.typeCouponCode(voucherCode);

		regularUserCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(false);
		shippingSteps.selectShippingAddress(shippingAddress);

		shippingSteps.goToPaymentMethod();

		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName() + "TP0");
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModelTp1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModelTp2, getClass().getSimpleName() + "TP2");
	}
}

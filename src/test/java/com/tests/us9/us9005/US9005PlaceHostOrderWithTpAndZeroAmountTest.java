package com.tests.us9.us9005;

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
import com.steps.frontend.checkout.cart.partyHost.HostCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddHostProductsWorkflow;

@WithTag(name = "US9.5 Place Host Order With 0 Amount Immediate and Tp Products", type = "Scenarios")
@Story(Application.HostCart.US9_5.class)
@RunWith(SerenityRunner.class)
public class US9005PlaceHostOrderWithTpAndZeroAmountTest extends BaseTest {

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
	public GeneralCartSteps generalCartSteps;
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

	private String username, password;
	private String billingAddress;
	private static UrlModel partyUrlModel = new UrlModel();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	
	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

//		genProduct1 = MagentoProductCalls.createProductModel();
//		genProduct1.setPrice("89.00");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createNotAvailableYetProductModel();
//		genProduct2.setPrice("49.90");
//		genProduct1.setIp("0");
//		MagentoProductCalls.createApiProduct(genProduct2);
//
//		genProduct3 = MagentoProductCalls.createProductModel();
//		genProduct3.setPrice("100.00");
//		genProduct1.setIp("0");
//		genProduct3.setStockData(
//				MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd")));
//		MagentoProductCalls.createApiProduct(genProduct3);
		
		 createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
			
		 genProduct1 = createdProductsList.get(1);
		 genProduct2 = createdProductsList.get(8);
		 genProduct3 = createdProductsList.get(11);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us9" + File.separator + "us9001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			billingAddress = prop.getProperty("billingAddress");

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

		partyUrlModel = MongoReader.grabUrlModels("US10009CreatePartyWithStylistHostTest").get(0);
	}

	@Test
	public void us9005PlaceHostOrderWithTpAndZeroAmountTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		generalCartSteps.clearCart();

		addHostProductsWorkflow.setHostProductToCart(genProduct1, "1", "0");
		addHostProductsWorkflow.setHostProductToCart(genProduct2, "1", "0");
		addHostProductsWorkflow.setHostProductToCart(genProduct3, "4", "0");

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		hostCartSteps.clickGoToShipping();
		hostCartSteps.acceptInfoPopupForNotConsumedBonus();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);

		// go to payment without bonuses first in order to get the order id
		// (which is dysplayed only on that page);if you apply bonuses in order
		// to place 0 amount order that page will be skipped
		shippingSteps.goToPaymentMethod();

		String orderId = FormatterUtils.getOrderId(shippingSteps.grabUrl());
		HostDataGrabber.orderModel.setOrderId(FormatterUtils.incrementOrderId(orderId, 1));
		HostDataGrabber.orderModelTp1.setOrderId(FormatterUtils.incrementOrderId(orderId, 2));
		HostDataGrabber.orderModelTp2.setOrderId(FormatterUtils.incrementOrderId(orderId, 3));

		paymentSteps.goBack();
		shippingSteps.goBack();

		regularUserCartSteps.selectDeliveryDate(genProduct3.getSku(),
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		hostCartSteps.selectProductDiscountType(genProduct1.getSku(), ContextConstants.JEWELRY_BONUS);
		hostCartSteps.selectProductDiscountType(genProduct2.getSku(), ContextConstants.JEWELRY_BONUS);
		hostCartSteps.selectProductDiscountType(genProduct3.getSku(), ContextConstants.JEWELRY_BONUS);

		hostCartSteps.clickGoToShipping();
		hostCartSteps.acceptInfoPopupForNotConsumedBonus();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);

		shippingSteps.goToPaymentMethod();

		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(HostDataGrabber.orderModel, getClass().getSimpleName() + "TP0");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveOrderModel(HostDataGrabber.orderModelTp2, getClass().getSimpleName() + "TP2");
	}
}

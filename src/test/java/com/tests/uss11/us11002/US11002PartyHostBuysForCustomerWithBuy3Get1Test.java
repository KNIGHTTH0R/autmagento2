package com.tests.uss11.us11002;

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

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.HostDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

@WithTag(name = "US11.2 Party Host Buys For Customer With Buy 3 Get 1 For 50%, ship to customer", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_2.class)
@RunWith(SerenityRunner.class)
public class US11002PartyHostBuysForCustomerWithBuy3Get1Test extends BaseTest {

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
	public GeneralCartSteps generalCartSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
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
	private String discountClass;
	private String billingAddress;
	private String country;
	private String plz;
	private String shippingValue;
	private String voucherValue;
	private CreditCardModel creditCardData;
	private static UrlModel urlModel = new UrlModel();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();
		
		System.out.println("WARENKORB FR CHARLIE BARAIAN ARTIKEL IN DEN WARENKORB LEGEN WARENKORB IST LEER".contains("CHARLIE BARAIAN"));

		creditCardData = new CreditCardModel();

//		genProduct1 = MagentoProductCalls.createProductModel();
//		genProduct1.setPrice("29.00");
//		genProduct1.setIp("25");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createProductModel();
//		genProduct2.setPrice("10.00");
//		genProduct2.setIp("8");
//		MagentoProductCalls.createApiProduct(genProduct2);
//
//		genProduct3 = MagentoProductCalls.createProductModel();
//		genProduct3.setPrice("29.90");
//		genProduct3.setIp("25");
//		MagentoProductCalls.createApiProduct(genProduct3);
		
        createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		
		genProduct1 = createdProductsList.get(3);
		genProduct2 = createdProductsList.get(6);
		genProduct3 = createdProductsList.get(14);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss11" + File.separator + "us11002.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			customerName = prop.getProperty("customerName");

			country = prop.getProperty("country");
			plz = prop.getProperty("plz");
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

		urlModel = MongoReader.grabUrlModels("US10002bCreatePartyWithCustomerHostTest" + SoapKeys.GRAB).get(0);

		System.out.println("dovada "+urlModel.getUrl() );
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);

	}

	@Test
	public void us11002PartyHostBuysForCustomerWithBuy3Get1Test() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		/*do {
			customerRegistrationSteps.navigate(urlModel.getUrl());
			partyDetailsSteps.orderForCustomer();
			partyDetailsSteps.orderForCustomerFromParty(customerName);
		} while (!orderForCustomerCartSteps.getCartOwnerInfo().contains(customerName.toUpperCase()));*/
		
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.orderForCustomer();
		partyDetailsSteps.orderForCustomerFromParty(customerName);
		
	/*	do {
			customerRegistrationSteps.navigate(urlModel.getUrl());
		//	partyDetailsSteps.orderForCustomer();
			if(partyDetailsSteps.orderForCustomer()){
				partyDetailsSteps.orderForCustomerFromParty(customerName);
			}else{
				break;
			}
			
		} while (!orderForCustomerCartSteps.getCartOwnerInfo().contains(customerName.toUpperCase()) );*/
		
		
		generalCartSteps.clearCart();
		HostBasicProductModel productData;
		
		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct1, "1", "0");
		HostCartCalculator.allProductsList.add(productData);
		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct2, "2", "0");
		HostCartCalculator.allProductsList.add(productData);
		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct3, "4", "0");
		HostCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		orderForCustomerCartSteps.grabProductsData();
		orderForCustomerCartSteps.grabTotals("No Voucher");
		HostCartCalculator.calculateCartBuy3Get1OrderForCustomerCartAndShippingTotals(discountClass, shippingValue,
				voucherValue);

		orderForCustomerCartSteps.clickGoToShipping();
		shippingPartySectionSteps.checkItemNotReceivedYet();
		shippingPartySectionSteps.enterPLZ(plz);
		shippingPartySectionSteps.selectCountry(country);

		HostDataGrabber.grabbedHostShippingProductsList = shippingSteps.grabHostProductsList();
		HostDataGrabber.hostShippingTotals = shippingSteps.grabSurveyData();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.urlModel.setName("Payment URL");
		DataGrabber.urlModel.setUrl(url);
		HostDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		HostDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabHostProductsList();

		HostDataGrabber.hostConfirmationTotals = confirmationSteps.grabConfirmationTotals();

		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		hostCartValidationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		hostCartValidationWorkflows.performCartValidationsWithBuy3Get1();

		customVerifications.printErrors();

	}

	@After
	public void saveData() {
		MongoWriter.saveHostCartCalcDetailsModel(HostCartCalculator.calculatedTotalsDiscounts,
				getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveOrderModel(HostDataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModel,
				getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveUrlModel(HostDataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (HostBasicProductModel product : HostCartCalculator.allProductsList) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}

}

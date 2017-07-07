package com.tests.us9.us9002b;

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
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.HostCartSteps;
import com.steps.frontend.checkout.shipping.contactHost.ContactHostShippingHostSteps;
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
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddHostProductsWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

@WithTag(name = "US9.2b Place Host Order (customer host) and ship to stylecoach", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(SerenityRunner.class)
public class US9002bPlaceHostOrderAndShipToSylecoachTest extends BaseTest {

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
	public HostCartSteps hostCartSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddHostProductsWorkflow addHostProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public HostCartValidationWorkflows hostCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ContactHostShippingHostSteps contactHostShippingHostSteps;

	private String username, password;
	private String discountClass;
	private String contactBillingAddress;
	private String shippingAddress;
	private String shippingValue;
	private String country;
	private String plz;
	private CreditCardModel creditCardData = new CreditCardModel();
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
//		genProduct1.setPrice("19.00");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createProductModel();
//		genProduct2.setPrice("10.00");
//		MagentoProductCalls.createApiProduct(genProduct2);
//
//		genProduct3 = MagentoProductCalls.createProductModel();
//		genProduct3.setPrice("24.90");
//		MagentoProductCalls.createApiProduct(genProduct3);
		
        createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		
		genProduct1 = createdProductsList.get(0);
		genProduct2 = createdProductsList.get(6);
		genProduct3 = createdProductsList.get(1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us9" + File.separator + "us9002b.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			contactBillingAddress = prop.getProperty("contactBillingAddress");
			shippingAddress = prop.getProperty("shippingAddress");
			country = prop.getProperty("country");
			plz = prop.getProperty("plz");
			shippingValue = prop.getProperty("shippingValue");

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

		partyUrlModel = MongoReader.grabUrlModels(""
				+ ""
				+ "" + SoapKeys.GRAB).get(0);
	}

	@Test
	public void us9002bPlaceHostOrderAndShipToSylecoachTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		generalCartSteps.clearCart();
		HostBasicProductModel productData;

		productData = addHostProductsWorkflow.setHostProductToCart(genProduct1, "1", "0");
		HostCartCalculator.allProductsList.add(productData);
		productData = addHostProductsWorkflow.setHostProductToCart(genProduct2, "1", "0");
		HostCartCalculator.allProductsList.add(productData);
		productData = addHostProductsWorkflow.setHostProductToCart(genProduct3, "4", "0");
		HostCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		hostCartSteps.grabProductsDataWhenThereIsNoBonus();
		hostCartSteps.grabTotals();

		HostCartCalculator.calculateCartAndShippingTotals(discountClass, shippingValue);

		hostCartSteps.clickGoToShipping();
		contactHostShippingHostSteps.checkItemNotReceivedYet();
		contactHostShippingHostSteps.selectCountry(country);
		contactHostShippingHostSteps.enterPLZ(plz);
		//the following line is duplicate (is a workaround due to a bug)
		contactHostShippingHostSteps.checkItemNotReceivedYet();
		shippingPartySectionSteps.clickShipToStylecoach();
		shippingPartySectionSteps.selectShipToStylecoachAddress(shippingAddress);
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

		hostCartValidationWorkflows.setBillingShippingAddress(contactBillingAddress, shippingAddress);
		hostCartValidationWorkflows.performCartValidations();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveHostCartCalcDetailsModel(HostCartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveOrderModel(HostDataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveUrlModel(RegularUserDataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (HostBasicProductModel product : HostCartCalculator.allProductsList) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}
}

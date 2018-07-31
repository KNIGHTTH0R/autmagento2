package com.tests.us9.us9001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.SearchSteps;
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
import com.tools.constants.ContextConstants;
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
import com.workflows.frontend.partyHost.AddHostProductsWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US9.1 Place Host Order With 40% Discount and JB Test", type = "Scenarios")
@Story(Application.HostCart.US9_1.class)
@RunWith(SerenityRunner.class)
public class US9001PlaceHostOrderWithForthyDiscountsAndJbTest extends BaseTest {

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
	public UserRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddHostProductsWorkflow addHostProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public HostCartValidationWorkflows hostCartValidationWorkflows;
	@Steps
	public ContactHostShippingHostSteps contactHostShippingHostSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public SearchSteps searchSteps;

	private String username, password;
	private String discountClass;
	private String billingAddress;
	private String shippingValue;
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
		
		
		
		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTestHostCart" + SoapKeys.GRAB);
		
		genProduct1 = createdProductsList.get(0);
		genProduct2 = createdProductsList.get(3);
		genProduct3 = createdProductsList.get(2);
		

		
		

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
		
		partyUrlModel = MongoReader.grabUrlModels("US10001CreatePartyWithCustomerHostTestVDV" + SoapKeys.GRAB).get(0);
		System.out.println("partyUrlModel " + partyUrlModel.getUrl());
	}

	@Test
	public void us9001PlaceHostOrderWithForthyDiscountsAndJbTest() {
		customerRegistrationSteps.performLogin(username, password);
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		generalCartSteps.clearCart();
		headerSteps.waitABit(15000);
		HostBasicProductModel productData;

		searchSteps.navigateToProductPage(genProduct1.getParentProductSku());
		productData = addHostProductsWorkflow.setHostChildProductToCart(genProduct1, "1", "0");
		HostCartCalculator.allProductsList.add(productData);
		
		searchSteps.navigateToProductPage(genProduct2.getParentProductSku());
		productData = addHostProductsWorkflow.setHostChildProductToCart(genProduct2, "2", "0");
		HostCartCalculator.allProductsList.add(productData);
		
		searchSteps.navigateToProductPage(genProduct3.getParentProductSku());
		productData = addHostProductsWorkflow.setHostChildProductToCart(genProduct3, "1", "0");
		HostCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
		hostCartSteps.selectProductDiscountType(genProduct1.getSku(), ContextConstants.JEWELRY_BONUS);
		hostCartSteps.updateProductList(HostCartCalculator.allProductsList, genProduct1.getSku(), ContextConstants.JEWELRY_BONUS);
		hostCartSteps.selectProductDiscountType(genProduct2.getSku(), ContextConstants.DISCOUNT_40_BONUS);
		hostCartSteps.updateProductList(HostCartCalculator.allProductsList, genProduct2.getSku(), ContextConstants.DISCOUNT_40_BONUS);

		hostCartSteps.grabProductsData();		
		hostCartSteps.grabTotals();
		HostCartCalculator.calculateCartAndShippingTotals(discountClass, shippingValue);

		hostCartSteps.clickGoToShipping();
		hostCartSteps.acceptInfoPopupForNotConsumedBonus();
		//shippingSteps.selectAddress(billingAddress);
	//	shippingSteps.setSameAsBilling(true);

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
		hostCartValidationWorkflows.performCartValidationsWith40DiscountAndJb();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveHostCartCalcDetailsModel(HostCartCalculator.calculatedTotalsDiscounts, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveOrderModel(HostDataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveUrlModel(HostDataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (HostBasicProductModel product : HostCartCalculator.allProductsList) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}
}


package com.tests.us9.us9007;

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
import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
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

@WithTag(name = "US9.1 Place Host Order With 40% Discount and JB Test", type = "Scenarios")
@Story(Application.HostCart.US9_1.class)
@RunWith(SerenityRunner.class)
public class US9007PlaceHostOrderWithSpecialPriceProductTest extends BaseTest {

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
	public CustomerRegistrationSteps customerRegistrationSteps;
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
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;

	private String username, password;
	private String billingAddress;
	private String shippingValue;
	private String discount;
	private CreditCardModel creditCardData = new CreditCardModel();
	private static UrlModel partyUrlModel = new UrlModel();
	private ProductDetailedModel genProduct1;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

	/*	genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		genProduct1.setSpecialPrice("55.00");
		MagentoProductCalls.createApiProduct(genProduct1);
		genProduct1.setPrice(genProduct1.getSpecialPrice());*/
		
		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTestHostCart" + SoapKeys.GRAB);
		genProduct1 = createdProductsList.get(11);
		genProduct1.setPrice(genProduct1.getSpecialPrice());

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us9" + File.separator + "us9001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			billingAddress = prop.getProperty("billingAddress");
			shippingValue = prop.getProperty("shippingValue");
			discount = prop.getProperty("discount");
			

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
		
		partyUrlModel = MongoReader.grabUrlModels("US9007CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
		System.out.println("partyUrlModel " + partyUrlModel.getUrl());
	}

	@Test
	public void us9007PlaceHostOrderWithSpecialPriceProductTestEm() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		generalCartSteps.clearCart();
		headerSteps.waitABit(20000);
		HostBasicProductModel productData;

		productData = addHostProductsWorkflow.setHostProductToCart(genProduct1, "3", "0");
		HostCartCalculator.allProductsList.add(productData);
		
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
	
		System.out.println(discount +" ---------"+shippingValue);
		
		hostCartSteps.grabProductsDataWhenThereIsNoBonus();	
		hostCartSteps.grabTotals();
		
		System.out.println("duscount:"+discount+" shipping: "+shippingValue);
		
		HostCartCalculator.calculateCartAndShippingTotals(discount, shippingValue);

		
		
		hostCartSteps.clickGoToShipping();
//		hostCartSteps.acceptInfoPopupForNotConsumedBonus();
		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);
		
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
		hostCartValidationWorkflows.performCheckoutValidations();

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


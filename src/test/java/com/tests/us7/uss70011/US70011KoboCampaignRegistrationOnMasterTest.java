package com.tests.us7.uss70011;

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
import com.steps.external.EmailClientSteps;
import com.steps.frontend.FancyBoxSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.KoboCampaignSteps;
import com.steps.frontend.KoboSuccesFormSteps;
import com.steps.frontend.KoboValidationSteps;
import com.steps.frontend.PomProductDetailsSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.ProfileSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPomSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.PomProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.borrowCart.PomCartCalculator;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.AdyenWorkflows;
import com.workflows.frontend.ShippingAndConfirmationWorkflows;

@WithTag(name = "US7.11 Kobo Campaign Registration On Master Test ", type = "Scenarios")
@Story(Application.KoboCampaign.US7_11.class)
@RunWith(ThucydidesRunner.class)
public class US70011KoboCampaignRegistrationOnMasterTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public KoboCampaignSteps koboCampaignSteps;
	@Steps
	public KoboValidationSteps koboValidationSteps;
	@Steps
	public KoboSuccesFormSteps koboSuccesFormSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public PomProductDetailsSteps pomProductDetailsSteps;
	@Steps
	public FancyBoxSteps fancyBoxSteps;
	@Steps
	public ShippingPomSteps shippingPomSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public ShippingAndConfirmationWorkflows shippingAndConfirmationWorkflows;
	@Steps
	public AdyenWorkflows adyenWorkflows;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CreditCardModel creditCardData = new CreditCardModel();
	private ProductDetailedModel genProduct1;
	private String discountClass;
	private String shippingValue;

	@Before
	public void setUp() throws Exception {
		DataGrabber.wipe();
		PomCartCalculator.wipe();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us70011.properties");
			prop.load(input);

			discountClass = prop.getProperty("discountClass");
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

		genProduct1 = MagentoProductCalls.createPomProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us70011KoboCampaignRegistrationOnMasterTest() {
		koboValidationSteps.startKoboCampaignProcess(MongoReader.getBaseURL() + UrlConstants.KOBO_CAMPAIGN);
		koboCampaignSteps.fillKoboCampaignRegistrationFormOnMasterShop(dataModel, addressModel);
		koboSuccesFormSteps.verifyKoboFormIsSuccsesfullyFilledIn();
		emailClientSteps.openMailinator();
		String url = emailClientSteps.grabConfirmationLinkFromEmail(dataModel.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""),
				ContextConstants.KOBO_CONFIRM_ACCOUNT_MAIL_SUBJECT);
		koboCampaignSteps.navigate(url);
		pomProductDetailsSteps.findStarterProductAndAddItToTheCart(genProduct1.getName());

		PomProductModel productData;

		productData = productSteps.setPomProductAddToCart(genProduct1.getName(), genProduct1.getSku(), genProduct1.getPrice());
		PomCartCalculator.allBorrowedProductsList.add(productData);

		PomCartCalculator.calculateCartAndShippingTotals(discountClass, shippingValue);

		fancyBoxSteps.goToShipping();
		String shippingUrl = shippingSteps.grabUrl();
		DataGrabber.shippingTotals = shippingSteps.grabSurveyData();
		shippingSteps.goToPaymentMethod();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(shippingUrl));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(shippingUrl));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		// confirmationSteps.agreeAndCheckout();
		// checkoutValidationSteps.verifySuccessMessage();
		// headerSteps.redirectToProfileHistory();
		// List<OrderModel> orderHistory = profileSteps.grabOrderHistory();
		//
		// String orderId = orderHistory.get(0).getOrderId();
		// profileSteps.verifyOrderId(orderId,
		// RegularUserDataGrabber.orderModel.getOrderId());

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, PomCartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, PomCartCalculator.shippingCalculatedModel);
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName());
	}

}
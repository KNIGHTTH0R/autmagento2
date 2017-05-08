package com.tests.us7.uss70011;


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
	import com.steps.frontend.checkout.CheckoutValidationSteps;
	import com.steps.frontend.checkout.ConfirmationSteps;
	import com.steps.frontend.checkout.PaymentSteps;
	import com.steps.frontend.checkout.ShippingSteps;
	import com.steps.frontend.checkout.shipping.regularUser.ShippingPomSteps;
	import com.steps.frontend.profile.ProfileSteps;
	import com.tests.BaseTest;
	import com.tools.CustomVerification;
	import com.tools.cartcalculations.borrowCart.PomCartCalculator;
	import com.tools.constants.ConfigConstants;
	import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
	import com.tools.data.backend.OrderModel;
	import com.tools.data.frontend.AddressModel;
	import com.tools.data.frontend.CreditCardModel;
	import com.tools.data.frontend.CustomerFormModel;
	import com.tools.data.frontend.PomProductModel;
	import com.tools.data.soap.ProductDetailedModel;
	import com.tools.datahandler.DataGrabber;
	import com.tools.persistance.MongoReader;
	import com.tools.persistance.MongoWriter;
	import com.tools.requirements.Application;
	import com.tools.utils.FormatterUtils;
	import com.workflows.frontend.AdyenWorkflows;
	import com.workflows.frontend.ShippingAndConfirmationWorkflows;

	import net.serenitybdd.junit.runners.SerenityRunner;
	import net.thucydides.core.annotations.Steps;
	import net.thucydides.core.annotations.Story;
	import net.thucydides.core.annotations.WithTag;

	@WithTag(name = "US7.11 Kobo Campaign Registration On Master Test ", type = "Scenarios")
	@Story(Application.KoboCampaign.US7_11.class)
	@RunWith(SerenityRunner.class)
	public class US70011PlacePomOrderTest extends BaseTest {

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
		@Steps
		public CustomVerification customVerifications;

		private String stylistEmail;
		private CustomerFormModel dataModel;
		private AddressModel addressModel;
		private CreditCardModel creditCardData = new CreditCardModel();
		private ProductDetailedModel genProduct1;
		private String discountClass;
		private String shippingValue;
		public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

		@Before
		public void setUp() throws Exception {
			DataGrabber.wipe();
			PomCartCalculator.wipe();

			int size = MongoReader.grabCustomerFormModels("US70011KoboCampaignRegistrationOnMasterTest1").size();
			if (size > 0) {
				stylistEmail = MongoReader.grabCustomerFormModels("US70011KoboCampaignRegistrationOnMasterTest1").get(0).getEmailName();
				System.out.println(stylistEmail);
			} else
				System.out.println("The database has no entries");
			
//			
//			genProduct1 = MagentoProductCalls.createPomProductModel();
//			genProduct1.setName("POM_" + genProduct1.getName());
//			genProduct1.setPrice("89.00");
//			MagentoProductCalls.createApiProduct(genProduct1);
			

			 createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
				genProduct1 = createdProductsList.get(7);



			dataModel = new CustomerFormModel();
			addressModel = new AddressModel();
			MongoConnector.cleanCollection(getClass().getSimpleName());
		}

		@Test
		public void us70011PlacePomOrderTest() {
			
			String url = emailClientSteps.grabConfirmationLinkFromEmail(
					stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),
					ContextConstants.KOBO_CONFIRM_ACCOUNT_MAIL_SUBJECT);
			koboCampaignSteps.navigate(url);
			pomProductDetailsSteps.findStarterProductAndAddItToTheCart(genProduct1.getName());

			PomProductModel productData;
			productData = productSteps.setPomProductAddToCart(genProduct1.getName(), genProduct1.getSku(),
					genProduct1.getPrice());
			PomCartCalculator.allBorrowedProductsList.add(productData);

			PomCartCalculator.calculateCartAndShippingTotals(discountClass, shippingValue);

			fancyBoxSteps.goToShipping();
			shippingSteps.selectPartyNoOptionIfPresent();
			DataGrabber.shippingTotals = shippingSteps.grabSurveyData();
			shippingSteps.goToPaymentMethod();
			String shippingUrl = shippingSteps.grabUrl();
			DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(shippingUrl));
			DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(shippingUrl));
			paymentSteps.expandCreditCardForm();
			paymentSteps.fillCreditCardForm(creditCardData);
			confirmationSteps.agreeAndCheckout();
			checkoutValidationSteps.verifySuccessMessage();
			headerSteps.redirectToProfileHistory();
			List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

			String orderId = orderHistory.get(0).getOrderId();
			profileSteps.verifyOrderId(orderId, DataGrabber.orderModel.getOrderId());
			shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals,
					PomCartCalculator.shippingCalculatedModel);
			shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

			adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel,
					PomCartCalculator.shippingCalculatedModel.getTotalAmount());
			adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

			customVerifications.printErrors();
		}

		@After
		public void saveData() {
			MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
			MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName());
		}

	}


package com.tests.us7.us7009;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.ContactBoosterRegistrationSteps;
import com.steps.frontend.FancyBoxSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.KoboSuccesFormSteps;
import com.steps.frontend.KoboValidationSteps;
import com.steps.frontend.PomProductDetailsSteps;
import com.steps.frontend.ProfileSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPomSteps;
import com.tests.BaseTest;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;

@WithTag(name = "US7", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7009KoboRegistrationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ContactBoosterRegistrationSteps contactBoosterRegistrationSteps;
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

	private String context;
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private String koboCode;
	private CreditCardModel creditCardData = new CreditCardModel();
	private ProductDetailedModel genProduct1;

	@Before
	public void setUp() throws Exception {
		RegularUserDataGrabber.wipe();

		genProduct1 = ApiCalls.createPomProductModel();
		genProduct1.setPrice("89.00");
		ApiCalls.createApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us7009.properties");
			prop.load(input);
			context = prop.getProperty("context");
			koboCode = prop.getProperty("koboCode");
			creditCardData.setCardNumber(prop.getProperty("cardNumber"));
			creditCardData.setCardName(prop.getProperty("cardName"));
			creditCardData.setMonthExpiration(prop.getProperty("cardMonth"));
			creditCardData.setYearExpiration(prop.getProperty("cardYear"));
			creditCardData.setCvcNumber(prop.getProperty("cardCVC"));

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
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us7009KoboRegistrationTest() {
		koboValidationSteps.enterKoboCodeAndGoToRegistrationProcess(MongoReader.getBaseURL() + context, koboCode);
		contactBoosterRegistrationSteps.fillContactBoosterRegistrationForm(dataModel, addressModel);
		koboSuccesFormSteps.verifyKoboFormIsSuccsesfullyFilledIn();
		emailClientSteps.openMailinator();
		String url = emailClientSteps.grabConfirmationLinkFromEmail(dataModel.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""),
				ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
		contactBoosterRegistrationSteps.navigate(url);
		pomProductDetailsSteps.findStarterProductAndAddItToTheCart(genProduct1.getName());
		fancyBoxSteps.goToShipping();
		shippingSteps.clickGoToPaymentMethod();
		String shippingUrl = shippingSteps.grabUrl();
		RegularUserDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(shippingUrl));
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(shippingUrl));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();

		headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		profileSteps.verifyOrderId(orderId, RegularUserDataGrabber.orderModel.getOrderId());

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName());
	}

}
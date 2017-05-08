package com.tests.us7.uss70010;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.ContactBoosterRegistrationSteps;
import com.steps.frontend.FancyBoxSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.KoboSuccesFormSteps;
import com.steps.frontend.KoboValidationSteps;
import com.steps.frontend.PomProductDetailsSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPomSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;




@WithTag(name = "US7.10 Kobo Registration On Voucher Owner Context Test ", type = "Scenarios")
@Story(Application.KoboRegistration.US7_10.class)
@RunWith(SerenityRunner.class)
public class US70010PlacePomOrderTest extends BaseTest{

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
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	public String stylistEmail;
	
	@Before
	public void setUp() throws Exception {
		RegularUserDataGrabber.wipe();

//		genProduct1 = MagentoProductCalls.createPomProductModel();
//		genProduct1.setName("POM_" + genProduct1.getName());
//		genProduct1.setPrice("89.00");
//		genProduct1.setSpecialPrice("79.00");
//		MagentoProductCalls.createApiProduct(genProduct1);
//		genProduct1.setPrice(genProduct1.getSpecialPrice());
        createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		genProduct1 = createdProductsList.get(7);

		int size = MongoReader.grabCustomerFormModels("US70010KoboRegOnVoucherOwnerContextTest1").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US70010KoboRegOnVoucherOwnerContextTest1").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");
		
		
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us70010PlacePomOrderTest() {
		
		String url = emailClientSteps.grabConfirmationLinkFromEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),
				ContextConstants.KOBO_CONFIRM_ACCOUNT_MAIL_SUBJECT);
		contactBoosterRegistrationSteps.navigate(url);
		pomProductDetailsSteps.findStarterProductAndAddItToTheCart(genProduct1.getName());
		fancyBoxSteps.goToShipping();
		shippingSteps.selectPartyNoOptionIfPresent();
		shippingSteps.goToPaymentMethod();
		String shippingUrl = shippingSteps.grabUrl();
		RegularUserDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(shippingUrl));
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(shippingUrl));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();
		
		
		// bug on staging-aut(cloud) env
		/*headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		profileSteps.verifyOrderId(orderId, RegularUserDataGrabber.orderModel.getOrderId());*/

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName());
	}

}
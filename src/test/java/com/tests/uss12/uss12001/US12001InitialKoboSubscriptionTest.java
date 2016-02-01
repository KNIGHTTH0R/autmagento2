package com.tests.uss12.uss12001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyBusinessSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.kobo.ContactBoosterCartSteps;
import com.steps.frontend.checkout.shipping.kobo.KoboShippingSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.datahandler.DataGrabber;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(ThucydidesRunner.class)
public class US12001InitialKoboSubscriptionTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ContactBoosterCartSteps contactBoosterCartSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public MyBusinessSteps myBusinessSteps;
	@Steps
	public KoboShippingSteps koboShippingSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	
	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");
	private CreditCardModel creditCardData = new CreditCardModel();

	@Before
	public void setUp() {

		DataGrabber.wipe();

		int size = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us12001InitialKoboSubscriptionTest() {
		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		myBusinessSteps.verifyKoboStatusBeforePlaceTheOrder();
		loungeSteps.goToMyBusiness();
		myBusinessSteps.verifyThatNumberOfLinksAreEqualTo("1");
		myBusinessSteps.accessKoboCart();
		contactBoosterCartSteps.selectContactBooster200Voucher();
		contactBoosterCartSteps.clickToShipping();
		koboShippingSteps.acceptTerms();
		shippingSteps.goToPaymentMethod();
		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		headerSteps.goToLounge();
		myBusinessSteps.verifyKoboOrderProcessingStatus();
		headerSteps.goToMyBusinessPage();
		myBusinessSteps.verifyKoboSectionContainsText(ContextConstants.WAITING_PAYMENT_CONFIRMATION);

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);

	}

}

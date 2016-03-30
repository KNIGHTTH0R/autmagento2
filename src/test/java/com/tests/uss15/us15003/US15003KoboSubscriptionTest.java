package com.tests.uss15.us15003;

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
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;

@WithTag(name = "US15.3 Check SC kobo subscription and SFM order details in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_3.class)
@RunWith(SerenityRunner.class)
public class US15003KoboSubscriptionTest extends BaseTest {

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

		int size = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").get(0);
			stylistRegistrationData.setEmailName(stylistRegistrationData.getEmailName().replace(ConfigConstants.MAILINATOR, ConfigConstants.EVOZON));
			System.out.println(stylistRegistrationData.getEmailName());
			
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15003KoboSubscriptionTest() {
		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToMyBusiness();
		myBusinessSteps.verifyThatNumberOfLinksAreEqualTo("1");
		myBusinessSteps.accessKoboCart();
		contactBoosterCartSteps.selectContactBooster100Voucher();
		contactBoosterCartSteps.clickToShipping();
		koboShippingSteps.acceptTerms();
		shippingSteps.goToPaymentMethod();
		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		headerSteps.goToMyBusinessPage();
		myBusinessSteps.verifyKoboSectionContainsText(ContextConstants.WAITING_PAYMENT_CONFIRMATION);

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName());
	}

}

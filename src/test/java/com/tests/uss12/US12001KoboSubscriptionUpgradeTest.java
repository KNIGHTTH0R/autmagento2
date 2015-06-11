package com.tests.uss12;

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
import com.tools.SoapKeys;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;

@WithTag(name = "US12", type = "frontend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US12001KoboSubscriptionUpgradeTest extends BaseTest {

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
	public CustomerFormModel stylistRegistrationData = new CustomerFormModel("");
	private CreditCardModel creditCardData = new CreditCardModel();
	String coboCode;
	private static String cardNumber;
	private static String cardName;
	private static String cardMonth;
	private static String cardYear;
	private static String cardCVC;

	@Before
	public void setUp() {

		DataGrabber.wipe();

		int size = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss12" + File.separator + "us12001.properties");
			prop.load(input);

			cardNumber = prop.getProperty("cardNumber");
			cardName = prop.getProperty("cardName");
			cardMonth = prop.getProperty("cardMonth");
			cardYear = prop.getProperty("cardYear");
			cardCVC = prop.getProperty("cardCVC");

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

		creditCardData.setCardNumber(cardNumber);
		creditCardData.setCardName(cardName);
		creditCardData.setMonthExpiration(cardMonth);
		creditCardData.setYearExpiration(cardYear);
		creditCardData.setCvcNumber(cardCVC);

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us12001KoboSubscriptionUpgradeTest() {
		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToMyBusiness();
		myBusinessSteps.verifyThatNumberOfLinksAreEqualTo("2");
		myBusinessSteps.accessKoboCart();
		contactBoosterCartSteps.selectContactBoosterVoucher();
		contactBoosterCartSteps.clickToShipping();
		koboShippingSteps.acceptTerms();
		shippingSteps.clickGoToPaymentMethod();
		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		headerSteps.goToLounge();
		coboCode = myBusinessSteps.getKoboCode();
		headerSteps.goToMyBusinessPage();
		myBusinessSteps.verifyThatNumberOfLinksAreEqualTo("2");
		myBusinessSteps.cancelSubstription();
		headerSteps.goToLounge();
		myBusinessSteps.verifyCancelledKoboMessageAndActiveUntilDate();
		headerSteps.goToMyBusinessPage();
		myBusinessSteps.verifyKoboSectionContainsText(ContextConstants.SUBSCRIPTION_CANCELLED);

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveKoboCode(coboCode, getClass().getSimpleName() + SoapKeys.GRAB);
	}

}

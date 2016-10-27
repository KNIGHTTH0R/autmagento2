package com.dataPreparation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyBusinessSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.kobo.ContactBoosterCartSteps;
import com.steps.frontend.checkout.shipping.kobo.KoboShippingSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.CreditCardModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.UseTestDataFrom;

@WithTag(name = "Data preparation", type = "Data preparation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "resources/dataPreparation/stylists.csv")
public class KoboSubscriptionDataPrepTest extends BaseTest {

	private String username, password;

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
	@Steps
	public ProductSteps productSteps;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private CreditCardModel creditCardData = new CreditCardModel();

	String koboCode;

	@Test
	public void koboSubscriptionDataPrepTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.goToMyBusiness();
		myBusinessSteps.accessKoboCart();
		productSteps.setKoboProductAddToCart("Kontakt-Booster 100", "Z980", "100.00", "100");
		contactBoosterCartSteps.selectContactBooster100Voucher();
		contactBoosterCartSteps.clickToShipping();
		shippingSteps.grabSurveyData();
		koboShippingSteps.acceptTerms();
		shippingSteps.goToPaymentMethod();
		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();
	}

	@Test
	public void payKoboOrderTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(DataGrabber.orderModel.getOrderId());
		backEndSteps.openOrderDetails(DataGrabber.orderModel.getOrderId());
		ordersSteps.markOrderAsPaid();

	}

	@Test
	public void writeToPropertiesFileKoboTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		koboCode = myBusinessSteps.getKoboCode();

		Properties prop = new Properties();

		InputStream input = null;
		OutputStream output = null;

		try {

			input = new FileInputStream(UrlConstants.ENV_PATH + "koboVouchers.properties");
			prop.load(input);
			input.close();
			output = new FileOutputStream(UrlConstants.ENV_PATH + "koboVouchers.properties");
			prop.setProperty("koboCode" + username, koboCode);

			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}

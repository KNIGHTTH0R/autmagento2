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
import com.steps.frontend.ProductSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.kobo.ContactBoosterCartSteps;
import com.steps.frontend.checkout.shipping.kobo.KoboShippingSteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.borrowCart.BorrowCartCalculator;
import com.tools.datahandlers.borrowCart.BorrowDataGrabber;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.borrowCart.BorrowCartShippingAndConfirmationWorkflows;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(ThucydidesRunner.class)
public class US12001KoboSubscriptionTest extends BaseTest {

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
	public BorrowCartShippingAndConfirmationWorkflows borrowCartShippingAndConfirmationWorkflows;

	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");
	private CreditCardModel creditCardData = new CreditCardModel();

	@Before
	public void setUp() {

		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();

		int size = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us12001KoboSubscriptionTest() {
		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		myBusinessSteps.verifyKoboStatusBeforePlaceTheOrder();
		loungeSteps.goToMyBusiness();
		myBusinessSteps.verifyThatNumberOfLinksAreEqualTo("1");
		myBusinessSteps.accessKoboCart();
		
		//we use borrow cart models and calculations because it contains the same data
		BorrowProductModel productData;
		
		productData = productSteps.setKoboProductAddToCart("Kontakt-Booster 100", "Z980", "100.00", "100");
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
		
		BorrowCartCalculator.calculateCartAndShippingTotals("19", "0.00");
		
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

		borrowCartShippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, BorrowCartCalculator.shippingCalculatedModel);
		borrowCartShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

//		confirmationSteps.agreeAndCheckout();
//		headerSteps.goToLounge();
//		myBusinessSteps.verifyKoboOrderProcessingStatus();
//		headerSteps.goToMyBusinessPage();
//		myBusinessSteps.verifyKoboSectionContainsText(ContextConstants.WAITING_PAYMENT_CONFIRMATION);

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);

	}

}

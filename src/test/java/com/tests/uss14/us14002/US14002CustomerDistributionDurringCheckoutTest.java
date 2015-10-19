package com.tests.uss14.us14002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.regularUser.RegularUserCartCalculator;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US14.2 Distribution during checkout to party host lead qualified SC", type = "Scenarios")
@Story(Application.DistributionDuringCheckout.US14_2.class)
@RunWith(ThucydidesRunner.class)
public class US14002CustomerDistributionDurringCheckoutTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public FooterSteps footerSteps;

	private CreditCardModel creditCardData;
	private CustomerFormModel dataModel;
	private ProductDetailedModel genProduct1;

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = ApiCalls.createProductModel();
		genProduct1.setPrice("89.00");
		ApiCalls.createApiProduct(genProduct1);
		creditCardData = new CreditCardModel();
		dataModel = MongoReader.grabCustomerFormModels("US14002HostLeadDistributionTest").get(0);
	}

	@Test
	public void us14002CustomerDistributionDurringCheckoutTest() {
		customerRegistrationSteps.performLogin(dataModel.getEmailName(), dataModel.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		customerRegistrationSteps.wipeRegularCart();
		RegularBasicProductModel productData;
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		regularUserCartSteps.clickGoToShipping();
		shippingSteps.selectKnowStylistNoOption();
		shippingSteps.goToPaymentMethod();
		String url = shippingSteps.grabUrl();
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();
	}

}

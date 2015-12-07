package com.tests.uss24;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import net.thucydides.junit.runners.ThucydidesParameterizedRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.FancyBoxSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.KoboCampaignSteps;
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
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US24.1 Check plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesParameterizedRunner.class)
@UseTestDataFrom(value = "resources/validPlzTestData.csv")
public class US24001KoboCampaignRegistrationPlzValidationTest extends BaseTest {

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

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CreditCardModel creditCardData = new CreditCardModel();
	private ProductDetailedModel genProduct1;
	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	@Before
	public void setUp() throws Exception {
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createPomProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us70011.properties");
			prop.load(input);

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
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		addressModel.setPostCode(plz);
	}

	@Test
	public void us24001KoboCampaignRegistrationPlzValidationTest() {
		koboValidationSteps.startKoboCampaignProcess(MongoReader.getBaseURL() + UrlConstants.KOBO_CAMPAIGN);
		koboCampaignSteps.fillKoboCampaignRegistrationFormOnMasterShop(dataModel, addressModel);
		koboSuccesFormSteps.verifyKoboFormIsSuccsesfullyFilledIn();
		emailClientSteps.openMailinator();
		String url = emailClientSteps.grabConfirmationLinkFromEmail(dataModel.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""),
				ContextConstants.KOBO_CONFIRM_ACCOUNT_MAIL_SUBJECT);
		koboCampaignSteps.navigate(url);
		pomProductDetailsSteps.findStarterProductAndAddItToTheCart(genProduct1.getName());
		fancyBoxSteps.goToShipping();
		shippingSteps.goToPaymentMethod();
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();

	}

}
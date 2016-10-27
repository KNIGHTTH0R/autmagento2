package com.tests.uss25;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.FancyBoxSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.KoboCampaignSteps;
import com.steps.frontend.KoboRegistrationStepsWithCsv;
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
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(SerenityRunner.class)
public class US25001KoboCampaignRegistrationPlzValidationTest extends BaseTest {

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
	public KoboRegistrationStepsWithCsv koboRegistrationStepsWithCsv;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private ProductDetailedModel genProduct1;

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
	}

	@Test
	public void us25001KoboCampaignRegistrationPlzValidationTest() {
		koboValidationSteps.startKoboCampaignProcess(MongoReader.getBaseURL() + UrlConstants.KOBO_CAMPAIGN);
		koboCampaignSteps.fillKoboCampaignRegistrationFormOnMasterShopWithoutPlz(dataModel, addressModel);
		try {
			withTestDataFrom("resources/invalidPlzTestData.csv").run(koboRegistrationStepsWithCsv).inputPostCodeCsv();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed !!!");
		}
	}

}
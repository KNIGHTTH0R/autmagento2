package com.tests.uss32;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US32.1 Check restriction for product available with TP", type = "Scenarios")
@Story(Application.KoboRegistration.US7_8.class)
@RunWith(SerenityRunner.class)
public class US32001CheckPomOrderTpRestrictionTest extends BaseTest {

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
	public ProfileSteps profileSteps;

	private String context;
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private String koboCode;
	private ProductDetailedModel genProduct1;
	String formatedAvailabilityDate;

	@Before
	public void setUp() throws Exception {

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.setName("POM_" + genProduct1.getName());
		genProduct1.getStockData().setIsDiscontinued("0");
		MagentoProductCalls.createApiProduct(genProduct1);

		String unformatedAvailabilityDate = DateUtils.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd");

		formatedAvailabilityDate = DateUtils.parseDate(unformatedAvailabilityDate, "yyyy-MM-dd", "dd. MMM. yyyy", new Locale.Builder().setLanguage(MongoReader.getContext())
				.build());
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us7008.properties");
			prop.load(input);
			context = prop.getProperty("context");

			input = new FileInputStream(UrlConstants.ENV_PATH + "koboVouchers.properties");
			prop.load(input);
			koboCode = prop.getProperty("koboCodemihaialexandrubarta@gmail.com");

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
	public void us32001CheckPomOrderTpRestrictionTest() {
		
		koboValidationSteps.enterKoboCodeAndGoToRegistrationProcess(MongoReader.getBaseURL() + context, koboCode);
		contactBoosterRegistrationSteps.fillContactBoosterRegistrationForm(dataModel, addressModel);
		koboSuccesFormSteps.verifyKoboFormIsSuccsesfullyFilledIn();
		String url = emailClientSteps.grabConfirmationLinkFromEmail(dataModel.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""),
				ContextConstants.KOBO_CONFIRM_ACCOUNT_MAIL_SUBJECT);
		contactBoosterRegistrationSteps.navigate(url);
		pomProductDetailsSteps.findProductAndClick(genProduct1.getName());
		pomProductDetailsSteps.verifyAddToCartButton(false);

	}
}
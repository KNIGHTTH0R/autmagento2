package com.tests.us6.us6002b;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StarterSetSteps;
import com.steps.frontend.StylistContextSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.Credentials;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;
import com.workflows.frontend.stylecoachRegistration.AddStarterSetProductsWorkflow;

@WithTag(name = "US6.2b SC Registration Existing Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_2.class)
@RunWith(SerenityRunner.class)
public class US6002bScRegExistingCustForbiddenCountryTest extends BaseTest{
	
	@Steps
	public HeaderSteps headerSteps;	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public EmailClientSteps emailClientSteps;	
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public StylistContextSteps stylistContextSteps;
	@Steps
	public AddStarterSetProductsWorkflow addStarterSetProductsWorkflow;
	@Steps
	public StarterSetSteps starterSetSteps;

	private CreditCardModel creditCardData = new CreditCardModel();
	private static DateModel formDate = new DateModel();
	private StylistPropertiesModel expectedBeforeLinkConfirmationStylistData = new StylistPropertiesModel();
	private CustomerFormModel stylistData = new CustomerFormModel("");
	private String birthDate;
	private String starterSet,starterKitPrice;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		
		birthDate = "Feb,1970,12";
		expectedBeforeLinkConfirmationStylistData =  new StylistPropertiesModel(ConfigConstants.NOT_CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
		int size = MongoReader.grabCustomerFormModels("US6002bCreateCustomerTest").size();
		if (size > 0) {
			stylistData = MongoReader.grabCustomerFormModels("US6002bCreateCustomerTest").get(0);
		} else
			Assert.assertTrue("Failure: No test data has been found.", false);
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us6" + File.separator + "us6001.properties");
			prop.load(input);

		
			starterSet = prop.getProperty("starterSet");
			starterKitPrice = prop.getProperty("starterKitPrice");

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
	}
	
	@Test
	public void us6002bScRegExistingCustForbiddenCountryTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistData.getEmailName());
		backEndSteps.openCustomerDetails(stylistData.getEmailName());
		StylistPropertiesModel grabBeforeLinkConfirmationStylistData =  backEndSteps.grabCustomerConfiguration();
		
		//confirmation link
		emailClientSteps.grabEmail(stylistData.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""),ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);
		
		headerSteps.navigateToRegisterFormAndLogout();
		stylistRegistrationSteps.clickLoginLinkFromMessage();
		customerRegistrationSteps.performLoginAfterChangingWebsite(stylistData.getEmailName(), stylistData.getPassword());
		String formCreationDate = stylistRegistrationSteps.fillStylistRegistrationPredefinedInfoForm(stylistData.getFirstName(), birthDate);
		formDate.setDate(formCreationDate);
		
		stylistContextSteps.addStylistReference(stylistData.getFirstName());

		addStarterSetProductsWorkflow.setStarterSetProductToCart(starterSet,starterKitPrice);
		starterSetSteps.submitStarterSetStep();
		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		
		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabBeforeLinkConfirmationStylistData, expectedBeforeLinkConfirmationStylistData);	
		customerAndStylistRegistrationWorkflows.validateStylistProperties("BEFORE CONFIRMATION LINK");
		
		customVerifications.printErrors();		
	}
	
	@After
	public void saveData() {
		MongoWriter.saveDateModel(formDate, getClass().getSimpleName());
	}
}
